package net.ars.sample.actor.naming;

public class UniqueActorNamingStrategy extends PrefixedActorNamingStrategy {
	
	@Override
	public String getActorName(final String namePrefix, final int actorId) {
		return super.getActorName(namePrefix, actorId) + System.nanoTime();
	}

}
