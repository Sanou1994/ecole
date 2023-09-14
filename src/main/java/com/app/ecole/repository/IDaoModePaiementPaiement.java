package com.app.ecole.repository;
import com.app.ecole.entities.Annee;
import com.app.ecole.entities.ModePaiement_Paiement;
import com.app.ecole.entities.MoisPaiement_TypePaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoModePaiementPaiement extends JpaRepository<ModePaiement_Paiement, UUID> {
    Optional<ModePaiement_Paiement> findByID(UUID id);
    List<ModePaiement_Paiement> findByPaiementID(UUID id);


}
