package com.app.ecole.dto.response;

import com.app.ecole.entities.Annee;
import com.app.ecole.entities.Eleve;
import com.app.ecole.entities.ServiceImpayesItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceImpayesResponse {
    private UUID ID;
    private UUID eleveID;
    private Eleve eleve;
    private Annee anneeScolaire;
    private List<Service_Mois> mes_services = new ArrayList<>();
    private boolean status;
    private Date createdOn;
    private Date updatedOn;
}
