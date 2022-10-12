package com.example.Java_Diplom.Services;

import com.example.Java_Diplom.Repositories.UserRepository;
import com.example.Java_Diplom.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private  SpringTemplateEngine templateEngine;

    public void sendMimeEmail(String toEmail, String subject, String htmlBody,String ActivationCode,
                              Map<String, Object> properties) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
MimeMessageHelper
        message=new MimeMessageHelper(mimeMessage,true);
      //  SimpleMailMessage message = new SimpleMailMessage();
    //    Map<String, Object> properties = new HashMap<>();
      //  properties.put("Obj","Зверь");
     //   properties.put("name", "Ashish");
        System.out.println("Sending message...");
        Context context=new Context();
        context.setVariables(properties);
String html=templateEngine.process(htmlBody,context);
//System.out.println(html);
        message.setFrom("necessary3228@gmail.com","Carlex true");
        message.setTo(toEmail);
        message.setText(html,true);
        message.setSubject(subject);
        mailSender.send(mimeMessage);
        System.out.println("Mail Send...");


    }


}
