/**
 * @author ClaireToutaneJules
 * @version 02/July/2021
 *  
 * Fichier JavaScript utilisant jQuery
 * permettant le lien entre page2.html et le contrôleur
 * afin de persister un objet emprunt, d'afficher la liste des emprunts en cours et d'ajouter la date de retour d'un emprunt
 */

$(function () {
    /**
     * Méthode pour envoyer les données pour créer un Emprunt en BDD si tous les champs sont valides
     * Cette méthode s'exécute lorsque l'utilisateur appuie sur le bouton enregistrer de la section "Création d'un emprunt"
     */
    $("#enregistrerEmprunt").on('click', function () {
        $("#message").html("Enregistrer !");
        let idLivre = $("#idLivre").val();
        let dateEmprunt = $("#dateEmprunt").val();
        let nomEmprunteur = $("#nomEmprunteur").val();
        $.ajax({
            url: "/page2/envoi",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "idLivre": idLivre,
                "dateEmprunt": dateEmprunt,
                "nomUser": nomEmprunteur
            })
        }).done(function (retour) { // 200
            $("#message").html(retour);
            $("#idLivre").val("");
            $("#dateEmprunt").val("");
            $("#nomEmprunteur").val("");
            $("#titreLivre").val("");
            afficherEmprunts();
        }).fail(function () { // 400, 501..
            $("#message").html("&Eacute;chec de connection avec le back-end !" + idLivre);
        });

    });

    /**
     * Méthode pour créer et remplir un tableau HTML avec les Livres enregistrés dans la BDD 
     * dont le titre commence par les lettres tapées par l'utilisateur
     */
    $("#titreLivre").on('input', function () {
        let titre = $("#titreLivre").val();
        if (!titre) {
            $("#message").html("Entrez un titre !");
            $("#livres tbody tr").remove();
        } else {
            $.ajax({
                url: "/page2/" + titre,
                type: "GET",
                dataType: "json"
            }).done(function (listRetour) { //200
                if (listRetour.length === 0) {
                    $("#message").html("Il n'y a pas de livre dans la biblioth&egrave;que ayant un titre commen&ccedil;ant par " + titre + ".");
                    $('#livres tbody tr').remove();
                } else {
                    $("#message").html("Il y a " + listRetour.length +
                            " livre(s) dans la biblioth&egrave;que ayant un titre commen&ccedil;ant par " + titre + ".");
                    let lignes = "";
                    for (const livre of listRetour) {
                        lignes += "<tr "+ (livre.disponible ? 'class=""' : 'class="indisponible"') + ">" +
                                "<th scope='row'>" + livre.id + "</th>" +
                                "<td>" + livre.titre + "</td>" +
                                "<td>" + livre.nomAuteur + "</td>" +
                                "<td>" + livre.annee + "</td>" +
                                "<td>" + (livre.disponible ? 'oui' : 'non') + "</td>" +
                                "</tr>";
                    }
                    $("#livres tbody").html(lignes);
                }
            }).fail(function () { //400, 501...
                $("#message").html("Serveur non disponible !");
            });

        }
    });

    /**
     * Appel de la méthode afficherEmprunts() pour afficher la liste des emprunts
     */
    $(document).ready(afficherEmprunts());

    /**
     * Méthode pour éditer la date rendu de l'emprunt
     * Cette méthode s'exécute lorsque l'utilisateur appuie sur le bouton enregistrer de la section "Ajout de la date de retour d'un livre"
     */
    $("#enregistrerDateRendu").on('click', function () {
        $("#message2").html("Modification en cours");
        let idEmprunt = $("#IdDeLEmprunt").val();
        let dateDeRendu = $("#dateDeRendu").val();
        $.ajax({
            url: "page2/envoi",
            type: "PATCH",
            contentType: "application/json",
            data: JSON.stringify({
                "idEmprunt": idEmprunt,
                "dateRendu": dateDeRendu
            })
        }).done(function (retour) { // 200
            $("#message2").html(retour);
            afficherEmprunts();
        }).fail(function () { // 400, 501..
            $("#message2").html("&Eacute;chec !");
        });

    });
});

/*
 * Méthode pour créer et remplir un tableau HTML avec les Emprunts enregistrés dans la BDD 
 * qui sont encore en cours (c.à.d. qui n'ont pas encore de date de retour)
 */
var afficherEmprunts = function () {
    $.ajax({
        url: "/page2/emprunts",
        type: "GET",
        dataType: "json"
    }).done(function (listeEmprunts) { //200
        let lignes = "";
        for (const emprunt of listeEmprunts) {
            lignes += "<tr>" +
                    "<td>" + emprunt.id + "</td>" +
                    "<td>" + emprunt.livre.titre + "</td>" +
                    "<td>" + emprunt.nomUser + "</td>" +
                    "<td>" + emprunt.dateEmprunt + "</td>" +
                    "</tr>";
            $("#listeEmprunts tbody").html(lignes);
        }
        if (listeEmprunts.length === 0) {
            $('#listeEmprunts tbody tr').remove();
        }
    }).fail(function () { //400, 501...
        $("#message").html("Serveur non disponible !");
    });
};