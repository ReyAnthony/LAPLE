<?php
	session_start();
	if(!isset($_SESSION['email']) || !isset($_SESSION['mdp'])){
<<<<<<< HEAD
		header('Location: http://localhost/private/sign_in.php');
=======
		header('Location: sign_in.php');
>>>>>>> feature/web
	}
?>
