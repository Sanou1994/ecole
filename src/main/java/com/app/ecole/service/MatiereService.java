package com.app.ecole.service;

import com.app.ecole.dto.request.MatiereDtoRequest;
import com.app.ecole.dto.request.TypePaiementDtoRequest;
import com.app.ecole.dto.response.MatiereDtoResponse;
import com.app.ecole.dto.response.TypePaiementDtoResponse;
import com.app.ecole.entities.Matiere;
import com.app.ecole.entities.Reponse;
import com.app.ecole.entities.Type_Paiement;
import com.app.ecole.repository.IDaoClasse;
import com.app.ecole.repository.IDaoMatiere;
import com.app.ecole.repository.IDaoTypePaiement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MatiereService implements ICrudService <MatiereDtoRequest> {
    @Autowired
    private IDaoMatiere daoMatiere;
    @Autowired
    private IDaoClasse daoClasse;
    @Autowired(required=true)
    private ModelMapper modelMapper;

    @Override
    public Reponse create(MatiereDtoRequest matiere) {
        Reponse reponse = new Reponse();
        try
        {
            if(matiere.getID() != null)
            {
                Optional<Matiere> isExitsTypePaiement= daoMatiere.findByID(matiere.getID());
                if(!isExitsTypePaiement.isPresent())
                {
                    reponse.setMessage("Cette matière n'existe plus !");
                    reponse.setCode(201);
                }
                else
                {
                    if(matiere.getNom() != null && matiere.getNom().length() != 0)
                    {
                        Optional<Matiere> updateGroupe=daoMatiere.findByNomIgnoreCase(matiere.getNom());

                        if(updateGroupe.isPresent() && matiere.getID().compareTo(updateGroupe.get().getID()) != 0)
                        {

                            if(matiere.getClasseID() != null && matiere.getClasseID().compareTo(isExitsTypePaiement.get().getClasseID()) != 0)
                            {
                                isExitsTypePaiement.get().setClasseID(matiere.getClasseID());
                            }

                            if(matiere.getCoeff() != 0)
                            {
                                isExitsTypePaiement.get().setCoeff(matiere.getCoeff());


                            }
                            Matiere groupe1 = daoMatiere.save(isExitsTypePaiement.get());
                            var groupeSave =modelMapper.map(groupe1, MatiereDtoResponse.class);
                            if(groupe1.getClasseID() != null){ daoClasse.findByID(groupe1.getClasseID()).ifPresent(p-> groupeSave.setClasse(p)); }

                            reponse.setMessage("Le coefficient a été modifié uniquement mais ce nom de matière existe déjà");
                            reponse.setResult(groupeSave);
                            reponse.setCode(200);


                        }
                        else
                        {
                            if(matiere.getCoeff() != 0)
                            {
                                isExitsTypePaiement.get().setCoeff(matiere.getCoeff());

                            }

                            if(matiere.getClasseID() != null && matiere.getClasseID().compareTo(isExitsTypePaiement.get().getClasseID()) != 0)
                            {
                                isExitsTypePaiement.get().setClasseID(matiere.getClasseID());
                            }
                            isExitsTypePaiement.get().setNom(matiere.getNom());
                            Matiere groupe1 = daoMatiere.save(isExitsTypePaiement.get());
                            var groupeSave =modelMapper.map(groupe1, MatiereDtoResponse.class);
                            if(groupe1.getClasseID() != null){ daoClasse.findByID(groupe1.getClasseID()).ifPresent(p-> groupeSave.setClasse(p)); }

                            reponse.setMessage("La matière a été modifiée avec succès");
                            reponse.setResult(groupeSave);
                            reponse.setCode(200);


                        }



                    }
                }
            }
            else {
                 var type_PaiementConverted =modelMapper.map(matiere, Matiere.class);
                            type_PaiementConverted.setStatus(true);
                            Matiere tp = daoMatiere.save(type_PaiementConverted);
                            var tpSave =modelMapper.map(tp, MatiereDtoResponse.class);
                            if(tp.getClasseID() != null){ daoClasse.findByID(tp.getClasseID()).ifPresent(p-> tpSave.setClasse(p)); }

                            reponse.setMessage("La matière a été enregistrée avec succès");
                            reponse.setResult(tpSave);
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

            List<MatiereDtoResponse> tps= daoMatiere.findByStatus(true)
                    .stream()
                    .map(p->{
                                MatiereDtoResponse   mat = modelMapper.map(p,MatiereDtoResponse.class);

                                if(p.getClasseID() != null){ daoClasse.findByID(p.getClasseID()).ifPresent(y-> mat.setClasse(y)); }
                                return  mat;
                            }).collect(Collectors.toList());

            reponse.setResult(tps);
            reponse.setMessage("Liste des type de matières !");
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
            Optional<Matiere> IsExistType_Paiement=daoMatiere.findById(id);
            IsExistType_Paiement.ifPresentOrElse(
                    (userP)->{

                        var result=modelMapper.map(IsExistType_Paiement.get(), MatiereDtoResponse.class);
                        if(userP.getClasseID() != null){ daoClasse.findByID(userP.getClasseID()).ifPresent(p-> result.setClasse(p)); }

                        reponse.setResult(result);
                        reponse.setMessage("La matière a été retrouvé avec succès");
                        reponse.setCode(200);


                    },
                    ()->{reponse.setMessage("Cette matière n'existe pas");
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
            Optional<Matiere> IsExistType_Paiement=daoMatiere.findById(id);

            if(IsExistType_Paiement.isPresent())
            {
                IsExistType_Paiement.get().setStatus(false);
                Matiere tpSave=daoMatiere.save(IsExistType_Paiement.get());
                MatiereDtoResponse groupeSave=modelMapper.map(tpSave, MatiereDtoResponse.class);
                if(tpSave.getClasseID() != null){ daoClasse.findByID(tpSave.getClasseID()).ifPresent(p-> groupeSave.setClasse(p)); }

                reponse.setResult(groupeSave);
                reponse.setMessage("Cette matière a été bloquée avec succès");
                reponse.setCode(200);

            }
            else
            {
                reponse.setMessage("Cette matière n'existe pas");
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
