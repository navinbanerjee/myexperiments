package com.silverpop.mock.manage.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverpop.configurator.Configurator;
import com.silverpop.core.SPLogger;
import com.silverpop.core.util.JMXUtil;
import com.silverpop.rest.ubx.controller.MockUbxApiImpl;

@Service("eventsStoreSizeManager")
public final class EventsStoreSizeManager implements EventsStoreSizeManagerMBean {
	private MockUbxApiImpl mockUbxApiImpl;
	private SPLogger log = SPLogger.getLogger(this.getClass());
	
	@Autowired
	public EventsStoreSizeManager(MockUbxApiImpl mockUbxApiImpl) {
		this.mockUbxApiImpl = mockUbxApiImpl;
		try {
			String moduleName = Configurator.getProperty("module.name", "mock-services");
			JMXUtil.registerMBeanInSPHierarchy(this, moduleName, "EventsStoreSizeManager");
		} catch(Exception ex) {
			log.error("Unable to register EventsStoreSizeManager MBean", ex);
		}
	}

	@Override
	public int fetchMaximumEventsPossibleInStore() {
		return mockUbxApiImpl.getMaximumEventsPossibleInStore();
	}

	@Override
	public void changeMaximumEventsPossibleInStore(int maximumEventsPossible) {
		mockUbxApiImpl.setMaximumEventsPossibleInStore(maximumEventsPossible);
	}
}
