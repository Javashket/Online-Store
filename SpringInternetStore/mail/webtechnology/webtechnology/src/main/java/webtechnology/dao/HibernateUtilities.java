package webtechnology.dao;

import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtilities {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static final Logger logger = Logger.getLogger(HibernateUtilities.class.getName());

	private static SessionFactory buildSessionFactory() {
		try {

			Configuration configure = new Configuration().configure();
			return configure.buildSessionFactory();

		} catch (Throwable ex) {
			
			logger.severe("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
			
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

}