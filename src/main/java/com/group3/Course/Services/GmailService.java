package com.group3.Course.Services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GmailService implements IGmailService {

	Properties properties = null;
	Session session = null;
	Message msg = null;

	private static Logger logger = LogManager.getLogger(GmailService.class);

	@Override
	public void setSMTPClient() {

		properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("portalSoftwareDev@gmail.com", "Courseportal@07");
			}
		});
	}

	@Override
	public void prepareMail(String subject, String message, String to) {

		try {
			msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("portalSoftwareDev@gmail.com", false));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("portalSoftwareDev@gmail.com"));
			msg.setSubject(subject);
			msg.setText(message);
		} catch (Exception e) {
			logger.info(e);
		}
	}

	@Override
	public void sendEmail() {

		try {
			Transport.send(msg);
		} catch (Exception e) {
			logger.info(e);
		}
	}
}