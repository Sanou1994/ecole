package com.app.ecole.service;

import com.app.ecole.dto.request.BanqueDtoRequest;
import com.app.ecole.dto.request.ParentDtoRequest;
import com.app.ecole.dto.response.BanqueDtoResponse;
import com.app.ecole.dto.response.ParentDtoResponse;
import com.app.ecole.dto.response.UserDtoResponse;
import com.app.ecole.entities.Banque;
import com.app.ecole.entities.Parent;
import com.app.ecole.entities.Reponse;
import com.app.ecole.entities.Utilisateur;
import com.app.ecole.repository.IDaoBanque;
import com.app.ecole.repository.IDaoParent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParentService implements ICrudService <ParentDtoRequest> {
    @Autowired
    private IDaoParent daoParent;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired(required=true)
    private ModelMapper modelMapper;


    @Override
    public Reponse create(ParentDtoRequest parentDtoRequest) {
        Reponse reponse = new Reponse();
        try
        {
            if(parentDtoRequest.getID() != null)
            {
                Optional<Parent>  userGot = daoParent.getUserByEmailAndTelephoneAndId(parentDtoRequest.getEmail(),parentDtoRequest.getTelephone(),parentDtoRequest.getID());
                userGot.ifPresentOrElse(
                        (userObj)->
                        {
                            reponse.setMessage("Cet email ou le téléphone est déjà utilisé !");
                            reponse.setCode(201);

                        },
                        ()->
                        {

                            Optional<Parent>  userById = daoParent.findById(parentDtoRequest.getID());

                            if(parentDtoRequest.getPrenom() != null && !parentDtoRequest.getPrenom().equals(userById.get().getPrenom()))
                            {
                                userById.get().setPrenom(parentDtoRequest.getPrenom());
                            }
                            if(parentDtoRequest.getNom() != null && !parentDtoRequest.getNom().equals(userById.get().getNom()))
                            {
                                userById.get().setNom(parentDtoRequest.getNom());
                            }
                            if(parentDtoRequest.getEmail() != null && !parentDtoRequest.getEmail().equals(userById.get().getEmail()))
                            {
                                userById.get().setEmail(parentDtoRequest.getEmail());
                            }
                            if(parentDtoRequest.getTelephone() != null && !parentDtoRequest.getTelephone().equals(userById.get().getTelephone()))
                            {
                                userById.get().setTelephone(parentDtoRequest.getTelephone());
                            }

                            Parent userSave = daoParent.save(userById.get());
                            var userDtoResponse=modelMapper.map(userSave, ParentDtoResponse.class);
                            reponse.setResult(userDtoResponse);
                            reponse.setMessage("Ce parent a été modifié avec succès");
                            reponse.setCode(200);

                        }
                );
            }
            else {
                Optional<Parent>  userByEmail = daoParent.getUserByEmailOrTelephone(parentDtoRequest.getEmail(),parentDtoRequest.getTelephone());
                String pwdCryp = bCryptPasswordEncoder.encode(parentDtoRequest.getEmail());
                userByEmail.ifPresentOrElse(
                        (userGot)->{
                            reponse.setMessage("Cet email ou ce téléphone est déjà utilisé !");
                            reponse.setCode(201);

                        },
                        ()->
                        {

                            var ambuser =modelMapper.map(parentDtoRequest, Parent.class);
                            ambuser.setPassword(pwdCryp);
                            ambuser.setStatus(true);
                            Parent userSave = daoParent.save(ambuser);
                            var amb =modelMapper.map(userSave, ParentDtoResponse.class);
                            reponse.setMessage("Ce parent a été enregistré avec succès");
                            reponse.setResult(amb);
                            reponse.setCode(200);

                        }


                );

            }





        }
        catch (Exception e)
        {
            reponse.setCode(500);
            reponse.setMessage("Une erreur interne est survenue coté serveur  !" +e.getMessage());
        }

        return reponse ;
    }

    @Override
    public Reponse getAll() {
        Reponse reponse = new Reponse();
        try
        {

            List<ParentDtoResponse> users= daoParent.findByStatus(true)
                    .stream()
                    .map(p->modelMapper.map(p,ParentDtoResponse.class)

                    ).collect(Collectors.toList());

            reponse.setResult(users);
            reponse.setMessage("Liste des parents !");
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
            Optional<Parent> user = daoParent.findById(id);
            user.ifPresentOrElse(
                    (userP)->{

                        var userDtoResponse=modelMapper.map(userP, ParentDtoResponse.class);
                        reponse.setResult(userDtoResponse);
                        reponse.setMessage("Ce parent a été retrouvé avec succès");
                        reponse.setCode(200);


                    },
                    ()->{reponse.setMessage("Ce parent n'existe pas");
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
            Optional<Parent> user = daoParent.findById(id);

            if(user.isPresent())
            {
                user.get().setStatus(!user.get().isStatus());
                Parent userSave=daoParent.save(user.get());
                reponse.setResult(modelMapper.map(userSave, ParentDtoResponse.class));
                reponse.setMessage("Ce parent a été bloqué avec succès");
                reponse.setCode(200);

            }
            else
            {
                reponse.setMessage("Ce parent n'existe pas");
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
