package com.app.ecole.repository;
import com.app.ecole.entities.Inscription;
import com.app.ecole.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDaoPaiement extends JpaRepository<Paiement, UUID> {
    Optional<Paiement> findByID(UUID id);
    List<Paiement> findByIdEleveAndStatus(UUID id,boolean status);
    List<Paiement> findByStatus(boolean status);

    List<Paiement> findByIdAnneeScolaireAndStatus(UUID id,boolean status);;

    List<Paiement> findByIdEleveAndIdAnneeScolaireAndStatus(UUID id, UUID inscriptionID,boolean status);
    List<Paiement> findAllByCreatedOnBetweenAndStatus(Date dateDebut, Date dateFin,boolean status);
    List<Paiement> findAllByCreatedOnBetweenAndIdAnneeScolaireAndStatus(Date dateDebut, Date dateFin, UUID anneID,boolean status);


    List<Paiement> findAllByCreatedOnBetweenAndIdCaissierAndStatus(Date dateDebut, Date dateFin, UUID caissierID,boolean statuss);

}
