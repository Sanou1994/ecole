package com.app.ecole.repository;
import com.app.ecole.entities.Annee;
import com.app.ecole.entities.Solde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoSolde extends JpaRepository<Solde, UUID> {
    Optional<Solde> findByID(UUID id);
    Optional<Solde> findByEleveIDAndAnneeScolaireID(UUID id,UUID anneeScolaire);
    List<Solde> findByEleveID(UUID id);


}
