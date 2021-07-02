/**
 * @author ClaireToutaneJules
 * @version 2/Juillet/2021
 * class lightEmprunt transitoire pour communication front/back
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

import java.time.LocalDate;

public class LightEmprunt {

    private int idEmprunt;
    private int idLivre;
    private String titreLivre;
    private String nomUser;
    private LocalDate dateEmprunt;
    private LocalDate dateRendu;

    /**
     * Methode toString classique pour affichage simple lors du debug
     *
     * @return une String représentant l'objet
     */
    @Override
    public String toString() {
        return "LightEmprunt{" + "idEmprunt=" + idEmprunt + ", idLivre=" + idLivre + ", titreLivre=" + titreLivre + ", nomUser=" + nomUser + ", dateEmprunt=" + dateEmprunt + ", dateRendu=" + dateRendu + '}';
    }

    /**
     *
     * @return id du livre que l'on veut emprunter
     */
    public int getIdLivre() {
        return idLivre;
    }

    /**
     *
     * @param idLivre du livre que l'on veut emprunter
     */
    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    /**
     *
     * @return id de l'emprunt
     */
    public int getIdEmprunt() {
        return idEmprunt;
    }

    /**
     *
     * @param idEmprunt
     */
    public void setIdEmprunt(int idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    /**
     *
     * @return titre du livre que l'on veut emprunter, non utilisé
     */
    public String getTitreLivre() {
        return titreLivre;
    }

    /**
     *
     * @param titreLivre que l'on veut emprunter
     */
    public void setTitreLivre(String titreLivre) {
        this.titreLivre = titreLivre;
    }

    /**
     *
     * @return nom de la personne effectuant l'emprunt
     */
    public String getNomUser() {
        return nomUser;
    }

    /**
     *
     * @param nomUser de la personne effectuant l'emprunt
     */
    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    /**
     *
     * @return date du début de l'emprunt
     */
    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    /**
     *
     * @param dateEmprunt du début de l'emprunt
     */
    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    /**
     *
     * @return date du rendu du livre a la bibliotheque
     */
    public LocalDate getDateRendu() {
        return dateRendu;
    }

    /**
     *
     * @param dateRendu du livre a la bibliotheque
     */
    public void setDateRendu(LocalDate dateRendu) {
        this.dateRendu = dateRendu;
    }

}
