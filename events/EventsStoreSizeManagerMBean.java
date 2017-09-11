package com.silverpop.mock.manage.events;

public interface EventsStoreSizeManagerMBean {
	
	public int fetchMaximumEventsPossibleInStore();
	
	public void changeMaximumEventsPossibleInStore(int maximumEventsPossible);

}
