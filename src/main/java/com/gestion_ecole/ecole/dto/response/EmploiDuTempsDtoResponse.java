package com.gestion_ecole.ecole.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor @Data
public class EmploiDuTempsDtoResponse {

	private Long id;
	private String titre;
	private long anneeScolaireID;
	

	
}
