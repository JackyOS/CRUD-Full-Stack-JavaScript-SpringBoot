package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	//lo registramos en spring para luego poder injectarlo cona autowire en el service
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}




}
