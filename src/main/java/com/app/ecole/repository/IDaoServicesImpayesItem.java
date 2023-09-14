package com.app.ecole.repository;
import com.app.ecole.entities.ServiceImpayesItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface IDaoServicesImpayesItem extends JpaRepository<ServiceImpayesItem, UUID> {


}
