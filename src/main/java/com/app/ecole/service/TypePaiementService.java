package com.app.ecole.service;

import com.app.ecole.dto.request.GroupeDtoRequest;
import com.app.ecole.dto.request.TypePaiementDtoRequest;
import com.app.ecole.dto.response.EleveDtoResponse;
import com.app.ecole.dto.response.GroupeDtoResponse;
import com.app.ecole.dto.response.TypePaiementDtoResponse;
import com.app.ecole.entities.*;
import com.app.ecole.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TypePaiementService extends ServiceBase implements ICrudService <TypePaiementDtoRequest> {
    @Autowired
    private IDaoTypePaiement daoTypePaiement;
    @Autowired
    private IDaoSoldeTypePaiement daoSoldeTypePaiement;
    @Autowired
    private IDaoReduction daoReduction;
    @Autowired
    private IDaoClasse daoClasse;
    @Autowired
    private IDaoEleve daoEleve;
    @Autowired
    private IDaoAnnee daoAnnee;
    @Autowired
    private IDaoInscription daoInscription;
    @Autowired
    private IDaoSolde daoSolde;
    @Autowired(required=true)
    private ModelMapper modelMapper;
    @Override
    public Reponse create(TypePaiementDtoRequest typePaiementDtoRequest) {
        Reponse reponse = new Reponse();
        try
        {

            if(typePaiementDtoRequest.getID() != null)
            {
                Optional<Type_Paiement> isExitsTypePaiement= daoTypePaiement.findByID(typePaiementDtoRequest.getID());

                    if(typePaiementDtoRequest.getTypePaiement() != null && typePaiementDtoRequest.getTypePaiement().length() != 0)
                    {



                                if (typePaiementDtoRequest.getMontant_annuel() != 0) {
                                    daoSoldeTypePaiement.findByTypePaiement(typePaiementDtoRequest.getID()).stream()
                                            .forEach( g->
                                            {
                                                g.setMontantRestant(typePaiementDtoRequest.getMontant_annuel());
                                                daoSoldeTypePaiement.save(g);
                                            });

                                    isExitsTypePaiement.get().setMontant_annuel(typePaiementDtoRequest.getMontant_annuel());
                                }
                                if (typePaiementDtoRequest.getMontant_mensuel() != 0) {
                                    isExitsTypePaiement.get().setMontant_mensuel(typePaiementDtoRequest.getMontant_mensuel());

                                }
                                isExitsTypePaiement.get().setTypePaiement(typePaiementDtoRequest.getTypePaiement());
                                Type_Paiement groupe1 = daoTypePaiement.save(isExitsTypePaiement.get());
                                var groupeSave = modelMapper.map(groupe1, TypePaiementDtoResponse.class);
                                if(groupeSave.getClasseMereID() != null)
                                {
                                    daoClasse.findById(groupeSave.getClasseMereID()).ifPresent( b->groupeSave.setClasseMere(b) );

                                }
                                if(groupe1 != null)
                                {

                                    groupe1.getReductions().stream()
                                            .forEach( x-> daoReduction.findById(x).ifPresent( b->groupeSave.getReductions().add(b) ));


                                }

                                reponse.setMessage("Le montant a été modifié uniquement mais ce nom de type de paiement existe déjà");
                                reponse.setResult(groupeSave);
                                reponse.setCode(200);


                     }
                        else
                        {
                            if (typePaiementDtoRequest.getMontant_annuel() != 0) {
                                isExitsTypePaiement.get().setMontant_annuel(typePaiementDtoRequest.getMontant_annuel());


                            }
                            if (typePaiementDtoRequest.getMontant_mensuel() != 0) {
                                isExitsTypePaiement.get().setMontant_mensuel(typePaiementDtoRequest.getMontant_mensuel());

                            }
                            isExitsTypePaiement.get().setTypePaiement(typePaiementDtoRequest.getTypePaiement());
                            Type_Paiement groupe1 = daoTypePaiement.save(isExitsTypePaiement.get());
                            var groupeSave =modelMapper.map(groupe1, TypePaiementDtoResponse.class);
                            reponse.setMessage("Le type de paiement a été modifié avec succès");
                            reponse.setResult(groupeSave);
                            reponse.setCode(200);


                        }



                }
            else
            {

                            var type_PaiementConverted =modelMapper.map(typePaiementDtoRequest, Type_Paiement.class);
                            type_PaiementConverted.setStatus(true);
                            Type_Paiement tp = daoTypePaiement.save(type_PaiementConverted);
                            var tpSave =modelMapper.map(tp, TypePaiementDtoResponse.class);

                            if(typePaiementDtoRequest != null && typePaiementDtoRequest.getClasseMereID() != null )
                            {
                               this.addTypePaiementToEleve(typePaiementDtoRequest.getClasseMereID(),tp );
                            }
                            if(typePaiementDtoRequest != null && typePaiementDtoRequest.getClasseMereID() != null)
                            {
                                daoClasse.findById(typePaiementDtoRequest.getClasseMereID()).ifPresent( b->tpSave.setClasseMere(b) );

                            }

                            tp.getReductions()
                                    .stream()
                                    .filter(f->f != null)
                                    .forEach( x-> daoReduction.findById(x).ifPresent( e->tpSave.getReductions().add(e) ));
                             tpSave.setReductions(tpSave.getReductions().stream().filter(f->f.getID()  != null).collect(Collectors.toList()));


                            reponse.setMessage("Le type de paiement a été enregistré avec succès");
                            reponse.setResult(tpSave);
                            reponse.setCode(200);



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

            List< TypePaiementDtoResponse> tps= daoTypePaiement.findByStatus(true)
                    .stream()
                    .map(p->
                            {

                                var groupeSave =  modelMapper.map(p,TypePaiementDtoResponse.class);
                                if(groupeSave.getClasseMereID() != null)
                                {
                                    daoClasse.findById(groupeSave.getClasseMereID()).ifPresent( b->groupeSave.setClasseMere(b) );

                                }
                                if(p != null)
                                {

                                    p.getReductions().stream()
                                            .forEach( x-> daoReduction.findById(x).ifPresent( b->groupeSave.getReductions().add(b) ));


                                }
                                groupeSave.setReductions(groupeSave.getReductions().stream().filter(f->f.getID()  != null).collect(Collectors.toList()));

                                return  groupeSave;
                            }


                    ).collect(Collectors.toList());

            reponse.setResult(tps);

            reponse.setMessage("Liste des type de paiements !");
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
            Optional<Type_Paiement> IsExistType_Paiement=daoTypePaiement.findById(id);
            IsExistType_Paiement.ifPresentOrElse(
                    (userP)->{

                        var result=modelMapper.map(IsExistType_Paiement.get(), TypePaiementDtoResponse.class);
                        if(result.getClasseMereID() != null)
                        {
                            daoClasse.findById(result.getClasseMereID()).ifPresent( b->result.setClasseMere(b) );

                        }
                        if(userP != null)
                        {

                            userP.getReductions().stream()
                                    .forEach( x-> daoReduction.findById(x).ifPresent( b->result.getReductions().add(b) ));


                        }
                        reponse.setResult(result);
                        reponse.setMessage("Le type de paiement a été retrouvé avec succès");
                        reponse.setCode(200);


                    },
                    ()->{reponse.setMessage("Ce type de paiements n'existe pas");
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
            Optional<Type_Paiement> IsExistType_Paiement=daoTypePaiement.findById(id);

            if(IsExistType_Paiement.isPresent())
            {
                IsExistType_Paiement.get().setStatus(!IsExistType_Paiement.get().isStatus());
                Type_Paiement tpSave=daoTypePaiement.save(IsExistType_Paiement.get());
                reponse.setResult(modelMapper.map(tpSave, TypePaiementDtoResponse.class));
                reponse.setMessage("Ce type de paiement a été bloqué avec succès");
                reponse.setCode(200);

            }
            else
            {
                reponse.setMessage("Ce type de paiement n'existe pas");
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
