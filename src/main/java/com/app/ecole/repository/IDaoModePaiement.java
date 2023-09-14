package com.app.ecole.repository;
import com.app.ecole.entities.Groupe;
import com.app.ecole.entities.Mode_Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoModePaiement extends JpaRepository<Mode_Paiement, UUID> {
    Optional<Mode_Paiement> findByModePaiementIgnoreCase(String nom);
    Optional<Mode_Paiement> findByID(UUID id);
    List<Mode_Paiement> findByStatus(Boolean status);


}
