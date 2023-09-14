package com.app.ecole.service;
import com.app.ecole.dto.request.PaiementDtoRequest;
import com.app.ecole.dto.response.*;
import com.app.ecole.entities.*;
import com.app.ecole.repository.*;
import com.app.ecole.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

@Service
public class PaiementService  extends ServiceBase implements ICrudService <PaiementDtoRequest> {
    @Autowired
    private IDaoSolde daoSolde;
    @Autowired
    private IDaoSoldeTypePaiement daoSoldeTypePaiement;
    @Autowired
    private IDaoUser daoUser;
    @Autowired
    private IDaoClasse daoClasse;

    @Autowired
    private IDaoReduction daoReduction;
    @Autowired
    private IDaoAnnee daoAnnee;
    @Autowired
    private IDaoEleve daoEleve;
    @Autowired
    private IDaoMoisPaiementTypePaiement daoMoisPaiementTypePaiement;
    @Autowired
    private IDaoModePaiementPaiement daoModePaiementPaiement;
    @Autowired
    private IDaoInscription daoInscription;
    @Autowired
    private IDaoTypePaiement daoTypePaiement;
    @Autowired
    private IDaoModePaiement daoModePaiement;
    @Autowired
    private IDaoPaiement daoPaiement;
    @Autowired(required=true)
    private ModelMapper modelMapper;
    @Override
    public Reponse create(PaiementDtoRequest paiementDtoRequest) {
        Reponse reponse = new Reponse();
        try
        {
            var annee=daoAnnee.findByStatus(true);
            if(paiementDtoRequest.getIdEleve() != null && paiementDtoRequest.getIdCaissier() != null && annee.isPresent() )
            {
                var monSolde=daoSolde.findByEleveIDAndAnneeScolaireID(paiementDtoRequest.getIdEleve(),annee.get().getID());
                if(monSolde.isPresent())
                {

                    //ADD PAIEMENT
                    var groupeConverted =modelMapper.map(paiementDtoRequest, Paiement.class);
                    groupeConverted.setDatePaiement(Utility.today());
                    groupeConverted.setIdAnneeScolaire(annee.get().getID());
                    groupeConverted.setNumeroPaiement(Utility.genererNumeroPaiement(daoPaiement.findAll().size()+1));
                    groupeConverted.setEtat(Utility.PAIEMENT_PAYE);
                    groupeConverted.setStatus(false);
                    Paiement groupe1 = daoPaiement.save(groupeConverted);

                    monSolde.get().setAvant( (paiementDtoRequest.getAvant() != null ? paiementDtoRequest.getAvant(): 0 ));
                    monSolde.get().setCredit( (paiementDtoRequest.getCredit() != null ? paiementDtoRequest.getCredit(): 0 ));
                    daoSolde.save(monSolde.get());


                   var paiementDtoRequestUpdate= paiementDtoRequest.getMois_paiements().stream().map( n->{n.setIdAnneeScolaire(annee.get().getID());return n;} ).collect(Collectors.toList());

                    // ADD MOIS ET tYPE PAIEMENT  TO PAIEMENT
                    this.addMoisAndTypePaiementToSoldeDurinngPaiement(groupe1.getID(),paiementDtoRequest.getIdEleve(),annee.get().getID(),paiementDtoRequest.getAvant(),paiementDtoRequest.getCredit(),paiementDtoRequestUpdate);


                   // ADD MODE DE PAIEMENT TO PAIEMENT
                    paiementDtoRequest.getMode_paiements().stream()
                            .filter( c-> c.getModePaiementID() != null)
                            .map( t->  modelMapper.map(t, ModePaiement_Paiement.class))
                            .forEach( t-> {t.setPaiementID(groupe1.getID());daoModePaiementPaiement.save(t); });

                    var groupeSave =modelMapper.map(groupe1, PaiementDtoResponse.class);
                    this.paiementReponseCreate(paiementDtoRequest,groupe1, groupeSave);
                    reponse.setResult(groupeSave);
                    reponse.setCode(200);
                    reponse.setMessage(" Le paiement a reussi   !" );
                    return reponse;

                }
                else
                {
                    reponse.setCode(201);
                    reponse.setMessage(" aucun solde existe   !" );
                }

            }
            else
            {
                reponse.setCode(201);
                reponse.setMessage("soit  l'élève ,soit l'année scolaire,soit la/le caissier(e) n'existe pas !" );
            }

       }
        catch (Exception e)
        {
            reponse.setCode(500);
            reponse.setMessage("Une erreur interne est survenue coté serveur  !");
        }

        return reponse ;
    }

    @Override
    public Reponse getAll() {
        Reponse reponse = new Reponse();
        try
        {
            List<PaiementDtoResponse> users= daoPaiement.findByStatus(false)
                    .stream()
                    .map(p->{
                        var paie=  modelMapper.map(p,PaiementDtoResponse.class);

                        this.paiementReponse(p, paie);
                        return  paie;
                    }).collect(Collectors.toList());

            reponse.setResult(users);
            reponse.setMessage("Liste des paiements !");
            reponse.setCode(200);



        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue : ");
            reponse.setCode(500);

        }

        return reponse;
    }

    @Override
    public Reponse getById(UUID id) {
        Reponse reponse = new Reponse();
        try
        {
            Optional<Paiement> IsExistGroupe=daoPaiement.findById(id);
            IsExistGroupe.ifPresentOrElse(
                    (userP)->{

                        var result=modelMapper.map(userP, PaiementDtoResponse.class);
                        this.paiementReponse(userP, result);
                        reponse.setResult(result);
                        reponse.setMessage("Le paiement a été retrouvé avec succès");
                        reponse.setCode(200);


                    },
                    ()->{reponse.setMessage("Ce paiement n'existe pas");
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
            Optional<Paiement> user = daoPaiement.findById(id);
            var annee=daoAnnee.findByStatus(true);

            if(user.isPresent() && annee.isPresent())
            {

                if(user.get().getIdEleve() != null && user.get().getIdCaissier() != null)
                {
                    var monSolde = daoSolde.findByEleveIDAndAnneeScolaireID(user.get().getIdEleve(), annee.get().getID());
                    if (monSolde.isPresent())
                    {
                        user.get().setStatus(true);
                        user.get().setEtat(Utility.PAIEMENT_ANNULE);
                        daoPaiement.save(user.get());

                        monSolde.get().setAvant( monSolde.get().getAvant_precedent());
                        monSolde.get().setCredit( monSolde.get().getCredit_precedent());
                        daoSolde.save(monSolde.get());
                       var list= daoMoisPaiementTypePaiement.findByPaiementID(user.get().getID());

                        var paiementDtoRequestUpdate= list.stream().map( n->{n.setIdAnneeScolaire(annee.get().getID());return n;} ).collect(Collectors.toList());

                        // REMOVE MOIS ET tYPE PAIEMENT  TO PAIEMENT
                        this.removeMoisAndTypePaiementToSoldeDurinngPaiement(user.get().getIdEleve(),annee.get().getID(),paiementDtoRequestUpdate);

                        reponse.setResult(modelMapper.map(user.get(), PaiementDtoResponse.class));
                        reponse.setMessage("Ce paiement a été bloqué avec succès");
                        reponse.setCode(200);

                    }
                    else {
                        reponse.setMessage("L'annulation de paiement a échoué car le solde n'existe plus ");
                        reponse.setCode(201);
                    }
                }

            }
            else
            {
                reponse.setMessage("Ce paiement ou l'année scolaire n'existe plus");
                reponse.setCode(201);

            }


        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue");
            reponse.setCode(500);

        }

        return reponse;
    }
    private void paiementReponseCreate(PaiementDtoRequest paiementDtoRequest,Paiement paiement,PaiementDtoResponse paie)
    {
        paie.setServices( new ArrayList<MoisPaiement_TypePaiementResponse>());
        var annee=daoAnnee.findByStatus(true);
        paie.setAnneeScolaire(annee.get());
        paie.setNumeroPaiement(paiement.getNumeroPaiement());
        annee.ifPresent( g-> paie.setAnneeScolaire(g));
                if(paiementDtoRequest.getCredit() != null){
                    paie.setCredit(paiementDtoRequest.getCredit());

                }
        if(paiementDtoRequest.getAvant() != null){
            paie.setAvant(paiementDtoRequest.getAvant());

        }
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

        daoModePaiementPaiement.findByPaiementID(paiement.getID()).forEach( b->
        {


            daoModePaiement.findById(b.getModePaiementID()).ifPresent(
                    c->{
                        var data =modelMapper.map(c,ModePaiementDtoResponse.class);
                        data.setMontantSaisi(b.getMontant());
                        data.setModePaiement(c.getModePaiement());
                        paie.getMode_paiements().add(data);


                    });
        });

        paiementDtoRequest.getMois_paiements().stream().forEach(
                s->
                {
                    var  et=  new Solde_TypePaiementResponse();
                    et.setDernierMoisPaye(s.getDernierMoisPaye());
                    et.setMois_payes(s.getMois_paiement());

                    if(s.getTypePaiementID() != null)
                    {
                        daoTypePaiement.findById(s.getTypePaiementID()).ifPresent(
                                c->{
                                    var data =modelMapper.map(c,TypePaiementDtoResponse.class);
                                    et.setTypePaiement(data);

                                });
                    }


                    paie.getServicePayes().add(et);

                });








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

        daoMoisPaiementTypePaiement.findByPaiementID(paiement.getID())
                .stream()
                .filter(v-> v.getTypePaiementID() != null)
                .forEach( b->
        {


            daoTypePaiement.findById(b.getTypePaiementID()).ifPresent(
                    c->{
                        var user =modelMapper.map(b, MoisPaiement_TypePaiementResponse.class);
                        var data =modelMapper.map(c,TypePaiementDtoResponse.class);

                        user.setTypePaiement(data);
                        paie.getServices().add(user);


                    });
        });


                    daoSolde.findByEleveIDAndAnneeScolaireID(paiement.getIdEleve(),annee.get().getID()).ifPresent(r->

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

                                paie.setServicePayes(tps);



                            }


                    );





    }

    public Reponse getAllPaiementByUser(UUID userID,Long dataDebut,Long dateFin)
    {
        Reponse reponse = new Reponse();
        List<PaiementDtoResponse> paiements= new ArrayList<>();

        try
        {

            if(userID != null &&  dataDebut != null &&  dateFin != null)
            {

                Timestamp timestampDebut = new Timestamp(dataDebut);
                Timestamp timestampFin = new Timestamp(dateFin);
                Date debut = new Date(timestampDebut.getTime());
                Date fin = new Date(timestampFin.getTime());

                daoPaiement.findAllByCreatedOnBetweenAndIdCaissierAndStatus(debut,fin,userID,false)
                           .stream().forEach(p->
                        {
                            System.out.println(p);
                            var paie=  modelMapper.map(p,PaiementDtoResponse.class);
                            this.paiementReponse(p, paie);
                            paiements.add(paie);
                        });

                reponse.setMessage("Liste des paiements éffectués !");
                reponse.setCode(200);
                reponse.setResult(paiements);


            }
            else if(userID != null &&  dataDebut == null  && dateFin == null)
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

                daoPaiement.findAllByCreatedOnBetweenAndIdCaissierAndStatus(calendar.getTime(),dateFinC,userID,false)
                        .stream().forEach(p->
                        {
                            var paie=  modelMapper.map(p,PaiementDtoResponse.class);
                            this.paiementReponse(p, paie);
                            paiements.add(paie);
                        });

                reponse.setMessage("Liste des paiements éffectués !");
                reponse.setCode(200);
                reponse.setResult(paiements);

            }
            else if(userID == null)
            {

                if(dataDebut != null  && dateFin != null)
                {
                    Timestamp timestampDebut = new Timestamp(dataDebut);
                    Timestamp timestampFin = new Timestamp(dateFin);
                    Date debut = new Date(timestampDebut.getTime());
                    Date fin = new Date(timestampFin.getTime());
                    daoPaiement.findAllByCreatedOnBetweenAndStatus(debut,fin,false)
                            .stream().forEach(p->
                            {
                                System.out.println(p);
                                var paie=  modelMapper.map(p,PaiementDtoResponse.class);
                                this.paiementReponse(p, paie);
                                paiements.add(paie);
                            });
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



                    daoPaiement.findAllByCreatedOnBetweenAndStatus(calendar.getTime(),dateFinC,false)
                            .stream().forEach(p->
                            {
                                System.out.println(p);
                                var paie=  modelMapper.map(p,PaiementDtoResponse.class);
                                this.paiementReponse(p, paie);
                                paiements.add(paie);
                            });

                }





                reponse.setMessage("Liste des paiements éffectués !");
                reponse.setCode(200);
                reponse.setResult(paiements);

            }
            else
            {
                reponse.setMessage("Veuillez choisir le/la caissier(e) !");
                reponse.setCode(201);
            }






        }
        catch (Exception e)
        {
            reponse.setMessage("Une erreur interne est survenue" );
            reponse.setCode(500);

        }

        return reponse;
    }

    public Reponse cumulRecetteParService(UUID anneeID,Long dataDebut,Long dateFin)
    {
        Reponse reponse = new Reponse();
        List<PaiementSatDtoResponse> paiements= new ArrayList<>();
        Map<UUID, Double> classeStatRecette = new HashMap<>();
        try
        {

            if(anneeID != null &&  dataDebut != null &&  dateFin != null)
            {

                Timestamp timestampDebut = new Timestamp(dataDebut);
                Timestamp timestampFin = new Timestamp(dateFin);
                Date debut = new Date(timestampDebut.getTime());
                Date fin = new Date(timestampFin.getTime());


               classeStatRecette = daoMoisPaiementTypePaiement.findAllByCreatedOnBetweenAndIdAnneeScolaire(debut,fin,anneeID)
                        .stream()
                        .collect(groupingBy(MoisPaiement_TypePaiement::getTypePaiementID,
                                summingDouble(i->daoPaiement.findById(i.getPaiementID()).isPresent()
                                    ? daoPaiement.findById(i.getPaiementID()).get().getMontant() : 0) ));


            }
            else if(anneeID != null &&  dataDebut == null  && dateFin == null)
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

              classeStatRecette = daoMoisPaiementTypePaiement.findAllByCreatedOnBetweenAndIdAnneeScolaire(calendar.getTime(),dateFinC,anneeID)
                      .stream()
                      .collect(groupingBy(MoisPaiement_TypePaiement::getTypePaiementID,
                              summingDouble(i->daoPaiement.findById(i.getPaiementID()).isPresent()
                                      ? daoPaiement.findById(i.getPaiementID()).get().getMontant() : 0) ));

            }
            else if(anneeID == null)
            {

                if(dataDebut != null  && dateFin != null)
                {
                    Timestamp timestampDebut = new Timestamp(dataDebut);
                    Timestamp timestampFin = new Timestamp(dateFin);
                    Date debut = new Date(timestampDebut.getTime());
                    Date fin = new Date(timestampFin.getTime());
                   classeStatRecette = daoMoisPaiementTypePaiement.findAllByCreatedOnBetween(debut,fin)
                           .stream()
                           .collect(groupingBy(MoisPaiement_TypePaiement::getTypePaiementID,
                                   summingDouble(i->daoPaiement.findById(i.getPaiementID()).isPresent()
                                           ? daoPaiement.findById(i.getPaiementID()).get().getMontant() : 0) ));

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
                    classeStatRecette = daoMoisPaiementTypePaiement.findAllByCreatedOnBetween(calendar.getTime(),dateFinC)
                            .stream()
                            .collect(groupingBy(MoisPaiement_TypePaiement::getTypePaiementID,
                                    summingDouble(i->daoPaiement.findById(i.getPaiementID()).isPresent()
                                            ? daoPaiement.findById(i.getPaiementID()).get().getMontant() : 0) ));


                }


                classeStatRecette.forEach( (n,j)->
                {
                   daoTypePaiement.findByID(n).ifPresent(x->
                   {
                       daoClasse.findById(x.getClasseMereID()).ifPresent(w->
                               {
                                   PaiementSatDtoResponse tp = new PaiementSatDtoResponse();
                                   tp.setService(x);
                                   tp.setMontantTotal(j);
                                   tp.setClasse(w);
                                   paiements.add(tp);
                               });


                   });



                });

                reponse.setMessage("Liste des paiements éffectués par services!");
                reponse.setCode(200);
                reponse.setResult(paiements);
            }
            else
            {
                reponse.setMessage("Veuillez choisir ressayer !");
                reponse.setCode(201);
            }
        }
        catch (Exception e)
        {
            reponse.setMessage("Une erreur interne est survenue" );
            reponse.setCode(500);

        }

        return reponse;
    }

}
