package com.egradebook.eGradeBook;

import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.serviceImplementation.UserServiceImplementation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class EGradeBookApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(EGradeBookApplication.class, args);

		//UserServiceImplementation userServiceImplementation = new UserServiceImplementation();
	}


}
