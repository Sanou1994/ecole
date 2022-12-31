package com.gestion_ecole.ecole.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity 
@Table(name="Inscription")
public class Inscription {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id; 
	@ManyToOne
    private Teacher teacher;
	@ManyToOne
    private Student student;
	@ManyToOne
    private Personnal personnal;
	@OneToMany(orphanRemoval = true,targetEntity=SupportPysique.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<SupportPysique> supportPysiques = new ArrayList<SupportPysique>();
	
	
	public Inscription() {
		super();
	}
	public Inscription(Long id, Teacher teacher, Student student, Personnal personnal,
			List<SupportPysique> supportPysiques) {
		super();
		this.id = id;
		this.teacher = teacher;
		this.student = student;
		this.personnal = personnal;
		this.supportPysiques = supportPysiques;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Personnal getPersonnal() {
		return personnal;
	}
	public void setPersonnal(Personnal personnal) {
		this.personnal = personnal;
	}
	public List<SupportPysique> getSupportPysiques() {
		return supportPysiques;
	}
	public void setSupportPysiques(List<SupportPysique> supportPysiques) {
		this.supportPysiques = supportPysiques;
	}
	
	
	
}
