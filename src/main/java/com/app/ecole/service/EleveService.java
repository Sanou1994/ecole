package com.app.ecole.service;
import com.app.ecole.dto.request.EleveDtoRequest;
import com.app.ecole.dto.response.*;
import com.app.ecole.entities.*;
import com.app.ecole.repository.*;
import com.app.ecole.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

@Service
public class EleveService extends ServiceBase implements ICrudService <EleveDtoRequest>
{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IDaoPaiement daoPaiement;
    @Autowired
    private IDaoTypePaiement daoTypePaiement;
    @Autowired
    private IDaoMoisPaiementTypePaiement daoMoisPaiementTypePaiement;
    @Autowired
    private IDaoModePaiementPaiement daoModePaiementPaiement;
    @Autowired
    private IDaoSoldeTypePaiement daoSoldeTypePaiement;
    @Autowired
    private IDaoSolde daoSolde;
    @Autowired
    private IDaoUser daoUser;
    @Autowired
    private IDaoClasse daoClasse;
    @Autowired
    private IDaoInscription daoInscription;
    @Autowired
    private IDaoServiceImpayes daoServiceImpayes;
    @Autowired
    private IDaoServicesImpayesItem daoServicesImpayesItem;
    @Autowired
    private IDaoAnnee daoAnnee;
    @Autowired
    private IDaoParent daoParent;

    @Autowired
    private IDaoEleve daoEleve;
    @Autowired(required=true)
    private ModelMapper modelMapper;

    @Override
    public Reponse create(EleveDtoRequest eleveDtoRequest) {
        Reponse reponse = new Reponse();
        try
        {
            if(eleveDtoRequest.getID() != null)
            {
                Optional<Eleve>  userById = daoEleve.findById(eleveDtoRequest.getID());
                if(eleveDtoRequest.getMatricule() != null && !eleveDtoRequest.getMatricule().equals(userById.get().getMatricule()))
                {
                    userById.get().setMatricule(eleveDtoRequest.getMatricule());
                }
                if(eleveDtoRequest.getIdParent() != null && eleveDtoRequest.getIdParent().compareTo(userById.get().getParentID()) != 0)
                {
                    userById.get().setParentID(eleveDtoRequest.getIdParent());
                }
                if(eleveDtoRequest.getGenre() != null && !eleveDtoRequest.getGenre().equals(userById.get().getGenre()))
                {
                    userById.get().setGenre(eleveDtoRequest.getGenre());
                }
                if(eleveDtoRequest.getPrenom() != null && !eleveDtoRequest.getPrenom().equals(userById.get().getPrenom()))
                {
                    userById.get().setPrenom(eleveDtoRequest.getPrenom());
                }
                if(eleveDtoRequest.getNom() != null && !eleveDtoRequest.getNom().equals(userById.get().getNom()))
                {
                    userById.get().setNom(eleveDtoRequest.getNom());
                }
                if(eleveDtoRequest.getDate_de_naissance() != null && !eleveDtoRequest.getDate_de_naissance().equals(userById.get().getDate_de_naissance()))
                {
                    userById.get().setDate_de_naissance(eleveDtoRequest.getDate_de_naissance());
                }
                if(eleveDtoRequest.getLieu() != null && !eleveDtoRequest.getLieu().equals(userById.get().getLieu()))
                {
                    userById.get().setLieu(eleveDtoRequest.getLieu());
                }
                if(eleveDtoRequest.getPays() != null && !eleveDtoRequest.getPays().equals(userById.get().getPays()))
                {
                    userById.get().setPays(eleveDtoRequest.getPays());
                }
                if(eleveDtoRequest.getAdresse() != null && !eleveDtoRequest.getAdresse().equals(userById.get().getAdresse()))
                {
                    userById.get().setAdresse(eleveDtoRequest.getAdresse());
                }

                if(eleveDtoRequest.getRemarques() != null && !eleveDtoRequest.getRemarques().equals(userById.get().getRemarques()))
                {
                    userById.get().setRemarques(eleveDtoRequest.getRemarques());
                }
                if(eleveDtoRequest.getActif() != null && !eleveDtoRequest.getActif().equals(userById.get().getActif()))
                {
                    userById.get().setActif(eleveDtoRequest.getActif());
                }
                if(eleveDtoRequest.getSituation() != null && !eleveDtoRequest.getSituation().equals(userById.get().getSituation()))
                {
                    userById.get().setSituation(eleveDtoRequest.getSituation());
                }


                if(eleveDtoRequest.getAdresse() != null && !eleveDtoRequest.getAdresse().equals(userById.get().getAdresse()))
                {
                    userById.get().setAdresse(eleveDtoRequest.getAdresse());
                }



                if(eleveDtoRequest.getEmail() != null && !eleveDtoRequest.getEmail().equals(userById.get().getEmail()))
                {
                    userById.get().setEmail(eleveDtoRequest.getEmail());
                }
                if(eleveDtoRequest.getTelephone() != null && !eleveDtoRequest.getTelephone().equals(userById.get().getTelephone()))
                {
                    userById.get().setTelephone(eleveDtoRequest.getTelephone());
                }

                Eleve userSave = daoEleve.save(userById.get());
                var userDtoResponse=modelMapper.map(userSave, EleveDtoResponse.class);
                reponse.setResult(userDtoResponse);
                reponse.setMessage("Ce compte a été modifié avec succès");
                reponse.setCode(200);
            }
            else {
                var anneeEncours=daoAnnee.findByStatus(true);
                if(eleveDtoRequest.getClasseID() != null && anneeEncours.isPresent())
                {
                    var classe=daoClasse.findByID(eleveDtoRequest.getClasseID());
                    if(classe.isPresent())
                    {

                        if(eleveDtoRequest.getNom() != null && eleveDtoRequest.getNom().length() !=0

                                && eleveDtoRequest.getPrenom() != null && eleveDtoRequest.getPrenom().length() !=0)
                        {
                            var matriculeMotif=eleveDtoRequest.getNom().substring(0, 1).toUpperCase() + eleveDtoRequest.getPrenom().substring(0, 1).toUpperCase();

                          //  String pwdCryp = bCryptPasswordEncoder.encode(eleveDtoRequest.getEmail());
                            var ambuser =modelMapper.map(eleveDtoRequest, Eleve.class);
                            ambuser.setMatricule(Utility.genererMatricule(daoEleve.findAll().size()+1,matriculeMotif));
                          //  ambuser.setPassword(pwdCryp);
                            if(eleveDtoRequest.getIdParent() == null && eleveDtoRequest.getParent() != null)
                            {
                                var parent =modelMapper.map(eleveDtoRequest.getParent(), Parent.class);
                                parent.setNombre_enfants(1);
                                var parentSave =  daoParent.save(parent);
                                ambuser.setParentID(parentSave.getID());
                            }
                            else {
                                daoParent.findById(eleveDtoRequest.getIdParent()).ifPresent(c->
                                        {
                                          c.setNombre_enfants(c.getNombre_enfants()+1);
                                            daoParent.save(c);
                                        });
                            }
                            ambuser.setStatus(true);
                            Eleve userSave = daoEleve.save(ambuser);

                            // CREATE INSCRIPTION
                            Inscription createInscription= new Inscription();
                            createInscription.setStatus(true);
                            createInscription.setMontantReduction(0);
                            createInscription.setEleveID(userSave.getID());
                            createInscription.setTypeInscription(false);
                            createInscription.setDateInscription(Utility.today());
                            createInscription.setClasseID(eleveDtoRequest.getClasseID());
                            createInscription.setMontantReduction(0);
                            createInscription.setAnneeScolaireID(anneeEncours.get().getID());
                            daoInscription.save(createInscription);


                            // CREATE SOLDE
                            this.addSoldeToEleve(classe.get().getClasseMere(),userSave.getID());
                            var amb =modelMapper.map(userSave, EleveDtoResponse.class);
                            reponse.setMessage("Ce compte a été enregistré avec succès");
                            reponse.setResult(amb);
                            reponse.setCode(200);
                        }
                        else
                        {
                            reponse.setMessage("Veuillez renseigner le nom et prénon svp");
                            reponse.setCode(201);
                        }

                    }

                    else {
                        reponse.setMessage("Veuillez choisir un parent ou la classe svp");
                        reponse.setCode(201);
                    }
                }
                else
                {
                    reponse.setMessage("Veuillez renseigner l'année encours  ou la classe svp");
                    reponse.setCode(201);
                }

            }

         }
    catch (Exception e)
        {
            reponse.setCode(500);
            reponse.setMessage("Une erreur interne est survenue coté serveur  !"  );
        }

        return reponse ;
    }

    @Override
    public Reponse getAll() {
        Reponse reponse = new Reponse();
        try
        {

            List<EleveDtoResponse> users= daoEleve.findByStatus(true)
                    .stream()
                    .map(p->
                            {
                            var eleves= modelMapper.map(p,EleveDtoResponse.class);
                            eleves.setServiceAbonnees(this.getAllServicesByEleveID(p.getID()));
                            return  eleves;
                            }

                    ).collect(Collectors.toList());

            reponse.setResult(users);
            reponse.setMessage("Liste des élèves !");
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
            Optional<Eleve> IsExistGroupe=daoEleve.findById(id);
            IsExistGroupe.ifPresentOrElse(
                    (userP)->{

                        var result=modelMapper.map(IsExistGroupe.get(), EleveDtoResponse.class);
                        reponse.setResult(result);
                        reponse.setMessage("L'élève a été retrouvé avec succès");
                        reponse.setCode(200);


                    },
                    ()->{reponse.setMessage("L'élève n'existe pas");
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
            Optional<Eleve> IsExistGroupe=daoEleve.findById(id);

            if(IsExistGroupe.isPresent())
            {
                IsExistGroupe.get().setStatus(!IsExistGroupe.get().isStatus());
                Eleve userSave=daoEleve.save( IsExistGroupe.get());
                reponse.setResult(modelMapper.map(userSave, EleveDtoResponse.class));
                reponse.setMessage("Cet élève a été bloqué avec succès");
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
    public Reponse search(String el) {
        Reponse reponse = new Reponse();

        try
        {
            List<EleveDtoResponse> users= daoEleve.findByMatriculeContainingIgnoreCaseOrNomContainingIgnoreCaseAndStatus(el,el,true)
                    .stream()
                    .map(p->
                            {

                                var eleves= modelMapper.map(p,EleveDtoResponse.class);
                                eleves.setServiceAbonnees(this.getAllServicesByEleveID(p.getID()));

                                daoAnnee.findByStatus(true).ifPresent(
                                        annee -> {




                                            daoSolde.findByEleveIDAndAnneeScolaireID(p.getID(),annee.getID()).ifPresent(r->

                                                    {
                                                      var tps=   r.getServicesAbonnements().stream()
                                                              .filter( i-> i.getMois_payes().size() != 0)
                                                                .map(f->
                                                                {
                                                                   var tp=  modelMapper.map(f, Solde_TypePaiementResponse.class);

                                                                    if(f.getTypePaiement()  != null)
                                                                    {
                                                                        daoTypePaiement.findByID(f.getTypePaiement()).ifPresent( b->
                                                                                {
                                                                                    var  type_p= modelMapper.map(b,TypePaiementDtoResponse.class);
                                                                                    if(type_p.getClasseMere() != null){ daoClasse.findByID(type_p.getClasseMereID()).ifPresent(m-> type_p.setClasseMere(m)); }

                                                                                    tp.setTypePaiement(type_p);
                                                                                }


                                                                        );
                                                                    }
                                                                    return  tp;

                                                                }).collect(Collectors.toList());

                                                        eleves.setServicePayes(tps);

                                                        daoInscription.findByAnneeScolaireID(annee.getID()).stream()
                                                                        .filter( g-> g.getEleveID().compareTo(p.getID()) ==0)
                                                                        .forEach(h-> {
                                                                            daoClasse.findById(h.getClasseID()).ifPresent( s-> eleves.setClasse(s));

                                                                        });

                                                        daoSolde.findByEleveIDAndAnneeScolaireID(p.getID(),annee.getID()).ifPresent(h-> eleves.getSoldes().add(h) );


                                                    }


                                            );


                                        });

                                daoAnnee.findByStatus(true).ifPresent(h->
                                {
                                    var tp=daoServiceImpayes.findByAnneeScolaireIDAndEleveID(h.getID(),p.getID());

                                    if(tp.isPresent())
                                    {

                                        var serviceImpayesResponse=modelMapper.map(tp.get(), ServiceImpayesResponse.class);
                                        serviceImpayesResponse.setAnneeScolaire(h);
                                        serviceImpayesResponse.setEleveID(p.getID());
                                        serviceImpayesResponse.setEleve(p);

                                        Map<UUID,List<ServiceImpayesItem>> serices_m = tp.get().getServices().stream()
                                                .collect(groupingBy(ServiceImpayesItem::getTypePaiementID));

                                        serices_m.forEach( (f,g)->
                                        {
                                            var paiementType =  daoTypePaiement.findByID(f);
                                            if(paiementType.isPresent() && paiementType.get().getEstObligatoire().booleanValue() )
                                            {
                                                var sb= new Service_Mois() ;
                                                sb.setTypePaiement(paiementType.get());
                                                g.stream()
                                                        .filter(a-> a.getTypePaiementID().compareTo(f) == 0)
                                                        .forEach(m->sb.getMois_non_payes().add(m.getMois_non_paye()));

                                                serviceImpayesResponse.getMes_services().add(sb);
                                                eleves.setServiceImpayes(serviceImpayesResponse);


                                            }



                                        });


                                    }


                                });


                                return  eleves;
                            }

                    ).collect(Collectors.toList());


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
    public Reponse getDetails(UUID id) {
        Reponse reponse = new Reponse();
        try
        {
            Optional<Eleve> IsExistGroupe=daoEleve.findById(id);
            IsExistGroupe.ifPresentOrElse(
                    (userP)->{
                        HashMap<String, Object> map = new HashMap<>();
                        daoAnnee.findByStatus(true).ifPresentOrElse(
                                annee -> {
                                    var inscription=daoInscription.findByEleveIDAndAnneeScolaireID(userP.getID(),annee.getID());
                                    if(userP.getParentID() != null)
                                    {
                                        var parent=daoParent.findById(userP.getParentID());
                                        parent.ifPresent( p-> map.put("parent", modelMapper.map(p, ParentDtoResponse.class)) );

                                    }
                                    if(inscription.isPresent() && inscription.get().getClasseID() != null)
                                    {
                                        var classe=daoClasse.findById(inscription.get().getClasseID());
                                        classe.ifPresent( p->{
                                                    map.put("classe", modelMapper.map(p, ClasseDtoResponse.class));

                                                });

                                    }
                                    List<InscriptionDetailsDtoResponse> inscriptions= daoInscription.findByEleveID(userP.getID())
                                            .stream()
                                            .map(p->
                                                    {
                                                        var inscript = modelMapper.map(p,InscriptionDetailsDtoResponse.class);

                                                        daoClasse.findByID(p.getClasseID()).ifPresent(n->inscript.setClasse(n) );
                                                        daoEleve.findById(p.getEleveID()).ifPresent(n->inscript.setEleve(n));
                                                        daoAnnee.findByID(p.getAnneeScolaireID()).ifPresent(n->inscript.setAnneeScolaire(n));

                                                        if(p.getClasseID() != null)
                                                        {
                                                            daoClasse.findByID(p.getClasseID()).ifPresent( b->inscript.setMontantScolarite(b.getScolariteAnnuelle()));
                                                        }
                                                       return  inscript;
                                                    } ).collect(Collectors.toList());

                                    daoSolde.findByEleveIDAndAnneeScolaireID(userP.getID(),annee.getID()).ifPresent(r-> map.put("solde",r) );



                                                daoSolde.findByEleveIDAndAnneeScolaireID(userP.getID(),annee.getID()).ifPresent(r->

                                                        {
                                                            var tps=   r.getServicesAbonnements().stream()
                                                                    .filter( i-> i.getMois_payes().size() != 0)
                                                                    .map(f->
                                                                    {
                                                                        var tp=  modelMapper.map(f, Solde_TypePaiementResponse.class);

                                                                        if(f.getTypePaiement()  != null)
                                                                        {
                                                                            daoTypePaiement.findByID(f.getTypePaiement()).ifPresent( b->tp.setTypePaiement(modelMapper.map(b,TypePaiementDtoResponse.class)));
                                                                        }
                                                                        return  tp;

                                                                    }).collect(Collectors.toList());
                                                            map.put("servicesPayes",r);

                                                        }

                                                );







                                    map.put("servicesPayesCompletements", this. servicesPayesCompletements(userP.getID()));



                                    List<PaiementDtoResponse> paiements= daoPaiement.findByIdEleveAndIdAnneeScolaireAndStatus(userP.getID(),annee.getID(),false)
                                            .stream()
                                            .map(p->
                                            {

                                                var paie=  modelMapper.map(p,PaiementDtoResponse.class);
                                                this.paiementReponse(p, paie);
                                                return  paie;


                                            }).collect(Collectors.toList());




                                    map.put("serviceAbonnes",this.getAllServicesByEleveID(id));
                                    map.put("inscriptions",inscriptions);
                                    map.put("paiements",paiements);
                                    map.put("annee_scolaire", modelMapper.map(annee, AnneeDtoResponse.class));
                                    reponse.setResult(map);
                                    reponse.setMessage("Details de l'élève");
                                    reponse.setCode(200);
                                },
                                () -> {
                                    reponse.setMessage("Aucune année encours");
                                    reponse.setCode(201);
                                }
                        );

                    },
                    ()->{reponse.setMessage("L'élève n'existe pas");
                        reponse.setCode(201);}
            );



        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue" +e.getMessage());
            reponse.setCode(500);

        }

        return reponse;
    }
    public Reponse solde(UUID el) {
        Reponse reponse = new Reponse();
        try
        {
            daoAnnee.findByStatus(true).ifPresentOrElse(

                    annee -> {

                        daoSolde.findByEleveIDAndAnneeScolaireID(el,annee.getID()).ifPresentOrElse(
                                solde -> {

                                    reponse.setResult(solde);
                                    reponse.setMessage(" Solde obtenu !");
                                    reponse.setCode(200);
                                },
                                () -> {
                                    reponse.setMessage("aucun solde disponible!");
                                    reponse.setCode(201);
                                }
                        );




                    },
                    () -> {

                        reponse.setMessage("aucune année scolaire encours !");
                        reponse.setCode(201);
                    }
            );



        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue");
            reponse.setCode(500);

        }

        return reponse;
    }
    public Reponse getElevesByClasse( UUID classeID) {
        Reponse reponse = new Reponse();
        try
        {
            reponse.setResult(this.getAllElevesByClasseID(classeID));
            reponse.setMessage("Liste des élèves  recherchés!");
            reponse.setCode(200);
        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue");
            reponse.setCode(500);

        }

        return reponse;
    }
    private void paiementReponse(Paiement paiement,PaiementDtoResponse paie)
    {
        paie.setServices( new ArrayList<MoisPaiement_TypePaiementResponse>());
        var annee=daoAnnee.findByStatus(true);
        paie.setAnneeScolaire(annee.get());
        paie.setNumeroPaiement(paiement.getNumeroPaiement());
        annee.ifPresent( g-> paie.setAnneeScolaire(g));
        daoUser.findById(paiement.getIdCaissier()).ifPresent(
                c->{
                    var user =modelMapper.map(c, UserDtoResponse.class);
                    paie.setCaissier(user);
                });
        daoEleve.findById(paiement.getIdEleve()).ifPresent(
                c->{
                    var user =modelMapper.map(c, EleveDtoResponse.class);
                    paie.setEleve(user);
                });

        daoMoisPaiementTypePaiement.findByPaiementID(paiement.getID()).forEach( b->
        {


            daoTypePaiement.findById(b.getTypePaiementID()).ifPresent(
                    c->{
                        var user =modelMapper.map(b, MoisPaiement_TypePaiementResponse.class);
                        var data =modelMapper.map(c,TypePaiementDtoResponse.class);
                        user.setTypePaiement(data);
                            paie.getServices().add(user);


                    });
        });
    }

    public Reponse statEleve(UUID anneScolaire,Long dataDebut,Long dateFin)
    {
        Reponse reponse = new Reponse();
        HashMap<String, Object> map = new HashMap<>();
        List<StatItemClasseDtoResponse> stats= new ArrayList<>();

        try
        {
             List<Inscription> inscriptions=null;
            List<Inscription> inscription5derniers=null;
            List<PaiementDtoResponse> paiement5derniers=null;


            if(anneScolaire != null &&  dataDebut != null &&  dateFin != null
                    &&  dataDebut.longValue() != 0 &&  dateFin.longValue() != 0)
            {
                Timestamp timestampDebut = new Timestamp(dataDebut);
                Timestamp timestampFin = new Timestamp(dateFin);
                Date debut = new Date(timestampDebut.getTime());
                Date fin = new Date(timestampFin.getTime());

                inscriptions=daoInscription.findAllByCreatedOnBetweenAndAnneeScolaireID(debut,fin,anneScolaire);

                inscription5derniers=daoInscription.findAllByCreatedOnBetweenAndAnneeScolaireID(debut,fin,anneScolaire)
                        .stream().sorted((o1, o2) -> o2.getCreatedOn().compareTo(o1.getCreatedOn()))
                        .limit(5)
                        .collect(Collectors.toList());

                paiement5derniers=daoPaiement.findAllByCreatedOnBetweenAndIdAnneeScolaireAndStatus(debut,fin,anneScolaire,false)
                        .stream().sorted((o1, o2) -> o2.getCreatedOn().compareTo(o1.getCreatedOn()))
                        .map(p->{
                            var paie=  modelMapper.map(p,PaiementDtoResponse.class);

                            this.paiementReponse(p, paie);
                            return  paie;
                        })
                        .limit(5)
                        .collect(Collectors.toList());



            }
            else if(anneScolaire != null && ( dataDebut == null || dataDebut.longValue() == 0 )  && (dateFin == null || dateFin.longValue() == 0))
            {

                inscription5derniers=daoInscription.findByAnneeScolaireID(anneScolaire)
                        .stream().sorted((o1, o2) -> o2.getCreatedOn().compareTo(o1.getCreatedOn()))
                        .limit(5)
                        .collect(Collectors.toList());

                paiement5derniers=daoPaiement.findByIdAnneeScolaireAndStatus(anneScolaire,false)
                        .stream().sorted((o1, o2) -> o2.getCreatedOn().compareTo(o1.getCreatedOn()))
                        .map(p->{
                            var paie=  modelMapper.map(p,PaiementDtoResponse.class);

                            this.paiementReponse(p, paie);
                            return  paie;
                        })
                        .limit(5)
                        .collect(Collectors.toList());
                inscriptions=daoInscription.findByAnneeScolaireID(anneScolaire);


            }
            else if(anneScolaire == null &&  dataDebut != null &&  dateFin != null
                     &&  dataDebut.longValue() != 0 &&  dateFin.longValue() != 0)
            {
                Timestamp timestampDebut = new Timestamp(dataDebut);
                Timestamp timestampFin = new Timestamp(dateFin);
                Date debut = new Date(timestampDebut.getTime());
                Date fin = new Date(timestampFin.getTime());

                inscriptions=daoInscription.findAllByCreatedOnBetween(debut,fin);
                inscription5derniers=daoInscription.findAllByCreatedOnBetween(debut,fin)
                        .stream().sorted((o1, o2) -> o2.getCreatedOn().compareTo(o1.getCreatedOn()))
                        .limit(5)
                        .collect(Collectors.toList());

                paiement5derniers=daoPaiement.findAllByCreatedOnBetweenAndStatus(debut,fin,false)
                        .stream().sorted((o1, o2) -> o2.getCreatedOn().compareTo(o1.getCreatedOn()))
                        .map(p->{
                            var paie=  modelMapper.map(p,PaiementDtoResponse.class);

                            this.paiementReponse(p, paie);
                            return  paie;
                        })
                        .limit(5)
                        .collect(Collectors.toList());
            }
            else
            {
                long millisDebut=System.currentTimeMillis();
                long millisFin=System.currentTimeMillis()+24*60*60*1000;

                // creating a new object of the class Date
                Date dateDebutC = new Date(millisDebut);
                Date dateFinC = new Date(millisFin);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateDebutC);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.HOUR_OF_DAY, 0);

                var debut=calendar.getTime();
                var fin=dateFinC;

                inscriptions=daoInscription.findAllByCreatedOnBetween(debut,fin);
                inscription5derniers=daoInscription.findAllByCreatedOnBetween(debut,fin)
                        .stream().sorted((o1, o2) -> o2.getCreatedOn().compareTo(o1.getCreatedOn()))
                        .limit(5)
                        .collect(Collectors.toList());

                paiement5derniers=daoPaiement.findAllByCreatedOnBetweenAndStatus(debut,fin,false)
                        .stream().sorted((o1, o2) -> o2.getCreatedOn().compareTo(o1.getCreatedOn()))
                        .map(p->{
                            var paie=  modelMapper.map(p,PaiementDtoResponse.class);

                            this.paiementReponse(p, paie);
                            return  paie;
                        })
                        .limit(5)
                        .collect(Collectors.toList());

            }


            Map<UUID, Long> classeStatInscripts = inscriptions.stream()
                                .collect(groupingBy(Inscription::getClasseID,Collectors.counting()));

            Map<UUID, Double> classeStatRecette = inscriptions.stream()
                    .collect(groupingBy(Inscription::getClasseID,summingDouble(i-> {
                        var montant=daoPaiement.findByIdEleveAndStatus(i.getEleveID(),false)
                                .stream().mapToDouble(f-> f.getMontant()).sum();


                      return montant;


                    }) ));





            Map<Eleve, Double> recetteParEleve = inscriptions.stream()
                    .collect(groupingBy(c-> daoEleve.findById(c.getEleveID()).isPresent()
                                    ?daoEleve.findById(c.getEleveID()).get() :null ,
                            summingDouble(i-> {
                        var montant=daoPaiement.findByIdEleveAndStatus(i.getEleveID(),false)
                                .stream().mapToDouble(f-> f.getMontant()).sum();
                        return montant;
                    }) ));





            classeStatInscripts.forEach( (n,j)->
            {
                daoClasse.findById(n).ifPresent( h-> {
                    StatItemClasseDtoResponse tp = new StatItemClasseDtoResponse();
                    ClasseDtoResponse  userP=  modelMapper.map(h,ClasseDtoResponse.class);
                    if(h.getClasseMere() != null){ daoClasse.findByID(h.getClasseMere()).ifPresent(m-> userP.setClasseMere(m)); }
                    tp.setClasse(userP);
                    tp.setTotal(j);

                    classeStatRecette.forEach( (f,g)->
                    {
                        if(n.compareTo(f) == 0)
                        {
                            tp.setRecette(g);

                        }

                    });


                    stats.add(tp);

                });

            });

            var totalRecette=inscriptions.stream().map(n->
            {
                return  daoPaiement.findByIdEleveAndStatus(n.getEleveID(),false)
                        .stream().mapToDouble(f-> f.getMontant()).sum();

            }).filter(d-> d !=0).collect(Collectors.summingDouble(Double::intValue));


            List<InscriptionDtoResponse> inscription5derniersC= inscription5derniers
                    .stream()
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





            map.put("paiement5derniers", paiement5derniers);
            map.put("inscription5derniers", inscription5derniersC);
            map.put("classeIDS-NombreTotalParClasse", stats);
            map.put("totalRecette", totalRecette);
            map.put("totalAnnuelInscrits", inscriptions.size());
            map.put("recetteParEleve", recetteParEleve);
            reponse.setResult(map);
            reponse.setMessage("Statistiques!");
            reponse.setCode(200);

        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue");
            reponse.setCode(500);

        }

        return reponse;
    }

}
