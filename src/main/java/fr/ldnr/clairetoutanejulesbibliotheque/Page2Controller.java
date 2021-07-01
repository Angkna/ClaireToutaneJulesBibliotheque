/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    //Methode pour crée un emprunt dans la bdd
    @RequestMapping(value = "/envoi", method = RequestMethod.POST)
    public String envoi(@RequestBody LightEmprunt lightEmprunt) {
        String message;
        logger.info("détail emprunt : " + lightEmprunt) ;
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Livre l = session.load(Livre.class, lightEmprunt.getIdLivre());
            Emprunt emprunt = new Emprunt();
            emprunt.setDateEmprunt(lightEmprunt.getDateEmprunt());
            emprunt.setNomUser(lightEmprunt.getNomUser());
            emprunt.setLivre(l);
            session.save(emprunt);
            tx.commit();
            logger.info("emprunt crée du livre : " + emprunt.getLivre() );
            message = "Reçu !";
        } catch (Exception e) {            
            logger.warn("Rollback!" + e.getMessage());
            tx.rollback();
            message = "Fail !";
        }
        session.close();
        return message;
    }
    
    //méthode pour l'affichage de la liste des livres recherchés
    @RequestMapping(value = "/{titre}", method = RequestMethod.GET)
    public List<Livre> lire(@PathVariable String titre)    {
        logger.info("Recherche livres avec comme titre : " + titre);
        Session ses = sessionFactory.openSession();
        String insHQL = "from Livre where titre like :t";
        @SuppressWarnings("unchecked")
        List<Livre> livres = ses.createQuery(insHQL).setParameter("t", titre + "%").getResultList();
        ses.close();
        if (livres != null){
            return livres;
        }else return new ArrayList<>();
    }
    
    //Methode pour edit un emprunt
    @RequestMapping(value = "/envoi", method = RequestMethod.PUT)
    public String editEmprunt(@RequestBody LightEmprunt lightEmprunt) {
        String message;
        logger.info("détail emprunt a modif : " + lightEmprunt.getIdEmprunt() + " : " + lightEmprunt.getDateRendu());
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Emprunt emprunt = session.load(Emprunt.class, lightEmprunt.getIdEmprunt());
            emprunt.setDateRendu(lightEmprunt.getDateRendu());
            session.save(emprunt);
            tx.commit();
            logger.info("emprunt modifié, livre rendu le : " + emprunt.getDateRendu());
            message = "Modification réussi !";
        } catch (Exception e) {            
            logger.warn("Rollback!" + e.getMessage());
            tx.rollback();
            message = "Fail !";
        }
        session.close();
        return message;
    }

    //méthode pour l'affichage de la liste des emprunts dont le livre n'a pas encore été rendu, càd en cours
    @RequestMapping(value = "/emprunts", method = RequestMethod.GET)
    public List<Emprunt> lireEmprunt() {
        logger.info("Emprunts en cours");
        Session ses = sessionFactory.openSession();
        String listeEmpruntsHQL = "from Emprunt where dateRendu is null order by dateEmprunt asc";
        @SuppressWarnings("unchecked")
        List<Emprunt> emprunts = ses.createQuery(listeEmpruntsHQL).list();
        ses.close();
        if (emprunts != null){
            return emprunts;
        } else {
            return new ArrayList<>();
        }
    }
}
