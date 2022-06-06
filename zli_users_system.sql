-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: zli
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users_system`
--

DROP TABLE IF EXISTS `users_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_system` (
  `UserID` int NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `CreditCard` varchar(45) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `UserRole` varchar(45) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `storeName` varchar(45) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_system`
--

LOCK TABLES `users_system` WRITE;
/*!40000 ALTER TABLE `users_system` DISABLE KEYS */;
INSERT INTO `users_system` VALUES (1,'Oneill','Panker','111111111111','052-222','oneill@zli','1','Customer','CONFIRMED',NULL,0),(2,'Topaz','Eldori','222222222222','053-333','topaz@zli','1','StoreManager','CONFIRMED','Karmiel',NULL),(3,'Koral','Biton','333333333333','054-444','koral@zli','1','NetworkManager','CONFIRMED',NULL,NULL),(4,'Adir','Miller','444444444444','055-555','adir@zli','1','CustomerServiceWorker','CONFIRMED',NULL,NULL),(5,'Shahar','Hasson','555555555555','056-666','shahar@zli','1','StoreWorkerWithPermmision','CONFIRMED','Karmiel',NULL),(6,'Naor','Zion','666666666666','057-777','naor@zli','1','StoreWorker','CONFIRMED','Karmiel',NULL),(7,'Naruto','Uzumaki','777777777777','058-888','naruto@zli','1','MarketingEmployee','CONFIRMED',NULL,NULL),(8,'Dorin','Beery','888888888888','059-999','dorin@zli','1','Customer','NOT_CONFIRMED',NULL,0),(9,'Shlomi','Koriat','999999999999','050-000','shlomi@zli','1','StoreManager','CONFIRMED','Tel Aviv',NULL),(10,'Yuval','Semo','000000000000','051-111','yuval@zli','1','StoreWorkerWithPermmision','CONFIRMED','Tel Aviv',NULL),(11,'Udi','Kagan','111111111111','052-111','udi@zli','1','Customer','FREEZED',NULL,0),(12,'Assi','Cohen','121212121212','053-222','assi@zli','1','Customer','CONFIRMED',NULL,10),(13,'Eran','Zarahovich','131313131313','054-333','eran@zli','1','Customer','CONFIRMED',NULL,0),(14,'Hen','Mizrahi','141414141414','055-444','hen@zli','1','Customer','CONFIRMED',NULL,0),(15,'Guy','Hochman','151515151515','056-555','guy@zli','1','Customer','CONFIRMED',NULL,0),(16,'Shalom','Assayag','161616161616','057-666','shalom@zli','1','Customer','CONFIRMED',NULL,0);
/*!40000 ALTER TABLE `users_system` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-06  9:41:49
