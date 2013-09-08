package com.epam.ht.util;

import static com.epam.ht.constant.HTConstant.HIBERNATE_CONFIG_PATH;
import static com.epam.ht.resource.PropertyGetter.getProperty;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

// for Hibernate
public final class SessionFactoryGetter {
	private static SessionFactory sessionFactory;

	static {
		Configuration config = new Configuration();
		config.configure(getProperty(HIBERNATE_CONFIG_PATH));
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		sessionFactory = config.buildSessionFactory(serviceRegistry);
	}

	private SessionFactoryGetter() {
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
