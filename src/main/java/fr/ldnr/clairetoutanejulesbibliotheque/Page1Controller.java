/**
 * @author Claire
 * @version 28/June/2021 p.m. Controller REST pour la mise en base de donnée
 * d'objet livre via la page index.html
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

import java.time.LocalDate;
import org.hibernate.HibernateException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.hibernate.Session;
import org.hibernate.Transaction;

@RestController
@RequestMapping("/index")
public class Page1Controller {
    /**
     * Logger pour faire du log.
     */
    public static final Logger logger = LoggerFactory.getLogger(Page1Controller.class);
    
    /**
     * Permet de créer une SessionFactory avec les configurations souhaitées automatiquement
     */
    public SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /**
     * methode permettant l'ajout d'un livre en base de données
     * @param livre
     * @return String
     */
    @RequestMapping(value = "/envoi", method = RequestMethod.POST)
    public String envoi(@RequestBody Livre livre) {
        String message;
        logger.info("" + livre);
        if (livre.getAnnee() <= 0 
                || livre.getTitre().equals("") || livre.getEditeur().equals("") 
                || livre.getNomAuteur().equals("") || livre.getPrenomAuteur().equals("")){
            message = "Vous n'avez pas rempli tous les champs correctement !";
        } else {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = session.beginTransaction();
                try {
                    session.save(livre);
                    tx.commit();
                    session.close();
                    message = "Livre ajout&eacute; &agrave; la base de donn&eacute;es avec succ&egrave;s !";
                } catch (HibernateException e) {
                    tx.rollback();
                    logger.warn("ROLLBACK : " + e.getMessage());
                    message = "Erreur cot&eacute; serveur, contactez le support.";
                }
            }
        }
        return message;
    }
    
    /**
     * Methode de remplissage de la base de donnée pour test et démo
     */
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public void generate(){
    logger.info("Remplissage de la base de données avec des données, si si vraiment ! ");
    try (Session session = sessionFactory.openSession()) {
        Transaction tx = session.beginTransaction();
        try {
            Livre l1 = new Livre("Le rouge et le noir", 1830, "Galimard", "Stendhal", "Bob");
            session.save(l1);
            Livre l2 = new Livre("Le rouge et le noir", 1830, "Galimard", "Stendhal", "Bob");
            session.save(l2);
            Livre l3 = new Livre("Le rouge et le noir", 1830, "Galimard", "Stendhal", "Bob");
            session.save(l3);
            Livre l4 = new Livre("Le rouge et le noir", 1830, "Galimard", "Stendhal", "Bob");
            session.save(l4);
            Livre l5 = new Livre("Le rouge et le noir", 1830, "Galimard", "Stendhal", "Bob");
            session.save(l5);
            Livre l6 = new Livre("Le rouge et le noir", 1830, "Galimard", "Stendhal", "Bob");
            session.save(l6);
            Livre l7 = new Livre("Les misérables", 1868, "Les Classiques", "Hugo", "Victor");
            session.save(l7);
            Livre l8 = new Livre("Les misérables", 1868, "Les Classiques", "Hugo", "Victor");
            session.save(l8);
            Livre l9 = new Livre("Les misérables", 1868, "Les Classiques", "Hugo", "Victor");
            session.save(l9);
            Livre l10 = new Livre("Les misérables", 1868, "Les Classiques", "Hugo", "Victor");
            session.save(l10);
            Livre l11 = new Livre("Les misérables", 1868, "Les Classiques", "Hugo", "Victor");
            session.save(l11);
            Livre l12 = new Livre("Les misérables", 1868, "Les Classiques", "Hugo", "Victor");
            session.save(l12);
            Livre l13 = new Livre("Les misérables", 1868, "Les Classiques", "Hugo", "Victor");
            session.save(l13);
            Livre l14 = new Livre("Before After", 2015, "ToutanesAndCo", "Toadd", "Anna");
            session.save(l14);
            Livre l15 = new Livre("Star Wars", 2546, "Les Futuristes", "Hugo", "Victor");
            session.save(l15);
            Livre l16 = new Livre("Des misérables", 1999, "Les Classiques", "Arnaqueur", "Un");
            session.save(l16);
            Emprunt e1 = new Emprunt(l1, "Marc", LocalDate.of(2020, 1, 12), LocalDate.of(2020, 1, 19));
            session.save(e1);
            Emprunt e2 = new Emprunt(l9, "Marc", LocalDate.of(2020, 1, 20), null);
            l9.setDisponible(false);
            session.save(e2);
            Emprunt e3 = new Emprunt(l12, "Marc", LocalDate.of(2021, 5, 3), LocalDate.of(2021, 6, 19));
            session.save(e3);
            Emprunt e4 = new Emprunt(l15, "Marc", LocalDate.of(2021, 6, 12), null);
            session.save(e4);
            l15.setDisponible(false);
            Emprunt e5 = new Emprunt(l3, "Marc", LocalDate.of(2021, 3, 1), null);
            session.save(e5);
            l3.setDisponible(false);
            Emprunt e6 = new Emprunt(l5, "Marc", LocalDate.of(2021, 5, 12), LocalDate.of(2020, 7, 19));
            session.save(e6);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            tx.rollback();
            logger.warn("ROLLBACK : " + e.getMessage());
        }
    }
    }
}
