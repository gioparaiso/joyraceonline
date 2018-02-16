package com.letsrace.spring.controller;

import java.io.File;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.mail.smtp.SMTPTransport;

public class HomeControllerUtil {

	protected boolean checkIfAlreadyLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String userId = (String) session.getAttribute("username");
		if ((userId == null) || (userId.equalsIgnoreCase(""))) {
			return false;
		}
		return true;
	}

	protected boolean checkIfAlreadyLoggedInAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String adminId = (String) session.getAttribute("adminname");
		if ((adminId == null) || (adminId.equalsIgnoreCase(""))) {
			return false;
		}
		return true;
	}

	protected String check999(String string) {
		if (string.equalsIgnoreCase("999") == true)
			return "none yet";
		if (string.equalsIgnoreCase("999.99") == true)
			return "none yet";
		return string + " s";
		// return string;
	}

	protected File getPhotoFileAndDir(HttpServletRequest request) {

		// gets absolute path of the web application
		String applicationPath = request.getServletContext().getRealPath("");
		// constructs path of the directory to save uploaded file

		String username = (String) request.getSession()
				.getAttribute("username");

		String userPhotoFile = username + ".jpg";

		String UPLOAD_DIR = "profilepics";

		// Creating the directory to store file
		// String rootPath = System.getProperty("catalina.home");
		File dir = new File(applicationPath + File.separator + UPLOAD_DIR);
		if (!dir.exists())
			dir.mkdirs();

		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator
				+ userPhotoFile);

		return serverFile;
	}

	/*
	 * returns 0 if not number
	 */
	public int isNumber(String passcode) {
		int retVal = 0;
		try {
			retVal = Integer.parseInt(passcode);
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
		return retVal;
	}

	public void sendEmail(final String username, final String password,
			String recipientEmail, String ccEmail, String title, String message)
			throws AddressException, MessagingException {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtps.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtps.auth", "true");

		/*
		 * If set to false, the QUIT command is sent and the connection is
		 * immediately closed. If set to true (the default), causes the
		 * transport to wait for the response to the QUIT command.
		 * 
		 * ref :
		 * http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp
		 * /package-summary.html
		 * http://forum.java.sun.com/thread.jspa?threadID=5205249 smtpsend.java
		 * - demo program from javamail
		 */
		props.put("mail.smtps.quitwait", "false");

		Session session = Session.getInstance(props, null);

		// -- Create a new message --
		final MimeMessage msg = new MimeMessage(session);

		// -- Set the FROM and TO fields --
		msg.setFrom(new InternetAddress(username + "@gmail.com"));
		msg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recipientEmail, false));

		if (ccEmail.length() > 0) {
			msg.setRecipients(Message.RecipientType.CC,
					InternetAddress.parse(ccEmail, false));
		}

		msg.setSubject(title);
		msg.setText(message, "utf-8");
		msg.setSentDate(new Date());

		SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

		t.connect("smtp.gmail.com", username, password);
		t.sendMessage(msg, msg.getAllRecipients());
		t.close();
	}

}
