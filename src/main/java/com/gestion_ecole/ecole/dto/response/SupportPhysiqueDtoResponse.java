package com.gestion_ecole.ecole.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor 
@NoArgsConstructor @Data
public class SupportPhysiqueDtoResponse {
	private Long id;
    private String name;
	private String url;
	private String type;
	private long dateCreation;
	public SupportPhysiqueDtoResponse(String name, String url, String type, long dateCreation) {
		super();
		this.name = name;
		this.url = url;
		this.type = type;
		this.dateCreation = dateCreation;
	}
	
	
}
