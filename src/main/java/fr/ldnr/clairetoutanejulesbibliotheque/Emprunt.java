/**
 *@Author ClaireToutaneJules
 * @Version 02/July/2021
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Permet de créer des objets emprunts et de les manipuler dans l'application
 *
 */
@Entity
@Table(name = "Emprunts")
@SuppressWarnings("PersistenceUnitPresent")
public class Emprunt implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Livre livre;
    private String nomUser;
    private LocalDate dateEmprunt;
    private LocalDate dateRendu;
/**
 * Constructeur sans paramètre pour utilisation du bean
 */
    public Emprunt() {

    }
/**
 * Constructeur avec paramètres pour utilisation de l'objet Emprunt dans l'application
 * @param livre
 * @param nomUser
 * @param dateEmprunt
 * @param dateRendu 
 */
    public Emprunt(Livre livre, String nomUser, LocalDate dateEmprunt, LocalDate dateRendu) {
        this.livre = livre;
        this.nomUser = nomUser;
        this.dateEmprunt = dateEmprunt;
        this.dateRendu = dateRendu;
    }

    /**
     * @return id Methode permettant de récupérer l'id de l'emprunt
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     * Methode permettant de mettre en place un id de l'emprunt
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Methode permettant de récupérer un livre
     *
     * @return livre
     */
    @ManyToOne
    public Livre getLivre() {
        return livre;
    }

    /**
     * Methode permettant de mettre en place un livre
     *
     * @param livre
     */
    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    /**
     * Methode permettant de récupérer un nom d'utilisateur
     *
     * @return nomUser
     */
    @Column(length = 100, nullable = false)
    public String getNomUser() {
        return nomUser;
    }

    /**
     * Methode permettant de mettre en place un nom d'utilisateur
     *
     * @param nomUser
     */
    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    /**
     * Methode permettant de récupérer une date d'emprunt
     *
     * @return dateEmprunt
     */
    @Column(nullable = false)
    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    /**
     * Methode permettant de mettre en place une date d'emprunt au livre
     * emprunté
     *
     * @param dateEmprunt
     */
    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.livre.setDisponible(false);
        this.dateEmprunt = dateEmprunt;
    }

    /**
     * Methode permettant de récupérer une date de rendu du livre emprunté
     *
     * @return dateRendu
     */
    @Column
    public LocalDate getDateRendu() {
        return dateRendu;
    }

    /**
     * Methode permettant de mettre en place la date à laquelle le livre
     * emprunté est rendu
     *
     * @param dateRendu
     */
    public void setDateRendu(LocalDate dateRendu) {
        this.livre.setDisponible(true);
        this.dateRendu = dateRendu;
    }

}
