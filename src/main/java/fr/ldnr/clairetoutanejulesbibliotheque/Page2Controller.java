/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

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

    @RequestMapping(value = "/envoi", method = RequestMethod.POST)
    public String envoi(@RequestBody LightEmprunt lightEmprunt) {
        String message;
        logger.info("titre " + lightEmprunt.getIdLivre() + " " +lightEmprunt.getNomUser() + " " + lightEmprunt.getDateEmprunt() );
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Livre l = session.load(Livre.class, lightEmprunt.getIdLivre());
//            String insHQL = "from Livre where id = :id";
//            @SuppressWarnings("unchecked")
//            List<Livre> livres = session.createQuery(insHQL).setParameter("id", lightEmprunt.getIdLivre()).getResultList();
            Emprunt emprunt = new Emprunt();
            emprunt.setDateEmprunt(lightEmprunt.getDateEmprunt());
            emprunt.setNomUser(lightEmprunt.getNomUser());
//            emprunt.setLivre(livres.get(0));
            emprunt.setLivre(l);
            session.save(emprunt);
            tx.commit();
            logger.info("emprunt crée du livre : " + emprunt.getLivre() );
            message = "Reçu !";
        } catch (Exception e) {
            tx.rollback();
            logger.warn("Rollback!" + e.getMessage());
            message = "Fail !";
        }
        session.close();
        return message;
    }

    @RequestMapping(value = "/{titre}", method = RequestMethod.GET)
    public List<Livre> lire(@PathVariable String titre)    {
        logger.info("Recherche livres avec comme titre : " + titre);
        Session ses = sessionFactory.openSession();
        String insHQL = "from Livre where titre like :t";
        @SuppressWarnings("unchecked")
        List<Livre> livres = ses.createQuery(insHQL).setParameter("t", titre + "%").getResultList();
        ses.close();
        return livres;
    }

}
