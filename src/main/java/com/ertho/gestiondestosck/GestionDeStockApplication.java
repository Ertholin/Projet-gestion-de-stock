package com.ertho.gestiondestosck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GestionDeStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionDeStockApplication.class, args);
	}

}
