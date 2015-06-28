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

  <body class="html">
    <?php require_once('session.php'); ?>
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
  <div class="container-fluid">
      <div class="row">
          <div class="col-lg-2"><img src="media/logo.png" height="120" width="150" alt="Logo de Laple"></div>
      </div>
  </div>
    <div class="container-fluid" id="about">
      <div class="row">
          <div style="color:black" class="col-lg-offset-2 col-lg-6"> Copyright (c) 2015. Anthony REY, Christian EBONGUE and Gabriel ZAAFRANI.</br> 
                                                                    This site web is a free interface: It allows to view satistic 
                                                                    for using software Laple. It allow too to compare statistic of 
                                                                    all users. For using statistic about softawre you must register
                                                                    an account. You can download software laple in section download
        
           </div>    
      </div>
    </div>
    <div style= "height: 60px" class="foot"><?php include('footer.php'); ?></div>
  </body>
</html>