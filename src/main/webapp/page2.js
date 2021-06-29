$(function () {
    // sélectionner l'élément d'id "envoyer"
    $("#enregistrer").on('click', function () {
        $("#message").html("Enregistrer !");
        let titreLivre = $("#titreLivre").val();
        let dateEmprunt = $("#dateEmprunt").val();
        let nomEmprunteur = $("#nomEmprunteur").val();

//		Envoyer les données vers le serveur à l'addresse /page2 et mettre les données dans  la requête
        $.ajax({
            url: "/page2/envoi",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "livre": JSON.stringify({
                    "titre": titre,
                    "annee": annee,
                    "editeur": editeur,
                    "nomAuteur": nomAuteur,
                    "prenomAuteur": prenomAuteur
                }),
                "dateEmprunt": dateEmprunt,
                "nomUser": nomEmprunteur

            })
        }).done(function (retour) { // 200

            $("#message").html(retour);

        }).fail(function () { // 400, 501..
            $("#message").html("Echec !");
        });
    });
});
