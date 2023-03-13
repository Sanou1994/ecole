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
public class Ass_parent_student
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private long studentID;
	private boolean approuve=false;
	private boolean rejette=true;
	private String date_enregistrement;
	private long parentID;
	public Ass_parent_student(long studentID, boolean approuve, boolean rejette, String date_enregistrement) {
		super();
		this.studentID = studentID;
		this.approuve = approuve;
		this.rejette = rejette;
		this.date_enregistrement = date_enregistrement;
	}
	
	
	
}
