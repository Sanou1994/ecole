package com.gestion_ecole.ecole.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.gestion_ecole.ecole.entities.Absence;
import com.gestion_ecole.ecole.entities.Filiere;
import com.gestion_ecole.ecole.entities.Note;
import com.gestion_ecole.ecole.entities.Paiement;
import com.gestion_ecole.ecole.entities.Seance;
import com.gestion_ecole.ecole.entities.SupportPysique;
public class PersonnalDtoRequest  extends UserDtoRequest{

		    
			private Filiere filiere;
			private List<Note> notes = new ArrayList<Note>();
			
			
			
			public PersonnalDtoRequest() {
				super();
				// TODO Auto-generated constructor stub
			}		
	
	
	public PersonnalDtoRequest(Long id, Long structureID, String prenom, String nom, String adresse,
					String numeroMatriciule, String typeDeRecrutement, String typeUser, String type, String naissance,
					String sexe, String nationalite, long dateCreation, boolean status, String lieu_naissance,
					String email, String telephone, String monToken, String password, String compteBancaire,
					String resetPasswordToken, String role, List<Absence> absences,
					List<SupportPysique> supportPysiques, List<Paiement> paiements, List<Seance> seances) {
				super(id, structureID, prenom, nom, adresse, numeroMatriciule, typeDeRecrutement, typeUser, type, naissance, sexe,
						nationalite, dateCreation, status, lieu_naissance, email, telephone, monToken, password, compteBancaire,
						resetPasswordToken, role, absences, supportPysiques, paiements, seances);
				// TODO Auto-generated constructor stub
			}


	public Filiere getFiliere() {
				return filiere;
			}
			public void setFiliere(Filiere filiere) {
				this.filiere = filiere;
			}
			public List<Note> getNotes() {
				return notes;
			}
			public void setNotes(List<Note> notes) {
				this.notes = notes;
			}
			
			
			
}
