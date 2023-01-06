package com.gestion_ecole.ecole.dto.response;

import java.io.Serializable;
import java.util.List;

import com.gestion_ecole.ecole.entities.Classe;
import com.gestion_ecole.ecole.entities.Departement;
import com.gestion_ecole.ecole.entities.Inscription;
import com.gestion_ecole.ecole.entities.Seance;
import com.gestion_ecole.ecole.entities.Student;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor @NoArgsConstructor
public class TeacherDtoResponse extends UserDtoResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Inscription> inscriptions ;
	private List<Student> students ;
private List<Classe> classes ;
private List<Seance> seances ;
private Classe classe;
private Student student;
private Departement departement;

public List<Inscription> getInscriptions() {
	return inscriptions;
}

public void setInscriptions(List<Inscription> inscriptions) {
	this.inscriptions = inscriptions;
}

public List<Student> getStudents() {
	return students;
}

public void setStudents(List<Student> students) {
	this.students = students;
}

public List<Classe> getClasses() {
	return classes;
}

public void setClasses(List<Classe> classes) {
	this.classes = classes;
}

public List<Seance> getSceances() {
	return seances;
}

public void setSceances(List<Seance> seances) {
	this.seances = seances;
}

public Classe getClasse() {
	return classe;
}

public void setClasse(Classe classe) {
	this.classe = classe;
}

public Student getStudent() {
	return student;
}

public void setStudent(Student student) {
	this.student = student;
}

public Departement getDepartement() {
	return departement;
}

public void setDepartement(Departement departement) {
	this.departement = departement;
}
	
}
