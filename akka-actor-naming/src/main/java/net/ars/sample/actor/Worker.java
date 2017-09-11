package net.ars.sample.actor;


public class Worker extends ArsUntypedActor {

	@Override
	public void onReceive(final Object msg) throws Exception {
		System.out.println("time when message was delivered..." + System.currentTimeMillis());
		if(msg instanceof Message) {
			Message message = (Message)msg;
			switch(message) {
				case BEGIN:
					start();
					break;
				default:
					super.handleMessage(message);
			}
		} else {
			super.handleMessage(msg);
		}
	}
	
	
	private void start() throws Exception {
		// complete work
		Thread.sleep(10);
		// tell parent to track progress - 50% completion, 100% completion for example 
		for(int count = 0; count < 2; count++) {
			getContext().parent().tell(Message.TRACK, getSelf());
		}
	}
	
}
