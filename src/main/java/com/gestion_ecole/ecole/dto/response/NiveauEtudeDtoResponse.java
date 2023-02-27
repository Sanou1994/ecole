package com.gestion_ecole.ecole.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Data
public class NiveauEtudeDtoResponse {

	private Long id;
	private String nom;
	private Long structureID;
	private boolean status;
	
}