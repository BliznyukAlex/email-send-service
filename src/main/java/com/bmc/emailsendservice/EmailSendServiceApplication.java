package com.bmc.emailsendservice;

import com.bmc.emailsendservice.data.AppData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailSendServiceApplication implements CommandLineRunner {

	@Autowired
	private AppData appData;
	public static void main(String[] args) {
		SpringApplication.run(EmailSendServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		appData.loadData();
		Thread.currentThread().join();
	}
}
