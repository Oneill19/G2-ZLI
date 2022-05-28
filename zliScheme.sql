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
-- Table structure for table `canceled_orders`
--

DROP TABLE IF EXISTS `canceled_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `canceled_orders` (
  `orderNumber` int NOT NULL,
  `compensationAmount` double NOT NULL,
  `DateTimeCanceled` varchar(45) NOT NULL,
  PRIMARY KEY (`orderNumber`),
  CONSTRAINT `canceled_orders_ibfk_1` FOREIGN KEY (`orderNumber`) REFERENCES `orders` (`orderNumber`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `canceled_orders`
--

LOCK TABLES `canceled_orders` WRITE;
/*!40000 ALTER TABLE `canceled_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `canceled_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaint` (
  `complaintID` int NOT NULL AUTO_INCREMENT,
  `complaintdeatils` varchar(3005) DEFAULT NULL,
  `OrderNumber` int NOT NULL,
  `Recivedate` datetime NOT NULL,
  `complaintStatus` varchar(3500) DEFAULT NULL,
  `complaintcol` varchar(45) DEFAULT NULL,
  `IsRemindrd` int NOT NULL,
  `refund` int DEFAULT NULL,
  `refunddeatils` varchar(4500) DEFAULT NULL,
  `UserID` int NOT NULL,
  PRIMARY KEY (`complaintID`),
  KEY `OrderNumber_idx` (`OrderNumber`),
  KEY `UserID_idx` (`UserID`),
  CONSTRAINT `OrderNumber` FOREIGN KEY (`OrderNumber`) REFERENCES `orders` (`orderNumber`),
  CONSTRAINT `UserID` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint`
--

LOCK TABLES `complaint` WRITE;
/*!40000 ALTER TABLE `complaint` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `itemSerial` varchar(45) NOT NULL,
  `itemName` varchar(45) NOT NULL,
  `itemPrice` double NOT NULL,
  `itemType` varchar(45) NOT NULL,
  `itemImage` varchar(100) DEFAULT NULL,
  `isSoldAlone` tinyint NOT NULL,
  PRIMARY KEY (`itemSerial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES ('1','White Rose',5,'Single Roses','/common/Assets/WhiteRose.png',1),('2','Red Rose',6,'Single Roses','/common/Assets/RedRose.png',1),('3','Blue Rose',7,'Single Roses','/common/Assets/BlueRose.png',1),('4','Yellow Rose',7,'Single Roses','/common/Assets/YellowRose.png',1),('5','Pink Rose',6,'Single Roses','/common/Assets/PinkRose.png',1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_in_product`
--

DROP TABLE IF EXISTS `item_in_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_in_product` (
  `productSerial` varchar(45) NOT NULL,
  `itemSerial` varchar(45) NOT NULL,
  `amount` int NOT NULL,
  PRIMARY KEY (`productSerial`,`itemSerial`),
  KEY `fk_item_in_product_item` (`itemSerial`),
  CONSTRAINT `item_in_product_ibfk_1` FOREIGN KEY (`productSerial`) REFERENCES `product` (`productSerial`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `item_in_product_ibfk_2` FOREIGN KEY (`itemSerial`) REFERENCES `item` (`itemSerial`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_in_product`
--

LOCK TABLES `item_in_product` WRITE;
/*!40000 ALTER TABLE `item_in_product` DISABLE KEYS */;
INSERT INTO `item_in_product` VALUES ('1','1',10),('1','2',10),('2','4',20);
/*!40000 ALTER TABLE `item_in_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderbydelivery`
--

DROP TABLE IF EXISTS `orderbydelivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderbydelivery` (
  `orderNumber` int NOT NULL,
  `receptionAddress` varchar(45) NOT NULL,
  `nameOfReceiver` varchar(45) NOT NULL,
  `phoneOfReceiver` varchar(45) NOT NULL,
  `deliveryPrice` double NOT NULL,
  `expectedDate` varchar(45) NOT NULL,
  PRIMARY KEY (`orderNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderbydelivery`
--

LOCK TABLES `orderbydelivery` WRITE;
/*!40000 ALTER TABLE `orderbydelivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderbydelivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orderNumber` int NOT NULL AUTO_INCREMENT,
  `totalPrice` double NOT NULL,
  `greetingCard` varchar(3000) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  `orderDesc` varchar(45) DEFAULT NULL,
  `fromStore` varchar(20) NOT NULL,
  `orderCreationDate` varchar(45) NOT NULL,
  `orderCreationTime` varchar(45) NOT NULL,
  `cutomerID` varchar(20) NOT NULL,
  `paymentMethod` varchar(45) NOT NULL,
  `orderStatus` varchar(45) NOT NULL,
  `confirmedDate` varchar(45) DEFAULT NULL,
  `completeDate` varchar(45) DEFAULT NULL,
  `deliveryMethod` varchar(45) NOT NULL,
  `supplyDate` varchar(45) DEFAULT NULL,
  `supplyTime` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderNumber`),
  KEY `fk_order_store` (`fromStore`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`fromStore`) REFERENCES `store` (`storeName`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,50.52,'Thanks','OffWhite','Wedding','Tel Aviv','2022-11-23','18:18','316397983','Credit','CONFIRMED',NULL,NULL,'Store',NULL,NULL),(2,110,NULL,'White','Wedding','Karmiel','2022-05-23','20:26','1','Credit','CANCELED',NULL,NULL,'Store',NULL,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `productSerial` varchar(45) NOT NULL,
  `productName` varchar(45) NOT NULL,
  `productPrice` double NOT NULL,
  `productImage` varchar(100) DEFAULT NULL,
  `other` varchar(45) DEFAULT NULL,
  `productType` varchar(45) NOT NULL,
  PRIMARY KEY (`productSerial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('1','Red and White Bouquet',110,'/common/Assets/RedWhiteBouquet.png',NULL,'Bouqute'),('2','Yellow Bouquet',140,'/common/Assets/YellowBouquet.png',NULL,'Bouqute');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `reportID` int NOT NULL AUTO_INCREMENT,
  `storeName` int DEFAULT NULL,
  `reportType` varchar(45) NOT NULL,
  `reportDate` datetime NOT NULL,
  PRIMARY KEY (`reportID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `idSale` int NOT NULL,
  `saleName` varchar(45) NOT NULL,
  `saleDate` varchar(45) NOT NULL,
  `discountAmount` int NOT NULL,
  PRIMARY KEY (`idSale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `storeName` varchar(20) NOT NULL,
  `storeAddress` varchar(45) NOT NULL,
  `storePhone` varchar(45) NOT NULL,
  PRIMARY KEY (`storeName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES ('Karmiel','Karmiel','04-877-1234'),('Tel Aviv','Tel Aviv','00-111-222');
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `surver_reports`
--

DROP TABLE IF EXISTS `surver_reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `surver_reports` (
  `SurveyID` int NOT NULL,
  `FileName` varchar(100) NOT NULL,
  `DateUploaded` varchar(45) NOT NULL,
  `PDFFile` longblob NOT NULL,
  PRIMARY KEY (`SurveyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surver_reports`
--

LOCK TABLES `surver_reports` WRITE;
/*!40000 ALTER TABLE `surver_reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `surver_reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey`
--

DROP TABLE IF EXISTS `survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey` (
  `SurveyID` int NOT NULL,
  `SurveyName` varchar(45) NOT NULL,
  `Question1` varchar(45) NOT NULL,
  `Question2` varchar(45) NOT NULL,
  `Question3` varchar(45) NOT NULL,
  `Question4` varchar(45) NOT NULL,
  `Question5` varchar(45) NOT NULL,
  `Question6` varchar(45) NOT NULL,
  PRIMARY KEY (`SurveyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey`
--

LOCK TABLES `survey` WRITE;
/*!40000 ALTER TABLE `survey` DISABLE KEYS */;
INSERT INTO `survey` VALUES (1,'Shopping Survey 2022','Did you enjoy?','Did It Was Expensive?','You spent a lot of money?','There is enough stuff?','Store manager is good?','Rate the last buy'),(2,'Color Survey 2022','How much you like blue flowers?','How much you like red flowers?','How much you like yellow flowers?','How much you like white flowers?','How much you like green flowers?','How much you like pink flowers?');
/*!40000 ALTER TABLE `survey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_answers`
--

DROP TABLE IF EXISTS `survey_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey_answers` (
  `SurveyID` int NOT NULL,
  `UserEmail` varchar(45) NOT NULL,
  `Answer1` int NOT NULL,
  `Answer2` int NOT NULL,
  `Answer3` int NOT NULL,
  `Answer4` int NOT NULL,
  `Answer5` int NOT NULL,
  `Answer6` int NOT NULL,
  PRIMARY KEY (`SurveyID`,`UserEmail`),
  KEY `UserID_idx` (`UserEmail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_answers`
--

LOCK TABLES `survey_answers` WRITE;
/*!40000 ALTER TABLE `survey_answers` DISABLE KEYS */;
INSERT INTO `survey_answers` VALUES (1,'dorin@zli',6,6,6,6,6,6),(1,'oneill@zli',1,1,1,1,1,1),(2,'dorin@zli',6,5,4,3,2,1),(2,'oneill@zli',1,2,3,4,5,6);
/*!40000 ALTER TABLE `survey_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_reports`
--

DROP TABLE IF EXISTS `survey_reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey_reports` (
  `SurveyID` int NOT NULL,
  `FileName` varchar(100) NOT NULL,
  `DateUploaded` varchar(45) DEFAULT NULL,
  `PDFFile` longblob,
  PRIMARY KEY (`SurveyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_reports`
--

LOCK TABLES `survey_reports` WRITE;
/*!40000 ALTER TABLE `survey_reports` DISABLE KEYS */;
INSERT INTO `survey_reports` VALUES (1,'Survey #1',NULL,NULL),(2,'Survey #2',NULL,NULL);
/*!40000 ALTER TABLE `survey_reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_customer`
--

DROP TABLE IF EXISTS `user_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_customer` (
  `userID` int NOT NULL,
  `balance` double NOT NULL,
  PRIMARY KEY (`userID`),
  CONSTRAINT `user_customer_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_customer`
--

LOCK TABLES `user_customer` WRITE;
/*!40000 ALTER TABLE `user_customer` DISABLE KEYS */;
INSERT INTO `user_customer` VALUES (1,0),(111222333,0);
/*!40000 ALTER TABLE `user_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_store_worker`
--

DROP TABLE IF EXISTS `user_store_worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_store_worker` (
  `userID` int NOT NULL,
  `storeName` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`),
  KEY `fk_store_worker_store` (`storeName`),
  CONSTRAINT `user_store_worker_ibfk_1` FOREIGN KEY (`storeName`) REFERENCES `store` (`storeName`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_store_worker_ibfk_3` FOREIGN KEY (`userID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_store_worker`
--

LOCK TABLES `user_store_worker` WRITE;
/*!40000 ALTER TABLE `user_store_worker` DISABLE KEYS */;
INSERT INTO `user_store_worker` VALUES (2,'Karmiel');
/*!40000 ALTER TABLE `user_store_worker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `UserID` int NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `CreditCard` varchar(45) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `UserRole` varchar(45) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `IsLogged` int NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Oneill','Panker','1234','052-222','oneill@zli','1','Customer','CONFIRMED',0),(2,'Topaz','Eldori','23456','050-000','topaz@zli','1','StoreManager','CONFIRMED',0),(3,'Koral','Biton','5555','054-444','koral@zli','1','NetworkManager','CONFIRMED',0),(4,'Adir','Miller','99999','053-3333','adir@zli','1','CustomerServiceWorker','CONFIRMED',0),(111222333,'Dorin','Beery','1111111111111111','0545344778','dorin@zli','1','Customer','CONFIRMED',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-29  0:46:25
