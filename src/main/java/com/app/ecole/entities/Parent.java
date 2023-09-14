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
public class Parent {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID ID;
	private String type_parent;
	private String nom;
	private String prenom;
	private String fonction;
	private String email;
	private String telephone;
	private String password;
	private boolean status=true;
	private Integer nombre_enfants;
	@CreationTimestamp
	private Date createdOn;
	@UpdateTimestamp
	private Date updatedOn;




}
