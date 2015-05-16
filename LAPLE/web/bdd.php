<?php
	try
		{
			$bdd = new PDO('mysql:host=localhost;dbname=Laple', 'root', 'masters', array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION));
		}
	catch (Exception $e)
		{
	  	die('Erreur : ' . $e->getMessage());
		}
?>
