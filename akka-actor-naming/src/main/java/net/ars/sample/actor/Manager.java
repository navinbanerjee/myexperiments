package net.ars.sample.actor;

import net.ars.sample.actor.naming.ActorNamingStrategy;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.InvalidActorNameException;
import akka.actor.Props;

public class Manager extends ArsUntypedSupervisorActor {
	
	public static final int NUM_WORKER = 1;
	private ActorNamingStrategy namingStrategy;
	
	public Manager(ActorNamingStrategy childNamingStrategy) {
		this.maxNumChildren = NUM_WORKER;
		this.namingStrategy = childNamingStrategy;
	}

	@Override
    public void postRestart(final Throwable reason) throws Exception {
		if(reason instanceof InvalidActorNameException) {
			logger.error("Holy Kaw - the one that we were looking out for...", reason);
			logger.error("Don't panic, go back to Manager class and perform one of the following actions...");
			logger.error("1. change the postRestart to make a synchronous call to start instead of sending a message");
			logger.error("2. uncomment the check for the children in the start method");
		}
    	super.postRestart(reason);
    	getSelf().tell(Message.RESTART, getSelf());
    	// SOLUTION: (1) : comment previous line and uncomment the next line
    	// start();
    }
	
	@Override
	public void onReceive(final Object msg) throws Exception {
		if(msg instanceof Message) {
			Message message = (Message)msg;
			switch(message) {
				case BEGIN:
				// there it goes
				case RESTART:
					start();
					break;
				case TRACK:
					bomb();
					break;
				default:
					super.handleMessage(message);
			}
		} else {
			super.handleMessage(msg);
		}
	}
		
	private void start() {
		// SOLUTION: (2) : uncomment the surrounding if condition below
		// if(getContext().children().size() == 0) {
			for(int count = 0; count < maxNumChildren; count++) {
				createAndWatchChildActor(Props.create(Worker.class), namingStrategy.getActorName("worker", count+1));
			}
			
			// we have the children created - Lets get them started too
			for(ActorRef child : getContext().getChildren()) {
				System.out.println("message given to scheduler : " + System.currentTimeMillis());
//				getContext().system().scheduler().scheduleOnce(Duration.create(50, TimeUnit.SECONDS), 
//						child, Message.BEGIN, getContext().system().dispatcher(), null);
				child.tell(Message.BEGIN, getSelf());
			}
		// }
			
		checkMaxChildren(maxNumChildren);
	}
	
	private void bomb() {
		throw new RuntimeException("'Tracking Resource' not available.");
	}

}
