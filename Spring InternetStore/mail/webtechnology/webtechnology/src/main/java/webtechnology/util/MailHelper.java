package webtechnology.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailHelper {

	public static void sendMail(Properties prop) {

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", prop.getProperty("host", "localhost"));
		properties.setProperty("mail.smtp.port", prop.getProperty("port", "25"));

		Session session = Session.getDefaultInstance(properties);

		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(prop.getProperty("sender")));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(prop.getProperty("recipient")));

			message.setSubject(prop.getProperty("subject"));
			message.setContent(prop.getProperty("content"), "text/html");

			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

}
