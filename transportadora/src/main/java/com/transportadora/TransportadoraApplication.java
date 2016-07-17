package com.transportadora;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class TransportadoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportadoraApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
}
