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
public class Solde
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID ID;
	private UUID eleveID;
	private UUID anneeScolaireID;
	private Float credit=0f;
	private Float avant=0f;
	private Float credit_precedent=0f;
	private Float avant_precedent=0f;
	@OneToMany(fetch = FetchType.EAGER)
	private List<Solde_TypePaiement> servicesAbonnements = new ArrayList<>();
	private boolean status;
	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
	private Date updatedOn;
}
