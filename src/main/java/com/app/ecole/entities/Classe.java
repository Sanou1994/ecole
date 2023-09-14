package com.app.ecole.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity @AllArgsConstructor
@NoArgsConstructor @Data
public class Classe {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID ID;
	private String nom;
	private float scolariteAnnuelle;
	private Boolean status=true;
    private UUID classeMere ;
	private String groupe;
	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
	private Date updatedOn;

}
