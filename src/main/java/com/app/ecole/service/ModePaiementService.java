package com.app.ecole.service;

import com.app.ecole.dto.request.GroupeDtoRequest;
import com.app.ecole.dto.request.ModePaiementDtoRequest;
import com.app.ecole.dto.response.GroupeDtoResponse;
import com.app.ecole.dto.response.ModePaiementDtoResponse;
import com.app.ecole.entities.Groupe;
import com.app.ecole.entities.Mode_Paiement;
import com.app.ecole.entities.Reponse;
import com.app.ecole.repository.IDaoGroupe;
import com.app.ecole.repository.IDaoModePaiement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModePaiementService implements ICrudService <ModePaiementDtoRequest> {
    @Autowired
    private IDaoModePaiement daoModePaiement;
    @Autowired(required=true)
    private ModelMapper modelMapper;

    @Override
    public Reponse create(ModePaiementDtoRequest groupe) {
        Reponse reponse = new Reponse();
        try
        {
            if(groupe.getID() != null)
            {
                if(groupe.getModePaiement() != null && groupe.getModePaiement().length() != 0)
                {


                        Optional<Mode_Paiement> isExitsGroupe= daoModePaiement.findByID(groupe.getID());

                        if(isExitsGroupe.isPresent())
                        {

                            isExitsGroupe.get().setModePaiement(groupe.getModePaiement());
                            Mode_Paiement mode_Paiement = daoModePaiement.save(isExitsGroupe.get());
                            var groupeSave =modelMapper.map(mode_Paiement, ModePaiementDtoResponse.class);
                            reponse.setMessage("Le mode de paiement a été modifié avec succès");
                            reponse.setResult(groupeSave);
                            reponse.setCode(200);


                        }
                        else
                        {

                            reponse.setMessage("Cet mode de paiement n'existe plus !");
                            reponse.setCode(201);


                        }

                }
                else {
                    reponse.setMessage("Veuillez renseigner le mode de paiement !");
                    reponse.setCode(201);
                }
            }
            else {


                            var groupeConverted =modelMapper.map(groupe, Mode_Paiement.class);
                            groupeConverted.setStatus(true);
                            Mode_Paiement groupe1 = daoModePaiement.save(groupeConverted);
                            var groupeSave =modelMapper.map(groupe1, ModePaiementDtoResponse.class);
                            reponse.setMessage("Le mode de paiement a été enregistré avec succès");
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

            List<ModePaiementDtoResponse> users= daoModePaiement.findByStatus(true)
                    .stream()
                    .map(p->modelMapper.map(p,ModePaiementDtoResponse.class)

                    ).collect(Collectors.toList());

            reponse.setResult(users);
            reponse.setMessage("Liste des modes de paiement !");
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
            Optional<Mode_Paiement> IsExistGroupe=daoModePaiement.findById(id);
            IsExistGroupe.ifPresentOrElse(
                    (userP)->{

                        var result=modelMapper.map(IsExistGroupe.get(), ModePaiementDtoResponse.class);
                        reponse.setResult(result);
                        reponse.setMessage("Le mode de paiement a été retrouvé avec succès");
                        reponse.setCode(200);


                    },
                    ()->{reponse.setMessage("Ce mode de paiement n'existe pas");
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
            Optional<Mode_Paiement> IsExistGroupe=daoModePaiement.findById(id);

            if(IsExistGroupe.isPresent())
            {
                IsExistGroupe.get().setStatus(!IsExistGroupe.get().getStatus());
                Mode_Paiement userSave=daoModePaiement.save( IsExistGroupe.get());
                reponse.setResult(modelMapper.map(userSave, ModePaiementDtoResponse.class));
                reponse.setMessage("Ce mode de paiement a été bloqué avec succès");
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
