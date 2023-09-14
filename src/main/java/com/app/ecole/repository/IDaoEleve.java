package com.app.ecole.repository;
import com.app.ecole.entities.Eleve;
import com.app.ecole.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoEleve extends JpaRepository<Eleve, UUID> {
Optional<Eleve> findByEmail(String email);
    @Query("SELECT u FROM Eleve u WHERE (u.email = ?1 and u.id != ?3) or (u.telephone = ?2 and u.id != ?3) ")
    Optional<Eleve> getUserByEmailAndTelephoneAndId(String email, String telephone, UUID id);
    @Query("SELECT u FROM Eleve u WHERE u.email = ?1 or u.telephone = ?2")
    Optional<Eleve> getUserByEmailOrTelephone(String email, String telephone);
    List<Eleve>findByStatus(Boolean status);

    Optional<Eleve>findById(UUID id);
    List<Eleve> findByMatriculeContainingIgnoreCaseOrNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseAndStatus(String el2,String el,String el1,Boolean status);

    List<Eleve> findByMatriculeContainingIgnoreCaseOrNomContainingIgnoreCaseAndStatus(String el,String el1,Boolean status);

}
