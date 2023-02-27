package com.gestion_ecole.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor @Data
public class SupportPhysiqueDtoRequest {
	private Long id;
    private String name;
	private String url;
	private String type;
	private long dateCreation;
	
}
