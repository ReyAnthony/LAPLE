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
if( !Method::isValid('POST') ){ Response::send(6,0); }

$hasMandatoryParams = isset($params['token'])
                   && isset($params['language'])
                   && isset($params['completionDate'])
                   && isset($params['typeExo'])
                   && isset($params['totalSymbol'])
                   && isset($params['totalNumberExo'])
                   && isset($params['currentSymbol'])
                   && isset($params['currentNumberExo'])
                   && isset($params['name_stat'])
                   && isset($params['nameDico'])
                   && isset($params['success'])
                   && isset($params['dicoType']);

if( !$hasMandatoryParams ){ Response::send(1,0); }
// ----  END REQUIREMENTS  ----

// ----  VERIFICATIONS  ----
$noChangeSymbol=NULL;
$profile = ManagerDB::getUserByToken($params['token']);	
$language=ManagerDB::checkLanguage($params['language']);
if('symbol'==strtolower($params['dicoType'])){
	$existingCurrentSymbol= ManagerDB::execute("SELECT keySymbol FROM Symbol WHERE keySymbol=?", [$params['currentSymbol']])->fetch()[0];
	if(isset($existingCurrentSymbol)){
		$noChangeSymbol=$params['currentSymbol'];
	}
}
if('words'==strtolower($params['dicoType'])){
	$existingCurrentSymbol= ManagerDB::execute("SELECT keyWords FROM Words WHERE keyWords=?", [$params['currentSymbol']])->fetch()[0];
	if(isset($existingCurrentSymbol)){
		$noChangeSymbol=$params['currentSymbol'];
	}
}

if(!isset($language)){Response::send(20,0); }	
if(!isset($profile)){ Response::send(4,0); }									
// ----  END VERIFICATIONS  ----
// ----  EXECUTION  ----

$language = ManagerDB::putStat($noChangeSymbol, $language, $profile,$params['completionDate'], $params['typeExo'],
								$params['totalSymbol'], $params['totalNumberExo'],$params['currentSymbol'], $params['currentNumberExo'], 
								$params['name_stat'], $params['nameDico'], $params['success'], $params['dicoType']);
Response::send(0,1);