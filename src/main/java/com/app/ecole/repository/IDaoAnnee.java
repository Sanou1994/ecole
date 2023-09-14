package com.app.ecole.repository;
import com.app.ecole.entities.Annee;
import com.app.ecole.entities.Banque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoAnnee extends JpaRepository<Annee, UUID> {
    Optional<Annee> findByNomIgnoreCase(String nom);
    Optional<Annee> findByID(UUID id);
    Optional<Annee> findByStatus(boolean status);


}
