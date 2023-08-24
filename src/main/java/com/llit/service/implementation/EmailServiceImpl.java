package com.llit.service.implementation;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.llit.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	public static final String EMAIL_DEV_USER = System.getenv("EMAIL_USER_DEV");
	public static final String EMAIL_ENV_USER = System.getenv("EMAIL_USER");
    @Autowired
    private JavaMailSender emailSender;

    public void sendMailWithLogFile(
      String subject, String exceptionMessage) {
    	  
    	MimeMessage message = emailSender.createMimeMessage();
	     	    
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
		    helper.setFrom(EMAIL_ENV_USER);
		    helper.setTo(EMAIL_DEV_USER);
		    helper.setSubject(subject);
		    helper.setText(exceptionMessage);
		        
		    FileSystemResource file 
		      = new FileSystemResource(new File("./cheapair.log"));
		    
		    helper.addAttachment("CheapAir.log", file);

		    emailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    }
}