package webtechnology.web.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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


@WebServlet(name="PersonModify", urlPatterns="/person/modify")
public class PersonModifyServlet extends HttpServlet {

	private static final long serialVersionUID = 1536820527829462212L;
	
	private final PersonService personService = new PersonServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id =0;
		
		try {
			id = Integer.parseInt(req.getParameter("id"));
		} catch (NumberFormatException e) {
			id=0;
		}
		
		Person p = personService.loadById(id);
		
		req.setAttribute("person", p);
		req.setAttribute("title", "Person add");
		req.getRequestDispatcher(ServletBase.JSP_BASE_PATH + File.separator + "personModify.jsp").forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Enumeration<String> params = req.getParameterNames();
		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		
		Person p = null;
		
		int id =0;
		
		try {
			id = Integer.parseInt(req.getParameter("id"));
		} catch (NumberFormatException e) {
			id=0;
		}

		if(id>0) {
			p = personService.loadById(id);
		} else {
			p = new Person();
		}
		
		Map<String, String> errors = new HashMap<>();
		
		if (firstName == null || firstName.trim().length() < 3) {
			errors.put("firstName", "First Name empty or less 3");
		}
		
		p.setFirstName(firstName);
		p.setLastName(lastName);

		if (!errors.isEmpty()) {
			req.setAttribute("person", p);
			req.setAttribute("errors", errors);
			req.setAttribute("title", "Person add");
			req.getRequestDispatcher(ServletBase.JSP_BASE_PATH + File.separator + "personModify.jsp").forward(req, resp);
		} else {
			personService.createOrUpdate(p);
			resp.sendRedirect("/");
		}
		
	}
	
}
