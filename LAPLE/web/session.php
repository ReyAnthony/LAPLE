<?php
	session_start();
	if(!isset($_SESSION['email']) || !isset($_SESSION['mdp'])){
		header('Location: sign_in.php');
	}
?>
