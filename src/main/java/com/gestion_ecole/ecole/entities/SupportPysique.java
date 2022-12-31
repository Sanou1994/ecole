package com.gestion_ecole.ecole.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class SupportPysique {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
	private String url;
	private long dateCreation;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(long dateCreation) {
		this.dateCreation = dateCreation;
	}
	public SupportPysique(Long id, String name, String url, long dateCreation) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.dateCreation = dateCreation;
	}
	public SupportPysique() {
		super();
	}
	
	
}
