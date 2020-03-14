package webtechnology.web.controller;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webtechnology.domain.entity.Person;
import webtechnology.domain.services.PersonService;
import webtechnology.domain.services.PersonServiceImpl;


@WebServlet(name="Index", urlPatterns="")
public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1536820527829462212L;
	
	private final PersonService personService = new PersonServiceImpl();
	private ResourceBundle messages;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Person> persons =  personService.getList(null);
		
		Cookie lang = ServletBase.getCookie(req, "lang");
		
		if (lang == null) {
			lang = new Cookie("lang", "en");
		}

		
		
		lang.setMaxAge(2*24*60*60); // 2 days = 
		
		resp.addCookie(lang);
		
		messages = ResourceBundle.getBundle("messages", (Locale)req.getAttribute("locale"));
		
		req.setAttribute("persons", persons);
		req.setAttribute("title", messages.getString("index.title"));
		req.getRequestDispatcher(ServletBase.JSP_BASE_PATH + File.separator + "index.jsp").forward(req, resp);
	}

}
