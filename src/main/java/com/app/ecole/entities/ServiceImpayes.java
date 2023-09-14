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
public class ServiceImpayes
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID ID;
	private UUID eleveID;
	private UUID anneeScolaireID;
	@OneToMany(fetch = FetchType.EAGER)
	private List<ServiceImpayesItem> services = new ArrayList<>();
	private boolean status;
	@CreationTimestamp
	private Date createdOn;
	@UpdateTimestamp
	private Date updatedOn;
}
