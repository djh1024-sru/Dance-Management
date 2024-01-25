package edu.sru.thangiah.group2.fall2023registrationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Fall2023registrationsystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(Fall2023registrationsystemApplication.class, args);
	}
}