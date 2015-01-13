package de.budisantoso.wcd.wh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
@SuppressWarnings("PMD")
public class WcdWorkingHoursApplication {

	public static void main(String[] args) {
		SpringApplication.run(WcdWorkingHoursApplication.class, args);
	}

}
