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

@Entity
@AllArgsConstructor
@NoArgsConstructor @Data
public class Type_Paiement
{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private UUID ID;
	private String typePaiement;
	private float montant_mensuel;
	private Boolean estObligatoire =false;
	private Boolean estScolarite=false;
	private float montant_annuel;
	private boolean status=true;
	private boolean estAnnuelle=false;
	private UUID classeMereID;
	@ElementCollection(targetClass = UUID.class, fetch = FetchType.EAGER)
	private List<UUID> reductions = new ArrayList<>();
	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
	private Date updatedOn;


}
