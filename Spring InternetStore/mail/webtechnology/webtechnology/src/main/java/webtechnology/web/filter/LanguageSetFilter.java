package webtechnology.web.filter;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import webtechnology.web.controller.ServletBase;

@WebFilter(urlPatterns="*")
public class LanguageSetFilter implements Filter {

	private static final Logger logger = Logger.getLogger(LanguageSetFilter.class.getName());
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		Cookie cookie = ServletBase.getCookie((HttpServletRequest) request, "lang");
        
		String lang = "en";
		if (cookie != null) {
			lang = cookie.getValue();
		} 
		
		Locale locale = Locale.ENGLISH;
		
		if (lang.equals("ru")) {
			locale = new Locale("ru", "RU");
		}

		logger.info("Set locale: " + locale);
		request.setAttribute("locale", locale);
		
        chain.doFilter(request, response);		
	}

	@Override
	public void destroy() {}
	
}
