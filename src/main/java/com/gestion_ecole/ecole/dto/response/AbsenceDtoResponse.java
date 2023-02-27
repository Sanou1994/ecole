package com.gestion_ecole.ecole.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor @Data
public class AbsenceDtoResponse {
	private Long id;
	private String libelle;
	private boolean status;
	private long structureID;
	
}
