/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

import java.time.LocalDate;

/**
 * class lightEmprunt pour communication front/back
 *
 */
public class LightEmprunt{

    private int idLivre;
    private String titreLivre;
    private String nomUser;
    private LocalDate dateEmprunt;
//    private LocalDate dateRendu;

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }
    
    public String getTitreLivre() {
        return titreLivre;
    }

    public void setTitreLivre(String titreLivre) {
        this.titreLivre = titreLivre;
    }
    
    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

//    public LocalDate getDateRendu() {
//        return dateRendu;
//    }
//
//    public void setDateRendu(LocalDate dateRendu) {
//        this.dateRendu = dateRendu;
//    }


}
