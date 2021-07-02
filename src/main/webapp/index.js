/** 
 * @author ClaireToutaneJules
 * @version 2/Juillet/2021
 * 
 * JavaScript utilisant jQuery liant la page index.html et le controlleur Page1Controller
 */

$(function () {
    /**
     * Evenement click bouton pour valider le formulaire d'ajout d'un livre a la bdd
     */
    $("#envoyer").on('click', function () {
        $("#message").html("Envoi !");
        let titre = $("#titre").val();
        let annee = $("#annee").val();
        let editeur = $("#editeur").val();
        let nomAuteur = $("#nomA").val();
        let prenomAuteur = $("#prenomA").val();
        $.ajax({
            url: "/index/envoi",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "titre": titre,
                "annee": annee,
                "editeur": editeur,
                "nomAuteur": nomAuteur,
                "prenomAuteur": prenomAuteur
            })
        }).done(function (retour) { // 200
            $("#message").html(retour);
        }).fail(function () { // 400, 501..
            $("#message").html("&Eacute;chec !");
        });
    });
    
    /**
     * Evenement bouton pour remplir la bdd pour test et démo
     */
    $("#generate").on('click', function () {
        $.ajax({
            url: "/index/generate",
            type: "POST"
        }).done(function () { // 200
            $("#test").html("Bdd remplie.");
        }).fail(function () { // 400, 501..
            $("#test").html("&Eacute;chec !");
        });
    });
});
