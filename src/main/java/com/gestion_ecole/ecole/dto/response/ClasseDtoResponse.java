package com.gestion_ecole.ecole.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor @NoArgsConstructor
public class ClasseDtoResponse  {
	private Long id;
	private String nom;
	private boolean status;
	private Long strutureID;
	
}
