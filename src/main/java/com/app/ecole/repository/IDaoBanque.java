package com.app.ecole.repository;
import com.app.ecole.entities.Banque;
import com.app.ecole.entities.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoBanque extends JpaRepository<Banque, UUID> {
    Optional<Banque> findByID(UUID id);
    List<Banque> findByStatus(Boolean status);


}
