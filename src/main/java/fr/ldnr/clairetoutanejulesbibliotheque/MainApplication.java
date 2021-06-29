/*
 * Depart de notre application bibliotheque.
 * Point de lancement
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author stag
 */
@SpringBootApplication
public class MainApplication {
    	//Attention a l'import : il y en a plein de différent, prendre le org.slf4j
	public static final Logger logger = LoggerFactory.getLogger(MainApplication.class);

	public static void main(String[] args) {
		//code standard pour dire a SpringBoot de prendre la main.
		SpringApplication.run(MainApplication.class, args);
		logger.info("Mon message de log : Succes !");
	}
}
