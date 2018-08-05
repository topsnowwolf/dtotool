package com.snowwolf.dtotool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SnowwolfApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SnowwolfApplication.class);
	}

	public static void main(String[] args) {
		System.setProperty("sun.jnu.encoding","UTF-8");
		SpringApplication.run(SnowwolfApplication.class, args);
	}
}
