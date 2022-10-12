package com.example.Java_Diplom;

import com.example.Java_Diplom.Services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

import javax.mail.MessagingException;
import java.util.UUID;

@SpringBootApplication
public class JavaDiplomApplication {
	@Autowired
	private EmailSenderService senderService;
	public static void main(String[] args) {
		SpringApplication.run(JavaDiplomApplication.class, args);
	}


	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
System.out.println(UUID.randomUUID().toString());
	//	senderService.sendMimeEmail("necessary3228@gmail.com",
		//		"Список пользователей",
	//			"Добрый день,Пока");

	}
}
