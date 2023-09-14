package com.app.ecole.repository;
import com.app.ecole.entities.ModePaiement_Paiement;
import com.app.ecole.entities.MoisPaiement_TypePaiement;
import com.app.ecole.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoMoisPaiementTypePaiement extends JpaRepository<MoisPaiement_TypePaiement, UUID> {
    Optional<MoisPaiement_TypePaiement> findByID(UUID id);
    List<MoisPaiement_TypePaiement> findByPaiementID(UUID id);
    List<MoisPaiement_TypePaiement> findAllByCreatedOnBetween(Date dateDebut, Date dateFin);

    List<MoisPaiement_TypePaiement> findAllByCreatedOnBetweenAndIdAnneeScolaire(Date dateDebut, Date dateFin, UUID anneID);


}
