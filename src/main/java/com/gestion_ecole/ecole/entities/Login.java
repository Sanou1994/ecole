package com.gestion_ecole.ecole.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Login {
	
	private String login;
	private String password;
	private String email;

}
