package com.epam.ht.dao;

import org.hibernate.SessionFactory;

public class CountryDAOHibernate {

	private static SessionFactory sessionFactory;

	/*static {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate_sp.cfg.xml");
		ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		sessionFactory = configuration
				.buildSessionFactory(serviceRegistryBuilder
						.buildServiceRegistry());
	}

	public void fillTable(int entityNumber) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = (Transaction) session.beginTransaction();
		String[] countryNames = { "sdfsdf", "dfgd", "vbnv", "xcvx", "uyu",
				"qweq", "kljf", "jgh", "wer", "tyuty", "cvbvc", "bnmb", "zcsd" };

		tx.commit();
	}*/

}
