$(document).ready(function(){
	/*
	$(".champsMod").click(function(){
		alert($(".valChampsMod_"+$(".code_nro").val()));
		$(".valChampsMod_"+$(".code_nro").val()).remove();
		//$(".inputValChampsMod").append('<input type="text" name="valChampsMod" />');
		//break;	
	});
*/
//gestion des datatables
	
	//tableau de liste déroulante
	var filterEtape1 = new Array();
	filterEtape1 = ['oui','non'];
	$('#example').dataTable({
		"sPaginationType":"full_numbers",
		//"aaSorting": [[ 13, "desc" ]],
		//"fnDrawCallback" : SortMyDatable,
		"sDom": '<"H"lr>t<"F"ip>',
		'iDisplayLength': 25,
		"bJQueryUI":true
	})
	.columnFilter({
   sPlaceHolder: "head:before",
   sRangeFormat: "De {from} a {to}",
   aoColumns: [ { type: "text" },	{ type: "select", bRegex: true, values: filterEtape1 }, 
								{ type: "text" },
								{ type: "select", bRegex: true, values: filterEtape1 }, 
								{ type: "select", bRegex: true, values: filterEtape1 }]
  });
	//gestion des onglets (panneaux). Evite le rechargement entier de la page
	//affiche l'onglet 1 par défaut
	$( "#tabs" ).tabs();
	
	if(($("#droit0").length)==1){
		$("#tabs").tabs('option', 'select',1);
	}	
	if(($("#droit2").length)==1){
		$("#tabs").tabs('select',3);
		$( "#tabs" ).tabs(
			'option',
     		'disabled',
     		[ 0, 1, 2]
    );
	}
	if(($("#droit3").length)==1){
		$("#tabs").tabs('option', 'select',1);
		$( "#tabs" ).tabs(
			'option',
     		'disabled',
     		[ 0, 3]
    );
	}
	if(($("#droit4").length)==1){
		$("#tabs").tabs('option', 'select',1);
		$( "#tabs" ).tabs(
			'option',
     		'disabled',
     		[0]
	    );
   } 
	
	//gestion de la fenêtre pop-up

	//parametre autoOpen:false, empêche l'ouverture automatique
	//de la fenêtre pop, lors du chargement de la page
	//show et ses paramètres, juste pour la manière(style) dont
	//la fenêtre pop s'ouvrira. Et hide c'est la manière...quand
	//on ferme la fenêtre
  $( "#dialog" ).dialog({
			autoOpen: false,
      show: {
        effect: "blind",
        duration: 1000
      },
      hide: {
        effect: "explode",
        duration: 1000
      }
	});
	// c'est le clic qui déclenchera l'ouverture de la fenêtre pop
	$( "#opener" ).click(function() {
      $( "#dialog" ).dialog( "open" );
    });
	
	
});

// cette fonction est utilisée par le manager(admin). 
// Elle permet de se connecter avec les droits des autres utilisateurs.
//Utilisateurs qui sont choisis dans la liste déroulante
function logIn(){
	user=$("#droit1 option:selected").text();
	if(user=='GestionIm'){
		$("#tabs").tabs('select',3);
		$( "#tabs" ).tabs(
			'option',
     		'disabled',
     		[ 0, 1, 2]
    );	
	}
			
	else if(user=='AcqRev'){
		$("#tabs").tabs('option', 'select',1);
		$( "#tabs" ).tabs(
			'option',
     		'disabled',
     		[ 0, 3]
    );
	}
	else if(user=='finance'){
		$("#tabs").tabs('option', 'select',1);
		$( "#tabs" ).tabs(
			'option',
     		'disabled',
     		[0]
    );
	}
	else{
		$("#tabs").tabs( 'option', 'select', 1 );
		$("#tabs").tabs( 'option', 'disabled', [] );
	}
}
// $tbl = array("msg"=>"OK"); json_encode($tbl);
// Val est un objet qui contient la valeur du champs modifié
function saveData(val, code_nro, action){
	//On récupère cette valeur
	var valeur = $(val).val();
	var erreur='erreur de modification';
$.ajax({
  url: 'saveData.php',
	type: 'GET',
	data:	'valeur='+val+'&code_nro='+code_nro+'&action='+action,
 	success: function(data){
			alert(data.message);
	},
	error : function(resultat, statut, erreur){
		alert(erreur);
   },
    complete : function(resultat, statut){

    }
	});


}



//Permet au user de modifier un champs(valid commission) du tableau et
// L'enregistre dans la base de donnée
function modifier(code_nro){
	//on enregistre dans des variables les élts qui vont remplacer 
	// un ancien <option> du <select>
	var action = 'valid_commission';
	//'onChange' permet de faire une action si un champs du tableau a été changé
	var selectDebut = '<select onChange="saveData(this, \''+code_nro+'\', \''+action+'\')" name="valChampsMod_'+code_nro+'>';
	var options = '<option value=""></option><option value="oui">Oui</option><option value="non">Non</option>';
  	var selectFin = '</select>';
	// On remplace l'ancien <option>
	$(".valChampsMod_"+code_nro).replaceWith(selectDebut+' '+options+' '+selectFin);
}

function genFiche(codeNro, action){
	 $('#dialog_content').load('/suivi_immo/poubelle/sls.html?codeNro='+codeNro+'&action='+action);
}



function showDialog(dialog_title,codeNro,action){
        
        var buttons = {};

				if(action == 'generer_fiche_acquisition'){

        buttons["Generer"] =  
		      function(){			
								genFiche(codeNro, action)

								$(this).dialog("close");
		      } 
				}       
        /*buttons["Annuler"] = function annuler(){                
					alert("popup fermée");                
					$(this).dialog("close");
        }
        */
        $('#dialog').dialog('destroy');
        $('#dialog').dialog({
                show: 'fade',
                hide: 'fade',
                width: 1000,
                resizable: false,
                modal: true,
                title: dialog_title,
                position: ['center', 'center'],
                buttons: buttons,
                open: function(event, ui){        
                        // les actions javascript à faire au niveau du popup
												
                },
                close: function(event, ui){
                        return false;
                }
        });
}

function actionDialog(codeNro, action){

    var dialog_title = '';
	switch(action){
	case "view_nro_details":
		dialog_title = "Détails du NRO "+codeNro;
		break;
	case "generer_fiche_acquisition":
		dialog_title = "fiche acquistion générée: CodeNro"+codeNro;
		break;
	default:
		dialog_title = "";
	}
  $('#dialog_content').load('charger_info.php?codeNro='+codeNro+'&action='+action, function(){ // la page PHP dans laquelle on load les données
        showDialog(dialog_title,codeNro,action);          
  });                
	
}



