package com.gestion_ecole.ecole.dto.request;

import java.util.List;

import com.gestion_ecole.ecole.entities.Absence;
import com.gestion_ecole.ecole.entities.Classe;
import com.gestion_ecole.ecole.entities.Departement;
import com.gestion_ecole.ecole.entities.Inscription;
import com.gestion_ecole.ecole.entities.Note;
import com.gestion_ecole.ecole.entities.Paiement;
import com.gestion_ecole.ecole.entities.Parent;
import com.gestion_ecole.ecole.entities.Seance;
import com.gestion_ecole.ecole.entities.Student;
import com.gestion_ecole.ecole.entities.SupportPysique;
import com.gestion_ecole.ecole.entities.Teacher;
public class StudentDtoRequest extends UserDtoRequest {
       
	private List<Inscription> inscriptions ;
	   private List<Note> notes ;
	   private List<Student> parains ;
	   private List<Teacher> professeurAppuis ;
	   private Teacher teacher;
	   private Parent parent;		
	   private Classe classe;
	   private Departement departement;
	   
	   
	public StudentDtoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StudentDtoRequest(Long id, String prenom, String nom, String adresse, String numeroMatriciule,
			String typeDeRecrutement, String type, String naissance, long dateCreation, boolean status, String email,
			String telephone, String monToken, String password, String compteBancaire, String resetPasswordToken,
			String role, List<Absence> absences, List<SupportPysique> supportPysiques, List<Paiement> paiements,
			List<Seance> seances) {
		super(id, prenom, nom, adresse, numeroMatriciule, typeDeRecrutement, type, naissance, dateCreation, status, email,
				telephone, monToken, password, compteBancaire, resetPasswordToken, role, absences, supportPysiques, paiements,
				seances);
		// TODO Auto-generated constructor stub
	}

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
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	   
	   
    
}
