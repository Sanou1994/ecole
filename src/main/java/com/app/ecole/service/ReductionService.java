package com.app.ecole.service;

import com.app.ecole.dto.request.AnneeDtoRequest;
import com.app.ecole.dto.request.ReductionDtoRequest;
import com.app.ecole.dto.response.AnneeDtoResponse;
import com.app.ecole.dto.response.ReductionDtoResponse;
import com.app.ecole.entities.Annee;
import com.app.ecole.entities.Reduction;
import com.app.ecole.entities.Reponse;
import com.app.ecole.repository.IDaoAnnee;
import com.app.ecole.repository.IDaoReduction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReductionService implements ICrudService <ReductionDtoRequest> {
    @Autowired
    private IDaoReduction daoReduction;
    @Autowired(required=true)
    private ModelMapper modelMapper;

    @Override
    public Reponse create(ReductionDtoRequest anneeDtoRequest) {
        Reponse reponse = new Reponse();
        try
        {
            if(anneeDtoRequest.getID() != null)
            {

                        Optional<Reduction> isExitsGroupe= daoReduction.findByID(anneeDtoRequest.getID());

                        if(isExitsGroupe.isPresent())
                        {

                            if(anneeDtoRequest.getCritere() != null && anneeDtoRequest.getCritere().length() != 0)
                            {
                                isExitsGroupe.get().setCritere(anneeDtoRequest.getCritere());


                            }
                            if(anneeDtoRequest.getTaux_reduction() != null && anneeDtoRequest.getTaux_reduction() > 0)
                            {
                                isExitsGroupe.get().setTaux_reduction(anneeDtoRequest.getTaux_reduction());

                            }

                            Reduction groupe1 = daoReduction.save(isExitsGroupe.get());
                            var groupeSave =modelMapper.map(groupe1, ReductionDtoResponse.class);
                            reponse.setMessage("La réduction a été modifiée avec succès");
                            reponse.setResult(groupeSave);
                            reponse.setCode(200);


                        }
                        else
                        {

                            reponse.setMessage("Cette réduction n'existe plus !");
                            reponse.setCode(201);


                        }

            }
            else
            {
                               var groupeConverted =modelMapper.map(anneeDtoRequest, Reduction.class);
                               groupeConverted.setStatus(true);
                               Reduction groupe1 = daoReduction.save(groupeConverted);
                               var groupeSave =modelMapper.map(groupe1, ReductionDtoResponse.class);
                               reponse.setMessage("La reduction a été enregistrée avec succès");
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

            List<ReductionDtoResponse> users= daoReduction.findByStatus(true)
                    .stream()
                    .map(p->modelMapper.map(p,ReductionDtoResponse.class)

                    ).collect(Collectors.toList());

            reponse.setResult(users);
            reponse.setMessage("Liste des reductions !");
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
            Optional<Reduction> IsExistGroupe=daoReduction.findById(id);
            IsExistGroupe.ifPresentOrElse(
                    (userP)->{

                        var result=modelMapper.map(IsExistGroupe.get(), ReductionDtoResponse.class);
                        reponse.setResult(result);
                        reponse.setMessage("La Reduction a été retrouvée avec succès");
                        reponse.setCode(200);


                    },
                    ()->{reponse.setMessage("La Reduction n'existe pas");
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
            Optional<Reduction> IsExistGroupe=daoReduction.findById(id);

            if(IsExistGroupe.isPresent())
            {
                IsExistGroupe.get().setStatus(!IsExistGroupe.get().isStatus());
                Reduction userSave=daoReduction.save( IsExistGroupe.get());
                reponse.setResult(modelMapper.map(userSave, ReductionDtoResponse.class));
                reponse.setMessage("Cette Reduction a été bloqué avec succès");
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
