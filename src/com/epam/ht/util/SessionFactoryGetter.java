package com.epam.ht.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import static com.epam.ht.resource.PropertyGetter.getProperty;
import static com.epam.ht.constant.HTConstant.*;

public final class SessionFactoryGetter {
	private static final SessionFactory sessionFactory;
	
	static {
		Configuration configuration = new Configuration();
		configuration.configure(getProperty(HIBERNATE_CONFIG_PATH));
		ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		sessionFactory = configuration
				.buildSessionFactory(serviceRegistryBuilder
						.buildServiceRegistry());
	}
	
	private SessionFactoryGetter() {
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
