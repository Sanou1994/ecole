package com.app.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoisPaiement_TypePaiementDtoRequest
{
	private UUID typePaiementID;
	private String dernierMoisPaye;
	private List<String> mois_paiement = new ArrayList<>();


}
