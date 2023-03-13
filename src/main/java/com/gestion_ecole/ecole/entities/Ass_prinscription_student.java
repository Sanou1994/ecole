package com.gestion_ecole.ecole.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor @NoArgsConstructor 
@Data
public class Ass_prinscription_student
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private long studentID;
	private long prinscriptionID;
	private boolean status = true;
	private String date_enregistrement;
	private long structureID;
	public Ass_prinscription_student(long studentID, long prinscriptionID, boolean status, String date_enregistrement,
			long structureID) {
		super();
		this.studentID = studentID;
		this.prinscriptionID = prinscriptionID;
		this.status = status;
		this.date_enregistrement = date_enregistrement;
		this.structureID = structureID;
	}
	
	
}
