/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * @author Claire
 * @version 28/June/2021 p.m.
 */

@Entity // La classe sera stockée
@Table(name = "livres") // Sans précision, la table s'appellera du même nom que la classe.
@SuppressWarnings("PersistenceUnitPresent")
public class Livre implements Serializable {
    private int id;
    private String titre;
    private int annee;
    private String editeur;
    private String nomAuteur;
    private String prenomAuteur;
    private boolean disponible;
    
    public Livre() {
        id = 0;
        disponible = true;
    }

    public Livre(String titre, int annee, String editeur, String nomAuteur, String prenomAuteur) {
        this.id = 0;
        this.titre = titre;
        this.annee = annee;
        this.editeur = editeur;
        this.nomAuteur = nomAuteur;
        this.prenomAuteur = prenomAuteur;
        this.disponible = true;
    }
    
    @Override
    public String toString() {
        return id + " " + prenomAuteur + " " + nomAuteur + " - " + titre + " (" + editeur + ", " + annee + ")";
    }
    
    @Column(nullable = false)
    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(length = 100, nullable = false)
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Column(length = 4, nullable = false)
    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    @Column(length = 50, nullable = false)
    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    @Column(length = 50, nullable = false)
    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    @Column(length = 50, nullable = false)
    public String getPrenomAuteur() {
        return prenomAuteur;
    }

    public void setPrenomAuteur(String prenomAuteur) {
        this.prenomAuteur = prenomAuteur;
    }
    
}
