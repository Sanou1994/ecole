package com.app.ecole.service;

import com.app.ecole.dto.response.MoisPaiement_TypePaiementResponse;
import com.app.ecole.dto.response.TypePaiementDtoResponse;
import com.app.ecole.entities.*;
import com.app.ecole.repository.*;
import com.app.ecole.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceBase
{
    @Autowired
    private  IDaoClasse daoClasse;
    @Autowired
    private IDaoServiceImpayes daoServiceImpayes;
    @Autowired
    private IDaoServicesImpayesItem daoServicesImpayesItem;
    @Autowired
    private IDaoSoldeTypePaiement daoSoldeTypePaiement;
    @Autowired(required=true)
    private  ModelMapper modelMapper;
    @Autowired
    private IDaoAnnee daoAnnee;
    @Autowired
    private IDaoMoisPaiementTypePaiement daoMoisPaiementTypePaiement;
    @Autowired
    private IDaoTypePaiement daoTypePaiement;
    @Autowired
    private  IDaoEleve daoEleve;
    @Autowired
    private  IDaoInscription daoInscription;
    @Autowired
    private IDaoSolde daoSolde;
    public  List<Eleve> getAllElevesByClasseMereID(UUID classeMereID)
    {
        List<Eleve> eleves=new ArrayList<>();;
        try
        {

            if(classeMereID != null)
            {
                daoClasse.findByClasseMereAndStatus(classeMereID,true)
                        .stream()
                        .forEach( n->
                        {
                            System.out.println(n);
                            daoInscription.findByClasseID(n.getID())
                                    .stream()
                                    .forEach(p->
                                            {
                                                var eleve = daoEleve.findById(p.getEleveID());
                                                eleve.ifPresent( v-> eleves.add(v));


                                            }

                                    );



                        });
            }
            return eleves ;

        }
        catch (Exception e)
        {
            System.out.println(e);
            return eleves ;

        }

    }
    public  List<Eleve> getAllElevesByAnneeScolaireID(UUID anneeScolaireID)
    {
        List<Eleve> eleves=new ArrayList<>();;
        try
        {

               daoAnnee.findByID(anneeScolaireID).ifPresent( s->
               {
                   daoInscription.findByAnneeScolaireID(s.getID())
                           .stream()
                           .forEach(p->
                                   {
                                       var eleve = daoEleve.findById(p.getEleveID());
                                       eleve.ifPresent( v-> eleves.add(v));
                                   }

                           );

               });
            return eleves ;

        }
        catch (Exception e)
        {
            return eleves ;

        }

    }
    public  List<Eleve> getAllElevesByClasseID(UUID classeID)
    {
        List<Eleve> eleves=new ArrayList<>();;
        try
        {

            if(classeID != null)
            {
                daoClasse.findByID(classeID)
                        .stream()
                        .forEach( n->
                        {
                            daoInscription.findByClasseID(n.getID())
                                    .stream()
                                    .forEach(p->
                                            {
                                                var eleve = daoEleve.findById(p.getEleveID());
                                                eleve.ifPresent( v-> eleves.add(v));


                                            }

                                    );



                        });
            }
            return eleves ;

        }
        catch (Exception e)
        {
            return eleves ;

        }

    }
    public  void addTypePaiementToEleve(UUID classeMereID, Type_Paiement typePaiement)
    {
            daoAnnee.findByStatus(true).ifPresent(g->
            {
                if(typePaiement.getID()!= null && classeMereID != null )
                {
                    this.getAllElevesByClasseMereID(classeMereID)
                            .stream()
                            .forEach( s->
                                    {
                                        daoSolde.findByEleveIDAndAnneeScolaireID( s.getID(),g.getID()).ifPresent(
                                                k->{
                                                    Solde_TypePaiement solde_TypePaiement= new Solde_TypePaiement();
                                                    solde_TypePaiement.setStatus(true);
                                                    solde_TypePaiement.setTypePaiement(typePaiement.getID());
                                                    solde_TypePaiement.setEtat("ENCOURS");
                                                    solde_TypePaiement.setAnnuelle(typePaiement.isEstAnnuelle());
                                                    solde_TypePaiement.setMontantRestant(typePaiement.getMontant_annuel()!=0 ?  typePaiement.getMontant_annuel() :typePaiement.getMontant_mensuel());
                                                    Solde_TypePaiement solde_TypePaiementCreate= daoSoldeTypePaiement.save(solde_TypePaiement);
                                                    k.getServicesAbonnements().add(solde_TypePaiementCreate);
                                                    daoSolde.save(k);

                                                });
                                    } );



                }});

    }
    public  List<TypePaiementDtoResponse> getAllServicesByEleveID(UUID eleveID)
    {
        List<TypePaiementDtoResponse> type_Paiement=new ArrayList<>();;
        try
        {

            if(eleveID != null)
            {

                            daoInscription.findByEleveID(eleveID)
                                    .stream()
                                    .forEach(p->
                                            {
                                                var classe = daoClasse.findById(p.getClasseID());
                                                classe.ifPresent( v-> {
                                                    if (v.getClasseMere() != null) {
                                                         daoTypePaiement.findByClasseMereIDAndStatus(v.getClasseMere(),true)
                                                                .stream()
                                                                .map(x -> modelMapper.map(x, TypePaiementDtoResponse.class))
                                                                .forEach( b->type_Paiement.add(b));
                                                    }

                                                });


                                            }

                                    );




            }
            return type_Paiement ;

        }
        catch (Exception e)
        {
            return type_Paiement ;

        }

    }

    public void addSoldeToEleve(UUID classeMereID,UUID eleveID)
    {
        // CREATE SOLDE
        Solde solde= new Solde();
        solde.setEleveID(eleveID);
        solde.setStatus(true);
        daoAnnee.findByStatus(true).ifPresent( d-> solde.setAnneeScolaireID(d.getID()));
        daoTypePaiement.findByClasseMereIDAndStatus(classeMereID,true)
                .stream().forEach(k-> {
                    Solde_TypePaiement solde_TypePaiement= new Solde_TypePaiement();
                    solde_TypePaiement.setStatus(true);
                    solde_TypePaiement.setEtat("ENCOURS");
                    solde_TypePaiement.setTypePaiement(k.getID());
                    solde_TypePaiement.setAnnuelle(k.isEstAnnuelle());
                    solde_TypePaiement.setMontantRestant(k.getMontant_annuel()!=0 ?  k.getMontant_annuel() :k.getMontant_mensuel());
                    Solde_TypePaiement solde_TypePaiementCreate= daoSoldeTypePaiement.save(solde_TypePaiement);
                    solde.getServicesAbonnements().add(solde_TypePaiementCreate);
                });
        daoSolde.save(solde);
    }

    public List<Object> servicesPayesCompletements(UUID eleveID)
    {
        List<Object> my_List= new ArrayList<>();
        daoAnnee.findByStatus(true).ifPresent(annee ->
        {
            daoSolde.findByEleveIDAndAnneeScolaireID(eleveID, annee.getID()).ifPresent(r ->
            {
                r.getServicesAbonnements().stream()
                        .filter(c -> c.getMontantRestant() == 0 && !c.isStatus())
                        .map(p ->
                        {
                            if (p.getDernierPaiementID() != null) {
                                var mois_sole = daoMoisPaiementTypePaiement.findByPaiementID(p.getDernierPaiementID())
                                        .stream()
                                        .map(x ->
                                        {
                                            var paie = modelMapper.map(x, MoisPaiement_TypePaiementResponse.class);

                                            if (x.getTypePaiementID() != null) {
                                                daoTypePaiement.findByID(x.getTypePaiementID()).ifPresent(b -> paie.setTypePaiement(modelMapper.map(b, TypePaiementDtoResponse.class)));
                                            }
                                            return paie;
                                        }).collect(Collectors.toList());

                                return mois_sole;
                            } else {
                                return p;
                            }

                        })
                        .forEach( e-> my_List.add(e));

            });

        });
        return  my_List;

    }

public void addMoisAndTypePaiementToSoldeDurinngPaiement(UUID paiementID,UUID eleveID,UUID anneeID,Float avant,Float credit,List<MoisPaiement_TypePaiement> mois_paiements )
{
    var monSolde=daoSolde.findByEleveIDAndAnneeScolaireID(eleveID,anneeID);

    if(avant != null && avant.floatValue() > 0)
    {
        monSolde.get().setAvant_precedent(monSolde.get().getAvant());
        monSolde.get().setAvant(avant);


    }
    if(credit != null && credit.floatValue() > 0)
    {
        monSolde.get().setCredit_precedent(monSolde.get().getCredit_precedent());
        monSolde.get().setCredit(credit);

    }
    daoSolde.save(monSolde.get());
    mois_paiements.stream()
            .filter( c-> c.getTypePaiementID() != null)
            .filter(p-> monSolde.get().getServicesAbonnements().stream().filter(c->c.getTypePaiement().compareTo(p.getTypePaiementID())==0).count() !=0)
            .map(b-> {
                var tp=monSolde.get().getServicesAbonnements().stream().filter(c->c.getTypePaiement().compareTo(b.getTypePaiementID())==0).findFirst();

                var mois_payes=tp.get().getMois_payes();
                var mois_a_payer=b.getMois_paiement().stream().filter( c->!mois_payes.contains(c)).collect(Collectors.toList());
                b.setMois_paiement(mois_a_payer);
                return  b;
            })
            .filter( s-> {
                var tp=monSolde.get().getServicesAbonnements().stream().filter(c->c.getTypePaiement().compareTo(s.getTypePaiementID())==0).findFirst();

                var typePaiementRedu = daoTypePaiement.findByID(s.getTypePaiementID());

                var montantAPayer=(typePaiementRedu.get().getMontant_annuel() != 0)?
                        s.getMois_paiement().size() *typePaiementRedu.get().getMontant_mensuel() : typePaiementRedu.get().getMontant_mensuel();
                return  typePaiementRedu.isPresent() && tp.isPresent() && montantAPayer <= tp.get().getMontantRestant();

            })

            .map( t-> {t.setPaiementID(paiementID); return  daoMoisPaiementTypePaiement.save(t); })

            .forEach(o->
            {
                var tp=monSolde.get().getServicesAbonnements().stream().filter(c->c.getTypePaiement().compareTo(o.getTypePaiementID())==0).findFirst();

                var typePaiementRedu = daoTypePaiement.findByID(o.getTypePaiementID());

                var montantAPayer=(typePaiementRedu.get().getMontant_annuel() != 0)?
                        o.getMois_paiement().size() *typePaiementRedu.get().getMontant_mensuel() : typePaiementRedu.get().getMontant_mensuel();

                if(typePaiementRedu.isPresent() && tp.isPresent() && montantAPayer <= tp.get().getMontantRestant())
                {

                    if( tp.get().isAnnuelle() && montantAPayer == tp.get().getMontantRestant())
                    {
                        tp.get().setStatus(false);
                        tp.get().setDernierPaiementID(paiementID);
                        tp.get().setEtat(Utility.PAIEMENT_PAYE);
                    }

                    tp.get().setDernierMoisPaye( o.getDernierMoisPaye());


                    o.getMois_paiement().forEach( q-> tp.get().getMois_payes().add(q) );
                  var list=  tp.get().getMois_payes()
                            .stream()
                            .filter(g-> Utility.isNumeric(g))
                            .map(v-> Integer.valueOf(v))
                            .sorted(Comparator.reverseOrder())
                            .collect(Collectors.toList());

                    if(list.size() <= 1)
                    {
                        tp.get().setAvantDernierMoisPaye(o.getDernierMoisPaye());

                    }
                    else {
                        tp.get().setAvantDernierMoisPaye(list.get(1).toString());

                    }

                    if(tp.get().isAnnuelle())
                    {
                        tp.get().setMontantRestant(tp.get().getMontantRestant().floatValue() - montantAPayer);

                    }
                    daoSoldeTypePaiement.save(tp.get());

                }
            });
}

public  void checkMoisImpaye(UUID annee)
{

    var liste=this.getAllElevesByAnneeScolaireID(annee);

    for (int o = 0;o<liste.size();o++)
    {
        var g=liste.get(o);
        var monSolde= daoSolde.findByEleveIDAndAnneeScolaireID(g.getID(),annee);
        if(monSolde.isPresent())
        {
            var h=monSolde.get();
            var abonnes= h.getServicesAbonnements();

            for(int u = 0; u<abonnes.size();u++ )
            {
                Date now = new Date();
                var k= abonnes.get(u);
                var  type=  daoTypePaiement.findByID(k.getTypePaiement());
                k.setDernierMoisPaye(k.getDernierMoisPaye() !=null ? (Integer.valueOf(k.getDernierMoisPaye())< Integer.valueOf(Utility.DERNIER_PAYE_INITIAL) ? String.valueOf(Integer.valueOf(k.getDernierMoisPaye()) +12) : k.getDernierMoisPaye() ) :Utility.DERNIER_PAYE_INITIAL);

                if( Utility.isNumeric(k.getDernierMoisPaye()) && Integer.valueOf(k.getDernierMoisPaye()) < Utility.getMonthNumberForJob(now)
                     && type.isPresent() && type.get().getEstObligatoire().booleanValue())
                {

                    var element=daoServiceImpayes.findByAnneeScolaireIDAndEleveID(annee,h.getEleveID());
                    if(element.isPresent())
                    {
                        for (int j=Integer.valueOf(k.getDernierMoisPaye())+1;j<=Utility.getMonthNumber(now);j++)
                        {
                            var t=j;

                                if(!element.get().getServices().stream().map(w->w.getMois_non_paye()).toList().contains(String.valueOf(t)))
                                {
                                    var serviceImpayesItem = new ServiceImpayesItem();
                                    serviceImpayesItem.setStatus(true);
                                    serviceImpayesItem.setMois_non_paye(String.valueOf(t));
                                    serviceImpayesItem.setTypePaiementID(k.getTypePaiement());
                                    element.get().getServices().add(daoServicesImpayesItem.save(serviceImpayesItem));
                                    daoServiceImpayes.save(element.get());

                                }


                        }

                    }
                    else
                    {
                        var serviceImpayes = new ServiceImpayes();
                        serviceImpayes.setEleveID(h.getEleveID());
                        serviceImpayes.setAnneeScolaireID(annee);
                        serviceImpayes.setStatus(true);

                        for (int i=Integer.valueOf(k.getDernierMoisPaye())+1;i<=Utility.getMonthNumber(now);i++)
                        {

                            var serviceImpayesItem = new ServiceImpayesItem();
                            serviceImpayesItem.setStatus(true);
                            serviceImpayesItem.setMois_non_paye(String.valueOf(i));
                            serviceImpayesItem.setTypePaiementID(k.getTypePaiement());
                            serviceImpayes.getServices().add(daoServicesImpayesItem.save(serviceImpayesItem));

                        }
                        daoServiceImpayes.save(serviceImpayes);


                    }

                }



            }


        }

    }

  }

    public void removeMoisAndTypePaiementToSoldeDurinngPaiement(UUID eleveID,UUID anneeID,List<MoisPaiement_TypePaiement> mois_paiements )
    {
        var monSolde=daoSolde.findByEleveIDAndAnneeScolaireID(eleveID,anneeID);

        mois_paiements.stream()
                .filter( c-> c.getTypePaiementID() != null)
                .filter(p-> monSolde.get().getServicesAbonnements().stream().filter(c->c.getTypePaiement().compareTo(p.getTypePaiementID())==0).count() !=0)
                .map(b->
                {
                    var tp=monSolde.get().getServicesAbonnements().stream().filter(c->c.getTypePaiement().compareTo(b.getTypePaiementID())==0).findFirst();
                    var mois_payes=tp.get().getMois_payes();

                    b.getMois_paiement().stream().filter( c->mois_payes.contains(c))
                            .forEach(t->{tp.get().getMois_payes().remove(t); daoSoldeTypePaiement.save(tp.get());});
                    return  b;
                })
                .filter( s-> {
                    var tp=monSolde.get().getServicesAbonnements().stream().filter(c->c.getTypePaiement().compareTo(s.getTypePaiementID())==0).findFirst();

                    var typePaiementRedu = daoTypePaiement.findByID(s.getTypePaiementID());
                    return  typePaiementRedu.isPresent() && tp.isPresent() ;

                })
                .forEach(o->
                {
                    var tp=monSolde.get().getServicesAbonnements().stream().filter(c->c.getTypePaiement().compareTo(o.getTypePaiementID())==0).findFirst();

                    var typePaiementRedu = daoTypePaiement.findByID(o.getTypePaiementID());

                    var montantAPayer=(typePaiementRedu.get().getMontant_annuel() != 0)?
                            o.getMois_paiement().size() *typePaiementRedu.get().getMontant_mensuel() : typePaiementRedu.get().getMontant_mensuel();

                    if(typePaiementRedu.isPresent() && tp.isPresent())
                    {


                        tp.get().setDernierMoisPaye( o.getAvantDernierMoisPaye());
                        if(tp.get().isAnnuelle())
                        {
                            tp.get().setMontantRestant(tp.get().getMontantRestant().floatValue() + montantAPayer);

                        }
                        daoSoldeTypePaiement.save(tp.get());

                    }
                });
    }

}
