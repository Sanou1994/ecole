package com.app.ecole.dto.response;
import com.app.ecole.entities.Classe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor @Data
public class ClasseDtoResponse {
	private UUID ID;
	private String nom;
	private float scolariteAnnuelle;
	private String groupe;
	private Classe classeMere ;
	private boolean status;
	private List<Classe> sousClasse= new ArrayList<>();
	private Date createdOn;
	private Date updatedOn;
}

