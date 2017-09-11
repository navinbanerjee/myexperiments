package net.ars.sample.actor.naming;

public interface ActorNamingStrategy {
	public String getActorName(String prefix, int actorId);
}
