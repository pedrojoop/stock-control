package com.br.stockcontrol.stockcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.br.stockcontrol.stockcontrol", "com.br.stockcontrol.stockcontrol.Repository"})
public class StockControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockControlApplication.class, args);
	}

}
