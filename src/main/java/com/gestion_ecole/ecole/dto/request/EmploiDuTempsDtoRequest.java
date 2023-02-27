package com.gestion_ecole.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor @Data
public class EmploiDuTempsDtoRequest {
	private Long id;
	private String titre;
	private long anneeScolaireID;
	
	
}
