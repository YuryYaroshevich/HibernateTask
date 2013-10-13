package com.epam.ht.util;

import static com.epam.ht.resource.PropertyGetter.getProperty;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

// for Hibernate DAO
public final class SessionFactoryGetter {
	private static SessionFactory sessionFactory;
	
	public static final String HIBERNATE_CONFIG_PATH = "hibernate.config";

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
