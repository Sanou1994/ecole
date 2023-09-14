package com.app.ecole.repository;
import com.app.ecole.entities.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoMatiere extends JpaRepository<Matiere, UUID> {
    Optional<Matiere> findByNomIgnoreCase(String nom);
    Optional<Matiere> findByID(UUID id);
    List<Matiere> findByStatus(Boolean status);


}
