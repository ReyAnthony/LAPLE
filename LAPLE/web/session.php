<?php
	session_start();
	if(!isset($_SESSION['email']) || !isset($_SESSION['mdp'])){
		header('Location: http://localhost/private/index.php');
	}
?>
