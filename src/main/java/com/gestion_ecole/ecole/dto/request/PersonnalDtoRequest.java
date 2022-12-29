package com.gestion_ecole.ecole.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.gestion_ecole.ecole.entities.Filiere;
import com.gestion_ecole.ecole.entities.Note;
import com.gestion_ecole.ecole.entities.Sceance;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
 @AllArgsConstructor @NoArgsConstructor
public class PersonnalDtoRequest  extends UserDtoRequest{
		    private Filiere filiere;
			private List<Note> notes = new ArrayList<Note>();
			private List<Sceance> sceances = new ArrayList<Sceance>();
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
			public List<Sceance> getSceances() {
				return sceances;
			}
			public void setSceances(List<Sceance> sceances) {
				this.sceances = sceances;
			}	
			
}
