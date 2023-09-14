package com.app.ecole.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor @Data
public class ReductionDtoResponse {
	private UUID ID;
	private String critere;
	private boolean status;
	private Float taux_reduction;
	private Date createdOn;
	private Date updatedOn;
}

