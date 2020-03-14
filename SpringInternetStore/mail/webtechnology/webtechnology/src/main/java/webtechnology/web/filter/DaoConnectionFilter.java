package webtechnology.web.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;

import webtechnology.dao.HibernateUtilities;
import webtechnology.dao.SessionContextHolder;

@WebFilter(urlPatterns="*")
public class DaoConnectionFilter implements Filter {

	private static final Logger logger = Logger.getLogger(DaoConnectionFilter.class.getName());
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		Session session = SessionContextHolder.getSessionScope();

		if (session == null) {
			session = HibernateUtilities.getSessionFactory().openSession();
			
			SessionContextHolder.setSessionScope(session);
			logger.info("DaoConnectionFilter: Create hibernate session");
		} else {
			logger.info("DaoConnectionFilter: Get hibernate session from SessionContectHolder");
		}

		chain.doFilter(req, res);

		logger.info("DaoConnectionFilter: Close hibernate session");
		session.close();
		SessionContextHolder.setSessionScope(null);
	}

	@Override
	public void destroy() {}

}
