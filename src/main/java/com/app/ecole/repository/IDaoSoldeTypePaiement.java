package com.app.ecole.repository;
import com.app.ecole.entities.Annee;
import com.app.ecole.entities.Solde_TypePaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoSoldeTypePaiement extends JpaRepository<Solde_TypePaiement, UUID> {
    Optional<Solde_TypePaiement> findByID(UUID id);
    List<Solde_TypePaiement> findByTypePaiement(UUID id);


}
