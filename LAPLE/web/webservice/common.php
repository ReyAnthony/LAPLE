<?php

/*OK*/class ManagerDB {
    static private $link;

    /**
     * Get the PDO connection for the MySQL database
     * @return PDO object
     */
    /*OK*/static function init(){
        if( !isset(self::$link) ){
            try {
                self::$link = new pdo('mysql:host=localhost;dbname=laple',
                                      'root',''/*password*/);
                self::$link->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            } catch(PDOException $e){
                Response::sendAsJSON(5,0);
            }
        }
        return self::$link;
    }
    /**
     * Executes the request given and returns its statement
     * @param string $preparedQuery
     * @param array $values
     * @return PDO statement
     */
    /*OK*/static function execute($preparedQuery, array $values=[]){
        try{
            $request = self::init()->prepare($preparedQuery);
            $request->execute($values);
        }catch(PDOException $e){
            echo "<pre>"; print_r($e->getTrace()); echo "</pre>"; 
            die('Erreur : ' . $e->getMessage());
        }
        return $request;
    }
    /**
     * Get the user using the specified token
     * @param string $token
     * @return User associative array or NULL
     */
    /*OK*/static function getUserByToken($token){
        $query = "SELECT p.id_profile, email, pseudo, mdp "
               . "FROM Profile p JOIN Session s USING(id_profile) WHERE s.token=?";
        $request = self::execute($query, array($token));
        $results = $request->fetchAll(PDO::FETCH_ASSOC);
        return empty($results) ? null : $results[0];
    }


    /*OK*/static function checkLanguage($language){
        $query = "SELECT idLanguage,nameLanguage "
               . "FROM Language WHERE nameLanguage = ?";
        $request = self::execute($query, array($language));
        $results = $request->fetchAll(PDO::FETCH_ASSOC);
        return empty($results) ? null : $results[0];
    }
    
     

    /*OK*/static function putStat($noChangeSymbol, $language, $profile, $completionDate, $typeExo, $totalSymbol, $totalNumberEX, $currentSymbol, $currentNumberEx, $symbolType, $success, $dicoType){
        
        //On vérifie si le symbole est déjà présent dans le dico
        // Si oui c'est une mise  à jour
        if($noChangeSymbol!=NULL){
            if('symbol'==strtolower($dicoType)){
                // On cherche le nombre de fois que le symbole a été effectué et reussi
                $query="SELECT D.idDico, tryNumber, numberSuccess
                        FROM Dico D, Symbol Sy
                        WHERE D.idDico = Sy.idDico
                        AND keySymbol = ?
                        AND typeDico = ?";
            }
            else{
                $query="SELECT D.idDico, tryNumber, numberSuccess
                        FROM Dico D, Words W
                        WHERE D.idDico = W.idDico
                        AND keyWords = ?
                        AND typeDico = ?";   
            }
            $result1=array();
            $result1=self::execute($query, array($currentSymbol, $dicoType))->fetch(PDO::FETCH_ASSOC);
            unset($query);
            //on met à jour le nombre de try et  de success
            $query = "UPDATE Dico SET tryNumber = :tryNumber, numberSuccess = :numberSuccess 
                    WHERE idDico = :idDico";
            $tab=array('tryNumber' => $result1['tryNumber']+1,
                        'numberSuccess' => $result1['numberSuccess']+$success,
                        'idDico' => $result1['idDico']);
            self::execute($query, $tab);
        }
         // Sinon non c'est une insertion
        else{
            $pdo = self::init();
            $pdo->beginTransaction();
            try{
                $query="INSERT INTO Dico (typeDico, tryNumber, numberSuccess) 
                        VALUES(?, ?, ?)";
                self::execute($query, array($dicoType, 1, $success));
                //on recupère l'id du dico qui sera inséré dans symbole si dicotype est symbole et dans Words si c'est words
                $lastId=self::init()->lastInsertId();
               
              
                if('symbol'==strtolower($dicoType)){
                    $query="INSERT INTO Symbol (idDico, keySymbol)
                            VALUES(?, ?)";
                    self::execute($query, array($lastId, $currentSymbol));
                }
                else{
                    $query="INSERT INTO Words (idDico, keyWords)
                            VALUES(?, ?)";
                    self::execute($query, array($lastId, $currentSymbol));   
                }
                $pdo->commit();
            }catch(PDOException $e){
               $pdo->rollBack();
                die('Erreur : ' . $e->getMessage());
                Response::send(5,0);
            }
         } 
        unset($query);
        $query=array();
        // on insert dans statistic
        $query="INSERT INTO Statistics (type, totalNumberEX, currentNumberEx)
                VALUES(?, ?, ?)";
        self::execute($query, array($typeExo, $totalNumberEX,$currentNumberEx));
        $lastId2=self::init()->lastInsertId();
        unset($query);
        //on relie statistique et dico
        $query="INSERT INTO toBelong (idStatistic, idDico, statDate)
                VALUES(?, ?, ?)";
        if(!isset($result1)){
         self::execute($query, array($lastId2, $lastId, $completionDate));
        }
        else{
            self::execute($query, array($lastId2, $result1['idDico'], $completionDate));   
        }
        unset($query);
        $query="SELECT idLanguage, id_profile, idStatistic 
                FROM associate 
                WHERE idStatistic = ?
                AND id_profile = ?
                AND idLanguage = ?";
        $result=self::execute($query, array($language['idLanguage'], $profile['id_profile'], $lastId2 ))->fetch(PDO::FETCH_ASSOC);
        if(!isset($result)){
             //on relie statistic, language, et l'utilisateur   
            $query="INSERT INTO associate (idStatistic, id_profile, idLanguage)
                    VALUES(?, ?, ?)";
            self::execute($query, array($lastId2, $profile['id_profile'], $language['idLanguage']));
        }

    }
    /*OK*/static function getLanguageByUser($idProfile, $limit, $offset){
        $query = "SELECT DISTINCT L.nameLanguage 
                    FROM Language L, associate A, Profile P 
                    WHERE L.idLanguage = A.idLanguage
                    AND P.id_profile=A.id_profile
                    AND A.id_profile = ?
                    LIMIT $limit OFFSET $offset";
        return self::execute($query, [$idProfile])->fetchAll(PDO::FETCH_ASSOC);
    }

    /*OK*/static function getAllStat($limit, $offset, $language){
        $query = "SELECT DISTINCT L.idLanguage, nameLanguage,
                                    P.id_profile, pseudo,
                                    S.idStatistic, type, name_stat, totalNumberEX, currentNumberEx, 
                                    D.idDico, typeDico, tryNumber,  numberSuccess,
                                    Sy.idDico, keySymbol,
                                    W.idDico, keyWords,
                                    T.statDate
                    FROM Language L, Profile P, associate A, Statistics S, Dico D, Symbol Sy, Words W, toBelong T
                    WHERE L.idLanguage = A.idLanguage 
                    AND P.id_profile = A.id_profile
                    AND S.idStatistic= A.idStatistic
                    AND S.idStatistic= T.idStatistic
                    AND D.idDico= T.idDico
                    AND (D.idDico = Sy.idDico OR D.idDico = W.idDico)
                    AND nameLanguage = ?
                    LIMIT $limit OFFSET $offset";
        return self::execute($query, [$language])->fetchAll(PDO::FETCH_ASSOC);;
    }

    /*OK*/static function getLanguage($limit, $offset){
        $query = "SELECT DISTINCT nameLanguage 
                    FROM Language L 
                    LIMIT $limit OFFSET $offset";
        return self::execute($query)->fetchAll(PDO::FETCH_ASSOC);
    }

    /**
     * Return a token after created it or updated it if it exists
     * @param int $email
     * @param int $password
     * @return String
     */
    /*OK*/static function connect($email, $password){
        $query  = "SELECT id_profile FROM `Profile` WHERE email=? AND mdp=?";
        $values = array($email, MD5($password));

        $idUser  = (int)ManagerDB::execute($query, $values)->fetch()[0];

        if(!isset($idUser)){Response::send(3,0);}

        $token = ManagerDB::execute("SELECT token FROM `Session` WHERE id_profile=".$idUser)->fetch()[0];

        // si le token existe pour le user, on le renvoie
        if(isset($token)){
            ManagerDB::execute("UPDATE Session SET end= NOW() + INTERVAL 1 DAY + 1 WHERE id_profile=".$idUser);
            return $token;
        }

        // création d'un nouveau token tant qu'il n'est pas unique
        do{
            $token = tools::generateToken(); // TODO à coder
        }while(ManagerDB::execute("SELECT token FROM `Session` WHERE token='".$token."'")->fetch()[0] != null);
        // insertion du token
        $queryC  = "INSERT INTO Session (id_profile, token, end) VALUES (?, ?, NOW() + INTERVAL 1 DAY)";
        $valuesC = array($idUser, $token);

        $pdo = ManagerDB::init();
        $pdo->beginTransaction();
        try {
         ManagerDB::execute($queryC, $valuesC);
         $pdo->commit();
        } catch(PDOException $e){
         $pdo->rollBack();
         Response::send(5,0);
         }

        $token = ManagerDB::execute("SELECT token FROM `Session` WHERE id_profile='".$idUser."'")->fetch()[0];
        return $token;
     }

}

/*OK*/class Method {
    
    /**
     * Checks if the request is the type of the specified method
     * @param type $methodName
     * @return boolean
     */
    /*OK*/static function isValid($methodName){
        return strcasecmp($_SERVER['REQUEST_METHOD'], $methodName) == 0;
    }
    /**
     * Returns an associative array of the given parameters in the request
     * @return associative array
     */
    /*OK*/static public function getParams(){
        if( Method::isValid('GET') || Method::isValid('POST') ){
            return $_REQUEST;
        }
        if( Method::isValid('PUT') || Method::isValid('DELETE') ){
            return self::getRequestParams();
        }
    }
    /**
     * Returns an associative array for PUT and DELETE requests
     * @return array
     */
    /*OK*/static private function getRequestParams(){
        $params = array();
        if( !isset($_SERVER['CONTENT_TYPE']) ){
            return $params;
        }
        if( stripos($_SERVER['CONTENT_TYPE'], 'form-data') ){
            return self::extractFormData();
        }
        if( stripos($_SERVER['CONTENT_TYPE'], 'x-www-form-urlencoded') ){
            $paramsInput = array();
            parse_str(file_get_contents("php://input"), $paramsInput);
            foreach($paramsInput as $key => $val){
                $params[$key] = self::isJson($val) ? json_decode($val, TRUE) : $val;
            }
        }
        return $params;
    }
    /**
     * Returns data from a form-data request
     * @return array
     */
    /*OK*/static private function extractFormData(){
        $body = str_replace(chr(13), NULL, file_get_contents("php://input"));
        $boundary = "--".explode('=', $_SERVER['CONTENT_TYPE'])[1];
        
        $parts = explode($boundary, $body);
        array_shift($parts);
        array_pop($parts);
        
        $params = array();
        foreach($parts as $str){
            $data = explode("Content-Disposition: form-data; name=\"",$str)[1];
            $values = explode(chr(10), $data);
            $key = str_replace('"', NULL, $values[0]);
            $val = $values[2];
            $params[$key] = self::isJson($val) ? json_decode($val, TRUE) : $val;
        }
        return $params;
    }
    /**
     * Checks if the given string is a valid formatted JSON
     * @param type $string
     * @return boolean
     */
    /*OK*/static function isJson($string) {
        json_decode($string);
        return json_last_error() === JSON_ERROR_NONE;
    }
    
}

/*OK*/class Response {
    static $output = 'JSON';
    
    /**
     * Apply the XML format for the server response
     */
    /*OK*/static function applyXmlFormat(){
        self::$output = 'XML';
    }
    /**
     * Writes the server response in the previously specified format with the specified data
     * @param int $code
     * @param mixed $result
     * @param string $child each child's name in case of XML output
     */
    /*OK*/static function send($code, $result, $child=NULL){
        if(self::$output === 'XML'){
            echo Tools::xml_encode(array(
                'code'   => $code,
                'result' => $child ? [$child=>$result] : $result
            ));
        }else{
            echo json_encode(array(
                'code'   => $code,
                'result' => $result
            ));
        }
        die;
    }

}

class Tools {
    
    /**
     * Generate a random string
     * @return string token
     */
    /*OK*/static function generateToken(){
        $max = (int) str_repeat(9, 64);
        $p1 = md5( rand(1,$max) );
        $p2 = md5( rand(1,$max) );
        return $p1.$p2;
    }
    /**
     * Build XML from raw data
     * @param type $mixed
     * @param type $domElement
     * @param DOMDocument $DOMDocument
     */
    /*OK*/static function xml_encode($mixed, $domElement=null, $DOMDocument=null) {
        if (is_null($DOMDocument)) {
            $DOMDocument = new DOMDocument;
            $DOMDocument->formatOutput = true;
            Tools::xml_encode($mixed, $DOMDocument, $DOMDocument);
            echo $DOMDocument->saveXML();
        }
        else {
            if (is_array($mixed)) {
                foreach ($mixed as $index => $mixedElement) {
                    if (is_int($index)) {
                        if ($index === 0) {
                            $node = $domElement;
                        }
                        else {
                            $node = $DOMDocument->createElement($domElement->tagName);
                            $domElement->parentNode->appendChild($node);
                        }
                    }
                    else {
                        $plural = $DOMDocument->createElement($index);
                        $domElement->appendChild($plural);
                        $node = $plural;
                        if (!(rtrim($index, 's') === $index)) {
                            $singular = $DOMDocument->createElement(rtrim($index, 's'));
                            $plural->appendChild($singular);
                            $node = $singular;
                        }
                    }
                    Tools::xml_encode($mixedElement, $node, $DOMDocument);
                }
            }
            else {
                $mixed = is_bool($mixed) ? ($mixed ? 'true' : 'false') : $mixed;
                $domElement->appendChild($DOMDocument->createTextNode($mixed));
            }
        }
    }
    
}

?>