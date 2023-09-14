package com.app.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModePaiementDtoRequest
{
	private UUID ID;
	private String modePaiement;


}
