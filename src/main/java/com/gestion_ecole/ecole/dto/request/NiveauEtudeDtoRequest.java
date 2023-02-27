package com.gestion_ecole.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Data
public class NiveauEtudeDtoRequest {
	private Long id;
	private String nom;
	private Long structureID;
	private boolean status;

}