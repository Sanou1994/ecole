package com.gestion_ecole.ecole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.gestion_ecole.ecole.service.IAccountService;

@SpringBootApplication
public class EcoleApplication implements CommandLineRunner{
   @Autowired
	private IAccountService iAccountService;
	public static void main(String[] args)   {
		SpringApplication.run(EcoleApplication.class, args);
	}
	 @Bean
		public BCryptPasswordEncoder getBCPE() {
			return new BCryptPasswordEncoder();
		}
	 @Bean
	 public RestTemplate getRestTemplate() {
		 return new RestTemplate();
	 }
	@Override
	public void run(String... args) throws Exception {
		iAccountService.initAccount();
		
	}
}
