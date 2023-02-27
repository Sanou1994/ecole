package com.gestion_ecole.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor @Data
public class AbsenceDtoRequest {
	private Long id;
	private long dateDebut;
	private long dateFin;
	private boolean status=false;
	private long dateAbscenceCreate;
	private long anneeScolaireID;
	
}
