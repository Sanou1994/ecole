package com.app.ecole.dto.request;

import com.app.ecole.entities.Annee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionDtoRequest
{
	private UUID ID;
	private UUID eleveID;
	private UUID classeID;
	private boolean typeInscription;
	private String dateInscription;
	private float tauxReduction;
	private float montantReduction;
	private boolean status;


}
