<?php
	
	ini_set("display_errors", 1);
	require('bdd.php');
?>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
		<link rel="stylesheet" href="/private/css/style.css" />
		<link rel="stylesheet" href="/private/css/formulaire.css" />
		<title>Account</title>
		
  </head>
	<h1 style="color:ThreeDDarkShadow; text-align:center">LAPLE</h1>
	
	<body id="got">
<?php	
	//initialisation du mot de passe par mail.
	if(isset($_POST['email']) && !isset($_POST['mdp']) && !isset($_POST['name']) && !isset($_POST['password'])) {
		$email=$_POST['email'];
		$req = $bdd->prepare('SELECT email FROM Profile WHERE email=?');
		$req->execute(array($email));
		$mail=NULL;
		while($donnee=$req->fetch()){
			$mail=$donnee['email'];
		}
		$req->closeCursor();	
		$message="Si vous n'etes pas le destinataire de ce mail, supprimer simplement le message.\r\n".
						"Sinon copiez le lien suivant dans la barre d'adresse du navigateur: \r\n".
						 "http://localhost/private/validate_account.php?verification=1";
		$subject='initialisation de mot de passe';
		//envoie du mail
		
		if($mail!=NULL) {
			mail('chrisebongue@hotmail.fr',$subject,$message);
			die("vérifier votre boite mail");
		}
		else {
			header('Location: http://localhost/private/forgot.php?verification=2');
		}
	}
	//mise à jour du nouveau mot de passe
	if(isset($_POST['email']) && isset($_POST['mdp']) && !isset($_POST['name']) && !isset($_POST['password'])){
		$email=$_POST['email'];
		$new_mdp=md5($_POST['new_mdp']);
		$req=$bdd->prepare('UPDATE Profile set mdp= :new_mdp WHERE email= :email');
		$req->execute(array('new_mdp' => $new_mdp,
								  'email' => $email));
		$req->closeCursor();
		echo '<a href="./index.php">Back to sign in</a>';
		die("Nouveau mot de passe crée");
	}		
	if(isset($_POST['name']) && isset($_POST['email']) && isset($_POST['password']) && 
		$_POST['name']!=NULL && $_POST['email']!=NULL && $_POST['password']!=NULL){
		$email=$_POST['email'];
		$passwd=md5($_POST['password']);
		$name=$_POST['name'];
		if(!preg_match("#.*@.*\._{0,4}#", $email)){
			header('Location: http://localhost/private/sign_up.php?verification=4');
		}
		$req = $bdd->prepare('SELECT email FROM Profile WHERE email=?');
		$req->execute(array($email));
		$mail=NULL;
		while($donnee=$req->fetch()){
			$mail=$donnee['email'];
		}
		
		if($mail!=NULL){
			header('Location: http://localhost/private/sign_up.php?verification=3');	
		}
		$req = $bdd->prepare('INSERT INTO Profile(name, email, mdp) VALUES(:name, :email, :mdp)');
		$req->execute(array('name' => $name,
									'email' => $email, 
									'mdp' => $passwd));
		echo "Creation de compte réussi <br>";
		echo '<a href="./index.php">Back to sign in</a>';
	}
	else {
		header('Location: http://localhost/private/sign_up.php?verification=1');		
	}	
?>
	</body>
</html>