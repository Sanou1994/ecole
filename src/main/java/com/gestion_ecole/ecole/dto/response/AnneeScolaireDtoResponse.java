package com.gestion_ecole.ecole.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor @Data
public class AnneeScolaireDtoResponse {
	private Long id;
	private long dateDebut;
	private long dateFin;
	private boolean status=false;
	private long dateAbscenceCreate;
	private long anneeScolaireID;
	
}
