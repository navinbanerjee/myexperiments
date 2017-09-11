package net.ars.sample.actor;

import net.ars.sample.actor.naming.ActorNamingStrategy;
import akka.actor.ActorRef;
import akka.actor.Props;

public class Executive extends ArsUntypedSupervisorActor {
	
	private static final int NUM_MANAGER = 1;
	private ActorNamingStrategy childNamingStrategy;
	
	public Executive(ActorNamingStrategy actorNamingStrategy) {
		this.maxNumChildren = NUM_MANAGER;
		this.childNamingStrategy = actorNamingStrategy;
	}
	
	@Override
	public void onReceive(final Object msg) throws Exception {
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
	
	private void start() {
		for(int count = 0; count < maxNumChildren; count++) {
			createAndWatchChildActor(Props.create(Manager.class, childNamingStrategy), childNamingStrategy.getActorName("manager", count+1));
		}
		
		// scheduleHealthCheck();
		
		// we have the children created - Lets get them started too
		for(ActorRef child : getContext().getChildren()) {
			child.tell(Message.BEGIN, getSelf());
		}
		
		checkMaxChildren(maxNumChildren);
	}
}
