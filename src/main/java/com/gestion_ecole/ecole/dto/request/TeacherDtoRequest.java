package com.gestion_ecole.ecole.dto.request;

import java.util.List;

import com.gestion_ecole.ecole.entities.Absence;
import com.gestion_ecole.ecole.entities.Classe;
import com.gestion_ecole.ecole.entities.Inscription;
import com.gestion_ecole.ecole.entities.Paiement;
import com.gestion_ecole.ecole.entities.Seance;
import com.gestion_ecole.ecole.entities.Student;
import com.gestion_ecole.ecole.entities.SupportPysique;
public class TeacherDtoRequest extends UserDtoRequest {
	
		
    private List<Inscription> inscriptions ;
    private List<Student> students ;
	private List<Classe> classes ;
	private String niveauEtude;
    private Classe classe;
    private Student student;
	
	private Long departementID;

	public TeacherDtoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public TeacherDtoRequest(Long id, Long structureID, String prenom, String name_logo, String url_logo, String nom,
			String adresse, String numeroMatriciule, String typeDeRecrutement, String typeUser, Long type,
			String naissance, String sexe, String nationalite, long dateCreation, long contratID, boolean status,
			String lieu_naissance, String email, String telephone, String monToken, String password,
			String compteBancaire, String resetPasswordToken, String role, List<Absence> absences,
			List<SupportPysique> supportPysiques, List<Paiement> paiements, List<Seance> seances,
			List<Inscription> inscriptions, List<Student> students, List<Classe> classes, String niveauEtude,
			Classe classe, Student student, Long departementID) {
		super(id, structureID, prenom, name_logo, url_logo, nom, adresse, numeroMatriciule, typeDeRecrutement, typeUser,
				type, naissance, sexe, nationalite, dateCreation, contratID, status, lieu_naissance, email, telephone,
				monToken, password, compteBancaire, resetPasswordToken, role, absences, supportPysiques, paiements,
				seances);
		this.inscriptions = inscriptions;
		this.students = students;
		this.classes = classes;
		this.niveauEtude = niveauEtude;
		this.classe = classe;
		this.student = student;
		this.departementID = departementID;
	}



	public TeacherDtoRequest(Long id, Long structureID, String prenom, String name_logo, String url_logo, String nom,
			String adresse, String numeroMatriciule, String typeDeRecrutement, String typeUser, Long type,
			String naissance, String sexe, String nationalite, long dateCreation, long contratID, boolean status,
			String lieu_naissance, String email, String telephone, String monToken, String password,
			String compteBancaire, String resetPasswordToken, String role, List<Absence> absences,
			List<SupportPysique> supportPysiques, List<Paiement> paiements, List<Seance> seances) {
		super(id, structureID, prenom, name_logo, url_logo, nom, adresse, numeroMatriciule, typeDeRecrutement, typeUser, type,
				naissance, sexe, nationalite, dateCreation, contratID, status, lieu_naissance, email, telephone, monToken,
				password, compteBancaire, resetPasswordToken, role, absences, supportPysiques, paiements, seances);
		// TODO Auto-generated constructor stub
	}



	public String getNiveauEtude() {
		return niveauEtude;
	}



	public void setNiveauEtude(String niveauEtude) {
		this.niveauEtude = niveauEtude;
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

	public Long getdepartementID() {
		return departementID;
	}

	public void setdepartementID(Long departementID) {
		this.departementID = departementID;
	}
    
}
