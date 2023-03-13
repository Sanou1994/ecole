package com.gestion_ecole.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Data
public class ClasseDtoRequest {
	private Long id;
	private String nom;
	private boolean status;
	private Long structureID;
	private Long filiere;
}
