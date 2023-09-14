package com.app.ecole.service;

import com.app.ecole.dto.request.ClasseDtoRequest;
import com.app.ecole.dto.request.MatiereDtoRequest;
import com.app.ecole.dto.response.*;
import com.app.ecole.entities.*;
import com.app.ecole.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class ClasseService implements ICrudService <ClasseDtoRequest> {
    @Autowired
    private IDaoClasse daoClasse;
    @Autowired(required=true)
    private ModelMapper modelMapper;
    @Autowired
    private IDaoServiceImpayes daoServiceImpayes;
    @Autowired
    private IDaoServicesImpayesItem daoServicesImpayesItem;
    @Autowired
    private IDaoSoldeTypePaiement daoSoldeTypePaiement;
    @Autowired
    private  IDaoEleve daoEleve;
    @Autowired
    private  IDaoInscription daoInscription;
    @Autowired
    private IDaoSolde daoSolde;
    @Autowired
    private IDaoTypePaiement daoTypePaiement;
    @Autowired
    private IDaoAnnee daoAnnee;
    @Override
    public Reponse create(ClasseDtoRequest matiere) {
        Reponse reponse = new Reponse();
        try
        {

            if(matiere.getID() != null)
            {
                Optional<Classe> isExitsClasse= daoClasse.findByID(matiere.getID());
                if(isExitsClasse.isPresent())
                {
                    if(matiere.getClasseMere() != null && matiere.getClasseMere().compareTo(isExitsClasse.get().getClasseMere()) != 0)
                    {
                        isExitsClasse.get().setClasseMere(matiere.getClasseMere());

                    }
                    if(matiere.getScolariteAnnuelle() != 0)
                    {
                        isExitsClasse.get().setScolariteAnnuelle(matiere.getScolariteAnnuelle());

                    }
                    if(matiere.getGroupe() != null && matiere.getGroupe().length() != 0 )
                    {
                        isExitsClasse.get().setGroupe(matiere.getGroupe());

                    }

                    if(matiere.getNom() != null && matiere.getNom().length() != 0)
                    {
                        Optional<Classe> updateGroupe=daoClasse.findByNomIgnoreCase(matiere.getNom());

                        if(!updateGroupe.isPresent())
                        {
                            isExitsClasse.get().setNom(matiere.getNom());


                        }

                    }

                    Classe groupe1 = daoClasse.save(isExitsClasse.get());
                    var groupeSave =modelMapper.map(groupe1, ClasseDtoResponse.class);
                    if(groupe1.getClasseMere() != null){ daoClasse.findByID(groupe1.getClasseMere()).ifPresent(p-> groupeSave.setClasseMere(p)); }
                    reponse.setMessage("La classe a été modifiée avec succès");
                    reponse.setResult(groupeSave);
                    reponse.setCode(200);
                }
                else {
                    reponse.setMessage("Cette classe n'existe plus !");
                    reponse.setCode(201);
                }
            }
            else {


                            var type_PaiementConverted =modelMapper.map(matiere, Classe.class);
                            type_PaiementConverted.setStatus(true);
                            Classe tp = daoClasse.save(type_PaiementConverted);
                            var tpSave =modelMapper.map(tp, ClasseDtoResponse.class);
                            if(tp.getClasseMere() != null){ daoClasse.findByID(tp.getClasseMere()).ifPresent(p-> tpSave.setClasseMere(p)); }

                            reponse.setMessage("La classe a été enregistrée avec succès");
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

            List<ClasseDtoResponse> tps= daoClasse.findByStatus(true)
                    .stream()
                    .map(p->{
                        ClasseDtoResponse  userP=  modelMapper.map(p,ClasseDtoResponse.class);
                        if(p.getClasseMere() != null){ daoClasse.findByID(p.getClasseMere()).ifPresent(m-> userP.setClasseMere(m)); }
                        return  userP;

                    }).collect(Collectors.toList());

            reponse.setResult(tps);
            reponse.setMessage("Liste des type de classes !");
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
            Optional<Classe> IsExistType_Paiement=daoClasse.findById(id);
            IsExistType_Paiement.ifPresentOrElse(
                    (userP)->{

                        var result=modelMapper.map(IsExistType_Paiement.get(), ClasseDtoResponse.class);
                        if(userP.getClasseMere() != null){ daoClasse.findByID(userP.getClasseMere()).ifPresent(p-> result.setClasseMere(p)); }

                        reponse.setResult(result);
                        reponse.setMessage("La classe a été retrouvé avec succès");
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
            Optional<Classe> IsExistGroupe=daoClasse.findById(id);

            if(IsExistGroupe.isPresent())
            {
                IsExistGroupe.get().setStatus(!IsExistGroupe.get().getStatus());
                Classe userSave=daoClasse.save( IsExistGroupe.get());
                reponse.setResult(modelMapper.map(userSave, ClasseDtoResponse.class));
                reponse.setMessage("Cette classe a changé de status avec succès");
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

    public Reponse getAllSousClasse(UUID id) {
        Reponse reponse = new Reponse();
        try
        {

            List<ClasseDtoResponse> tps= daoClasse.findByClasseMereAndStatus(id,true)
                    .stream()
                    .map(p->{
                                ClasseDtoResponse  userP=  modelMapper.map(p,ClasseDtoResponse.class);
                                if(p.getClasseMere() != null){ daoClasse.findByID(p.getClasseMere()).ifPresent(m-> userP.setClasseMere(m)); }
                                return  userP;

                            }).collect(Collectors.toList());

            reponse.setResult(tps);
            reponse.setMessage("Liste des sous classes !");
            reponse.setCode(200);



        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue");
            reponse.setCode(500);

        }

        return reponse;
    }
    public  Reponse getAllElevesNonEnReglesByClasseID(UUID classeID)
    {
        Reponse reponse = new Reponse();
        try
        {
            List<ServiceImpayesResponse> serviceImpayes=new ArrayList<>();

            if(classeID != null)
            {

                daoInscription.findByClasseID(classeID)
                        .stream()
                        .filter(l->daoAnnee.findByStatus(true).isPresent() && l.getEleveID() != null && daoEleve.findById(l.getEleveID()).isPresent())
                        .map(f-> daoEleve.findById(f.getEleveID()).get())
                        .forEach(p->{
                            var h=daoAnnee.findByStatus(true).get();
                            var tp=daoServiceImpayes.findByAnneeScolaireIDAndEleveID(h.getID(), p.getID());
                            if(tp.isPresent())
                            {

                                var serviceImpayesResponse=modelMapper.map(tp.get(), ServiceImpayesResponse.class);


                                Map<UUID,List<ServiceImpayesItem>> serices_m = tp.get().getServices().stream()
                                        .collect(groupingBy(ServiceImpayesItem::getTypePaiementID));

                                serices_m.forEach( (f,g)->
                                {
                                    var paiementType =  daoTypePaiement.findByID(f);
                                    if(paiementType.isPresent() && paiementType.get().getEstObligatoire())
                                    {
                                        var sb= new Service_Mois() ;
                                        sb.setTypePaiement(paiementType.get());
                                        g.stream()
                                                .filter(a-> a.getTypePaiementID().compareTo(f) == 0)
                                                .forEach(m->sb.getMois_non_payes().add(m.getMois_non_paye()));
                                        serviceImpayesResponse.setAnneeScolaire(h);
                                        serviceImpayesResponse.setEleveID(p.getID());
                                        serviceImpayesResponse.setEleve(p);
                                        serviceImpayesResponse.getMes_services().add(sb);
                                        serviceImpayes.add(serviceImpayesResponse);


                                    } });
                            }

                        } );

                reponse.setCode(200);
                reponse.setResult(serviceImpayes);
                reponse.setMessage(" Liste des élèves non en règle");

            }
            else {
                reponse.setCode(201);
                reponse.setResult(new ArrayList<>());
                reponse.setMessage("La classe n'existe plus");
            }

        }
        catch (Exception e)
        {
            reponse.setCode(500);
            reponse.setResult(new ArrayList<>());
            reponse.setMessage(" un problème est survenu");

        }
        return reponse ;

    }
}
