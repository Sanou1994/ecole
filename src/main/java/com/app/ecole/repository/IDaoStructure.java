package com.app.ecole.repository;
import com.app.ecole.entities.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoStructure extends JpaRepository<Structure, UUID> {
    Optional<Structure> findByID(UUID id);
    Optional<Structure> findByStatus(boolean status);


}
