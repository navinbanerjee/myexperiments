package net.ars.sample.actor;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;

public abstract class ArsUntypedActor extends UntypedActor {
	
	protected int childCheckIntervalDurationMs = 1000; 
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	protected int maxNumChildren = 0;

	@Override
    public void preStart() throws Exception {
    	super.preStart();
    	logger.debug(getSelf().path() + " starting up...");
    }
    
    @Override
    public void postStop() throws Exception {
    	logger.debug(getSelf().path() + " stopping...");
        super.postStop();
    }

    @Override
    public void postRestart(final Throwable reason) throws Exception {
    	logger.info(getSelf().path() + " postRestart...");
        super.postRestart(reason);
    }
    
    protected ActorRef createAndWatchChildActor(final Props props, final String childActorName) {
    	ActorRef child = getContext().actorOf(props, childActorName);
    	getContext().watch(child);
    	return child;
    }
	
    protected void checkMaxChildren(int maxChildrenExpected) {
    	int numChildrenActual = getContext().children().size();
    	if(numChildrenActual > maxChildrenExpected) {
    		logger.error(getSelf().path() + " has " + numChildrenActual + " while we expected no more than " + maxChildrenExpected);
    	}
    }
    
    protected void scheduleHealthCheck() {
    	getContext().system().scheduler().schedule(Duration.create(childCheckIntervalDurationMs, TimeUnit.MILLISECONDS), 
    			Duration.create(childCheckIntervalDurationMs, TimeUnit.MILLISECONDS),
    			getSelf(),
                Message.CHECK,
                getContext().system().dispatcher(), 
                getSelf()
                );
    }
    
    public void handleMessage(Object msg) {
    	if(msg instanceof Message) {
			Message message = (Message)msg;
			switch(message) {
				case CHECK:
					checkMaxChildren(maxNumChildren);
					break;
				default:
					unhandled(message);
			}
    	} else if (msg instanceof Terminated) {
			ActorRef deadActorRef = ((Terminated)msg).getActor();
			logger.warn("Actor died : " + deadActorRef.path());
		}
    }
}
