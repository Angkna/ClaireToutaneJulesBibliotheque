/**
 * Depart de notre application bibliotheque.
 * Point de lancement
 * 
 * @author ClaireToutaneJules
 * @version 02/July/2021
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
    //Attention a l'import : il y en a plein de différent, prendre le org.slf4j

    /**
     * Lance les loggers
     *
     */
    public static final Logger logger = LoggerFactory.getLogger(MainApplication.class);

    /**
     * Lance l'application
     *
     * @param args
     */
    public static void main(String[] args) {
        //code standard pour dire a SpringBoot de prendre la main.
        SpringApplication.run(MainApplication.class, args);
        logger.info("Mon message de log : Succes !");
    }
}
