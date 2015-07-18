<?php
	session_start();
		ini_set("display_errors", 1);
	require('bdd.php');
	if(isset($_POST['email']) && isset($_POST['password'])){
		$email=$_POST['email'];
		$passwd=md5($_POST['password']);
<<<<<<< HEAD
		$req = $bdd->prepare('SELECT name, email, mdp FROM Profile WHERE email=? AND mdp=?');
		$req->execute(array($email, $passwd));
		while($donnee=$req->fetch()){
			$_SESSION['name']=$donnee['name'];
=======
		$req = $bdd->prepare('SELECT pseudo, email, mdp FROM Profile WHERE email=? AND mdp=?');
		$req->execute(array($email, $passwd));
		while($donnee=$req->fetch()){
			$_SESSION['name']=$donnee['pseudo'];
>>>>>>> feature/web
			$_SESSION['email']=$donnee['email'];
			$_SESSION['mdp']=$donnee['mdp'];
		}
			$req->closeCursor();
		if(isset($_SESSION['email']) && isset($_SESSION['mdp'])){	
			header('Location: index.html');		
		}
		else {
			header('Location: sign_in.php?verif=1');		
		}
	}
	else {
		header('Location: sing_in.php?verif=1');		
	}

?>
