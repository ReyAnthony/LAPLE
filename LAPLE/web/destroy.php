<?php
	session_start();
	$_SSESION=array();
	session_destroy();

	header('Location: sign_in.php');
?>

