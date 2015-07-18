<?php
require_once '../common.php';
/*
 * available parameters :
 * email, password,
 * output(optionnal)
*/

$params = Method::getParams();

// ----  OUTPUT FORMAT  ----
if( isset($params['output']) && (strcasecmp($params['output'], "xml") == 0) ){
    Response::applyXmlFormat();
}
// ----  END OUTPUT FORMAT  ----

// ----  REQUIREMENTS  ----
if( !Method::isValid('GET') ){ Response::send(6,0); }
$hasMandatoryParams = isset($params['email'])
                   && isset($params['password']);
if( !$hasMandatoryParams ){ Response::send(1,0); }
// ----  END REQUIREMENTS  ----

// query for user connection
$token = ManagerDB::connect($params['email'], $params['password']);

Response::send(0, $token);