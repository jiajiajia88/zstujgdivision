package com.szy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ZstuJgDivisionApplication {

	/*@RequestMapping("/")
	String index() {
		return "Hello Spring Boot";
	}*/

	public static void main(String[] args) {
		SpringApplication.run(ZstuJgDivisionApplication.class, args);
	}
}