package com.gestion_ecole.ecole.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.gestion_ecole.ecole.entities.Ass_parent_student;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor @NoArgsConstructor
public class ParentDtoResponse extends UserDtoResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	 private String professionParent;
	 private String typeParent;
	 private String relationTuteur;
	private List<Ass_parent_student> ass_parent_students = new ArrayList<Ass_parent_student>();

	
	public String getProfessionParent() {
		return professionParent;
	}

	public void setProfessionParent(String professionParent) {
		this.professionParent = professionParent;
	}

	public String getTypeParent() {
		return typeParent;
	}

	public void setTypeParent(String typeParent) {
		this.typeParent = typeParent;
	}

	public String getRelationTuteur() {
		return relationTuteur;
	}

	public void setRelationTuteur(String relationTuteur) {
		this.relationTuteur = relationTuteur;
	}

	public List<Ass_parent_student> getAss_parent_students() {
		return ass_parent_students;
	}

	public void setAss_parent_students(List<Ass_parent_student> ass_parent_students) {
		this.ass_parent_students = ass_parent_students;
	}
	   
}
