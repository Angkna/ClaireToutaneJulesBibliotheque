$(function () {
    // Envoi donnée pour crée Emprunt en BDD si valide
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
            afficherEmprunts();
        }).fail(function () { // 400, 501..
            $("#message").html("Echec de connection avec le backend !" + idLivre);
        });

    });

    //Génération de la liste des Livres en BDD :
    $("#titreLivre").on('input', function () {
        let titre = $("#titreLivre").val();
        if (!titre) {
            $("#message").html("Entrez un titre !");
            $("#livres tbody").html();
        } else {
            $.ajax({
                url: "/page2/" + titre,
                type: "GET",
                dataType: "json"
            }).done(function (listRetour) { //200
                if (listRetour.length === 0) {
                    $("#message").html("Il n'y a pas de livres dans la bibliothèque ayant un titre commençant par ce titre.");
                } else {
                    $("#message").html("Il y a " + listRetour.length +
                            " livres dans la bibliothèque ayant un titre commençant par " + titre + ".");
                    let lignes = "";
                    for (const livre of listRetour) {
                        lignes += "<tr>" +
                                "<th scope='row'>" + livre.id + "</th>" +
                                "<td>" + livre.titre + "</td>" +
                                "<td>" + livre.nomAuteur + "</td>" +
                                "<td>" + livre.annee + "</td>" +
                                "<td>" + livre.disponible + "</td>" +
                                boolean disponible =false? "non":"oui"
                                "</tr>";
                    }
                    $("#livres tbody").html(lignes);
                }
            }).fail(function () { //400, 501...
                $("#message").html("Serveur non disponible !");
            });

        }
    });

    // Affichage de la liste des emprunts
    $(document).ready(afficherEmprunts());
//    $(document).ready(function(){
//        $.ajax({
//            url: "/page2/emprunts",
//            type: "GET",
//            dataType: "json"
//        }).done(function (listeEmprunts) { //200
//                let lignes = "";
//                for (const emprunt of listeEmprunts) {
//                    lignes += "<tr>" +
//                            "<td>" + emprunt.id + "</td>" +
//                            "<td>" + emprunt.nomUser + "</td>" +
//                            "<td>" + emprunt.dateEmprunt + "</td>" +
//                            "</tr>";
//                $("#listeEmprunts tbody").html(lignes);
//            }
//        }).fail(function () { //400, 501...
//            $("#message").html("Serveur non disponible !");
//        });
//
//    });

    //edition de la date rendu de l'emprunt
    $("#enregistrerDateRendu").on('click', function () {
        $("#message2").html("Modification en cours");
        let idEmprunt = $("#IdDeLEmprunt").val();
        let dateDeRendu = $("#dateDeRendu").val();
        $.ajax({
            url: "page2/envoi",
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify({
                "idEmprunt": idEmprunt,
                "dateRendu": dateDeRendu
            })
        }).done(function (retour) { // 200
            $("#message2").html(retour);
            afficherEmprunts();
        }).fail(function () { // 400, 501..
            $("#message2").html("Echec !");
        });

    });
});

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
    }).fail(function () { //400, 501...
        $("#message").html("Serveur non disponible !");
    });
};