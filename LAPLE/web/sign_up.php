<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
		<link rel="stylesheet" href="/private/css/style.css" />
		<link rel="stylesheet" href="/private/css/formulaire.css" />
		<title>Account</title>
		
  </head>
	<h1 style="color:ThreeDDarkShadow; text-align:center">LAPLE</h1>
	
	<body>
		<h2 style="color:ThreeDDarkShadow; text-align:center">Create account</h2>
		<form id="form" method="post" action="http://localhost/private/validate_account.php?">
   	<fieldset>
    	<legend>Sign up</legend>
    		<h5 style="color: red"><?php 
    			if(isset($_GET['verification']) && $_GET['verification']==1){
					echo "Formulaire mal rempli<br>";	
				}
			?>
			</h5>
  			<img src="media/logo.png" height="75" width="150" alt="Logo de Laple">
  			<label for="name">Name</label>
	     	<input type="text" name="name" id="name" />

      	<label for="email">Email</label>				
	     	<input type="text" name="email" id="email" />
			<br/>
			
      	<label for="mot de passe">Password</label>
      	<input type="password" name="password" id="mdp" />
			<input type="submit" value="Sign up" />
   	</fieldset>
	</form>
	
</body>
</html>