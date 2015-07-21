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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-20 23:33:44
