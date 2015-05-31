<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
		<link rel="stylesheet" href="/private/css/style.css" />
		<link rel="stylesheet" href="/private/css/formulaire.css" />
		<title>Accueil</title>
		
  </head>
	<h1 style="color:ThreeDDarkShadow; text-align:center">LAPLE</h1>
	
	<body id="index">
		<h2 style="color:ThreeDDarkShadow; text-align:center">Authentification</h2>
		<form id="form" method="post" action="http://localhost/private/connexion.php">
   	<fieldset>
    	<legend>Sign in</legend>
    		 <h6 style="color:Red"><?php if(isset($_GET['verif']) && $_GET['verif']==1)
  									  		 			echo "Login ou mot de passe incorrrect";
    		 										$_GET['verif']=0;
    		 			?>
    		 </h6>
    		 <img src="media/logo.png" height="75" width="150" alt="Logo de Laple">
      	<label for="email">Email</label>
				
	     	<input type="text" name="email" id="email" />
			<br/>
      	<label for="mot de passe">Password</label>
      	<input type="password" name="password" id="mdp" />
			<a style="color:white" href='./forgot.php'>Forgot your password?</a>
			<input type="submit" value="Sign in" />
			<br/><br/><br/><br/><br/>
			<a style="color:white" href="./sign_up.php?">Sign up</a>	
   	</fieldset>
	</form>
	
</body>
</html>