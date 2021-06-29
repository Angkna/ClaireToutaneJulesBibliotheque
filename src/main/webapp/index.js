/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
	// sélectionner l'élément d'id "envoyer"
	$("#envoyer").on('click', function(){
		$("#message").html("Envoi !");
		let titre = $("#titre").val();
		let annee = $("#annee").val();
		let editeur = $("#editeur").val();
		let nomAuteur = $("#nomA").val();
                let prenomAuteur = $("#prenomA").val();
//		Envoyer les données vers le serveur à l'addresse /envoi et en mettant toutes ces données-là dans le coeur de la requête
		$.ajax({
			url:"/index/envoi",
			type:"POST",
<<<<<<< HEAD
//			dataType:"json", // Type que l'on s'attend à obtenir en retour. Ici, une String.
=======
			//dataType:"json",
>>>>>>> 619755dba6036fd7adc5deca7d1d23291d0a2910
			contentType:"application/json",
			data:JSON.stringify({
				"titre":titre,
				"annee":annee,
				"editeur":editeur,
				"nomAuteur":nomAuteur,
                                "prenomAuteur":prenomAuteur
			})
		}).done(function(retour){ // 200
<<<<<<< HEAD
			$("#message").html(retour)
=======
			$("#message").html(retour);
>>>>>>> 619755dba6036fd7adc5deca7d1d23291d0a2910
		}).fail(function(){ // 400, 501..
			$("#message").html("Echec !");
		});
	});
});
