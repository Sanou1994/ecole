package com.app.ecole.entities;
import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity @AllArgsConstructor
@NoArgsConstructor @Data
public class Mode_Paiement
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID ID;
	private String modePaiement;
	@CreationTimestamp
	private Date createdOn;
    private Boolean status=true;
	@UpdateTimestamp
	private Date updatedOn;
}
