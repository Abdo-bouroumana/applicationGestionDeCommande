package com.abderrazak.applicationGestion;

import com.abderrazak.applicationGestion.model.*;
import com.abderrazak.applicationGestion.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ApplicationGestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationGestionApplication.class, args);

	}

}
