/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
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
        logger.info("détail emprunt : " + lightEmprunt);
        if (lightEmprunt.getIdLivre() <= 0
                || lightEmprunt.getNomUser().equals("") || lightEmprunt.getDateEmprunt() == null) {
            message = "Vous n'avez pas remplis tout les champs correctement !";
        } else {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            try {
                Livre l = session.get(Livre.class, lightEmprunt.getIdLivre());
                if (l == null) {
                    message = "Pas de livre avec cet ID existe en base de donn&eacute;e !";
                    logger.info("Pas de livre avec cet ID n'exsite en BDD");
                } else if (l.getDisponible()) {
                    Emprunt emprunt = new Emprunt();
                    emprunt.setDateEmprunt(lightEmprunt.getDateEmprunt());
                    emprunt.setNomUser(lightEmprunt.getNomUser());
                    emprunt.setLivre(l);
                    l.setDisponible(false);
                    session.save(emprunt);
                    tx.commit();
                    logger.info("emprunt crée du livre : " + emprunt.getLivre());
                    message = "Emprunt cr&eacute;&eacute; avec succ&egrave;s !";
                } else {
                    logger.info("livre non disponible : impossible de crée l'emprunt");
                    message = "Ce livre n'est pas disponible !";
                }
            } catch (HibernateException e) {
                logger.warn("Rollback! " + e.getMessage());
                tx.rollback();
                message = "Erreur interne ! Contactez le support pour plus d'informations.";
            }
            session.close();
        }
        return message;
    }

    //méthode pour l'affichage de la liste des livres recherchés
    @RequestMapping(value = "/{titre}", method = RequestMethod.GET)
    public List<Livre> lire(@PathVariable String titre) {
        logger.info("Recherche des livres avec leurs titres commençant par : " + titre);
        Session ses = sessionFactory.openSession();
        String insHQL = "from Livre where titre like :t";
        @SuppressWarnings("unchecked")
        List<Livre> livres = ses.createQuery(insHQL).setParameter("t", titre + "%").getResultList();
        ses.close();
        return livres;
    }

    //Methode pour edit un emprunt
    @RequestMapping(value = "/envoi", method = RequestMethod.PUT)
    public String editEmprunt(@RequestBody LightEmprunt lightEmprunt) {
        String message;
        logger.info("détail emprunt a modif : " + lightEmprunt.getIdEmprunt() + " : " + lightEmprunt.getDateRendu());
        if (lightEmprunt.getIdEmprunt() <= 0 || lightEmprunt.getDateRendu() == null) {
            message = "Vous n'avez pas remplis tout les champs correctement !";
        } else {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            try {
                Emprunt emprunt = session.get(Emprunt.class, lightEmprunt.getIdEmprunt());
                if (emprunt == null) {
                    message = "Cet id d'emprunt n'existe pas en base de donn&eacute;es";
                } else if (emprunt.getDateRendu() != null) {
                    message = "Cet emprunt est d&eacute;ja archiv&eacute; (car a d&eacute;j&agrave; une date de rendu)";
                } else if (emprunt.getDateEmprunt().isAfter(lightEmprunt.getDateRendu())) {
                    message = "Attention : la date de rendu doit &ecirc;tre postérieure &agrave; la date d'emprunt !";
                } else {
                    emprunt.setDateRendu(lightEmprunt.getDateRendu());
                    emprunt.getLivre().setDisponible(true);
                    session.save(emprunt);
                    tx.commit();
                    logger.info("emprunt modifié, livre rendu le : " + emprunt.getDateRendu());
                    message = "Date de rendu enregistr&eacute;e !";
                }
            } catch (HibernateException e) {
                logger.warn("Rollback!" + e.getMessage());
                tx.rollback();
                message = "Erreur interne ! Contactez le support pour plus d'info.";
            }
            session.close();
        }
        return message;
    }

    //méthode pour l'affichage de la liste des emprunts dont le livre n'a pas encore été rendu, càd en cours
    @RequestMapping(value = "/emprunts", method = RequestMethod.GET)
    public List<Emprunt> lireEmprunt() {
        logger.info("Rechargement de la liste des emprunts en cours");
        Session ses = sessionFactory.openSession();
        String listeEmpruntsHQL = "from Emprunt where dateRendu is null order by dateEmprunt asc";
        @SuppressWarnings("unchecked")
        List<Emprunt> emprunts = ses.createQuery(listeEmpruntsHQL).list();
        ses.close();
        if (emprunts != null) {
            return emprunts;
        } else {
            return new ArrayList<>();
        }
    }
}
