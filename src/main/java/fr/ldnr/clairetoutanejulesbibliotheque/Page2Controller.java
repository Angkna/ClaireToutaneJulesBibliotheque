/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author stag
 */
@RestController
@RequestMapping("/page2")
public class Page2Controller {

    //on ajoute un logger pour savoir si le serveur fonctionne
    public static final Logger logger = LoggerFactory.getLogger(Page2Controller.class);

    public SessionFactory sessionFactory;
    // Permet de créer une SessionFactory avec les configurations souhaitées automatiquement
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @RequestMapping(value = "/envoi", method = RequestMethod.POST)
    public String envoi(@RequestBody Emprunt emprunt) {
        logger.info("" + emprunt.getNomUser() + " " + emprunt.getDateEmprunt());
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(emprunt);
        tx.commit();
        session.close();
        return "Reçu !";
    }
}
