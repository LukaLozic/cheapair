package com.llit.service;

import com.llit.dto.FlightResponseBody;
import com.llit.dto.FlightSearchRequestBody;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

public interface EmailService {
    void sendMailWithLogFile(String subject, String exceptionMessage);
}
