package com.app.ecole.repository;
import com.app.ecole.entities.Parent;
import com.app.ecole.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoUser extends JpaRepository<Utilisateur, UUID> {
Optional<Utilisateur> findByEmail(String email);
    @Query("SELECT u FROM Utilisateur u WHERE (u.email = ?1 and u.id != ?3) or (u.telephone = ?2 and u.id != ?3) ")
    Optional<Utilisateur> getUserByEmailAndTelephoneAndId(String email, String telephone, UUID id);
    @Query("SELECT u FROM Utilisateur u WHERE u.email = ?1 or u.telephone = ?2")
    Optional<Utilisateur> getUserByEmailOrTelephone(String email, String telephone);
    Optional<Utilisateur>findByStatus(Boolean status);

    Optional<Utilisateur>findById(UUID id);

}
