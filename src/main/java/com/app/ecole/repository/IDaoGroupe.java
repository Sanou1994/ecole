package com.app.ecole.repository;
import com.app.ecole.entities.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoGroupe extends JpaRepository<Groupe, UUID> {
    Optional<Groupe> findByNomGroupeIgnoreCase(String nom);
    Optional<Groupe> findByID(UUID id);
    List<Groupe> findByStatus(Boolean status);


}
