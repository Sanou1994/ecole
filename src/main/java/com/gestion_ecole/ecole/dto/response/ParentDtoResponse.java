package com.gestion_ecole.ecole.dto.response;

import java.util.List;

import com.gestion_ecole.ecole.entities.Student;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor @NoArgsConstructor
public class ParentDtoResponse extends UserDtoResponse{
	private List<Student> students ;

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	   
}
