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

if( !Method::isValid('POST') ){ Response::send(6,0); }
$hasMandatoryParams = isset($params['email'])
                   && isset($params['newPassword']);
if( !$hasMandatoryParams ){ Response::send(1,0); }

// ----  END REQUIREMENTS  ----

// VERIFICATION
$existingEmail  = ManagerDB::execute("SELECT id_Profile FROM Profile WHERE email=?", [$params['email']])->fetch()[0];



// ----  UPDATE TO DO  ----
// query for Profile UPDATE


// ----  EXECUTION  ----
if(isset($params['password'])){
		$token = ManagerDB::update($params['email'], $params['newPassword'], NULL, $params['password']);
 }
 	else{
 		if(!isset($params['message'])){Response::send(1,0);}
	 		$token=ManagerDB::update($params['email'], $params['newPassword'], $params['message']);
	}
	  
// ----  INSERT DONE  ----

if($token!=NULL){Response::send(0, $token);}
//token existe déjà
else{Response::send(1, 27);}