/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Claire
 * @version 28/June/2021 p.m.
 */

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
<<<<<<< HEAD
        // Permet de créer une SessionFactory avec les configurations souhaitées automatiquement
=======
>>>>>>> 619755dba6036fd7adc5deca7d1d23291d0a2910
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    
    @RequestMapping(value="/envoi", method=RequestMethod.POST)
	public String envoi(@RequestBody Livre livre) {
		logger.info("" + livre);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(livre);
		tx.commit();
		session.close();
<<<<<<< HEAD
		return "Reçu !";
=======
		return "ok";
>>>>>>> 619755dba6036fd7adc5deca7d1d23291d0a2910
	}

}
