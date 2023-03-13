package com.gestion_ecole.ecole.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.LancerPreinscription;

public interface LancerPreinscriptionRepository extends JpaRepository<LancerPreinscription, Long> {
	Optional<LancerPreinscription> findByDepartementIDAndAnneeScolaireIDAndFiliereIDAndClasseIDAndDatePrologementAndDateDebutAndDateFin(long departementID,long anneeScolaireID,long filiereID,long classeID,long datePrologement,long dateDebut,long dateFin);
	List<LancerPreinscription> findByStructureID(long id);
}
