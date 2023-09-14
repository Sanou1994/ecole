package com.app.ecole.dto.request;

import com.app.ecole.entities.Eleve;
import com.app.ecole.entities.Matiere;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasseDtoRequest
{
	private UUID ID;
	private UUID classeMere ;
	private String nom;
	private String groupe;
	private float scolariteAnnuelle;
	private boolean status;

}
