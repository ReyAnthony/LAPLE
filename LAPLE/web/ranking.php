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
			      <li><a class="navbar-brand" href="about.php">About</a></li>
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
          <div class="col-lg-offset-2 col-lg-2"><a href="ranking.php?rang=0">All ranking</a></div>
          <div class="col-lg-2"><a href="ranking.php?rang=1">Flash card</a></div>
          <div class="col-lg-2"><a href="ranking.php?rang=2">Dictation</a></div>
          <div class="col-lg-2"><a href="ranking.php?rang=3">Funzone</a></div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-offset-2 col-lg-2">
          <table style= "border-width:1px; border-color:black; border-style:solid; color:white">
          <thead>
             <tr>
                <th>Rang</th>
                <th >Nom</th>
                <th >Score</th>
             </tr>  
          </thead> 
           <tbody>
          <?php
            if(!isset($_GET['rang'])){
              echo '<tr>
                      <td>1</td>
                    </tr>';
            }
            else{
              $num=$_GET['rang'];
              if($num==0){
                echo '<tr>
                      <td>2</td>
                    </tr>';
              } 
              if($num==1){
                echo '<tr>
                      <td>3</td>
                    </tr>';
              }
               if($num==2){
                echo '<tr>
                      <td>4</td>
                    </tr>';
              }
               if($num==3){
                echo '<tr>
                      <td>4</td>
                    </tr>';
              }
            }
          ?>

          </tbody> 

        </table>
      </div>
    <div style= "height: 60px" class="foot"><?php include('footer.php'); ?></div>
  </body>
</html>