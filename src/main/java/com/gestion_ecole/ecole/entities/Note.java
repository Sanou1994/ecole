package com.gestion_ecole.ecole.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="Note")
public class Note {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
    private Student student;
	@ManyToOne
    private Personnal personnal;
	public Note(Long id, Student student, Personnal personnal) {
		super();
		this.id = id;
		this.student = student;
		this.personnal = personnal;
	}
	public Note() {
		super();
	}
	
}
