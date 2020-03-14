package webtechnology.server;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.scan.StandardJarScanner;

public class WebApplication {

	public static void main(String[] args) throws LifecycleException, ServletException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);

		String contextPath = "/";
        String docBase = new File("src/main/webapp").getAbsolutePath();

		StandardContext context = (StandardContext) tomcat.addWebapp(contextPath, docBase);
		
		// Additions to make annotations work
		String buildPath = "target/classes";
		String webAppMount = "/WEB-INF/classes";

		File additionalWebInfClasses = new File(buildPath);
		WebResourceRoot resources = new StandardRoot(context);
		resources.addPreResources( new DirResourceSet(resources, webAppMount, additionalWebInfClasses.getAbsolutePath(), contextPath));
		context.setResources(resources);

		StandardJarScanner scanner=new StandardJarScanner(); 
		scanner.setScanManifest( false );
		scanner.setScanClassPath(true);
		context.setJarScanner(scanner); 

		context.setDelegate(true);
		context.setReloadable(true);

		tomcat.getConnector().setURIEncoding("UTF-8");
		
		tomcat.start();
		tomcat.getServer().await();
	}

}