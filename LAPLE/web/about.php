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
											                "Note: This software is distributed in the hope that it will be useful,<br/>" .
											                "but WITHOUT ANY WARRANTY; without even the implied warranty of<br/>" .
											                "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the<br/>" .
											                "GNU General Public License for more details.<br/>" .
											                "<br/>" .
											                "You should have received a copy of the GNU General Public License<br/>" .
											                "along with this program.  If not, see http://www.gnu.org/licenses." ;
										          ?>
			</div>
        </div>
    </div>
    <div style= "height: 60px" class="foot"><?php include('footer.php'); ?></div>
  </body>
</html>