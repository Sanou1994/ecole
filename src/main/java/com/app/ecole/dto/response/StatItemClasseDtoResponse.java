package com.app.ecole.dto.response;

import com.app.ecole.entities.Classe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor @Data
public class StatItemClasseDtoResponse {
	private ClasseDtoResponse classe;
	private Long total;
	private Double recette;

}

