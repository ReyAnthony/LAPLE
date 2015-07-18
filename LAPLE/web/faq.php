<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/style.css" />
    <link rel="stylesheet" href="/css/formulaire.css" />
      <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
      <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <title>Accueil</title>
    
  </head>

  <body class="html">
    <?php require_once('session.php'); 
          require('bdd.php');
           ini_set("display_errors", 1);
    ?>
	
  	<nav class="navbar navbar-default">
		  <div class="container-fluid">
			    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">

		      <a class="navbar-brand" href="index.html">Welcome</a>

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
    		    //on ajoute la question et son auteur en base ici
    		    if(!isset($_GET['question']) && !isset($_GET['answer']) && !isset($_GET['qu'])){
    		    	$email=$_SESSION['email'];
	    		 	$req0=$bdd->prepare('SELECT id_profile FROM Profile WHERE email=?');
					$req0->execute(array($email));
					$id_profile=-1;
					while($donnee0=$req0->fetch()){
						$id_profile=$donnee0['id_profile'];
					}
					$req1=$bdd->prepare('SELECT pseudo FROM Profile WHERE email=?');
					$req1->execute(array($email));
					$name=NULL;
					while($donnee1=$req1->fetch()){
						$name=$donnee1['pseudo'];
					}

					$request=$bdd->query('SELECT P1.pseudo as name1, P2.pseudo as name2, idQuestion, question, answer 

											FROM FAQ F left join Profile P1 ON F.id_profile= P1.id_profile
														left join Profile P2 ON F.answer_id_profile= P2.id_profile');

					//$request->execute(array( 'P1.id_profile', 'P2.id_profile'));

					//l'envoie de l'id de la question permettra de savoir la question à laquelle une reponse appartient
					while($donnee=$request->fetch()){
						
						echo "<a style='color:white' href='faq.php?qu=".$donnee['idQuestion']."'>(".$donnee['name1'].")->".$donnee['question']."</a></br>";
						if($donnee['answer']!=NULL){
							echo "<FONT style='color:white'>(".$donnee['name2'].")=>(".$donnee['name1'].")->".$donnee['answer']."</FONT><br>";
						}
					}
					echo "</br> Question";
		    			echo "<textarea name='question' rows='5' cols='25'></textarea>";

    		    }
    		    else{
		    		 if(isset($_GET['question'])){
		    		 	$email=$_SESSION['email'];
		    		 	$question=$_GET['question'];
		    		 	$req0=$bdd->prepare('SELECT id_profile FROM Profile WHERE email=?');
						$req0->execute(array($email));
						$id_profile=-1;
						while($donnee0=$req0->fetch()){
							$id_profile=$donnee0['id_profile'];
						}

						$req1=$bdd->prepare('SELECT pseudo FROM Profile WHERE email=?');
						$req1->execute(array($email));
						$name=NULL;
						while($donnee1=$req1->fetch()){
							$name=$donnee1['pseudo'];

						}

		    		 	$req = $bdd->prepare('INSERT INTO FAQ(question, id_profile) VALUES(:question, :id_profile)');
						$req->execute(array('question' => $question,
											'id_profile' => $id_profile));

						$request=$bdd->query('SELECT P1.pseudo as name1, P2.pseudo as name2, idQuestion, question, answer 
											FROM FAQ F left join Profile P1 ON F.id_profile= P1.id_profile
														left join Profile P2 ON F.answer_id_profile= P2.id_profile');

						//$request->execute(array( 'P1.id_profile', 'P2.id_profile'));

						//l'envoie de l'id de la question permettra de savoir la question à laquelle une reponse appartient
						while($donnee=$request->fetch()){
							
							echo "<a style='color:white' href='faq.php?qu=".$donnee['idQuestion']."'>(".$donnee['name1'].")->".$donnee['question']."</a></br>";
							if($donnee['answer']!=NULL){
								echo "<FONT style='color:white'>(".$donnee['name2'].")=>(".$donnee['name1'].")->".$donnee['answer']."</FONT><br>";
							}
						}
					
						unset($_GET['question']);
						$req->closeCursor();
					}

					//idem pour reponse
					if(isset($_GET['answer'])){
		    		 	$email=$_SESSION['email'];
		    		 	$answer=$_GET['answer'];
		    		 	$idQuestion=$_GET['idQuestion'];
		    		 	$req0=$bdd->prepare('SELECT id_profile FROM Profile WHERE email=?');
						$req0->execute(array($email));
						$answer_id_profile=-1;
						while($donnee0=$req0->fetch()){
							$answer_id_profile=$donnee0['id_profile'];
						}

						$req1=$bdd->prepare('SELECT pseudo FROM Profile WHERE email=?');
						$req1->execute(array($email));
						$name=NULL;
						while($donnee1=$req1->fetch()){
							$name=$donnee1['pseudo'];

						}

		    		 	$req = $bdd->prepare('UPDATE FAQ SET answer=:answer, answer_id_profile=:answer_id_profile WHERE idQuestion=:idQuestion');
						$req->execute(array('answer' => $answer,
											'answer_id_profile' => $answer_id_profile,
											 'idQuestion' => $idQuestion));

						$request=$bdd->query('SELECT P1.pseudo as name1, P2.pseudo as name2, idQuestion,question, answer 

												FROM FAQ F left join Profile P1 ON F.id_profile= P1.id_profile
															left join Profile P2 ON F.answer_id_profile= P2.id_profile');

						//$request->execute(array( 'P1.id_profile', 'P2.id_profile'));

						//l'envoie de l'id de la question permettra de savoir la question à laquelle une reponse appartient
						while($donnee=$request->fetch()){
							
							echo "<a style='color:white' href='faq.php?qu=".$donnee['idQuestion']."'>(".$donnee['name1'].")->".$donnee['question']."</a></br>";
							if($donnee['answer']!=NULL){
								echo "<FONT style='color:white'>(".$donnee['name2'].")=>(".$donnee['name1'].")->".$donnee['answer']."</FONT><br>";
							}
						}
						
						unset($_GET['question']);
						$req->closeCursor();
					}




		    		if(isset($_GET['qu'])){
		    			echo "</br> Answer";
		    			echo "<input type='hidden' name='idQuestion' value='".$_GET['qu']."'</br>";
		    			echo "<textarea name='answer' rows='5' cols='25'></textarea>";
		    		}
		    		else{
		    			echo "</br> Question";
		    			echo "<textarea name='question' rows='5' cols='25'></textarea>";
		    		}
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