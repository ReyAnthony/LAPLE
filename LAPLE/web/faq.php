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
  	<?php 
		require_once('session.php'); 
		require('bdd.php');
		ini_set("display_errors", 1);
	?>
	
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
	<form id="form" method="get" action="faq.php">
   	<fieldset>
    	<legend>Foire aux questions</legend>

    		 <img src="media/logo.png" height="75" width="150" alt="Logo de Laple"></br>
    		    <?php
	    		 if(isset($_GET['question'])){
	    		 	$email=$_SESSION['email'];
	    		 	$question=$_GET['question'];
	    		 	$req0=$bdd->prepare('SELECT id_profile FROM Profile WHERE email=?');
					$req0->execute(array($email));
					$id_profile=-1;
					while($donnee0=$req0->fetch()){
						$id_profile=$donnee0['id_profile'];
					}
					$req1=$bdd->prepare('SELECT name FROM Profile WHERE email=?');
					$req1->execute(array($email));
					$name=NULL;
					while($donnee1=$req1->fetch()){
						$name=$donnee1['name'];
					}

	    		 	$req = $bdd->prepare('INSERT INTO FAQ(question, id_profile) VALUES(:question, :id_profile)');
					$req->execute(array('question' => $question,
										'id_profile' => $id_profile));
					$req2 = $bdd->query('SELECT idQuestion, question, answer FROM FAQ');
					while($donnee=$req2->fetch()){
						echo "<ul><a style='color:white' href='faq.php?qu='".$donnee['idQuestion'].">(".$name.")->".$donnee['question']."</a></ul>";
						if($donnee['answer']!=NULL){
							echo "<li>".$donnee['answer']."</li>";
						}
					}
					unset($_GET['question']);
					$req->closeCursor();
    			}


    			if(isset($_GET['answer'])){
	    		 	$email=$_SESSION['email'];
	    		 	$answer=$_GET['answer'];
	    		 	$req0=$bdd->prepare('SELECT id_profile FROM Profile WHERE email=?');
					$req0->execute(array($email));
					$answer_id_profile=-1;
					while($donnee0=$req0->fetch()){
						$answer_id_profile=$donnee0['id_profile'];
					}
					$req1=$bdd->prepare('SELECT name FROM Profile WHERE email=?');
					$req1->execute(array($email));
					$name=NULL;
					while($donnee1=$req1->fetch()){
						$name=$donnee1['name'];
					}

	    		 	$req = $bdd->prepare('INSERT INTO FAQ(answer, answer_id_profile) VALUES(:question, :answer_id_profile)');
					$req->execute(array('question' => $question,
										'answer_id_profile' => $answer_id_profile));
					$req2 = $bdd->query('SELECT idQuestion, question, answer FROM FAQ');
					while($donnee=$req2->fetch()){
						echo "<ul><a style='color:white' href='faq.php?an='".$donnee['idQuestion'].">(".$name.")->".$donnee['question']."</a></ul>";
						if($donnee['answer']!=NULL){
							echo "<li>".$donnee['answer']."</li>";
						}
					}
					unset($_GET['question']);
					$req->closeCursor();
    			}




	    		if(isset($_GET['qu'])){
	    			echo "</br> Answer";
	    			echo "<input type='hidden name='".$_GET['qu']."'";
	    			echo "<textarea name='answer' rows='5' cols='50'></textarea>";
	    		}
	    		else{
	    			echo "</br> Question";
	    			echo "<textarea name='question' rows='5' cols='50'></textarea>";
	    		}
			?>
	     	
			<input type="submit" value="Validate" />
			<br/><br/><br/><br/><br/>	
   	</fieldset>
	</form>
    </div>
    <div style= "height: 60px" class="foot"><?php include('footer.php'); ?></div>
  </body>
</html>