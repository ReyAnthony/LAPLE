<?php
require_once '../common.php';
/*
 * available parameters :
 * token, offset, limit,
 * output(optionnal)
*/

// ----  OUTPUT FORMAT  ----
$params = Method::getParams();
if( isset($params['output']) && (strcasecmp($params['output'], "xml") == 0) ){
    Response::applyXmlFormat();
}
// ----  END OUTPUT FORMAT  ----

// ----  REQUIREMENTS  ----
if( !Method::isValid('GET') ){ Response::send(6,0); }

$hasMandatoryParams = isset($params['token'])
                   && isset($params['offset'])
                   && isset($params['limit'])
                   && isset($params['language']);
if( !$hasMandatoryParams ){ Response::send(1,0); }
// ----  END REQUIREMENTS  ----

// ----  VERIFICATIONS  ----
$profile = ManagerDB::getUserByToken($params['token']);	
$language=ManagerDB::checkLanguage($params['language']);
if(!isset($language)){Response::send(20,0); }	
if(!isset($profile)){ Response::send(4,0); }									
if(!is_numeric($params['offset']) || !is_numeric($params['limit'])){ Response::send(1, 0); }
if($params['offset'] <0 || $params['limit'] <0){ Response::send(1, 0); }
// ----  END VERIFICATIONS  ----
// ----  EXECUTION  ----
$language = ManagerDB::getAllStat($params['limit'],$params['offset'], $params['language']);
Response::send(0,$language, "language");