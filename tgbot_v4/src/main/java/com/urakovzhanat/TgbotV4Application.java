package com.urakovzhanat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TgbotV4Application {

	public static void main(String[] args) {
		SpringApplication.run(TgbotV4Application.class, args);
	}

}
