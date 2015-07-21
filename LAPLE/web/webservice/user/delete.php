<?php
require_once '../common.php';
/*
 * available parameters :
 * token, password
 * output(optionnal)
*/

$params = Method::getParams();

// ----  OUTPUT FORMAT  ----
if( isset($params['output']) && (strcasecmp($params['output'], "xml") == 0) ){
    Response::applyXmlFormat();
}
// ----  END OUTPUT FORMAT  ----

// ----  REQUIREMENTS  ----
if( !Method::isValid('POST') ){ Response::send(6,0); }
$hasMandatoryParams = isset($params['token'])
                   && isset($params['password']);
if( !$hasMandatoryParams ){ Response::send(1,0); }
// ----  END REQUIREMENTS  ----

// ----  VERIFICATIONS  ----
$profile = ManagerDB::getUserByToken($params['token']);
if(!isset($profile)){ Response::send(4,0); }
$passwordOk  = ManagerDB::execute("SELECT id_profile FROM Profile WHERE id_profile=? AND mdp=?", [$profile['id_profile'],$params['password']])->fetch()[0];

if(!isset($passwordOk)){ Response::send(9,0); }
// ----  END VERIFICATIONS  ----


// ----  DELETE TO DO  ----
// query for user insertion
$queryD  = "DELETE FROM Profile WHERE id_profile=? AND mdp=?";
$valuesD = array($profile['id_profile'], $params['password']);


// ----  EXECUTION  ----
$pdo = ManagerDB::init();
$pdo->beginTransaction();
try {
    ManagerDB::execute($queryD, $valuesD);
    $pdo->commit();
    Response::send(0,1);
} catch(PDOException $e){
    $pdo->rollBack();
    Response::send(5,0);
}
// ----  DELETE DONE  ----