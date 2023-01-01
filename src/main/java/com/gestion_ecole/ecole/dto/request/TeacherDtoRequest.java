package com.gestion_ecole.ecole.dto.request;

import java.util.List;

import com.gestion_ecole.ecole.entities.Absence;
import com.gestion_ecole.ecole.entities.Classe;
import com.gestion_ecole.ecole.entities.Departement;
import com.gestion_ecole.ecole.entities.Inscription;
import com.gestion_ecole.ecole.entities.Paiement;
import com.gestion_ecole.ecole.entities.Seance;
import com.gestion_ecole.ecole.entities.Student;
import com.gestion_ecole.ecole.entities.SupportPysique;
public class TeacherDtoRequest extends UserDtoRequest {
	
		
    private List<Inscription> inscriptions ;
    private List<Student> students ;
	private List<Classe> classes ;
	
    private Classe classe;
    private Student student;
	
	private Departement departement;

	public TeacherDtoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public TeacherDtoRequest(Long id, String prenom, String nom, String adresse, String numeroMatriciule,
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Classe> getClasses() {
		return classes;
	}

	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}

	
	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
    
}
