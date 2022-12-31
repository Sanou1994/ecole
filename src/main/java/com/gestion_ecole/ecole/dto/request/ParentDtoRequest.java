package com.gestion_ecole.ecole.dto.request;

import java.util.List;

import com.gestion_ecole.ecole.entities.Abscence;
import com.gestion_ecole.ecole.entities.Paiement;
import com.gestion_ecole.ecole.entities.Sceance;
import com.gestion_ecole.ecole.entities.Student;
import com.gestion_ecole.ecole.entities.SupportPysique;

public class ParentDtoRequest extends UserDtoRequest {
	public ParentDtoRequest(Long id, String prenom, String nom, String adresse, String numeroMatriciule,
			String typeDeRecrutement, String type, String naissance, long dateCreation, boolean status, String email,
			String telephone, String monToken, String login, String password, String compteBancaire,
			String resetPasswordToken, String role, List<Abscence> abscences, List<SupportPysique> supportPysiques,
			List<Paiement> paiements, List<Sceance> seances) {
		super(id, prenom, nom, adresse, numeroMatriciule, typeDeRecrutement, type, naissance, dateCreation, status, email,
				telephone, monToken, login, password, compteBancaire, resetPasswordToken, role, abscences, supportPysiques,
				paiements, seances);
		// TODO Auto-generated constructor stub
	}

	private List<Student> students ;

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	
}
