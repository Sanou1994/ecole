package com.app.ecole.service;

import com.app.ecole.dto.request.AnneeDtoRequest;
import com.app.ecole.dto.request.BanqueDtoRequest;
import com.app.ecole.dto.response.AnneeDtoResponse;
import com.app.ecole.dto.response.BanqueDtoResponse;
import com.app.ecole.entities.Annee;
import com.app.ecole.entities.Banque;
import com.app.ecole.entities.Reponse;
import com.app.ecole.repository.IDaoAnnee;
import com.app.ecole.repository.IDaoBanque;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AnneeService implements ICrudService <AnneeDtoRequest> {
    @Autowired
    private IDaoAnnee daoAnnee;
    @Autowired(required=true)
    private ModelMapper modelMapper;

    @Override
    public Reponse create(AnneeDtoRequest anneeDtoRequest) {
        Reponse reponse = new Reponse();
        try
        {
            if(anneeDtoRequest.getID() != null)
            {
                if(anneeDtoRequest.getNom() != null && anneeDtoRequest.getNom().length() != 0)
                {
                    Optional<Annee> updateGroupe=daoAnnee.findByNomIgnoreCase(anneeDtoRequest.getNom());
                    if(updateGroupe.isPresent() && !updateGroupe.get().getID().equals(anneeDtoRequest.getID()))
                    {
                        reponse.setMessage("Cette annee est déjà utilisé !");
                        reponse.setCode(201);
                    }
                    else {
                        Optional<Annee> isExitsGroupe= daoAnnee.findByID(anneeDtoRequest.getID());

                        if(isExitsGroupe.isPresent())
                        {

                            isExitsGroupe.get().setNom(anneeDtoRequest.getNom());
                            Annee groupe1 = daoAnnee.save(isExitsGroupe.get());
                            var groupeSave =modelMapper.map(groupe1, AnneeDtoResponse.class);
                            reponse.setMessage("L'annee a été modifié avec succès");
                            reponse.setResult(groupeSave);
                            reponse.setCode(200);


                        }
                        else
                        {

                            reponse.setMessage("Cette année n'existe plus !");
                            reponse.setCode(201);


                        }
                    }
                }
                else {
                    reponse.setMessage("Veuillez renseigner l'année !");
                    reponse.setCode(201);
                }
            }
            else
            {
                Optional<Annee>isExitsGroupe=daoAnnee.findByNomIgnoreCase(anneeDtoRequest.getNom());

                isExitsGroupe.ifPresentOrElse(
                        (userGot)->{
                            reponse.setMessage("Cette année est déjà utilisée !");
                            reponse.setCode(201);

                        },
                        ()->
                        {

                            var anneeEncours=daoAnnee.findByStatus(true);
                           if(anneeEncours.isPresent())
                           {
                               reponse.setMessage("une année scolaire est déjà encours");
                               reponse.setCode(201);
                           }
                           else {
                               anneeDtoRequest.setStatus(true);
                               var groupeConverted =modelMapper.map(anneeDtoRequest, Annee.class);
                               Annee groupe1 = daoAnnee.save(groupeConverted);
                               var groupeSave =modelMapper.map(groupe1, AnneeDtoResponse.class);
                               reponse.setMessage("L'année a été enregistré avec succès");
                               reponse.setResult(groupeSave);
                               reponse.setCode(200);
                           }




                        }


                );
            }
        }
        catch (Exception e)
        {
            reponse.setCode(500);
            reponse.setMessage("Une erreur interne est survenue coté serveur  !" );
        }

        return reponse ;
    }

    @Override
    public Reponse getAll() {
        Reponse reponse = new Reponse();
        try
        {

            List<AnneeDtoResponse> users= daoAnnee.findAll()
                    .stream()
                    .map(p->modelMapper.map(p,AnneeDtoResponse.class)

                    ).collect(Collectors.toList());

            reponse.setResult(users);
            reponse.setMessage("Liste des années !");
            reponse.setCode(200);



        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue");
            reponse.setCode(500);

        }

        return reponse;
    }

    @Override
    public Reponse getById(UUID id) {
        Reponse reponse = new Reponse();
        try
        {
            Optional<Annee> IsExistGroupe=daoAnnee.findById(id);
            IsExistGroupe.ifPresentOrElse(
                    (userP)->{

                        var result=modelMapper.map(IsExistGroupe.get(), AnneeDtoResponse.class);
                        reponse.setResult(result);
                        reponse.setMessage("L'année a été retrouvée avec succès");
                        reponse.setCode(200);


                    },
                    ()->{reponse.setMessage("L'année n'existe pas");
                        reponse.setCode(201);}
            );



        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue");
            reponse.setCode(500);

        }

        return reponse;
    }

    @Override
    public Reponse disableById(UUID id) {
        Reponse reponse = new Reponse();
        try
        {
            Optional<Annee> IsExistGroupe=daoAnnee.findById(id);

            if(IsExistGroupe.isPresent())
            {
                IsExistGroupe.get().setStatus(!IsExistGroupe.get().isStatus());
                Annee userSave=daoAnnee.save( IsExistGroupe.get());
                reponse.setResult(modelMapper.map(userSave, AnneeDtoResponse.class));
                reponse.setMessage("Cette année a été bloqué avec succès");
                reponse.setCode(200);

            }
            else
            {
                reponse.setMessage("Cette année n'existe pas");
                reponse.setCode(201);

            }


        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue");
            reponse.setCode(500);

        }

        return reponse;
    }
}
