package com.app.ecole.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModePaiement_PaiementResponse
{
    private UUID ID;
    private TypePaiementDtoResponse typePaiement;
    private String dernierMoisPaye;
    private List<String> mois_paiement = new ArrayList<>();
    private boolean status;
    private Date createdOn;
    private Date updatedOn;
}
