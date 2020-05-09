package com.group3.ApplicationContext;

import org.springframework.context.ConfigurableApplicationContext;

public class ConfigApplicationContext {
	protected static ConfigurableApplicationContext ApplicationContext;

	public ConfigurableApplicationContext getApplicationContext() {
		return ApplicationContext;
	}

	public void setApplicationContext(ConfigurableApplicationContext ApplicationContext) {
		this.ApplicationContext = ApplicationContext;
	}

}
