$(function () {
    // sélectionner l'élément d'id "envoyer"
    $("#enregistrer").on('click', function () {
        $("#message").html("Enregistrer !");
        let idLivre = $("#idLivre").val();
        //let titreLivre = $("#titreLivre").val();
        let dateEmprunt = $("#dateEmprunt").val();
        let nomEmprunteur = $("#nomEmprunteur").val();
//	Envoyer les données vers le serveur à l'addresse /page2 et mettre les données dans  la requête
        $.ajax({
            url: "/page2/envoi",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "idLivre": idLivre,
                //"titreLivre": titreLivre,
                "dateEmprunt": dateEmprunt,
                "nomUser": nomEmprunteur
            })
        }).done(function (retour) { // 200
            $("#message").html(retour);
        }).fail(function () { // 400, 501..
            $("#message").html("Echec !");
        });

    });

    $("#titreLivre").on('input', function () {
        let titre = $("#titreLivre").val();
        $.ajax({
            url: "/page2/" + titre,
            type: "GET",
            dataType: "json"
        }).done(function (listRetour) { //200
            $("#message").html("Il y a " + listRetour.length +
                    " livres dans la bibliothèque ayant un titre commençant par " + titre + ".");
            let lignes = "";
            let num = 0;
            for (const livre of listRetour) {
                num++;
                lignes += "<tr>" +
                        "<th scope='row'>" + livre.id + "</th>" +
                        "<td>" + livre.titre + "</td>" +
                        "<td>" + livre.nomAuteur + "</td>" +
                        "<td>" + livre.annee + "</td>" +
                        "<td>" + livre.disponible + "</td>" +
                        "</tr>";
            }
            $("#livres tbody").html(lignes);
        }).fail(function () { //400, 501...
            $("#message").html("Serveur non disponible !");
        });

    });
});
