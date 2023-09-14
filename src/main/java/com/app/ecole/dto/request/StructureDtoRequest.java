package com.app.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StructureDtoRequest
{
	private UUID ID;
	private String nom;
	private String telephone;
	private String email;
	private String url_logo;
	private String logo;
	private String url_favicon;
	private String favicon;
	private String pays;
	private String adresse;

}
