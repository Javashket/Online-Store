package webtechnology.web.listener;


import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import webtechnology.dao.HibernateUtilities;


@WebListener
public class ContextListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(ContextListener.class.getName());
	
	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		logger.info("ServletContextListener Hibernate start SessionFactory.");
		HibernateUtilities.getSessionFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		logger.info("ServletContextListener Hibernate shutdown.");
		HibernateUtilities.shutdown();
	}
	
}
