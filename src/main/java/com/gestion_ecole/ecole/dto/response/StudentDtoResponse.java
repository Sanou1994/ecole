package com.gestion_ecole.ecole.dto.response;

import java.io.Serializable;
import java.util.List;

import com.gestion_ecole.ecole.entities.Classe;
import com.gestion_ecole.ecole.entities.Inscription;
import com.gestion_ecole.ecole.entities.Note;
import com.gestion_ecole.ecole.entities.Parent;
import com.gestion_ecole.ecole.entities.Student;
import com.gestion_ecole.ecole.entities.Teacher;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor @NoArgsConstructor
public class StudentDtoResponse extends UserDtoResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Inscription> inscriptions ;
	   private List<Note> notes ;
	   private List<Student> parains ;
	   private List<Teacher> professeurAppuis ;
	   private Teacher teacher;
	   private Parent parent;		
	   private Classe classe;
	   private Long departementID;
	   private Long filiereID;
		  private Long niveauEtudeID;

	public List<Inscription> getInscriptions() {
		return inscriptions;
	}
	public void setInscriptions(List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	public List<Student> getParains() {
		return parains;
	}
	public void setParains(List<Student> parains) {
		this.parains = parains;
	}
	public List<Teacher> getProfesseurAppuis() {
		return professeurAppuis;
	}
	public void setProfesseurAppuis(List<Teacher> professeurAppuis) {
		this.professeurAppuis = professeurAppuis;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	
	public Long getFiliereID() {
		return filiereID;
	}
	public void setFiliereID(Long filiereID) {
		this.filiereID = filiereID;
	}
	public Long getDepartementID() {
		return departementID;
	}
	public void setDepartementID(Long departementID) {
		this.departementID = departementID;
	}
	public Long getNiveauEtudeID() {
		return niveauEtudeID;
	}
	public void setNiveauEtudeID(Long niveauEtudeID) {
		this.niveauEtudeID = niveauEtudeID;
	}
	   
}
