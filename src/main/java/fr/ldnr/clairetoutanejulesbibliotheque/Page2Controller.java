/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author stag
 */
@RestController
public class Page2Controller {
    //on ajoute un logger pour savoir si le serveur fonctionne
	public static final Logger logger = LoggerFactory.getLogger( Page2Controller.class);
	@RequestMapping("/health")
	public String health() {
		return "ok";
	}
    
}
