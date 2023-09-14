package com.app.ecole.dto.response;

import com.app.ecole.entities.Reduction;
import com.app.ecole.entities.Type_Paiement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MoisPaiement_TypePaiementResponse
{
    private UUID ID;
    private TypePaiementDtoResponse typePaiement;
    private String dernierMoisPaye;
    private String avantDernierMoisPaye;
    private List<String> mois_paiement = new ArrayList<>();
    private boolean status;
    private Date createdOn;
    private Date updatedOn;
}
