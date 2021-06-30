$(function () {
    // Envoi donnée pour crée Emprunt en BDD si valide
    $("#enregistrer").on('click', function () {
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
        }).fail(function () { // 400, 501..
            $("#message").html("Echec de connection avec le backend !");
        });

    });

    //Génération de la liste des Livres en BDD :
    $("#titreLivre").on('input', function () {
        let titre = $("#titreLivre").val();
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
                            "</tr>";
                }
                $("#livres tbody").html(lignes);
            }
        }).fail(function () { //400, 501...
            $("#message").html("Serveur non disponible !");
        });

    });
    
    //edition de la date rendu de l'emprunt
    //TODO
    $("#enregistrerDateRendu").on('click', function () {
        $("#message2").html("Modification en cours");
        let idEmprunt = $("#IdDeLEmprunt").val();
        let dateDeRendu = $("#dateDeRendu").val();
        $.ajax({
            url: "??????", //a crée dans le controller
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "id": idEmprunt,
                "dateRendu": dateDeRendu
            })
        }).done(function (retour) { // 200
            $("#message").html(retour);
        }).fail(function () { // 400, 501..
            $("#message").html("Echec !");
        });

    });
});
