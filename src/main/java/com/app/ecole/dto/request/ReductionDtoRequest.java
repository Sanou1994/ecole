package com.app.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReductionDtoRequest
{
	private UUID ID;
	private String critere;
	private Float taux_reduction;
}
