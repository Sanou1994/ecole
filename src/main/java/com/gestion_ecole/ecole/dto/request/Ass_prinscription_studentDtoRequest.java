package com.gestion_ecole.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor @NoArgsConstructor 
@Data
public class Ass_prinscription_studentDtoRequest {
	private Long id;
	private long studentID;
	private long prinscriptionID;
	private String date_enregistrement;
	private long structureID;
	private boolean status;
	public Ass_prinscription_studentDtoRequest(long studentID, long prinscriptionID, String date_enregistrement,
			long structureID, boolean status) {
		super();
		this.studentID = studentID;
		this.prinscriptionID = prinscriptionID;
		this.date_enregistrement = date_enregistrement;
		this.structureID = structureID;
		this.status = status;
	}
	
}
