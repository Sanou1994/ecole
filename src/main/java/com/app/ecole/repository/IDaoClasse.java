package com.app.ecole.repository;
import com.app.ecole.entities.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoClasse extends JpaRepository<Classe, UUID> {
    Optional<Classe> findByNomIgnoreCase(String nom);
    Optional<Classe> findByID(UUID id);
    List<Classe> findByStatus(Boolean status);

    List<Classe> findByClasseMereAndStatus(UUID id,Boolean status);
}
