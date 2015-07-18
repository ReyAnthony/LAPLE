<?php
require_once '../common.php';


$params = Method::getParams();

// ----  OUTPUT FORMAT  ----
if( isset($params['output']) && (strcasecmp($params['output'], "xml") == 0) ){
    Response::applyXmlFormat();
}
// ----  END OUTPUT FORMAT  ----

// ----  REQUIREMENTS  ----
if( !Method::isValid('POST') ){ Response::send(6,0); }
$hasMandatoryParams = isset($params['email'])
                   && isset($params['password'])
                   && isset($params['pseudo']);
if( !$hasMandatoryParams ){ Response::send(1,0); }
// ----  END REQUIREMENTS  ----

// ----  VERIFICATIONS  ----
// check if Profile already exists
$existingEmail  = ManagerDB::execute("SELECT id_Profile FROM Profile WHERE email=?", [$params['email']])->fetch()[0];
if(isset($existingEmail)){ Response::send(2,0); }
$existingPseudo  = ManagerDB::execute("SELECT id_profile FROM Profile WHERE pseudo=?", [$params['pseudo']])->fetch()[0];
if(isset($existingPseudo)){ Response::send(8,0); }

// ----  END VERIFICATIONS  ----

// ----  INSERT TO DO  ----
// query for Profile insertion
$queryC  = "INSERT INTO Profile (email, mdp, pseudo";


$queryC .= ") ";
$queryC .= "VALUES (?, ?, ?)";

$valuesC = array($params['email'], MD5($params['password']), $params['pseudo']);



// ----  EXECUTION  ----
$pdo = ManagerDB::init();
$pdo->beginTransaction();
try {
    ManagerDB::execute($queryC, $valuesC);
    $pdo->commit();
    $token = ManagerDB::connect($params['email'], $params['password']);
    $Profile = ManagerDB::getUserByToken($token);
    $Profile['token'] = $token;
    Response::send(0, $Profile);
} catch(PDOException $e){
    $pdo->rollBack();
    die('Erreur : ' . $e->getMessage());
    Response::send(5,0);
}
// ----  INSERT DONE  ----
