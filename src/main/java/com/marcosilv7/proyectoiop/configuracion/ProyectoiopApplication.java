package com.marcosilv7.proyectoiop.configuracion;

import com.marcosilv7.proyectoiop.DemoLingo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@ComponentScan("com.marcosilv7.proyectoiop.*")
@EntityScan("com.marcosilv7.proyectoiop.*")
@EnableJpaRepositories(value = {
		"com.marcosilv7.proyectoiop.dao.repository"
})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ProyectoiopApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoiopApplication.class, args);
	}

	@Value("${url-rutaReporteLingo}")
	private String urlRutaReporte;

	@Value("${url-logLingo}")
	private String urlRutaLogReporte;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<ProyectoiopApplication> applicationClass = ProyectoiopApplication.class;

	@Bean
	public TemplateResolver templateResolver() {
		TemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	@Bean
	@Scope("prototype")
	public DemoLingo lingo(){
		return new DemoLingo(urlRutaReporte,urlRutaLogReporte);
	}
}
