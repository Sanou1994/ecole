package com.app.ecole.service;

import com.app.ecole.dto.request.GroupeDtoRequest;
import com.app.ecole.dto.response.GroupeDtoResponse;
import com.app.ecole.dto.response.UserDtoResponse;
import com.app.ecole.entities.Groupe;
import com.app.ecole.entities.Reponse;
import com.app.ecole.repository.IDaoGroupe;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class GroupeService implements ICrudService <GroupeDtoRequest> {
    @Autowired
    private IDaoGroupe daoGroupe;
    @Autowired(required=true)
    private ModelMapper modelMapper;


    @Override
    public Reponse create(GroupeDtoRequest groupe) {
        Reponse reponse = new Reponse();
        try
        {
            if(groupe.getID() != null)
            {
                if(groupe.getNomGroupe() != null && groupe.getNomGroupe().length() != 0)
                {
                    Optional<Groupe> updateGroupe=daoGroupe.findByNomGroupeIgnoreCase(groupe.getNomGroupe());
                    if(updateGroupe.isPresent() && !updateGroupe.get().getID().equals(groupe.getID()))
                    {
                        reponse.setMessage("Cet groupe est déjà utilisé !");
                        reponse.setCode(201);
                    }
                    else {
                        Optional<Groupe> isExitsGroupe= daoGroupe.findByID(groupe.getID());

                        if(isExitsGroupe.isPresent())
                        {

                            isExitsGroupe.get().setNomGroupe(groupe.getNomGroupe());
                            Groupe groupe1 = daoGroupe.save(isExitsGroupe.get());
                            var groupeSave =modelMapper.map(groupe1, GroupeDtoResponse.class);
                            reponse.setMessage("Le groupe a été modifié avec succès");
                            reponse.setResult(groupeSave);
                            reponse.setCode(200);


                        }
                        else
                        {

                            reponse.setMessage("Cet groupe n'existe plus !");
                            reponse.setCode(201);


                        }
                    }
                }
                else {
                    reponse.setMessage("Veuillez renseigner le groupe !");
                    reponse.setCode(201);
                }
            }
            else
            {
                Optional<Groupe>isExitsGroupe=daoGroupe.findByNomGroupeIgnoreCase(groupe.getNomGroupe());

                isExitsGroupe.ifPresentOrElse(
                        (userGot)->{
                            reponse.setMessage("Cet groupe est déjà utilisé !");
                            reponse.setCode(201);

                        },
                        ()->
                        {

                            var groupeConverted =modelMapper.map(groupe, Groupe.class);
                            groupeConverted.setStatus(true);
                            Groupe groupe1 = daoGroupe.save(groupeConverted);
                            var groupeSave =modelMapper.map(groupe1, GroupeDtoResponse.class);
                            reponse.setMessage("Le groupe a été enregistré avec succès");
                            reponse.setResult(groupeSave);
                            reponse.setCode(200);

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

            List< GroupeDtoResponse> users= daoGroupe.findByStatus(true)
                    .stream()
                    .map(p->modelMapper.map(p,GroupeDtoResponse.class)

                    ).collect(Collectors.toList());

            reponse.setResult(users);
            reponse.setMessage("Liste des groupes !");
            reponse.setCode(200);



        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue" +e.getMessage());
            reponse.setCode(500);

        }

        return reponse;
    }

    @Override
    public Reponse getById(UUID id) {
        Reponse reponse = new Reponse();
        try
        {
            Optional<Groupe> IsExistGroupe=daoGroupe.findById(id);
            IsExistGroupe.ifPresentOrElse(
                    (userP)->{

                        var result=modelMapper.map(IsExistGroupe.get(), GroupeDtoResponse.class);
                        reponse.setResult(result);
                        reponse.setMessage("Le groupe a été retrouvé avec succès");
                        reponse.setCode(200);


                    },
                    ()->{reponse.setMessage("Ce compte n'existe pas");
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
            Optional<Groupe> IsExistGroupe=daoGroupe.findById(id);

            if(IsExistGroupe.isPresent())
            {
                IsExistGroupe.get().setStatus(!IsExistGroupe.get().isStatus());
                Groupe userSave=daoGroupe.save( IsExistGroupe.get());
                reponse.setResult(modelMapper.map(userSave, GroupeDtoResponse.class));
                reponse.setMessage("Ce groupe a été bloqué avec succès");
                reponse.setCode(200);

            }
            else
            {
                reponse.setMessage("Ce groupe n'existe pas");
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
