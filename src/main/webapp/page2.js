$(function () {
    // sélectionner l'élément d'id "envoyer"
    $("#enregistrer").on('click', function () {
        $("#message").html("Enregistrer !");
        let titreLivre = $("#titreLivre").val();
        let dateEmprunt = $("#dateEmprunt").val();
        let nomEmprunteur = $("#nomEmprunteur").val();
//	Envoyer les données vers le serveur à l'addresse /page2 et mettre les données dans  la requête
        $.ajax({
            url: "/page2/envoi",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "titreLivre": titreLivre,
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
            $("#message").html("Trouvé ! Livres ayant le titre de " + titre + " (" + listRetour.length + ").");
            let lignes = "";
            let num = 0;
            for (const livre of listRetour) {
                num++;
                lignes += "<tr>" +
                        "<th scope='row'>" + livre.id + "</th>" +
                        "<td>" + livre.titre + "</td>" +
                        "<td>" + livre.nomAuteur + "</td>" +
                        "<td>" + livre.annee + "</td>" +
                        "</tr>";
            }
            $("#livres tbody").html(lignes);
        }).fail(function () { //400, 501...
            $("#message").html("Serveur non disponible !");
        });

    });
});
