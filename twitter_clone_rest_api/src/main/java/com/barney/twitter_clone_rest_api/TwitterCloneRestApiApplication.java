package com.barney.twitter_clone_rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		TwitterCloneRestApiApplication.class,
		Jsr310JpaConverters.class
})public class TwitterCloneRestApiApplication {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC+3"));
	}

	public static void main(String[] args) {
		SpringApplication.run(TwitterCloneRestApiApplication.class, args);
	}

}
