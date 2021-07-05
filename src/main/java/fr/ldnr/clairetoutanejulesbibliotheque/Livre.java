/**
 * @author ClaireToutaneJules
 * @version 02/July/2021
 *
 * Entité Livre qui sera persisté dans la base de donnée.
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

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
    private boolean disponible; // ATTENTION : redondant (car check via les emprunt possible)

    /**
     * Constructeur sans paramètre Ce constructeur définit par défaut l'id à 0
     * et le livre comme disponible à l'emprunt
     */
    public Livre() {
        id = 0;
        disponible = true;
    }

    /**
     * Constructeur avec paramètres
     *
     * @param titre
     * @param annee
     * @param editeur
     * @param nomAuteur
     * @param prenomAuteur Ce constructeur hydrate ces 5 attributs du nouveau
     * livre avec les variables passées comme paramètres du constructeur Ce
     * constructeur définit par défaut l'id à 0 et le livre comme disponible à
     * l'emprunt
     */
    public Livre(String titre, int annee, String editeur, String nomAuteur, String prenomAuteur) {
        this.id = 0;
        this.titre = titre;
        this.annee = annee;
        this.editeur = editeur;
        this.nomAuteur = nomAuteur;
        this.prenomAuteur = prenomAuteur;
        this.disponible = true;
    }

    /**
     * Re-définition de la méthode toString pour afficher les attributs d'un
     * livre
     *
     * @return String
     */
    @Override
    public String toString() {
        return id + " " + prenomAuteur + " " + nomAuteur + " - " + titre
                + " (" + editeur + ", " + annee + "). Disponible : " + disponible + ".";
    }

    /**
     * Méthode getDisponible pour récupérer l'état de disponibilité d'un livre
     *
     * @return boolean
     */
    @Column(nullable = false)
    public boolean getDisponible() {
        return disponible;
    }

    /**
     * Méthode setDisponible pour définir l'état de disponibilité d'un livre
     *
     * @param disponible
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Méthode getId pour récupérer l'id d'un livre
     *
     * @return int L'annotation @Id définit que l'attribut id sera considéré
     * comme la primary key de la table livres dans notre base de données
     * L'annotation @GeneratedValue permet à l'id de s'auto-incrémenter
     * L'annotation @Column(name = "id") définit que l'attribut id sera une
     * colonne de nom "id" de la table livres dans notre BDD
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    /**
     * Méthode setId pour définir l'id d'un livre
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Méthode getTitre pour récupérer le titre d'un livre
     *
     * @return String L'annotation @Column(length = 100, nullable = false)
     * définit que l'attribut titre sera une colonne de la table livres dans
     * notre BDD + Le titre devra comporter 100 caractères au maximum et ne
     * pourra pas être nul
     */
    @Column(length = 100, nullable = false)
    public String getTitre() {
        return titre;
    }

    /**
     * Méthode setTitre pour définir le titre d'un livre
     *
     * @param titre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Méthode getAnnee pour récupérer l'année d'édition d'un livre
     *
     * @return int L'annotation @Column(length = 4, nullable = false) définit
     * que l'attribut annee sera une colonne de la table livres dans notre BDD +
     * L'année devra comporter 4 chiffres au maximum et ne pourra pas être nul
     */
    @Column(length = 4, nullable = false)
    public int getAnnee() {
        return annee;
    }

    /**
     * Méthode setAnnee pour définir l'annee de parution d'un livre
     *
     * @param annee
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * Méthode getEditeur pour récupérer l'éditeur d'un livre
     *
     * @return String L'annotation @Column(length = 50, nullable = false)
     * définit que l'attribut éditeur sera une colonne de la table livres dans
     * notre BDD + L'éditeur devra comporter 50 caractères au maximum et ne
     * pourra pas être nul
     */
    @Column(length = 50, nullable = false)
    public String getEditeur() {
        return editeur;
    }

    /**
     * Méthode setEditeur pour définir l'éditeur d'un livre
     *
     * @param editeur
     */
    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    /**
     * Méthode getNomAuteur pour récupérer le nom de l'auteur d'un livre
     *
     * @return String L'annotation @Column(length = 50, nullable = false)
     * définit que l'attribut nomAuteur sera une colonne de la table livres dans
     * notre BDD + Le nom de l'auteur devra comporter 50 caractères au maximum
     * et ne pourra pas être nul
     */
    @Column(length = 50, nullable = false)
    public String getNomAuteur() {
        return nomAuteur;
    }

    /**
     * Méthode setNomAuteur pour définir le nom de l'auteur d'un livre
     *
     * @param nomAuteur
     */
    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    /**
     * Méthode getPrenomAuteur pour récupérer le nom de l'auteur d'un livre
     *
     * @return String L'annotation @Column(length = 50, nullable = false)
     * définit que l'attribut prenomAuteur sera une colonne de la table livres
     * dans notre BDD + Le prénom de l'auteur devra comporter 50 caractères au
     * maximum et ne pourra pas être nul
     */
    @Column(length = 50, nullable = false)
    public String getPrenomAuteur() {
        return prenomAuteur;
    }

    /**
     * Méthode setPrenomAuteur pour définir le prénom de l'auteur d'un livre
     *
     * @param prenomAuteur
     */
    public void setPrenomAuteur(String prenomAuteur) {
        this.prenomAuteur = prenomAuteur;
    }

}
