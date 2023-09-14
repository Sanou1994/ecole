package com.app.ecole.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor @Data
public class AnneeDtoResponse {
	private UUID ID;
	private String nom;
	private boolean status;
	private Date createdOn;
	private Date updatedOn;
}

