package com.bridz.utility;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.management.JMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bridz.exception.JmsException;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void send(String to, String subject, String body) throws JMException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		try {

			helper = new MimeMessageHelper(message, true);
			helper.setSubject(subject);
			helper.setTo(to);
			helper.setText(body, true);
		} catch (MessagingException e) {

			throw new JmsException(201, "Error in mime message in email service class");
		}

		javaMailSender.send(message);
	}

}
