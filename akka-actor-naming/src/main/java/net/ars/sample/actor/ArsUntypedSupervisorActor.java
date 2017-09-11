package net.ars.sample.actor;

import scala.concurrent.duration.Duration;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.japi.Function;

public abstract class ArsUntypedSupervisorActor extends ArsUntypedActor {

	protected static final int MAX_RETRY = 100;
	protected static final String WITHIN_TIME = "1 second";
	
	protected SupervisorStrategy strategy = new OneForOneStrategy(MAX_RETRY, Duration.create(WITHIN_TIME), 
			new Function<Throwable, Directive>() {
				public Directive apply(Throwable t) {
					if (t instanceof Exception) {
						logger.info("Using restart strategy...");
		                return SupervisorStrategy.restart();
		            } else {
		            	logger.info("Using escalate strategy...");
		                return SupervisorStrategy.escalate();
		            }
		        }
			});
	
	@Override
	public SupervisorStrategy supervisorStrategy() {
		return strategy;
	}
}
