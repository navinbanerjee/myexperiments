package net.ars.sample.actor;

import net.ars.sample.actor.naming.ActorNamingStrategy;
import net.ars.sample.actor.naming.PrefixedActorNamingStrategy;
import net.ars.sample.actor.naming.UniqueActorNamingStrategy;
import uk.org.simonsite.log4j.appender.TimeAndSizeRollingAppender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
	private static final int PLAY_TIME_SEC = 60;
	
	public static void main(final String[] args) throws Exception {
		// We are only interested in the InvalidActorNameException or other errors logged
		//	System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "error");
		// see: simplelogger.properties
		
		ActorSystem actorSystem = ActorSystem.create("ars-akka");
		ActorRef masterRef = actorSystem.actorOf(Props.create(Executive.class, getChildNamingStrategy(args)), "executive");
		masterRef.tell(Message.BEGIN, ActorRef.noSender());
		
		
		
		Logger logger = LoggerFactory.getLogger(Main.class);
		// force the printing of this information - hence error
		logger.error("The Main thread going off to sleep for " + PLAY_TIME_SEC + " seconds...");
		TimeAndSizeRollingAppender appen = null;
		appen.activateOptions();
		org.apache.log4j.Logger.getRootLogger().getAppender("asd");
		Thread.sleep(PLAY_TIME_SEC * 1000);
		actorSystem.shutdown();
	}
	
	private static ActorNamingStrategy getChildNamingStrategy(final String[] args) {
		if(args.length > 0) {
			return new UniqueActorNamingStrategy();
		}
		return new PrefixedActorNamingStrategy();
	}
}
