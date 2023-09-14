package com.app.ecole.repository;
import com.app.ecole.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoInscription extends JpaRepository<Inscription, UUID> {
    Optional<Inscription> findByID(UUID id);
    List<Inscription> findByEleveID(UUID id);
    List<Inscription> findAllByCreatedOnBetween(Date dateDebut,Date dateFin);
    List<Inscription> findByAnneeScolaireID(UUID anneID);
    List<Inscription> findAllByCreatedOnBetweenAndAnneeScolaireID(Date dateDebut, Date dateFin, UUID anneID);

    List<Inscription> findByClasseID(UUID id);
    Optional<Inscription> findByEleveIDAndAnneeScolaireID(UUID id,UUID anneID);
}
