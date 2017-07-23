package com.marcosilv7.proyectoiop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.marcosilv7.proyectoiop.*")
@EntityScan("com.marcosilv7.proyectoiop.*")
@EnableJpaRepositories(value = {
		"com.marcosilv7.proyectoiop.dao.repository.*"
})
@SpringBootApplication
public class ProyectoiopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoiopApplication.class, args);
	}
}
