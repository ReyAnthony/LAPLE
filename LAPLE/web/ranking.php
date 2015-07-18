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
<<<<<<< HEAD
  	<?php require_once('session.php'); ?>
=======
  	<?php require_once('session.php'); 
          require('bdd.php');
           ini_set("display_errors", 1);
    ?>
>>>>>>> feature/web
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
<<<<<<< HEAD
      </div>
      <div class="row">
        <div class="col-lg-offset-2 col-lg-2">
          <table style= "border-width:1px; border-color:black; border-style:solid; color:white">
=======
      
      <div class="row">
        <div class="col-lg-offset-2 col-lg-8">
          <table style= "border-width:1px; border-color:black; border-style:solid; color:black">
>>>>>>> feature/web
          <thead>
             <tr>
                <th>Rang</th>
                <th >Email</th>
                <th >Pseudo</th>
                <th >Score</th>
                <?php if(!isset($_GET['rang'])){
                  ?>
                  <th>flashcard</th>
                    <th>dictation</th>
                    <th>funzone</th>
                  <?php
                }
                ?>
             </tr>  
          </thead> 
           <tbody>
          <?php 

            $request=$bdd->query('SELECT * FROM Profile');

          //$request->execute(array( 'P1.id_profile', 'P2.id_profile'));

          //l'envoie de l'id de la question permettra de savoir la question à laquelle une reponse appartient
          while($donnee=$request->fetch()){
              $prof[]=$donnee["id_profile"];
            }
            foreach ($prof as $key => $value) {

              $query=$bdd->query("select DISTINCT pseudo, email, nameLanguage, type, nameStat, S.idStatistic, D.idDico, T.statDate, T.statDate, 
                                nameDico, type, keyDico, nameStat, totalNumberEX, currentNumberEx,
                                typeDico, tryNumber,  numberSuccess
                                FROM Language L, associate A, Statistics S, Dico D, toBelong T, Profile P
                                WHERE S.idStatistic= T.idStatistic AND D.idDico= T.idDico AND A.id_profile= P.id_profile
                                AND A.idStatistic= S.idStatistic AND A.idLanguage= L.idLanguage AND A.id_profile=".$value." ORDER BY totalNumberEX ASC");
              $funzone=0;
            $dictation=0;
            $flashcard=0;
            $tmp=0;
            $tab=array();
            $tab2=array();
            $tab3=array();
            $email;
            $pseudo;
              while($donnee=$query->fetch()){
                   $tmp=(($donnee['numberSuccess']/ $donnee["tryNumber"]) *100)/2;
                   $email=$donnee["email"];
                   $pseudo=$donnee['pseudo'];
                  if($donnee["type"]=="funzone"){
                    $funzone= $funzone + $tmp;
                   
                  }
                  if($donnee["type"]=="dictation"){
                    $dictation= $dictation + $tmp;
                   
                }
                if($donnee["type"]=="flascard"){
                  $flashcard= $flascard + $tmp;
                  
                }
              }
              $allstat=($funzone+$dictation+$flashcard)/3;
              if($allstat!=0)
                $userStat[]=array($email => array("pseudo"=> $pseudo, "funzone" =>$funzone, "dictation" => $dictation,
                                               "flashcard" => $flashcard, "allstat" => $allstat));
                
           

            }
            //ksort($userStat);
            //echo "<pre>"; echo print_r($userStat); 
             //echo "<pre>"; echo print_r($tabB); echo "</pre>";
           
            //echo "<pre>"; echo print_r($tab3); echo "</pre>";
            if(!isset($_GET['rang'])){
                  $i=1;
                  foreach ($userStat as $key => $value) {
                    
                             foreach ($value as $keys => $values) {
                              echo '<tr>
                                      <td>'.$i.'</td>
                                      <td >'.$keys.'</td>
                                      <td>'.$values['pseudo'].'</td> 
                                     <td>'.$values['allstat'].'</td>
                                     <td>'.$values['flashcard'].'</td>
                                     <td>'.$values['dictation'].'</td>
                                     <td>'.$values['funzone'].'</td>';
                               $i=$i+1;
                             }
                    echo '</tr>'; 
                  }
            }
            if(isset($_GET['rang'])){
                $num=$_GET['rang'];
                if($_GET['rang']==0){
                  $i=1;
                  foreach ($userStat as $key => $value) {
                    
                             foreach ($value as $keys => $values) {
                              echo '<tr>
                                      <td>'.$i.'</td>
                                      <td >'.$keys.'</td>
                                      <td>'.$values['pseudo'].'</td> 
                                     <td>'.$values['allstat'].'</td>';
                               $i=$i+1;
                             }
                    echo '</tr>'; 
                  }
              
                }
                if($_GET['rang']==1){
                  $i=1;
                  foreach ($userStat as $key => $value) {
                    
                             foreach ($value as $keys => $values) {
                              echo '<tr>
                                      <td>'.$i.'</td>
                                      <td >'.$keys.'</td>
                                      <td>'.$values['pseudo'].'</td> 
                                     <td>'.$values['flashcard'].'</td>';
                               $i=$i+1;
                             }
                    echo '</tr>'; 
                  }
              
                }

                if($_GET['rang']==2){
                  $i=1;
                  foreach ($userStat as $key => $value) {
                    
                             foreach ($value as $keys => $values) {
                              echo '<tr>
                                      <td>'.$i.'</td>
                                      <td >'.$keys.'</td>
                                      <td>'.$values['pseudo'].'</td> 
                                     <td>'.$values['dictation'].'</td>';
                               $i=$i+1;
                             }
                    echo '</tr>'; 
                  }
                }

                if($_GET['rang']==3){
                  $i=1;
                  foreach ($userStat as $key => $value) {
                    
                             foreach ($value as $keys => $values) {
                              echo '<tr>
                                      <td>'.$i.'</td>
                                      <td >'.$keys.'</td>
                                      <td>'.$values['pseudo'].'</td> 
                                     <td>'.$values['funzone'].'</td>';
                               $i=$i+1;
                             }
                    echo '</tr>'; 
                  }
                }
            }
            /*
            function trier($array){
              $tmp=array();
              $tmp2=$array;
              foreach ($array as $key => $value) {
                foreach ($value as $keys => $values) {
                    foreach ($tmp2 as $keyTmp => $valueTmp) {
                      foreach ($valueTmp as $keyTmp2 => $valueTmp2) {
                        if($values['allstat']<$valueTmp['allstat'])
                          $tmp[]=$array[$keys];
                      }
                      # code...
                    }
                  }
                  # code...
                }
              }
            }*/
          ?>

          </tbody> 

        </table>
      </div>
<<<<<<< HEAD
=======
      </div>
      </div>
>>>>>>> feature/web
    <div style= "height: 60px" class="foot"><?php include('footer.php'); ?></div>
  </body>
</html>