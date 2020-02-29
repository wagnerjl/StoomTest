package br.com.stoom.StoomTest;

import com.mongodb.MongoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StoomTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoomTestApplication.class, args);
	}

	@Bean
	public MongoClient mongoClient() {
		return new MongoClient("localhost");
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), "stoomtest");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
