package net.ars.sample.actor.naming;

public class PrefixedActorNamingStrategy implements ActorNamingStrategy {
	
	@Override
	public String getActorName(final String namePrefix, final int actorId) {
		return namePrefix + actorId;
	}

}
