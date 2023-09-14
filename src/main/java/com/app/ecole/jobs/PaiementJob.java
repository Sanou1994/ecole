package com.app.ecole.jobs;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.app.ecole.entities.ServiceImpayes;
import com.app.ecole.entities.ServiceImpayesItem;
import com.app.ecole.repository.*;
import com.app.ecole.service.ServiceBase;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
@EnableScheduling
public class PaiementJob extends ServiceBase
{
    @Autowired
    private IDaoAnnee daoAnnee;

    @Scheduled(cron = "0 0 0 3 * ?")
    public void verfierServiceImpayesParMoisEtObligatoires()
    {

        var annee = daoAnnee.findByStatus(true);
        if(annee.isPresent())
        {

            Thread thread1 = new Thread()
            {
                public void run(){
                      checkMoisImpaye(annee.get().getID());

                    System.out.println("Thread 1 Running "+new Date());
                }
            };

            thread1.start();

            Thread thread2 = new Thread()
            {
                public void run(){
                    checkMoisImpaye(annee.get().getID());

                    System.out.println("Thread 2 Running "+new Date());
                }
            };

            thread2.start();

            Thread thread3 = new Thread()
            {
                public void run(){
                    checkMoisImpaye(annee.get().getID());

                    System.out.println("Thread 3 Running "+new Date());
                }
            };

            thread3.start();

            Thread thread4 = new Thread()
            {
                public void run(){
                    checkMoisImpaye(annee.get().getID());

                    System.out.println("Thread 4 Running "+new Date());
                }
            };

            thread4.start();

            Thread thread5 = new Thread()
            {
                public void run(){
                    checkMoisImpaye(annee.get().getID());

                    System.out.println("Thread 5 Running "+new Date());
                }
            };

            thread5.start();



        }


    }
}
