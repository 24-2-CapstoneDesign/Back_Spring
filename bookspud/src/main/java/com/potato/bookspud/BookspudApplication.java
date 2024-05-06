package com.potato.bookspud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookspudApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookspudApplication.class, args);
	}

}
