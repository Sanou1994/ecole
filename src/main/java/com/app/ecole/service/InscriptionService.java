package com.app.ecole.service;

import com.app.ecole.dto.request.AnneeDtoRequest;
import com.app.ecole.dto.request.InscriptionDtoRequest;
import com.app.ecole.dto.response.*;
import com.app.ecole.entities.*;
import com.app.ecole.repository.*;
import com.app.ecole.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class InscriptionService extends ServiceBase implements ICrudService <InscriptionDtoRequest> {
    @Autowired
    private IDaoSoldeTypePaiement daoSoldeTypePaiement;
    @Autowired
    private IDaoTypePaiement daoTypePaiement;

    @Autowired
    private IDaoServiceImpayes daoServiceImpayes;
    @Autowired
    private IDaoEleve daoEleve;
    @Autowired
    private IDaoSolde daoSolde;
    @Autowired
    private IDaoClasse daoClasse;
    @Autowired
    private IDaoAnnee daoAnnee;
    @Autowired
    private IDaoInscription daoInscription;
    @Autowired(required=true)
    private ModelMapper modelMapper;

    @Override
    public Reponse create(InscriptionDtoRequest inscriptionDtoRequest) {
        Reponse reponse = new Reponse();
        try
        {
            if(inscriptionDtoRequest.getID() != null)
            {
                Optional<Inscription>  userGot = daoInscription.findByID(inscriptionDtoRequest.getID());
                userGot.ifPresentOrElse(
                        (userObj)->
                        {
                            if(inscriptionDtoRequest.isTypeInscription())
                            {
                                userObj.setTypeInscription(inscriptionDtoRequest.isTypeInscription());
                            }

                            if(inscriptionDtoRequest.getMontantReduction() !=0 && inscriptionDtoRequest.getMontantReduction() != userObj.getMontantReduction())
                            {
                                userObj.setMontantReduction(inscriptionDtoRequest.getMontantReduction());
                            }
                            if(inscriptionDtoRequest.getTauxReduction() !=0 && inscriptionDtoRequest.getTauxReduction() != userObj.getTauxReduction())
                            {
                                userObj.setTauxReduction(inscriptionDtoRequest.getTauxReduction());
                            }



                            if(inscriptionDtoRequest.getClasseID() != null && inscriptionDtoRequest.getClasseID().compareTo(userObj.getClasseID()) != 0)
                            {
                                userObj.setClasseID(inscriptionDtoRequest.getClasseID());
                            }
                            if(inscriptionDtoRequest.getEleveID() != null && inscriptionDtoRequest.getEleveID().compareTo(userObj.getEleveID()) != 0)
                            {
                                userObj.setEleveID(inscriptionDtoRequest.getEleveID());
                            }

                            Inscription userSave = daoInscription.save(userObj);
                            var userDtoResponse=modelMapper.map(userSave, InscriptionDtoResponse.class);
                            reponse.setResult(userDtoResponse);
                            reponse.setMessage("Cette inscription a été modifié avec succès");
                            reponse.setCode(200);


                        },
                        ()->
                        {
                            reponse.setMessage("Cette inscription n'existe plus !");
                            reponse.setCode(201);


                        }
                );
            }
            else
            {

                daoAnnee.findByStatus(true).ifPresentOrElse(annee ->{

                            var inscription=daoInscription.findByEleveIDAndAnneeScolaireID(inscriptionDtoRequest.getEleveID(),annee.getID());
                              if(inscription.isPresent())
                              {
                                  reponse.setMessage(" Cet élève a déjà une inscription encours");
                                  reponse.setCode(201);
                              }
                              else
                              {
                                  var groupeConverted =modelMapper.map(inscriptionDtoRequest, Inscription.class);
                                  groupeConverted.setAnneeScolaireID(annee.getID());
                                  groupeConverted.setStatus(true);

                                  Inscription groupe1 = daoInscription.save(groupeConverted);
                                  var groupeSave =modelMapper.map(groupe1, InscriptionDtoResponse.class);
                                  daoClasse.findByID(groupe1.getID()).ifPresent(n->groupeSave.setClasse(n) );
                                  daoEleve.findById(groupe1.getEleveID()).ifPresent(n->groupeSave.setEleve(n));
                                  daoAnnee.findByID(groupe1.getAnneeScolaireID()).ifPresent(n->groupeSave.setAnneeScolaire(n));



                                  if(groupe1.getEleveID() != null &&  groupeSave.getClasse().getClasseMere() != null )
                                  {
                                      // CREATE SOLDE
                                      this.addSoldeToEleve(groupeSave.getClasse().getClasseMere(),groupe1.getEleveID());
                                      daoClasse.findByID(groupeSave.getClasse().getClasseMere()).ifPresent(n->groupeSave.setMontantScolarite(n.getScolariteAnnuelle()));

                                  }
                                  else
                                  {
                                      groupeSave.setMontantScolarite(groupeSave.getClasse().getScolariteAnnuelle());
                                  }
                                  reponse.setMessage(" Cette inscription a été enregistré avec succès");
                                  reponse.setResult(groupeSave);
                                  reponse.setCode(200);
                              }
                        },
                        () -> {
                            reponse.setMessage(" Aucune année scolaire n'est disponible");
                            reponse.setCode(201);
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

            List<InscriptionDtoResponse> users= daoInscription.findAll()
                    .stream()
                    .filter(r->r.getEleveID() != null && daoEleve.findById(r.getEleveID()).isPresent() && daoEleve.findById(r.getEleveID()).get().isStatus())
                    .map(p->
                            {
                                var inscript = modelMapper.map(p,InscriptionDtoResponse.class);

                                daoClasse.findByID(p.getClasseID()).ifPresent(n->inscript.setClasse(n) );
                                daoEleve.findById(p.getEleveID()).ifPresent(n->inscript.setEleve(n));
                                daoAnnee.findByID(p.getAnneeScolaireID()).ifPresent(n->inscript.setAnneeScolaire(n));

                                if(inscript.getClasse().getClasseMere() != null)
                                {
                                    daoClasse.findByID(inscript.getClasse().getClasseMere()).ifPresent(n->inscript.setMontantScolarite(n.getScolariteAnnuelle()) );

                                }
                                else {
                                    inscript.setMontantScolarite(inscript.getClasse().getScolariteAnnuelle());
                                }

                                return  inscript;
                            }

                    ).collect(Collectors.toList());
            reponse.setResult(users);
            reponse.setMessage("Liste des inscriptions !");
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
            Optional<Inscription> IsExistGroupe=daoInscription.findById(id);
            IsExistGroupe.ifPresentOrElse(
                    (userP)->{

                        var result=modelMapper.map(IsExistGroupe.get(), InscriptionDtoResponse.class);
                        daoClasse.findByID(userP.getID()).ifPresent(n->result.setClasse(n) );
                        daoEleve.findById(userP.getEleveID()).ifPresent(n->result.setEleve(n));
                        daoAnnee.findByID(userP.getAnneeScolaireID()).ifPresent(n->result.setAnneeScolaire(n));
                        if(result.getClasse().getClasseMere() != null)
                        {
                            daoClasse.findByID(result.getClasse().getClasseMere()).ifPresent(n->result.setMontantScolarite(n.getScolariteAnnuelle()) );

                        }
                        else {
                            result.setMontantScolarite(result.getClasse().getScolariteAnnuelle());
                        }
                        reponse.setResult(result);
                        reponse.setMessage("Cette Inscription a été retrouvée avec succès");
                        reponse.setCode(200);
                    },
                    ()->{reponse.setMessage("Cette Inscription n'existe pas");
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
            Optional<Inscription> IsExistGroupe=daoInscription.findById(id);

            if(IsExistGroupe.isPresent())
            {
                IsExistGroupe.get().setStatus(false);
                Inscription userSave=daoInscription.save(IsExistGroupe.get());
                reponse.setResult(modelMapper.map(userSave, InscriptionDtoResponse.class));
                reponse.setMessage("Cette inscription a été bloquée avec succès");
                reponse.setCode(200);

            }
            else
            {
                reponse.setMessage("Cette inscription n'existe pas");
                reponse.setCode(201);

            }


        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue");
            reponse.setCode(500);

        }

        return reponse;
    }

    public Reponse search(String el) {
        Reponse reponse = new Reponse();

        try
        {
            List<InscriptionDtoResponse> users= new ArrayList<>();

            daoEleve.findByMatriculeContainingIgnoreCaseOrNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseAndStatus(el,el,el,true)
                    .stream()
                    .filter(r->r.getID() != null && daoEleve.findById(r.getID()).isPresent() && daoEleve.findById(r.getID()).get().isStatus())
                    .forEach(p->
                    {
                        daoInscription.findByEleveID(p.getID()).stream()
                                .forEach( g->{
                                    var inscript = modelMapper.map(p,InscriptionDtoResponse.class);
                                    daoClasse.findByID(g.getClasseID()).ifPresent(n->inscript.setClasse(n) );
                                    daoEleve.findById(g.getEleveID()).ifPresent(n->inscript.setEleve(n));
                                    daoAnnee.findByID(g.getAnneeScolaireID()).ifPresent(n->inscript.setAnneeScolaire(n));

                                    if(inscript.getClasse().getClasseMere() != null)
                                    {
                                        daoClasse.findByID(inscript.getClasse().getClasseMere()).ifPresent(n->inscript.setMontantScolarite(n.getScolariteAnnuelle()) );

                                    }
                                    else {
                                        inscript.setMontantScolarite(inscript.getClasse().getScolariteAnnuelle());
                                    }
                                    users.add(inscript);

                                });
                    }
                    );


            reponse.setResult(users);
            reponse.setMessage("Liste des élèves recherchés !");
            reponse.setCode(200);

        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue");
            reponse.setCode(500);

        }

        return reponse;
    }


    public Reponse search(String el,UUID id) {
        Reponse reponse = new Reponse();

        try
        {
            var inscriptFiltered=(Utility.TYPE_CLASSE.equals(el)) ? daoInscription.findByClasseID(id):daoInscription.findByAnneeScolaireID(id);

            var ins=inscriptFiltered
                    .stream()
                    .map( p->{
                        var inscript = modelMapper.map(p,InscriptionDtoResponse.class);
                        daoClasse.findByID(p.getClasseID()).ifPresent(n->inscript.setClasse(n) );
                        daoEleve.findById(p.getEleveID()).ifPresent(n->inscript.setEleve(n));
                        daoAnnee.findByID(p.getAnneeScolaireID()).ifPresent(n->inscript.setAnneeScolaire(n));

                        if(inscript.getClasse().getClasseMere() != null)
                        {
                            daoClasse.findByID(inscript.getClasse().getClasseMere()).ifPresent(n->inscript.setMontantScolarite(n.getScolariteAnnuelle()) );

                        }
                        else {
                            inscript.setMontantScolarite(inscript.getClasse().getScolariteAnnuelle());
                        }
                        return inscript;

                    });

            reponse.setResult(ins);
            reponse.setMessage("Liste des inscriptions recherchés !");
            reponse.setCode(200);

        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue");
            reponse.setCode(500);

        }

        return reponse;
    }
}
