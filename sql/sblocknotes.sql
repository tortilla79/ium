-- MySQL dump 10.17  Distrib 10.3.18-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: sblocknotes
-- ------------------------------------------------------
-- Server version	10.3.18-MariaDB-0+deb10u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar` (
  `daydate` enum('mon','tue','wed','thu','fry','sat','sun') NOT NULL,
  `hourdate` int(11) NOT NULL,
  `subject` varchar(20) NOT NULL,
  KEY `subject` (`subject`),
  CONSTRAINT `calendar_ibfk_1` FOREIGN KEY (`subject`) REFERENCES `subject` (`name`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`hourdate` > 14 and `hourdate` < 19)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar`
--

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
INSERT INTO `calendar` VALUES ('mon',15,'matematica'),('mon',16,'fisica'),('mon',17,'informatica'),('mon',18,'matematica');
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson` (
  `user` smallint(6) NOT NULL,
  `teacher` tinyint(4) NOT NULL,
  `subject` varchar(20) NOT NULL,
  `appointment` date NOT NULL,
  `daydate` enum('mon','tue','wed','thu','fry','sat','sun') NOT NULL,
  `hourdate` int(11) NOT NULL,
  `status` enum('done','book','annul') NOT NULL,
  PRIMARY KEY (`user`,`teacher`,`subject`,`appointment`,`hourdate`),
  KEY `subject` (`subject`),
  KEY `teacher` (`teacher`),
  CONSTRAINT `lesson_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  CONSTRAINT `lesson_ibfk_2` FOREIGN KEY (`subject`) REFERENCES `subject` (`name`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `lesson_ibfk_3` FOREIGN KEY (`teacher`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `CONSTRAINT_1` CHECK (`hourdate` > 14 and `hourdate` < 19)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (2,5,'informatica','2019-11-23','mon',15,'annul'),(2,6,'informatica','2019-11-29','mon',17,'done'),(3,10,'inglese','2019-12-03','mon',16,'done');
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES ('chimica'),('fisica'),('geografia'),('informatica'),('inglese'),('matematica'),('storia');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'mario','rossi'),(2,'roberto','scarto'),(3,'gianluca','retto'),(4,'milena','gatrio'),(5,'giorgia','panice'),(6,'lucia','artesa'),(7,'franco','filet'),(8,'beatrice','guarda'),(9,'dario','impara'),(10,'serena','dente');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_subject`
--

DROP TABLE IF EXISTS `teacher_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_subject` (
  `teacher` tinyint(4) NOT NULL,
  `subject` varchar(20) NOT NULL,
  KEY `teacher` (`teacher`),
  KEY `subject` (`subject`),
  CONSTRAINT `teacher_subject_ibfk_1` FOREIGN KEY (`teacher`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher_subject_ibfk_2` FOREIGN KEY (`subject`) REFERENCES `subject` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_subject`
--

LOCK TABLES `teacher_subject` WRITE;
/*!40000 ALTER TABLE `teacher_subject` DISABLE KEYS */;
INSERT INTO `teacher_subject` VALUES (1,'matematica'),(2,'matematica'),(3,'chimica'),(4,'chimica'),(5,'informatica'),(6,'informatica'),(7,'fisica'),(8,'fisica'),(9,'inglese'),(10,'inglese');
/*!40000 ALTER TABLE `teacher_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `privilege` enum('admin','client') NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','guglielmo','beccuti',''),(2,'client','landolfo','rufolo',''),(3,'client','marco','valdo','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'sblocknotes'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-01 14:13:12
