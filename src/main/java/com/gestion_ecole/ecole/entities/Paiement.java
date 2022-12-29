package com.gestion_ecole.ecole.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity 
public class Paiement {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User user;
	private long datePaiement;
	private String order_number;
	private double montant;
	private String typeTransaction;
	private String status;
	@ManyToOne
	private Departement departement;
	
	public Paiement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Paiement(Long id, User user, long datePaiement, String order_number, double montant, String typeTransaction,
			String status, Departement departement) {
		super();
		this.id = id;
		this.user = user;
		this.datePaiement = datePaiement;
		this.order_number = order_number;
		this.montant = montant;
		this.typeTransaction = typeTransaction;
		this.status = status;
		this.departement = departement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(long datePaiement) {
		this.datePaiement = datePaiement;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getTypeTransaction() {
		return typeTransaction;
	}

	public void setTypeTransaction(String typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	
}
