package com.desk.helppanel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelppanelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelppanelApplication.class, args);
	}

}
// ngrok http --domain=subtly-lucky-bear.ngrok-free.app http://localhost:8080/