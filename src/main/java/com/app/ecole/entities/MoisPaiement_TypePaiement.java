package com.app.ecole.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity @AllArgsConstructor
@NoArgsConstructor @Data
public class MoisPaiement_TypePaiement
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID ID;
	private UUID typePaiementID;
	private UUID idAnneeScolaire;
	private UUID paiementID;
	private String avantDernierMoisPaye;
	private String dernierMoisPaye;
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	private List<String> mois_paiement = new ArrayList<>();
	private boolean status;
	@CreationTimestamp
	private Date createdOn;
	@UpdateTimestamp
	private Date updatedOn;
}
