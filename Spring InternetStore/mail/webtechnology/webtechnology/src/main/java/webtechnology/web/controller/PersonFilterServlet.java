package webtechnology.web.controller;

import java.util.List;
import java.util.Locale;
import java.util.Properties;
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
import webtechnology.util.MailHelper;


@WebServlet(name="PersonFilter", urlPatterns="/person/filter")
public class PersonFilterServlet extends HttpServlet {

	private static final long serialVersionUID = 1536820527829462212L;
	
	private final PersonService personService = new PersonServiceImpl();
	private ResourceBundle messages;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String q = req.getParameter("q");
		
		String res =  personService.getJsonListAsString(q);
		
		Properties prop = new Properties();
		
		prop.setProperty("port", "5225");
		prop.setProperty("recipient", "admin@webtech.com");
		prop.setProperty("sender", "no-reply@webtech.com");
		prop.setProperty("subject", "Filter usage");
		prop.setProperty("content", "<h1>AJax</h1><p style='color: blue;'>Filter ajax search</p>");
		
		MailHelper.sendMail(prop);
	
		
		resp.setContentType(ServletBase.CONTENT_TYPE_JSON);
		resp.setContentLengthLong(res.length());
		
		resp.getWriter().print(res);
		resp.getWriter().flush();
	}

}
