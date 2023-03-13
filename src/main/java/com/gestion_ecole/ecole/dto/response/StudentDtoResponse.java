package com.gestion_ecole.ecole.dto.response;

import java.io.Serializable;
import java.util.List;

import com.gestion_ecole.ecole.entities.Classe;
import com.gestion_ecole.ecole.entities.Inscription;
import com.gestion_ecole.ecole.entities.Note;
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
	   private Classe classe;
	   private Long departementID;
	   private Long filiereID;
	   private Long niveauEtudeID;
	   private Long parentID;
	
	
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
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public Long getDepartementID() {
		return departementID;
	}
	public void setDepartementID(Long departementID) {
		this.departementID = departementID;
	}
	public Long getFiliereID() {
		return filiereID;
	}
	public void setFiliereID(Long filiereID) {
		this.filiereID = filiereID;
	}
	public Long getNiveauEtudeID() {
		return niveauEtudeID;
	}
	public void setNiveauEtudeID(Long niveauEtudeID) {
		this.niveauEtudeID = niveauEtudeID;
	}
	public Long getParentID() {
		return parentID;
	}
	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}
	
	
	public StudentDtoResponse(Long id, Long structureID, String prenom, String nom, String adresse,
			String numeroMatriciule, String typeDeRecrutement, Long type, String sexe, String naissance,
			long dateCreation, boolean status, String nationalite, String login, String email, String telephone,
			String monToken, String password, String compteBancaire, String resetPasswordToken, String role,
			String lieu_naissance, String name_logo, String url_logo, long contratID, boolean monPremiereConnexion,
			List<Inscription> inscriptions, List<Note> notes, List<Student> parains, List<Teacher> professeurAppuis,
			Teacher teacher, Classe classe, Long departementID, Long filiereID, Long niveauEtudeID, Long parentID) {
		super(id, structureID, prenom, nom, adresse, numeroMatriciule, typeDeRecrutement, type, sexe, naissance,
				dateCreation, status, nationalite, login, email, telephone, monToken, password, compteBancaire,
				resetPasswordToken, role, lieu_naissance, name_logo, url_logo, contratID, monPremiereConnexion);
		this.inscriptions = inscriptions;
		this.notes = notes;
		this.parains = parains;
		this.professeurAppuis = professeurAppuis;
		this.teacher = teacher;
		this.classe = classe;
		this.departementID = departementID;
		this.filiereID = filiereID;
		this.niveauEtudeID = niveauEtudeID;
		this.parentID = parentID;
	}
	public StudentDtoResponse(Long id, Long structureID, String prenom, String nom, String adresse,
			String numeroMatriciule, String typeDeRecrutement, Long type, String sexe, String naissance,
			long dateCreation, boolean status, String nationalite, String login, String email, String telephone,
			String monToken, String password, String compteBancaire, String resetPasswordToken, String role,
			String lieu_naissance, String name_logo, String url_logo, long contratID, boolean monPremiereConnexion) {
		super(id, structureID, prenom, nom, adresse, numeroMatriciule, typeDeRecrutement, type, sexe, naissance, dateCreation,
				status, nationalite, login, email, telephone, monToken, password, compteBancaire, resetPasswordToken, role,
				lieu_naissance, name_logo, url_logo, contratID, monPremiereConnexion);
		// TODO Auto-generated constructor stub
	}
	
}
