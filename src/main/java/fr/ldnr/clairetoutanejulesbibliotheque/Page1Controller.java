/**
 * @author Claire
 * @version 28/June/2021 p.m.
 * Controller REST pour la mise en base de donnée
 * d'objet livre via la page index.html
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

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

    public static final Logger logger = LoggerFactory.getLogger(Page1Controller.class);

    public SessionFactory sessionFactory;
    
    // Permet de créer une SessionFactory avec les configurations souhaitées automatiquement
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @RequestMapping(value = "/envoi", method = RequestMethod.POST)
    public String envoi(@RequestBody Livre livre) {
        logger.info("" + livre);
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                session.save(livre);
                tx.commit();
                session.close();
                return "Reçu !";
            } catch (HibernateException e) {
                tx.rollback();
                logger.warn("ROLLBACK : " + e.getMessage());
            }
        }
        return "Erreur ?";
    }
}
