package com.remsnew;



import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.remsnew.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableJpaRepositories
@SpringBootApplication
public class RemsnewApplication {

	public static void main(String[] args) throws UserNotFoundException {
		log.info("Started main");
		SpringApplication.run(RemsnewApplication.class, args);
		log.info("end main");
		
		
	}
 
}
