package webtechnology.dao;

import org.hibernate.Session;

public class SessionContextHolder {

	private static final ThreadLocal<Session> threadLocalScope = new  ThreadLocal<>();
	
	public final static Session getSessionScope() {
		return threadLocalScope.get();
	}
	
	public final static void setSessionScope(Session session) {
		threadLocalScope.set(session);
	}	
	
}
