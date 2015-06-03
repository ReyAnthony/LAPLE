<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
		<link rel="stylesheet" href="/private/css/style.css" />
		<link rel="stylesheet" href="/private/css/formulaire.css" />
	    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
	    <script src="bootstrap/js/jquery.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
		<title>Accueil</title>
		
  </head>

  <body class="bod">
  	<?php require_once('session.php'); ?>
  	<nav class="navbar navbar-default">
		  <div class="container-fluid">
			    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <a class="navbar-brand" href="welcome.php">Welcome</a>
		    </div>
    	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    	    	<ul class="nav navbar-nav">
			      <li><a class="navbar-brand" href="download.php">Download</a></li>
			      <li><a class="navbar-brand" href="ranking.php">Ranking</a></li>
			      <li><a class="navbar-brand" href="ranking.php">About</a></li>
			      <li><a class="navbar-brand" href="guide.php">LAPLE'S Guide</a></li>
			      <li><a class="navbar-brand" href="faq.php">FAQ</a></li>
		  		</ul><!-- /.container-fluid -->
   		    	<ul class="nav navbar-nav navbar-right">
			 		<a class="navbar-brand" href="destroy.php">Sign out</a>
			  </ul><!-- /.container-fluid -->
			 </div>
		  </div>
	</nav>
	<div class="container">
  		<div class="row">
        	<div class="col-lg-4"><img src="media/logo.png" height="75" width="150" alt="Logo de Laple"></div>
    	<div>
  	<div class="container-fluid" id="download">
  		<div class="row">
        	<div class="col-lg-offset-4 col-lg-4"><a href="ressource/test.txt">Download video</a></div>
        </div>
        <div class="row">
        	<div class="col-lg-offset-4 col-lg-4"><a href="ressource/test.txt">Download Plugin</a></div>
        </div>
        <div class="row">
        	<div class="col-lg-offset-4 col-lg-4"><a href="ressource/test.txt">Download other</a></div>
        </div>
    </div>
    <div style= "height: 60px" class="foot"><?php include('footer.php'); ?></div>
  </body>
</html>