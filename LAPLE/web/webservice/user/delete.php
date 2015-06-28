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
if( !Method::isValid('DELETE') ){ Response::send(6,0); }
$hasMandatoryParams = isset($params['token'])
                   && isset($params['password']);
if( !$hasMandatoryParams ){ Response::send(1,0); }
// ----  END REQUIREMENTS  ----

// ----  VERIFICATIONS  ----
$user = ManagerDB::getUserByToken($params['token']);
if(!isset($user)){ Response::send(4,0); }
$passwordOk  = ManagerDB::execute("SELECT idUser FROM user WHERE idUser=? AND password=?", [$user['idUser'],$params['password']])->fetch()[0];
if(!isset($passwordOk)){ Response::send(9,0); }
// ----  END VERIFICATIONS  ----


// ----  DELETE TO DO  ----
// query for user insertion
$queryD  = "DELETE FROM user WHERE idUser=? AND password=?";
$valuesD = array($user['idUser'], $params['password']);


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