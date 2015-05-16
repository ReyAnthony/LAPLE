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
		<h2 style="color:ThreeDDarkShadow; text-align:center">Change password</h2>
		<form id="form" method="post" action="http://localhost/private/validate_account.php?">
   	<fieldset>
    	<legend>New password</legend>
    		<?php
				if(isset($_GET['verification']) && $_GET['verification']==2){
      			echo "email inexistant";
     		 	}
     		?>
  			<img src="media/logo.png" height="75" width="150" alt="Logo de Laple">
				
      	<label for="email">Email</label>				
	     	<input type="text" name="email" id="email" />
			<?php
				if(isset($_GET['verification']) && $_GET['verification']==1){
      			echo '<label for="mot de passe">Password</label>';
      			echo '<input type="password" name="new_mdp" id="new_mdp" />';
     		 	}
     		?>
			<br/>
			<input type="submit" value="Validate" />
   	</fieldset>
	</form>
	
</body>
</html>