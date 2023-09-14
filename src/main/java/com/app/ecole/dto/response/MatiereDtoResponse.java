package com.app.ecole.dto.response;

import com.app.ecole.entities.Classe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor @Data
public class MatiereDtoResponse {
	private UUID ID;
	private String nom;
	private float coeff;
	private boolean status;
	private Classe classe;
	private Date createdOn;
	private Date updatedOn;
}

