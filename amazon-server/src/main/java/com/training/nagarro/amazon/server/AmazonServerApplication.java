package com.training.nagarro.amazon.server;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.training.nagarro.amazon.server.repo.DealRepository;



@SpringBootApplication
public class AmazonServerApplication implements CommandLineRunner {
	private ApplicationContext applicationContext;
	
	@Autowired
	public AmazonServerApplication(ApplicationContext applicationContext) {
		super();
		this.applicationContext = applicationContext;
	}

	public static void main(String[] args) {
		SpringApplication.run(AmazonServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.applicationContext.getBean("dealRepository", DealRepository.class).fetchAllDealsForJeansCategory();
	}
}
