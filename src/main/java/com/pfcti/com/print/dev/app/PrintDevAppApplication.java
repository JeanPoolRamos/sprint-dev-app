package com.pfcti.com.print.dev.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.h2.tools.Server;
import java.sql.SQLException;

@SpringBootApplication
public class PrintDevAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrintDevAppApplication.class, args);
	}

}
