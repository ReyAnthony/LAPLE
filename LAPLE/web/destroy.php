<?php
	session_start();
	$_SSESION=array();
	session_destroy();
	header('Location: http://localhost/private/sign_in.php');
?>

