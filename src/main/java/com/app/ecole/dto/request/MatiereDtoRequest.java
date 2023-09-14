package com.app.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatiereDtoRequest
{
	private UUID ID;
	private String nom;
	private float coeff;
	private boolean status;
	private UUID classeID;


}
