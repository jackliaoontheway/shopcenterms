package com.noone.shopcenterms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ShopcentermsApplication {

	public static void main(String[] args) {
		
//		String driPath = System.getProperty("user.dir") + "\\Drivers";
//		System.out.println(driPath);
		SpringApplication.run(ShopcentermsApplication.class, args); 
		
//		System.out.println(System.getProperty("java.library.path"));
	}
}
