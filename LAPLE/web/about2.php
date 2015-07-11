<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
		<link rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" href="css/formulaire.css" />
	    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
	    <script src="bootstrap/js/jquery.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
		<title>Accueil</title>
		
  </head>

  <body class="bod">
  	
	<div>
		<a style='color:black' href='index.php'>Back to sign in</a></br></br></br></br>
	</div>
	<div class="container-fluid">
  		<div class="row">
        	<div class="col-lg-4"><img src="media/logo.png" height="75" width="150" alt="Logo de Laple"></div>
    	<div>
  	<div class="container-fluid">
        <div class="row">
        	<div style="color:white" class="col-lg-offset-2 col-lg-4"><?php 
        												echo "Copyright (c) 2015 <br/>
											                Anthony REY, Christian EBONGUE and Gabriel ZAAFRANI <br/><br/>".
											                "This site web is a free interface: It allows to view satistic". 
											                " for using software Laple. It allow too to compare statistic of".
											                " all users. For using statistic about softawre you must register".
											                " an account. You can download software laple in section download<br/>" .
											                "Note: This software is distributed in the hope that it will be useful,<br/>";
										          ?>
			</div>
        </div>
    </div>
    <div style= "height: 60px" class="foot"><?php include('footer.php'); ?></div>
  </body>
</html>