package com.app.ecole.repository;
import com.app.ecole.entities.Annee;
import com.app.ecole.entities.ServiceImpayes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoServiceImpayes extends JpaRepository<ServiceImpayes, UUID> {

    Optional<ServiceImpayes> findByAnneeScolaireIDAndEleveID(UUID anneeScolaireID,UUID eleveID);


}
