package com.app.ecole.repository;
import com.app.ecole.entities.Reduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoReduction extends JpaRepository<Reduction, UUID> {
    Optional<Reduction> findByID(UUID id);
    List<Reduction> findByStatus(boolean status);


}
