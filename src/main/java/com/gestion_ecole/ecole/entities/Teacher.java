package com.gestion_ecole.ecole.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="Teacher")
public class Teacher extends User{
	@OneToMany(targetEntity=Inscription.class,cascade = CascadeType.REMOVE, mappedBy="teacher")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Inscription> inscriptions = new ArrayList<Inscription>();
	@OneToMany(orphanRemoval = true,targetEntity=Student.class, cascade = CascadeType.ALL,mappedBy = "teacher")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Student> students = new ArrayList<Student>();
	@OneToMany(orphanRemoval = true,targetEntity=Classe.class, cascade = CascadeType.ALL,mappedBy = "teacher")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Classe> classes = new ArrayList<Classe>();
	@ManyToOne
    private Classe classe;
	@ManyToOne
    private Student student;
	private String niveauEtude;
	private Long departementID;
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


	
	public Teacher(Long id, Long structureID, String prenom, String nom, String sexe, String adresse,
			String numeroMatriciule, String typeDeRecrutement, Long type, String naissance, String nationalite,
			String lieu_naissance, long dateCreation, boolean status, boolean monPremiereConnexion, String email,
			String telephone, String name_logo, String url_logo, Long contratID, String monToken, String password,
			String compteBancaire, String resetPasswordToken, String role, List<Absence> absences,
			List<SupportPysique> supportPysiques, List<Paiement> paiements, List<Seance> seances, List<Code> codes) {
		super(id, structureID, prenom, nom, sexe, adresse, numeroMatriciule, typeDeRecrutement, type, naissance, nationalite,
				lieu_naissance, dateCreation, status, monPremiereConnexion, email, telephone, name_logo, url_logo, contratID,
				monToken, password, compteBancaire, resetPasswordToken, role, absences, supportPysiques, paiements, seances,
				codes);
		// TODO Auto-generated constructor stub
	}






	public Teacher(List<Inscription> inscriptions, List<Student> students, List<Classe> classes, Classe classe,
			Student student, String niveauEtude, Long departementID) {
		super();
		this.inscriptions = inscriptions;
		this.students = students;
		this.classes = classes;
		this.classe = classe;
		this.student = student;
		this.niveauEtude = niveauEtude;
		this.departementID = departementID;
	}





	public Long getDepartementID() {
		return departementID;
	}
	public void setDepartementID(Long departementID) {
		this.departementID = departementID;
	}


	public String getNiveauEtude() {
		return niveauEtude;
	}


	public void setNiveauEtude(String niveauEtude) {
		this.niveauEtude = niveauEtude;
	}
	

	}
