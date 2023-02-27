package com.gestion_ecole.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor @Data
public class AnneeScolaireDtoRequest {
	private Long id;
	private String libelle;
	private boolean status;
	private long structureID;
	
}
