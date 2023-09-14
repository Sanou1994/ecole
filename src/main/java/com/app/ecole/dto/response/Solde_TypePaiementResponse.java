package com.app.ecole.dto.response;

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
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Solde_TypePaiementResponse {
    private UUID ID;
    private TypePaiementDtoResponse  typePaiement;
    private Float montantRestant;
    private String etat;
    private boolean status;
    private boolean isAnnuelle;
    private UUID dernierPaiementID;
    private List<String> mois_payes = new ArrayList<>();
    private String dernierMoisPaye;
    private String avantDernierMoisPaye;
    private Date createdOn;
    private Date updatedOn;
}
