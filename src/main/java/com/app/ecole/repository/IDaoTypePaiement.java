package com.app.ecole.repository;
import com.app.ecole.entities.Type_Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoTypePaiement extends JpaRepository<Type_Paiement, UUID> {
    Optional<Type_Paiement> findByTypePaiementIgnoreCase(String nom);
    Optional<Type_Paiement> findByID(UUID id);
    List<Type_Paiement> findByClasseMereIDAndStatus(UUID id,Boolean status);
    List<Type_Paiement> findByStatus(Boolean status);


}
