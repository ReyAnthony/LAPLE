CREATE DATABASE  IF NOT EXISTS `laple` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `laple`;
-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: laple
-- ------------------------------------------------------
-- Server version	5.5.43-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Dico`
--

DROP TABLE IF EXISTS `Dico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Dico` (
  `idDico` int(11) NOT NULL AUTO_INCREMENT,
  `typeDico` varchar(25) NOT NULL,
  `nameDico` varchar(45) DEFAULT NULL,
  `keyDico` varchar(45) NOT NULL,
  PRIMARY KEY (`idDico`),
  UNIQUE KEY `UNIQUE_Triple` (`typeDico`,`nameDico`,`keyDico`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Dico`
--

LOCK TABLES `Dico` WRITE;
/*!40000 ALTER TABLE `Dico` DISABLE KEYS */;
INSERT INTO `Dico` VALUES (58,'Symbol','Hiragana','107'),(53,'Symbol','Hiragana','3'),(55,'Symbol','Kanji','103'),(56,'Symbol','Kanji','13'),(54,'Words','Hiragana','10'),(57,'Words','Kanji','11');
/*!40000 ALTER TABLE `Dico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Download`
--

DROP TABLE IF EXISTS `Download`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Download` (
  `idMovies` int(11) NOT NULL,
  `idLanguage` int(11) NOT NULL,
  PRIMARY KEY (`idMovies`,`idLanguage`),
  KEY `FK_Download_idLanguage` (`idLanguage`),
  CONSTRAINT `FK_Download_idLanguage` FOREIGN KEY (`idLanguage`) REFERENCES `Language` (`idLanguage`),
  CONSTRAINT `FK_Download_idMovies` FOREIGN KEY (`idMovies`) REFERENCES `Movies` (`idMovies`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Download`
--

LOCK TABLES `Download` WRITE;
/*!40000 ALTER TABLE `Download` DISABLE KEYS */;
/*!40000 ALTER TABLE `Download` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FAQ`
--

DROP TABLE IF EXISTS `FAQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FAQ` (
  `idQuestion` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(500) DEFAULT NULL,
  `answer` varchar(500) DEFAULT NULL,
  `id_profile` int(11) NOT NULL,
  `answer_id_profile` int(11) DEFAULT NULL,
  PRIMARY KEY (`idQuestion`),
  KEY `FK_FAQ_id_profile` (`id_profile`),
  KEY `fk_FAQ_Profile1_idx` (`answer_id_profile`),
  CONSTRAINT `FK_FAQ_id_profile` FOREIGN KEY (`id_profile`) REFERENCES `Profile` (`id_profile`),
  CONSTRAINT `fk_FAQ_Profile1` FOREIGN KEY (`answer_id_profile`) REFERENCES `Profile` (`id_profile`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FAQ`
--

LOCK TABLES `FAQ` WRITE;
/*!40000 ALTER TABLE `FAQ` DISABLE KEYS */;
INSERT INTO `FAQ` VALUES (1,'toto','gss',3,4);
/*!40000 ALTER TABLE `FAQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Language`
--

DROP TABLE IF EXISTS `Language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Language` (
  `idLanguage` int(11) NOT NULL AUTO_INCREMENT,
  `nameLanguage` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`idLanguage`),
  UNIQUE KEY `nameLanguage` (`nameLanguage`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Language`
--

LOCK TABLES `Language` WRITE;
/*!40000 ALTER TABLE `Language` DISABLE KEYS */;
INSERT INTO `Language` VALUES (4,'english'),(3,'japanese');
/*!40000 ALTER TABLE `Language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Movies`
--

DROP TABLE IF EXISTS `Movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Movies` (
  `idMovies` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idMovies`),
  UNIQUE KEY `path` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Movies`
--

LOCK TABLES `Movies` WRITE;
/*!40000 ALTER TABLE `Movies` DISABLE KEYS */;
/*!40000 ALTER TABLE `Movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Profile`
--

DROP TABLE IF EXISTS `Profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Profile` (
  `id_profile` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `mdp` varchar(200) NOT NULL,
  `pseudo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_profile`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Profile`
--

LOCK TABLES `Profile` WRITE;
/*!40000 ALTER TABLE `Profile` DISABLE KEYS */;
INSERT INTO `Profile` VALUES (3,'joe@hotmail.fr','6b34fe24ac2ff8103f6fce1f0da2ef57','joe'),(4,'chris@hotmail.fr','6b34fe24ac2ff8103f6fce1f0da2ef57','chris'),(6,'tata@hotmail.fr','49d02d55ad10973b7b9d0dc9eba7fdf0','tata'),(7,'papa@hotmail.fr','0ac6cd34e2fac333bf0ee3cd06bdcf96','papa'),(10,'fifi@hotmail.fr','b21afc54fb48d153c19101658f4a2a48','fifi'),(11,'pepe@hotmail.fr','926e27eecdbc7a18858b3798ba99bddd','pepe');
/*!40000 ALTER TABLE `Profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Session`
--

DROP TABLE IF EXISTS `Session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Session` (
  `idSession` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_profile` int(11) NOT NULL,
  `token` varchar(200) NOT NULL,
  `end` datetime DEFAULT NULL,
  PRIMARY KEY (`idSession`),
  UNIQUE KEY `idUser_UNIQUE` (`id_profile`),
  CONSTRAINT `fk_Session_idProfile` FOREIGN KEY (`id_profile`) REFERENCES `Profile` (`id_profile`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Session`
--

LOCK TABLES `Session` WRITE;
/*!40000 ALTER TABLE `Session` DISABLE KEYS */;
INSERT INTO `Session` VALUES (3,3,'4a7ec7bc375307dfc1ce365a8050d34411e4e787481a4fa1d4fd10f1f0dd5cd4','2015-07-20 11:46:31');
/*!40000 ALTER TABLE `Session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Statistics`
--

DROP TABLE IF EXISTS `Statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Statistics` (
  `idStatistic` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('flash card','dictation','funzone') DEFAULT NULL,
  `nameStat` varchar(25) DEFAULT NULL,
  `totalNumberEx` int(11) DEFAULT NULL,
  `currentNumberEx` int(11) DEFAULT NULL,
  PRIMARY KEY (`idStatistic`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Statistics`
--

LOCK TABLES `Statistics` WRITE;
/*!40000 ALTER TABLE `Statistics` DISABLE KEYS */;
INSERT INTO `Statistics` VALUES (38,'dictation','lesson',10,3),(39,'funzone','lesson',10,8),(40,'dictation','lesson',10,3),(41,'dictation','lesson',10,3),(42,'funzone','exercise',20,7),(43,'dictation','lesson',10,3),(44,'funzone','exercise',20,7),(79,'funzone','exercise',10,3);
/*!40000 ALTER TABLE `Statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Symbol`
--

DROP TABLE IF EXISTS `Symbol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Symbol` (
  `idDico` int(11) NOT NULL AUTO_INCREMENT,
  `keyDico` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idDico`),
  UNIQUE KEY `idSymbol_UNIQUE` (`idDico`),
  CONSTRAINT `fk_Symbol_idDico` FOREIGN KEY (`idDico`) REFERENCES `Dico` (`idDico`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Symbol`
--

LOCK TABLES `Symbol` WRITE;
/*!40000 ALTER TABLE `Symbol` DISABLE KEYS */;
INSERT INTO `Symbol` VALUES (53,'3'),(55,'103'),(56,'13'),(58,'107');
/*!40000 ALTER TABLE `Symbol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Words`
--

DROP TABLE IF EXISTS `Words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Words` (
  `keyDico` varchar(25) DEFAULT NULL,
  `idDico` int(11) NOT NULL,
  PRIMARY KEY (`idDico`),
  CONSTRAINT `FK_Words_idDico` FOREIGN KEY (`idDico`) REFERENCES `Dico` (`idDico`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Words`
--

LOCK TABLES `Words` WRITE;
/*!40000 ALTER TABLE `Words` DISABLE KEYS */;
INSERT INTO `Words` VALUES ('10',54),('11',57);
/*!40000 ALTER TABLE `Words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `associate`
--

DROP TABLE IF EXISTS `associate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `associate` (
  `id_profile` int(11) NOT NULL DEFAULT '0',
  `idStatistic` int(11) NOT NULL,
  `idLanguage` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_profile`,`idLanguage`,`idStatistic`),
  UNIQUE KEY `FK_associate` (`id_profile`,`idStatistic`,`idLanguage`),
  KEY `FK_associate_idStatistic` (`idStatistic`),
  KEY `FK_associate_idLanguage` (`idLanguage`),
  KEY `FK_assoicate_id_profile` (`id_profile`),
  CONSTRAINT `fk_associate_idLanguage` FOREIGN KEY (`idLanguage`) REFERENCES `Language` (`idLanguage`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_associate_idStatistic` FOREIGN KEY (`idStatistic`) REFERENCES `Statistics` (`idStatistic`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `associate`
--

LOCK TABLES `associate` WRITE;
/*!40000 ALTER TABLE `associate` DISABLE KEYS */;
INSERT INTO `associate` VALUES (10,38,3),(10,39,3),(3,40,4),(4,41,4),(3,42,3),(3,43,3),(10,44,3),(10,79,3);
/*!40000 ALTER TABLE `associate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `toBelong`
--

DROP TABLE IF EXISTS `toBelong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `toBelong` (
  `idStatistic` int(11) NOT NULL,
  `idDico` int(11) NOT NULL,
  `statDate` datetime NOT NULL,
  `tryNumber` int(11) DEFAULT NULL,
  `numberSuccess` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idStatistic`,`idDico`),
  KEY `FK_toBelong_idDico` (`idDico`),
  CONSTRAINT `FK_toBelong_idDico` FOREIGN KEY (`idDico`) REFERENCES `Dico` (`idDico`),
  CONSTRAINT `FK_toBelong_idStatistic` FOREIGN KEY (`idStatistic`) REFERENCES `Statistics` (`idStatistic`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `toBelong`
--

LOCK TABLES `toBelong` WRITE;
/*!40000 ALTER TABLE `toBelong` DISABLE KEYS */;
INSERT INTO `toBelong` VALUES (38,53,'2015-05-27 16:58:44',13,'4'),(39,54,'2015-05-27 16:58:44',23,'2'),(40,53,'2015-05-27 18:58:44',11,'3'),(41,55,'2015-05-27 16:58:44',4,'1'),(42,55,'2015-05-27 16:58:44',6,'6'),(43,56,'2015-05-27 16:58:44',11,'11'),(44,58,'2015-05-27 17:58:44',9,'5'),(79,58,'2015-07-14 12:31:47',10,'3');
/*!40000 ALTER TABLE `toBelong` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-20 23:33:11
