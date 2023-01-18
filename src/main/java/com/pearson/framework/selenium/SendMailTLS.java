package com.pearson.framework.selenium;

import java.io.IOException;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.pearson.automation.utils.FileUtil;
import com.pearson.framework.ReportTheme;
import com.pearson.framework.ReportThemeFactory;
import com.pearson.framework.ReportThemeFactory.Theme;
import com.pearson.framework.Util;
import com.pearson.framework.selenium.ResultSummaryManager;
import com.pearson.framework.Settings;

public class SendMailTLS extends ResultSummaryManager {

	Properties properties = Settings.loadFromPropertiesFile();

	InetAddress ip;
	String hostname;

	private String testOutputFilePath = "src/test/resources/AppTestData/Sites/EmailDetails.txt";
	private String testOutputFilePath1 = "src/test/resources/AppTestData/Sites/EmailDetails1.txt";
	private String testOutputFilePath2 = "src/test/resources/AppTestData/Sites/EmailDetails2.txt";

	public void sendMsg(final String updatescenarioName,
			final int totalExecuted, final String totalExecutionTime,
			final int nTestsPassed, final int nTestsFailed) throws MessagingException {

		ReportTheme reportTheme = ReportThemeFactory.getReportsTheme(Theme
				.valueOf("MYSTIC"));

		// summaryReport = new SeleniumReport(reportSettings, reportTheme);

		String mailTo = properties.getProperty("SendMailTo");
		String[] toFinal = mailTo.split(",");
		String FROM_ADDR = properties.getProperty("FromMailAddress");
		String FROM_DISPLAY_NAME = "AUTORUNNER";
		String Env = properties.getProperty("ApplicationUrl").substring(8);

		final String scenario = this.frameworkParameters.getRunConfiguration();
		final String host = properties.getProperty("HOST");
		final String smtp_username = properties.getProperty("SMTP_USERNAME");
		final String smtp_password = properties.getProperty("SMTP_PASSWORD");


		try {
			ip = InetAddress.getLocalHost();
			hostname = ip.getHostName();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		//props.put("mail.smtp.host", "email-smtp.us-east-1.amazonaws.com");
		props.put("mail.smtp.port", "587");

		/*
		 * Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		 * protected PasswordAuthentication getPasswordAuthentication() { return new
		 * PasswordAuthentication(username, password); } });
		 */
		// Create a Session object to represent a mail session with the specified properties. 
    	Session session = Session.getDefaultInstance(props);
		 Transport transport = session.getTransport();
		 
		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM_ADDR, FROM_DISPLAY_NAME));

			InternetAddress[] toAddresses = new InternetAddress[toFinal.length];
			for (int i = 0; i < toFinal.length; i++) {
				toAddresses[i] = new InternetAddress(toFinal[i]);
			}
			message.setRecipients(Message.RecipientType.TO, toAddresses);
			message.setSubject("Alfresco Automation : Dry Run Result."
					+ "Suite Name :" + scenario + ",Env :" + Env + " Machine :"
					+ ip);

			String siteName = new FileUtil()
					.readDataFromFile(testOutputFilePath);
			String siteName1 = new FileUtil()
					.readDataFromFile(testOutputFilePath1);
			String siteName2 = new FileUtil()
					.readDataFromFile(testOutputFilePath2);

			// creates message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			String message1 = siteName + scenario + siteName2 + totalExecuted
					+ siteName2 + nTestsPassed + siteName2 + nTestsFailed
					+ siteName2 + totalExecutionTime + siteName1;
			messageBodyPart.setContent(message1, "text/html");

			// creates multi-part
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			String reportspath = ResultSummaryManager.reportPath;
			final String attachFiles = reportspath + Util.getFileSeparator()
					+ "HTML Results" + Util.getFileSeparator() + "Summary.html";
			// final String excelFiles
			// =reportspath+Util.getFileSeparator()+"Excel Results"+Util.getFileSeparator()+"Summary.xls";
			// adds attachments
			if (attachFiles != null) {

				MimeBodyPart attachPart = new MimeBodyPart();

				try {
					attachPart.attachFile(attachFiles);
					// attachPart.attachFile(excelFiles);
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				multipart.addBodyPart(attachPart);

			}			
			message.setContent(multipart);
			
			 transport.connect(host, smtp_username, smtp_password);			
			 transport.sendMessage(message, message.getAllRecipients());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally
        {
            // Close and terminate the connection.
            transport.close();
        }
	}
}

/*package com.pearson.framework.selenium;

import java.io.IOException;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.pearson.automation.utils.FileUtil;
import com.pearson.framework.Report;
import com.pearson.framework.ReportTheme;
import com.pearson.framework.ReportThemeFactory;
import com.pearson.framework.ReportThemeFactory.Theme;
import com.pearson.framework.Util;
import com.pearson.framework.selenium.SeleniumReport;
import com.pearson.framework.selenium.ResultSummaryManager;
import com.pearson.framework.Settings;

public class SendMailTLS extends ResultSummaryManager {

	Properties properties = Settings.loadFromPropertiesFile();

	InetAddress ip;
	String hostname;

	private String testOutputFilePath = "src/test/resources/AppTestData/Sites/EmailDetails.txt";
	private String testOutputFilePath1 = "src/test/resources/AppTestData/Sites/EmailDetails1.txt";
	private String testOutputFilePath2 = "src/test/resources/AppTestData/Sites/EmailDetails2.txt";

	public void sendMsg(final String updatescenarioName,
			final int totalExecuted, final String totalExecutionTime,
			final int nTestsPassed, final int nTestsFailed) {

		ReportTheme reportTheme = ReportThemeFactory.getReportsTheme(Theme
				.valueOf("MYSTIC"));

		// summaryReport = new SeleniumReport(reportSettings, reportTheme);

		String mailTo = properties.getProperty("SendMailTo");
		String[] toFinal = mailTo.split(",");
		String FROM_ADDR = properties.getProperty("FromMailAddress");
		String FROM_DISPLAY_NAME = "AUTORUNNER";
		String Env = properties.getProperty("ApplicationUrl").substring(8);

		final String scenario = this.frameworkParameters.getRunConfiguration();
		final String username = properties.getProperty("FromMailAddress");
		final String password = properties.getProperty("Mailpassword");

		try {
			ip = InetAddress.getLocalHost();
			hostname = ip.getHostName();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "relay.mx.pearson.com");
		props.put("mail.smtp.port", "25");

		try {
			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM_ADDR, FROM_DISPLAY_NAME));

			InternetAddress[] toAddresses = new InternetAddress[toFinal.length];
			for (int i = 0; i < toFinal.length; i++) {
				toAddresses[i] = new InternetAddress(toFinal[i]);
			}
			message.setRecipients(Message.RecipientType.TO, toAddresses);
			message.setSubject("Alfresco Automation : Dry Run Result."
					+ "Suite Name :" + scenario + ",Env :" + Env + " Machine :"
					+ ip);

			String siteName = new FileUtil()
					.readDataFromFile(testOutputFilePath);
			String siteName1 = new FileUtil()
					.readDataFromFile(testOutputFilePath1);
			String siteName2 = new FileUtil()
					.readDataFromFile(testOutputFilePath2);

			// creates message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			String message1 = siteName + scenario + siteName2 + totalExecuted
					+ siteName2 + nTestsPassed + siteName2 + nTestsFailed
					+ siteName2 + totalExecutionTime + siteName1;
			messageBodyPart.setContent(message1, "text/html");

			// creates multi-part
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			String reportspath = ResultSummaryManager.reportPath;
			final String attachFiles = reportspath + Util.getFileSeparator()
					+ "HTML Results" + Util.getFileSeparator() + "Summary.html";
			// final String excelFiles
			// =reportspath+Util.getFileSeparator()+"Excel Results"+Util.getFileSeparator()+"Summary.xls";
			// adds attachments
			if (attachFiles != null) {

				MimeBodyPart attachPart = new MimeBodyPart();

				try {
					attachPart.attachFile(attachFiles);
					// attachPart.attachFile(excelFiles);
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				multipart.addBodyPart(attachPart);

			}

			
			 * // adds attachments if (excelFiles != null ) {
			 * 
			 * MimeBodyPart attachPartexl = new MimeBodyPart();
			 * 
			 * try { attachPartexl.attachFile(excelFiles); //
			 * attachPart.attachFile(excelFiles); } catch (IOException ex) {
			 * ex.printStackTrace(); }
			 * 
			 * multipart.addBodyPart(attachPartexl);
			 * 
			 * }
			 

			// sets the multi-part as e-mail's content
			message.setContent(multipart);
			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
*/
