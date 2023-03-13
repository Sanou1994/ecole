package com.gestion_ecole.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor 
@Data
public class Ass_parent_studentDtoRequest
{
	private Long id;
	private long studentID;
	private boolean approuve=false;
	private boolean rejette=true;
	private String date_enregistrement;
	private long parentID;
	public Ass_parent_studentDtoRequest(long studentID, boolean approuve, boolean rejette, String date_enregistrement) {
		super();
		this.studentID = studentID;
		this.approuve = approuve;
		this.rejette = rejette;
		this.date_enregistrement = date_enregistrement;
	}
	
	
	
}
