package com.app.ecole.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity @AllArgsConstructor
@NoArgsConstructor @Data
public class Groupe {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID ID;
	private String nomGroupe;
	private boolean status=true;
	@CreationTimestamp
	private Date createdOn;
	@UpdateTimestamp
	private Date updatedOn;


}
