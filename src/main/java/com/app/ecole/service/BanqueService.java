package com.app.ecole.service;

import com.app.ecole.dto.request.BanqueDtoRequest;
import com.app.ecole.dto.request.GroupeDtoRequest;
import com.app.ecole.dto.response.BanqueDtoResponse;
import com.app.ecole.dto.response.GroupeDtoResponse;
import com.app.ecole.entities.Banque;
import com.app.ecole.entities.Groupe;
import com.app.ecole.entities.Reponse;
import com.app.ecole.repository.IDaoBanque;
import com.app.ecole.repository.IDaoGroupe;
import com.app.ecole.repository.IDaoUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BanqueService implements ICrudService <BanqueDtoRequest> {
    @Autowired
    private IDaoBanque daoBanque;
    @Autowired
    private IDaoUser daoUser;
    @Autowired(required=true)
    private ModelMapper modelMapper;

    @Override
    public Reponse create(BanqueDtoRequest banqueDtoRequest) {
        Reponse reponse = new Reponse();
        try
        {


            if(banqueDtoRequest.getID() != null)
            {


                        Optional<Banque> isExitsGroupe= daoBanque.findByID(banqueDtoRequest.getID());

                        if(isExitsGroupe.isPresent())
                        {
                            if(banqueDtoRequest.getNom() != null && banqueDtoRequest.getNom().length() != 0)
                            {
                                isExitsGroupe.get().setNom(banqueDtoRequest.getNom());
                            }
                            if(banqueDtoRequest.getNom_verseur() != null && banqueDtoRequest.getNom_verseur().length() != 0)
                            {
                                isExitsGroupe.get().setNom_verseur(banqueDtoRequest.getNom_verseur());
                            }

                            if(banqueDtoRequest.getMontant() != null && banqueDtoRequest.getMontant().floatValue() > 0)
                            {
                                isExitsGroupe.get().setMontant(banqueDtoRequest.getMontant());
                            }
                            if(banqueDtoRequest.getNumeroPaiment() != null && banqueDtoRequest.getNumeroPaiment().length() != 0)
                            {
                                isExitsGroupe.get().setNumeroPaiment(banqueDtoRequest.getNumeroPaiment());
                            }
                            if(banqueDtoRequest.getDateVersement() != null && banqueDtoRequest.getDateVersement().length() != 0)
                            {
                                isExitsGroupe.get().setDateVersement(banqueDtoRequest.getDateVersement());
                            }
                            if(banqueDtoRequest.getEnregistreurID() != null && banqueDtoRequest.getEnregistreurID().compareTo(isExitsGroupe.get().getEnregistreurID()) != 0)
                            {
                                isExitsGroupe.get().setEnregistreurID(banqueDtoRequest.getEnregistreurID());
                            }
                            Banque groupe1 = daoBanque.save(isExitsGroupe.get());
                            var groupeSave =modelMapper.map(groupe1, BanqueDtoResponse.class);

                            if(groupe1.getEnregistreurID() != null)
                            {
                                daoUser.findById(groupe1.getEnregistreurID()).ifPresent(c->groupeSave.setEnregistreur(c) );
                            }


                            reponse.setMessage("La banque a été modifié avec succès");
                            reponse.setResult(groupeSave);
                            reponse.setCode(200);


                        }
                        else
                        {

                            reponse.setMessage("Cette banque n'existe plus !");
                            reponse.setCode(201);


                        }


            }
            else
            {


                            var groupeConverted =modelMapper.map(banqueDtoRequest, Banque.class);
                             groupeConverted.setStatus(true);
                            Banque groupe1 = daoBanque.save(groupeConverted);
                            var groupeSave =modelMapper.map(groupe1, BanqueDtoResponse.class);
                            if(groupe1.getEnregistreurID() != null)
                            {
                                daoUser.findById(groupe1.getEnregistreurID()).ifPresent(c->groupeSave.setEnregistreur(c) );
                            }
                            reponse.setMessage("La banque a été enregistré avec succès");
                            reponse.setResult(groupeSave);
                            reponse.setCode(200);




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

            List< BanqueDtoResponse> users= daoBanque.findByStatus(true)
                    .stream()
                    .map(p->{
                               var user=   modelMapper.map(p,BanqueDtoResponse.class);
                                if(p.getEnregistreurID() != null)
                                {
                                    daoUser.findById(p.getEnregistreurID()).ifPresent(c->user.setEnregistreur(c) );
                                }
                                return  user;
                    }

                    ).collect(Collectors.toList());

            reponse.setResult(users);
            reponse.setMessage("Liste des banques !");
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
            Optional<Banque> IsExistGroupe=daoBanque.findById(id);
            IsExistGroupe.ifPresentOrElse(
                    (userP)->{

                        var result=modelMapper.map(userP, BanqueDtoResponse.class);
                        if(userP.getEnregistreurID() != null)
                        {
                            daoUser.findById(userP.getEnregistreurID()).ifPresent(c->result.setEnregistreur(c) );
                        }
                        reponse.setResult(result);
                        reponse.setMessage("La banque a été retrouvée avec succès");
                        reponse.setCode(200);


                    },
                    ()->{reponse.setMessage("La banque n'existe pas");
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
            Optional<Banque> IsExistGroupe=daoBanque.findById(id);

            if(IsExistGroupe.isPresent())
            {
                IsExistGroupe.get().setStatus(!IsExistGroupe.get().isStatus());
                Banque userSave=daoBanque.save( IsExistGroupe.get());
                reponse.setResult(modelMapper.map(userSave, BanqueDtoResponse.class));
                reponse.setMessage("Cette banque a été bloqué avec succès");
                reponse.setCode(200);

            }
            else
            {
                reponse.setMessage("Cette banque n'existe pas");
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
