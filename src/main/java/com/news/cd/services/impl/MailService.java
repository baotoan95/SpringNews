package com.news.cd.services.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service("mailService")
public class MailService {
	private JavaMailSender mailSender;
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public boolean sender(String[] toAddress, String subject, String messageBody) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("baotoan.95@gmail.com"); // From
			helper.setTo(toAddress);
			helper.setSubject(subject);
			helper.setText(messageBody, true); // Allow HTML
			mailSender.send(message);
			return true;
		} catch (MessagingException e) {
			return false;
		}catch(Exception e){
			return false;
		}
		
	}
}
