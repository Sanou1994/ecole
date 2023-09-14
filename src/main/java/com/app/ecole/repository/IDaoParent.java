package com.app.ecole.repository;
import com.app.ecole.entities.Parent;
import com.app.ecole.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoParent extends JpaRepository<Parent, UUID> {
Optional<Utilisateur> findByEmail(String email);
    @Query("SELECT u FROM Parent u WHERE (u.email = ?1 and u.id != ?3) or (u.telephone = ?2 and u.id != ?3) ")
    Optional<Parent> getUserByEmailAndTelephoneAndId(String email,String telephone, UUID id);
    @Query("SELECT u FROM Utilisateur u WHERE u.email = ?1 or u.telephone = ?2")
    Optional<Parent> getUserByEmailOrTelephone(String email, String telephone);
    List<Parent> findByStatus(Boolean status);

Optional<Parent>findById(UUID id);

}
