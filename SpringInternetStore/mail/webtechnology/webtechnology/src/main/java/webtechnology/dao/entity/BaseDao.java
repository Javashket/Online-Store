package webtechnology.dao.entity;

import org.hibernate.Session;

import webtechnology.dao.HibernateUtilities;
import webtechnology.dao.SessionContextHolder;

public class BaseDao {

	public Session getSession() {
		Session session = SessionContextHolder.getSessionScope();

		if (session != null) {
			System.out.println("Return hibernate session from SessionContextHolder");
		}

		if (session == null) {
			session = HibernateUtilities.getSessionFactory().openSession();
			System.out.println("Open new hibernate session");
		}

		return session;
	}

}
