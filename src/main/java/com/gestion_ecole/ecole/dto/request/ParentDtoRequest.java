package com.gestion_ecole.ecole.dto.request;

import java.util.List;

import com.gestion_ecole.ecole.entities.Absence;
import com.gestion_ecole.ecole.entities.Paiement;
import com.gestion_ecole.ecole.entities.Seance;
import com.gestion_ecole.ecole.entities.SupportPysique;

import lombok.NoArgsConstructor;
@NoArgsConstructor
public class ParentDtoRequest extends UserDtoRequest {
	 private String professionParent;
    private long studentID;
    private String relationTuteur;
	 private String typeParent;
	public long getStudentID() {
		return studentID;
	}
	public void setStudentID(long studentID) {
		this.studentID = studentID;
	}
	
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
	public ParentDtoRequest(Long id, Long structureID, String prenom, String name_logo, String url_logo, String nom,
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
	
	
}
