package webtechnology.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ServletBase {

	public static String JSP_BASE_PATH = "/WEB-INF/jsp";
	
	public static String CONTENT_TYPE_JSON = "application/json";
	
	public static Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }
	
}
