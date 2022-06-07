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
-- Table structure for table `colors`
--

DROP TABLE IF EXISTS `colors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colors` (
  `color` varchar(45) NOT NULL,
  PRIMARY KEY (`color`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colors`
--

LOCK TABLES `colors` WRITE;
/*!40000 ALTER TABLE `colors` DISABLE KEYS */;
INSERT INTO `colors` VALUES ('Blue'),('Green'),('Orange'),('Pink'),('Purple'),('Red'),('White'),('Yellow');
/*!40000 ALTER TABLE `colors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaint` (
  `ComplaintID` int NOT NULL AUTO_INCREMENT,
  `OrderNumber` int NOT NULL,
  `CustomerID` int NOT NULL,
  `WorkerID` int NOT NULL,
  `StoreName` varchar(45) NOT NULL,
  `ComplaintDetails` varchar(3000) DEFAULT NULL,
  `RecieveDate` varchar(45) NOT NULL,
  `RecieveTime` varchar(45) NOT NULL,
  `Status` varchar(3500) NOT NULL,
  `IsReminded` int NOT NULL,
  `Refund` float DEFAULT NULL,
  `RefundDetails` varchar(4500) DEFAULT NULL,
  PRIMARY KEY (`ComplaintID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint`
--

LOCK TABLES `complaint` WRITE;
/*!40000 ALTER TABLE `complaint` DISABLE KEYS */;
INSERT INTO `complaint` VALUES (5,60,8,4,'Karmiel','bad bad very bad','2022-06-04','20:17','CLOSED',1,NULL,NULL),(6,70,12,4,'Karmiel','Very Bad','2022-06-07','13:13','OPEN',0,NULL,NULL),(7,75,13,4,'Karmiel','Very Bad','2022-06-06','14:14','OPEN',0,NULL,NULL),(8,76,12,4,'Karmiel','Very Bad','2022-06-07','12:12','OPEN',0,NULL,NULL),(9,78,14,4,'Karmiel','Very Bad','2022-06-05','13:13','OPEN',0,NULL,NULL),(10,80,15,4,'Karmiel','Very Bad','2022-06-04','13:00','OPEN',0,NULL,NULL),(11,67,12,4,'Karmiel','Very Bad','2022-06-05','17:00','OPEN',0,NULL,NULL),(12,71,13,4,'Karmiel','Very Bad','2022-06-04','12:00','OPEN',0,NULL,NULL),(13,73,14,4,'Karmiel','Very Bad','2022-06-07','19:00','CLOSED',1,NULL,NULL),(14,76,12,4,'Haifa','Very Bad','2022-06-07','20:00','CLOSED',1,NULL,NULL),(15,78,16,4,'Haifa','Very Bad','2022-06-04','21:10','CLOSED',1,NULL,NULL),(16,71,17,4,'Haifa','Very Bad','2022-06-09','12:30','CLOSED',1,NULL,NULL),(17,72,18,4,'Haifa','Very Bad','2022-06-01','11:11','OPEN',0,NULL,NULL),(18,74,12,4,'Haifa','Very Bad','2022-06-03','12:45','OPEN',0,NULL,NULL),(19,76,13,4,'Haifa','Very Bad','2022-06-05','12:07','OPEN',0,NULL,NULL),(20,77,12,4,'Eilat','Very Bad','2022-06-07','11:11','OPEN',0,NULL,NULL),(21,79,12,4,'Eilat','Very Bad','2022-06-01','13:13','OPEN',0,NULL,NULL),(22,80,13,4,'Eilat','Very Bad','2022-06-06','12:14','OPEN',0,NULL,NULL),(23,84,14,4,'Eilat','Very Bad','2022-06-05','13:00','OPEN',0,NULL,NULL),(24,85,15,4,'Jerusalem','Very Bad','2022-06-04','17:17','OPEN',0,NULL,NULL),(25,86,16,4,'Jerusalem','Very Bad','2022-06-04','08:30','OPEN',0,NULL,NULL),(26,87,17,4,'Jerusalem','Very Bad','2022-06-05','09:09','OPEN',0,NULL,NULL),(27,88,17,4,'Jerusalem','Very Bad','2022-06-04','10:01','OPEN',0,NULL,NULL),(28,89,18,4,'Karmiel','Very Bad','2022-06-04','11:12','OPEN',0,NULL,NULL),(29,90,1,4,'Tel Aviv','Very Bad','2022-06-04','13:14','OPEN',0,NULL,NULL),(30,75,8,4,'Tel Aviv','Very Bad','2022-06-03','13:10','OPEN',0,NULL,NULL),(31,76,34,4,'Tel Aviv','Very Bad','2022-06-07','12:00','OPEN',0,NULL,NULL),(32,88,35,4,'Kiryat Bialik','Very Bad','2022-06-05','17:00','OPEN',0,NULL,NULL),(33,90,36,4,'Kiryat Bialik','Very Bad','2022-06-02','14:14','OPEN',0,NULL,NULL),(34,81,34,4,'Kiryat Bialik','Very Bad','2022-06-02','12:12','OPEN',0,NULL,NULL);
/*!40000 ALTER TABLE `complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `custom_in_order`
--

DROP TABLE IF EXISTS `custom_in_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `custom_in_order` (
  `customSerial` varchar(45) NOT NULL,
  `amount` int NOT NULL,
  `orderNumber` varchar(45) NOT NULL,
  PRIMARY KEY (`customSerial`,`orderNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custom_in_order`
--

LOCK TABLES `custom_in_order` WRITE;
/*!40000 ALTER TABLE `custom_in_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `custom_in_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `custom_product`
--

DROP TABLE IF EXISTS `custom_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `custom_product` (
  `customProducSerial` varchar(45) NOT NULL,
  `customProductName` varchar(45) NOT NULL,
  `priceRange` varchar(45) NOT NULL,
  `color` varchar(45) NOT NULL,
  `productList` varchar(45) NOT NULL,
  `itemList` varchar(45) NOT NULL,
  `idSale` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`customProducSerial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custom_product`
--

LOCK TABLES `custom_product` WRITE;
/*!40000 ALTER TABLE `custom_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `custom_product` ENABLE KEYS */;
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
  `idSale` int DEFAULT '0',
  `Color` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`itemSerial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES ('1','White Rose',5,'Single Roses','/common/Assets/WhiteRose.png',1,0,'White'),('2','Red Rose',6,'Single Roses','/common/Assets/RedRose.png',1,0,'Red'),('3','Blue Rose',7,'Single Roses','/common/Assets/BlueRose.png',1,0,'Blue'),('4','Yellow Rose',7,'Single Roses','/common/Assets/YellowRose.png',1,0,'Yellow'),('5','Pink Rose',6,'Single Roses','/common/Assets/PinkRose.png',1,0,'Pink');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_in_order`
--

DROP TABLE IF EXISTS `item_in_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_in_order` (
  `itemSerial` varchar(45) NOT NULL,
  `amount` int NOT NULL,
  `orderNumber` int NOT NULL,
  PRIMARY KEY (`itemSerial`,`orderNumber`),
  KEY `itemInOrder` (`orderNumber`),
  CONSTRAINT `item_in_order_ibfk_1` FOREIGN KEY (`itemSerial`) REFERENCES `item` (`itemSerial`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `item_in_order_ibfk_2` FOREIGN KEY (`orderNumber`) REFERENCES `orders` (`orderNumber`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_in_order`
--

LOCK TABLES `item_in_order` WRITE;
/*!40000 ALTER TABLE `item_in_order` DISABLE KEYS */;
INSERT INTO `item_in_order` VALUES ('1',1,61),('1',1,62),('1',1,63),('2',1,61),('3',4,60),('3',3,61);
/*!40000 ALTER TABLE `item_in_order` ENABLE KEYS */;
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
INSERT INTO `item_in_product` VALUES ('1','1',10),('1','2',10),('2','4',20),('4','5',10),('5','5',24);
/*!40000 ALTER TABLE `item_in_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_in_sale`
--

DROP TABLE IF EXISTS `item_in_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_in_sale` (
  `itemSerial` varchar(45) NOT NULL,
  `idSale` int NOT NULL,
  PRIMARY KEY (`itemSerial`,`idSale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_in_sale`
--

LOCK TABLES `item_in_sale` WRITE;
/*!40000 ALTER TABLE `item_in_sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_in_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_cancelation`
--

DROP TABLE IF EXISTS `order_cancelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_cancelation` (
  `orderNumber` int NOT NULL,
  `requestCancelationDate` varchar(45) DEFAULT NULL,
  `requestCancelationTime` varchar(45) DEFAULT NULL,
  `confirmCancelationDate` varchar(45) DEFAULT NULL,
  `confirmCancelationTime` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_cancelation`
--

LOCK TABLES `order_cancelation` WRITE;
/*!40000 ALTER TABLE `order_cancelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_cancelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderbydelivery`
--

DROP TABLE IF EXISTS `orderbydelivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderbydelivery` (
  `orderNumber` int NOT NULL,
  `nameOfReceiver` varchar(45) NOT NULL,
  `phoneOfReceiver` varchar(45) NOT NULL,
  `receptionAddress` varchar(45) NOT NULL,
  PRIMARY KEY (`orderNumber`),
  CONSTRAINT `orderbydelivery_ibfk_1` FOREIGN KEY (`orderNumber`) REFERENCES `orders` (`orderNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderbydelivery`
--

LOCK TABLES `orderbydelivery` WRITE;
/*!40000 ALTER TABLE `orderbydelivery` DISABLE KEYS */;
INSERT INTO `orderbydelivery` VALUES (68,'dani@zli','235235','Kiryat Atta111'),(72,'dorin@zli','dorin@zli','Karmiel1222121'),(73,'sdafa','fasd','asfdfasdffasdffasf');
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
  `fromStore` varchar(20) DEFAULT NULL,
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
  KEY `fk_order_store` (`fromStore`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (60,798,'',NULL,'','Karmiel','2022-06-03','00:31','8','Credit Card','COMPLETED',NULL,NULL,'Pickup','2022-07-02','13:13'),(61,812,'Happy Birthday!',NULL,'?','Tel Aviv','2022-06-04','02:32','8','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2023-06-15','13:17'),(62,5,'Happy Shavuot!',NULL,'','Tel Aviv','2022-06-04','10:24','8','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-07-09','12:13'),(63,255,'For your wife :)',NULL,'','Karmiel','2022-06-04','10:45','8','Credit Card','CONFIRMED','2022-06-07 00:00',NULL,'Pickup','2023-06-21','16:00'),(64,110,'',NULL,'For My Day','Karmiel','2022-06-06','11:00','1','Credit Card','CONFIRMED','2022-06-07 00:00',NULL,'Pickup','2022-06-15','12:22'),(65,110,NULL,NULL,NULL,'Karmiel','2022-05-06','11:00','1','Credit Card','WAITING_FOR_CANCELATION',NULL,NULL,'Delivery','2022-06-10','12:22'),(66,560,'',NULL,'','Karmiel','2022-06-07','00:20','15','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-06-14','12:22'),(67,150,'',NULL,'','Karmiel','2022-06-07','00:21','14','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-06-14','14:44'),(68,135,'',NULL,'','General','2022-06-07','00:25','17','Credit Card','CONFIRMED','2022-06-07 00:00',NULL,'Delivery','2022-06-07','14:44'),(69,500,'',NULL,'','Karmiel','2022-06-07','00:40','1','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-06-22','22:22'),(70,1100,'',NULL,'','Karmiel','2022-06-07','00:41','1','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-06-20','13:13'),(71,2300,'',NULL,'','Karmiel','2022-06-07','00:44','1','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-06-22','08:00'),(72,4520,'',NULL,'','Karmiel','2022-06-07','00:51','8','Credit Card','WAITING_FOR_CANCELATION',NULL,NULL,'Delivery','2022-06-22','22:22'),(73,1140,'',NULL,'','Karmiel','2022-06-07','01:03','35','Credit Card','WAITING_FOR_CANCELATION',NULL,NULL,'Delivery','2022-06-16','12:22'),(75,2589.9,'Love You','',NULL,'Haifa','2022-05-03','13:13','13','Credit Card','WAITING_FOR_CANCELATION',NULL,NULL,'Pickup','2022-06-20','13:15'),(76,154.5,NULL,NULL,NULL,'Haifa','2022-06-07','14:00','12','Credit Card','WAITING_FOR_CANCELATION',NULL,NULL,'Delivery','2022-06-19','12:12'),(77,2022,NULL,NULL,NULL,'Eilat','2022-06-07','14:30','13','Credit Card','WAITING_FOR_CANCELATION',NULL,NULL,'Delivery','2022-06-16','13:00'),(78,104,NULL,NULL,NULL,'Karmiel','2022-06-07','13:00','14','Credit Card','WAITING_FOR_CANCELATION',NULL,NULL,'Delivery','2022-06-16','09:00'),(79,1250,NULL,NULL,NULL,'Haifa','2022-06-07','15:00','15','Credit Card','WAITING_FOR_CANCELATION',NULL,NULL,'Delivery','2022-06-16','10:00'),(80,150.2,NULL,NULL,NULL,'Karmiel','2022-06-07','15:30','16','Credit Card','WAITING_FOR_CANCELATION',NULL,NULL,'Delivery','2022-06-16','15:00'),(81,79.9,NULL,NULL,NULL,'Eilat','2022-06-07','16:00','17','Credit Card','CONFIRMED','2022-06-07 00:00',NULL,'Delivery','2022-06-16','14:30'),(82,200,NULL,NULL,NULL,'Kiryat Bialik','2022-06-07','17:00','17','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-06-16','12:45'),(83,450,NULL,NULL,NULL,'Kiryat Bialik','2022-06-07','08:00','8','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-06-16','12:12'),(84,205,NULL,NULL,NULL,'Jerusalem','2022-06-07','09:09','9','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-06-16','07:30'),(85,284,'',NULL,NULL,'Jerusalem','2022-06-07','16:12','1','Credit Card','WAITING_FOR_CANCELATION',NULL,NULL,'Pickup','2022-06-16','09:30'),(86,281,NULL,NULL,NULL,'Jerusalem','2022-06-07','07:00','35','Credit Card','WAITING_FOR_CANCELATION',NULL,NULL,'Pickup','2022-06-16','20:00'),(87,1581,NULL,NULL,NULL,'Tiberias','2022-06-07','12:12','12','Credit Card','WAITING_FOR_CANCELATION',NULL,NULL,'Pickup','2022-06-16','14:00'),(88,250,NULL,NULL,NULL,'Tiberias','2022-06-07','13:00','17','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-07-16','16:25'),(89,147,NULL,NULL,NULL,'Tiberias','2022-06-07','15:15','13','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-08-16','17:45'),(90,459,NULL,NULL,NULL,'Karmiel','2022-06-07','19:00','14','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-09-15','14:00');
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
  `idSale` int DEFAULT '0',
  `Color` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`productSerial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('1','Red and White Bouquet',110,'/common/Assets/RedWhiteBouquet.png',NULL,'Bouqute',1,'Red'),('2','Yellow Bouquet',140,'/common/Assets/YellowBouquet.png',NULL,'Bouqute',1,'Yellow'),('3','Blue Bouquet',150,'/common/Assets/BlueBouquet.jpg',NULL,'Bouqute',2,'Blue'),('4','Pink Bouquet',100,'/common/Assets/PinkBouquet.jpg',NULL,'Bouqute',0,'Pink'),('5','new premade',100,'/common/Assets/flower.png',NULL,'boud',0,'Green');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_in_order`
--

DROP TABLE IF EXISTS `product_in_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_in_order` (
  `productSerial` varchar(45) NOT NULL,
  `amount` int NOT NULL,
  `orderNumber` int NOT NULL,
  PRIMARY KEY (`productSerial`,`orderNumber`),
  KEY `productInOrder` (`orderNumber`),
  CONSTRAINT `product_in_order_ibfk_1` FOREIGN KEY (`productSerial`) REFERENCES `product` (`productSerial`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_in_order_ibfk_2` FOREIGN KEY (`orderNumber`) REFERENCES `orders` (`orderNumber`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_in_order`
--

LOCK TABLES `product_in_order` WRITE;
/*!40000 ALTER TABLE `product_in_order` DISABLE KEYS */;
INSERT INTO `product_in_order` VALUES ('1',7,60),('1',2,61),('1',1,63),('1',1,64),('1',1,65),('1',2,69),('2',4,61),('2',1,63),('2',8,73),('3',30,72);
/*!40000 ALTER TABLE `product_in_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_in_sale`
--

DROP TABLE IF EXISTS `product_in_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_in_sale` (
  `productSerial` varchar(45) NOT NULL,
  `idSale` int NOT NULL,
  PRIMARY KEY (`productSerial`,`idSale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_in_sale`
--

LOCK TABLES `product_in_sale` WRITE;
/*!40000 ALTER TABLE `product_in_sale` DISABLE KEYS */;
INSERT INTO `product_in_sale` VALUES ('1',0),('1',1),('1',3),('2',1),('3',0);
/*!40000 ALTER TABLE `product_in_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `reportID` int NOT NULL AUTO_INCREMENT,
  `storeName` varchar(45) DEFAULT NULL,
  `reportYear` varchar(45) NOT NULL,
  `reportMonth` varchar(45) NOT NULL,
  `amountProduct` int DEFAULT NULL,
  `amountItem` int DEFAULT NULL,
  `revenueProduct` float DEFAULT NULL,
  `revenueItem` float DEFAULT NULL,
  `quarterly` int NOT NULL,
  `totalrevenue` float NOT NULL,
  PRIMARY KEY (`reportID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (1,'Karmiel','2022','01',23,35,2800,235,1,3035),(2,'Karmiel','2022','02',17,70,2380,490,1,2870),(3,'Karmiel','2022','03',10,10,1500,1570,1,3070),(4,'Tel Aviv','2017','04',1,1,140,7,2,147),(5,'Tel aviv','2018','05',2,2,220,13,2,233),(6,'Tel Aviv','2010','09',10,30,1450,180,3,1630),(7,'Haifa','2007','12',5,5,750,25,4,775),(8,'Haifa','2020','10',15,100,2100,700,4,2800),(9,'Karmiel','2020','10',15,200,1650,1300,4,2950),(10,'Karmiel','2020','11',10,12,110,60,4,1160),(11,'Karmiel','2020','12',10,50,1250,350,4,1600),(12,'Haifa','2020','11',15,30,1860,150,4,2010),(13,'Haifa','2020','12',2,600,220,3000,4,3220),(14,'Eilat','2012','07',22,150,2800,900,3,3700),(15,'Eilat','2012','08',20,400,2900,2600,3,5500),(16,'Eilat','2012','09',20,500,2200,2500,3,4700),(17,'Tel Aviv','2012','07',25,250,3350,1750,3,5100),(18,'Tel Aviv ','2012','08',20,200,2500,1400,3,3900),(19,'Tel Aviv ','2012','09',25,200,3300,2600,3,5900),(20,'Kiryat Bialik','2016','06',30,450,3150,2250,2,5400),(21,'Kiryat Bialik','2021','05',20,150,2900,900,2,3800),(22,'Tiberias','2021','02',20,200,2200,1400,1,3600),(23,'Tiberias','2005','03',30,100,3150,700,1,3850),(24,'Jerusalem','2021','04',15,400,1860,2600,2,4460),(25,'Jerusalem','2021','05',21,300,3010,1500,2,4510),(26,'Jerusalem','2021','06',21,550,2940,2750,2,5690),(27,'Tiberias','2004','07',25,150,2200,900,3,3100),(28,'Tiberias','2003','08',15,350,1860,1750,3,3610),(29,'Tiberias','2019','12',22,450,2800,2950,4,5750),(30,'Jerusalem','2019','03',10,550,1500,3025,1,4525),(31,'Kiryat Bialik','2017','06',20,650,2500,3900,3,6400),(32,'Karmiel','2022','06',39,1,4510,100,2,2300),(33,'null','2022','06',39,1,5720,15,2,1120);
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `idSale` int NOT NULL AUTO_INCREMENT,
  `saleName` varchar(45) DEFAULT 'sale',
  `discountAmount` int NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'ACTIVE' COMMENT 'ACTIVE\\nNOT ACTIVE',
  PRIMARY KEY (`idSale`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
INSERT INTO `store` VALUES ('Eilat','Eilat','00-000-000'),('Haifa','Haifa','11-111-111'),('Jerusalem','Jerusalem','33-333-333'),('Karmiel','Karmiel','04-877-1234'),('Kiryat Bialik','Kiryat Bialik','44-444-444'),('Tel Aviv','Tel Aviv','00-111-222'),('Tiberias','Tiberias','22-222-222');
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
  `Year` int DEFAULT NULL,
  `Month` int DEFAULT NULL,
  PRIMARY KEY (`SurveyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey`
--

LOCK TABLES `survey` WRITE;
/*!40000 ALTER TABLE `survey` DISABLE KEYS */;
INSERT INTO `survey` VALUES (1,'Shopping Survey 2022','Did you enjoy?','Did It Was Expensive?','You spent a lot of money?','There is enough stuff?','Store manager is good?','Rate the last buy',0,0),(2,'Color Survey 2022','How much you like blue flowers?','How much you like red flowers?','How much you like yellow flowers?','How much you like white flowers?','How much you like green flowers?','How much you like pink flowers?',0,0),(3,'buying experience 2022','How was your shopping experience?','How Much You Like Our Catalog?','Do We need to add more items to the catalog?','Did our promotions satisfy you?','Would you go back to buy from us again?','Our Interface Is convenient To Order?',0,0);
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
INSERT INTO `survey_reports` VALUES (2,'colorsurvey2022.pdf','2022-06-07',_binary '%PDF-1.7\r\n%����\r\n1 0 obj\r\n<</Type/Catalog/Pages 2 0 R/Lang(he-IL) /StructTreeRoot 10 0 R/MarkInfo<</Marked true>>/Metadata 21 0 R/ViewerPreferences 22 0 R>>\r\nendobj\r\n2 0 obj\r\n<</Type/Pages/Count 1/Kids[ 3 0 R] >>\r\nendobj\r\n3 0 obj\r\n<</Type/Page/Parent 2 0 R/Resources<</Font<</F1 5 0 R>>/ExtGState<</GS7 7 0 R/GS8 8 0 R>>/ProcSet[/PDF/Text/ImageB/ImageC/ImageI] >>/MediaBox[ 0 0 595.32 841.92] /Contents 4 0 R/Group<</Type/Group/S/Transparency/CS/DeviceRGB>>/Tabs/S/StructParents 0>>\r\nendobj\r\n4 0 obj\r\n<</Filter/FlateDecode/Length 288>>\r\nstream\r\nx���OK\�0�\�|�\�1�\�o�\�\�a\�,�;h+\�\���\�\�f��\�޴\�\�\�\�j \�\r��\�C�CZ6-f�t]\\-!ӛM�k\�\�\��9\�o�H!��W�p�F#X%r���\�\�-%����RA)!-\�J�n	�+�=�˅\r�_c_Yyl�q4�\�-�n%%��\��ĲB}M\�*ν�d+�p\�ǀ?Q#Ա�+\�P}púO�h\��}�\�0\����þ���w��\�ɸ�\�N\�	z\�vz�	Vd~��sV\�8�����X�	o|���\�\�S�!�1ސ�\�x\�\�ƨlv\�qz�7��\�W|\��\r\nendstream\r\nendobj\r\n5 0 obj\r\n<</Type/Font/Subtype/TrueType/Name/F1/BaseFont/BCDEEE+Calibri/Encoding/WinAnsiEncoding/FontDescriptor 6 0 R/FirstChar 32/LastChar 121/Widths 19 0 R>>\r\nendobj\r\n6 0 obj\r\n<</Type/FontDescriptor/FontName/BCDEEE+Calibri/Flags 32/ItalicAngle 0/Ascent 750/Descent -250/CapHeight 750/AvgWidth 521/MaxWidth 1743/FontWeight 400/XHeight 250/StemV 52/FontBBox[ -503 -250 1240 750] /FontFile2 20 0 R>>\r\nendobj\r\n7 0 obj\r\n<</Type/ExtGState/BM/Normal/ca 1>>\r\nendobj\r\n8 0 obj\r\n<</Type/ExtGState/BM/Normal/CA 1>>\r\nendobj\r\n9 0 obj\r\n<</Author(Oneill Panker) /Creator(��\0M\0i\0c\0r\0o\0s\0o\0f\0t\0�\0 \0W\0o\0r\0d\0 \�\�\�\�\0 \0M\0i\0c\0r\0o\0s\0o\0f\0t\0 \03\06\05) /CreationDate(D:20220607022442+03\'00\') /ModDate(D:20220607022442+03\'00\') /Producer(��\0M\0i\0c\0r\0o\0s\0o\0f\0t\0�\0 \0W\0o\0r\0d\0 \�\�\�\�\0 \0M\0i\0c\0r\0o\0s\0o\0f\0t\0 \03\06\05) >>\r\nendobj\r\n17 0 obj\r\n<</Type/ObjStm/N 8/First 53/Filter/FlateDecode/Length 309>>\r\nstream\r\nx��R\�j\�0}�����V�0��!�\�\n{�=\�zW�m\"1��ݻv\�1�\����sNr��PS��1� \�\�dJA1\����+��@Na6\�D\���a�\�ۙ0�\����\Z\�;>\0�F��χ�X\�CK\�Y6oj2��S�H�\r�}@g\�	��(�\�cj+\�\�t\'��v)�Ҩ0�6ncz�1]��n��\�g\�	c�\�p[�\�\�3\�=��>�kk��\�o�*\reG-;\�\�p���5v��\�\\|�w\�N{kO�C\�r$�I��;\�\�/G{xQ\�\�=\"�\���\�:,+��qU���׸�/;y$�_�\�.����\�\�\�;�\r\nendstream\r\nendobj\r\n19 0 obj\r\n[ 226 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 507 0 507 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 533 0 0 0 631 0 0 0 0 0 0 0 0 0 0 543 459 0 0 0 0 0 0 0 0 0 0 0 0 0 479 0 0 525 498 0 0 0 0 0 0 230 0 0 527 525 0 349 391 335 525 452 0 0 453] \r\nendobj\r\n20 0 obj\r\n<</Filter/FlateDecode/Length 31526/Length1 100304>>\r\nstream\r\nx�\�}	@SW\��9�&�\0	�5@�P���j%��+Q@Q�ETTܗjK�jKk��\�7�/\�6D�h7\�\�6\��O�ig��\�n\�m�\����\�\��U�\�\�~�\���\�9\�Y\�\�\�\���qƘ:V[\\PT��\�\n\�z2���`|ሳ���ϘΘ�\��\�>\\��1~z\�\�ϯk]��:c�\�/u\�i}}0c7lgLS\�\��g��\�0��\�\�\�-��^\�M���ۚ+547\�5|?a�\�1ސf8\�\�J:�r\�=�\�/]\�sW�\����ea}]�i+�0�]+�O�_��5Ӕ�\r\�1s\�o\\Zwպ�\��s\�g/��\�x\��of2\�\�I\��/i]�di��m\��\�[7�F\�\��ؚ}�\�\'L\�\"d\��=޹����ÿa	&\�O\�<#��1+&:x�-\�S\�Ø\�\�\�/�a�Q\�\�Cn\r�T\�K\�.<�>\�fa\�觀�\�&Ƣ�h\�\�Lչ�EL\��+�\�2�X}�mT��)�zEQt����ev\�c=O\�f\0�P\�t2�A\�34�\�\�t\'\�]�Nݭ�w\�l����\�ϳ_l�\"V�\�{��\�\�lگ=濳�\�\��\�b\\]\��\�w�Q�\�u�\'�~��\��{�\��1���\�p\\��\�˧2\�:\��K\��SLy\�\�\�r��\�\�dM?\�\Z\�cG\�R��Il̏��bI\�]s3��\'_\�0K=i\����\�+l�O�\�/1\� V{\����\��z���ʮ�%�t���g�t�:\�S\�_SMee?\�G��~\�\�?��\��\�?�/\�姶U�e=NZw�\�\�~̔\�[���V:O~\��G\�7\�o�\�2���(�Y������70Gw��GuЂ��\�ǔ���u�\���\�\\�SL\�\���\� M��\��~\�f��]\�ŧlW\�fk\�u\�9Di��7燼Lk��ظS����*ײ\\�\'�\�F��-h��\��y�q*SC��\�S-��Eu�\�\'���\�T����Zs	ZЂ��-hAZЂ����g?\�\�La\�g\�\�vFAZЂ��-hAZЂ��-hA�)\�\�o�-hAZЂ��-hAZЂ��-h��)�,*�i�\�5�؈\0O����n��\���-hAZЂ��-hAZЂ��-hAZЂ��-hA\ZY\�\�\�{A\�\�lj\0I�/I�x��Tӱ\�ĜP6�pփ�gY!\�&�rV\�\ZYke\�\�*��������a\�Vg�s]\�3]ڿ�^\�czMF��Z\���\��׿_��\������\�7`^�6��]�j\�\'�\�ׁ�6~�\�\r\��7��sW}�\��\�w��U/g\�lO\�)|-?��\�/\�W�]|\�jm�<�\�\�BY	�k\\\n;��c�r�I\�:\�g�f$F�\�\�8\�\��/��{8\�4�y�{<λP�\�]\��\�}\��v6����\�h���sO�\�\rK�,^Ժp���ys\�4\�njl�5sF\��i\�U�ފ�\�e��&N?n\�ѣJ��\nFz�G�6|\�мܜ!��2��핞\�\�\�\�o�Z\"\�M\�0Ch�^�*��-v�\�:}\�>]�k�\�~�쪃�\�G�\�	W\��m|\�Z������l:���Zz�[r�s8ޯ��\�\��=[\�rv�\�J\�\�E�*�\'hZ��\�QHMEgq|s�\�\�k�ž�\�\�\�ŵE�\�d,t6\Z��eF�	\�\�\�\�\��{�\��Pz\�P�!\\\\֧�\�5�J\�*��쩩U��jc�B\n}�\�X\�9b\�\�<gG\�}\�\�wZجZ���\�P7�ҧ֡S�Z\�޾\�gu�z��|�W�[n��u�\�.6nr��O�fq9ۿa��\���\�{\���4\�7LHq�\�aB�\�s\�q��b.\�uz\�,|me�Tv�Yv?�d��|J��\�\'kb���M\�tw�u���*�\r|/o���\�r�\�\�k\�i�F�ӧ�\�Ϊo\\\�\�\�**��UT�<E����w�\�B��Z\�\���J_��\�gsP8�b\r\�Wj]\�|�B����e�y9�\�k�h�b,WY\��\��^\� �}G6��*1_l!%�����\�稵7\��lrV\�S}�*��\�U\�X%V\�e��~�Kծ��½�\�Z6w�fpV*v�J�\�|�\n��\�ҊbE�;+��\�f�J��PǍ���V8ZT��k\�h{jU*\�)�d\�I�\�33��\�9\�uN:5j-&\�\�Y\�Xt\��T�``���\"b�0z\�r��Uj\Zv.|\n�\�\\b\�>V\�t5��\\x�<��\�\�D���W\�\ZWV]��v\�)�8�D��T�TT˂R�g�\�m�˪�Gi\�\�\�\��\�\�j��W{{CS\�ģl\�\��\��W\�\�r�f�]�b���v�9���{�ǝ��\�\�8K\�\�:�\�f�wx<\�ŵ\�C�/\�]c\Z\�]\�\�\�\�\�\'W���׎b\�������?��\�\�\�)��\�ca\�yNE�_\�JamAUGO\�U\�q2\�Ѽ��\n�(8EA�4�\�޾\�\�X�V�\�Z���3\�g�>\�\�;�Y\�B\�څ<ȕ\�;uT㑭u�\�\�F�{ZPc5{^$L�$\�`\"��\�c��y\�J���\n���h\�\�3\���9Ysw�0�}�6\�\�@\�6���nf.�3�G7\�=z\�\�\�f��O�(��0�\�\�\'\�\�����jn����ų�o\�\�̧�F`\�!f�\�\�X\�3�\n�?_��\�\"��x�y,\�b�C��օ�;��\�9\�5U\�\�\�ꪨL}\�~�*{i:P]\�s\�\�O�v�j\�\�k���`\�J\�74mL}��M\��\�0BX`�(\�����N�x\�\�\\��GG[��\�-.Z9�Jۯ\�\Z\�I�1�\�\�BYU\�Q��\�ჽnL\�$(sc\�䱣��UQ�B͘y�U��NzFʱ�\�ea���g�.�Q�\��d\�\�4S�\����-�)S�9��Ъ*��V\�h�k[|&\�(��P: :�\Z#\�\�M��h�����Mv�\�\�)&���j_xژ:�ݨ�	W�\�l��)0ƣ\�\rwnF\�q$tv\�\�Z�z�\�\�o?��1�lTV\�~�\�7\�ݯ�\�Do�\�no7��x��!��5��V/\�\n`��iϛ�X�*]c;��n��\�\�c]x�(iHtTl�TgC�h�)�jg\�I�c\Z�״6x�e�,�@��\�7��bsw�D\0\�`Z&\��q\�\�Y�k��\�ɔMĊ8۝\�P��\�:��\�\"uo<�x\�Ħi�wV\�\�ÎKj\�K\�E�Z_[\�J�\�\�ľ\�xx0��_[���\�Y�Ԕ�U��ڱ�\�&䩮:�*(��)�\�R��v�3d*Uv_(^LMu��T�A|\��\�9\�ۆ\�\�\�]\�>mߖ�1�OǶ#߭nW]�H��Dݨ�-�t�\�\�\�\�.\�\�F��X\"p8�f���v��\�Ժ	k{T�3�Gp\r\����)�xU�7�S[\�:;J\�Q�\�@\�0,M4�- f3\�\�Q�vԣ}/tSc�6*f6�\�W*�h�I�En���Jq�|ru�<�TQ=\��ੲ�\�N�RQX��\�\�.�����C���m#\�C\�\�\�I�x9�#˕\'�\�Y.s(O�m���ɼ\�\�\����U�+\��\���/�_?~�\0�~\�e:\�-6�\0\�n\�\0\�\��lF\�̄��ٔGX\�\0,�\0z�}u7cDΜ\�\�;\�\��X,\�z)\�Iq�mR�)\�R��b��K�Z�UR��b�˥X&\�R)�H�H�V)J�@��R�H1O��Ȓ�Y�\�R4I\�(E��R̒�N�Z)fJ1C�\Z)�K1M�j)����b�S��JQ!E���(��T�IRL�b�\�\'\�X)\�H1Z�QR�HQ,E��RH1R\n��R��\�4)�K1L��R\�I�+E�C�,\� )��(\�\0)�K�%E���\�+�[�>R����R�K�&EO)\\R��\"U\n�)R�H�\"I\n��R$H/E��R\�Ha�\"Z�()�RX���\"B�p)\�R��0J&�A�P)B�\�K��B�B��K��wIqD�\�R� \�!)J�/)��\�;)��\�)��\�R|%ŗR|!\�\�R|&\�)>�\�)�!\�\�R|$\�ߥ���\�C)>�\�/R�/\�{R�+\�;R�-ş�xK�7�xC�ץxM�W�xE��R�,\�KR�(\�R</\�sR<+\�3R<-\�SR�I�\'�xB�ǥxL�G����H���xH��x@����O��R쑢S�\�R\�+\�.)vJ�C\n�R���G����K�\�Rl�\�N)\�\�v)n�\�V)n�\�f)n�\�F)n�b�\�Kq�\�Jq�WKq�WJq��Kq��J�E�K���Kq�Jq���8_��h�\�\\)Αb��\� �L{�L{�L{�L{�L{�L{�L{�L{�L{�L{�L{�L{�L{�L{�L{�L{�L{�L{�b)d�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e\�\�e\�\�e\�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�e�\�wѩ�\�O\�@\�\�O���\�Y����6*�It�?\�ZK�5D��&Z\�O	Z\�O.� ZN��\�Ri	\�br.�\'�Z�-�&�Z�\����As�\�5\�&j�\'�\Z�\�@TO4�����h&\�\�WC�\�Dӈ����*��M!�U�M&*#*%�D4�h\�x�qDc��1�1D�����QD%~�8P�\�>TDTHT@u#���(��� :�h8�F4��\�\�\�\r!\ZL�\r\"ʦQ\r \�O�eeR�~D}�\�D}�z�\"ʠ�Ӊ\�h̞D.�4t*���9�R�����\�D��ĉ��x\�$PQ,9c�l\�&�\"�R��(��D\�Df�3�¨\�@J\�O(\��	e �JN�J��iĻ��hM�a*�@t�\� \���J\�}G�-\�7��\n\�\���r\�?��їD_P\�\�T��\�\0ѧT�	\�?\��1\�GD\'�5�+�>�\�T�\��D\�QݻD\��m�?�E�&5y�J��換\nz\�7�\n\�~r�L�ыD/P�牞#\�D\�=M�5�ѓ\�|�\�q�ǈ%�#�|�J\�#z�\�$z���\�G��hQ\'�\�M�{�v\�$\�\�\����\�@D>�{�\�&��h;\�6�;��8��4\�\�D�QݭD�\�LtэD7m%����F��\�\Z���\�*�+����S\�2�K��P\�%4\��.����.$��h3\��\��<*��Kt\�&����:\�\�,\�\�D\��1M�uDg�c��6c~�?f\���\�}\r�;�h�?�����$ZA��h\�R�%4�b꾈�\�SZH�-���Z�\�\�%�C���f\�̚�{#Q��\'�ETGTK4�h\�t\r\�l:\�4�\�j\Z��.TI4��;�.\�Q*�ʉ&��mP�\�&�0\�o��D�m=h�\�\�4���#\Z\�!/\�c�4�h9K��3@\�~\�&P�\�v&�\�ok��J@#�<D�D#�Qx��Ө4\�o�\r#\Z귊G#�(\�o\��[+AC�\�j\�`�D�\��\r���Vqc��V�7��2�{?�B_\"7\rև�7\r֋(�(�(\�oQ\�I\�1{И�4��Fq�P�d�$\";Q\"Q�\�R��[f�\�����X�\"Q4Qu�R9#�\"�\�\�\�D-�\�#2��PK=�ԑS%R�8�tE\�r��w�lp�\0}8��\�\�����\Z�_�\�K��\0>>��)�	\����\��G�߁�E\�v�5�\��!��\�}�\�������ooo\0��\�s�>��*���\���t\�\��K\�/��/\0\�ϡ�Y��	�\�x\Z�)\�?A?>\��D�\�\�\�͎\�\�g;E\�?b�G��O\�>|><<`^\�߼\�q�y�c�y�c\�	\��^`\�v�n|~����V9\�6�v\�eZ\�\�nZ\�\�f:\�q\'pp;pp+p���\�f�M��\�sx�i�\�z\�렯���\Zc]���\�XW�w9pp)����]��.2Nt\\h�\��8۱\�x�\�|\�m�\rj�\�l5ױ�\�:\�yۼgmk�\�]\�=c\�Z�i-7�������\�־�\�b\\\�]\�=}\�j\�*\�\n\�\�m+�{���I\�\�\�]�m�W�̶l\�2�\�e|\�2^���_\��̲̹L5/�.�.ٶ\�\��.n[\�[�\�[�\�b�-\�\�ή};\�SJ��5�\�-%����\�z4\��\�\�\�\�\��6o�\�m\�m�6nk�\�\�\��\�\�\�zg\�\�xgl��Nϭ�N\�V\�ʭ�NE�)�^\�\noyn�w�2\�܉މ�O\�\��m�wl\�h\�m���rK�Ÿy�dIr&�1��I�	���v��=�v��\��\�jTd�#Q\���\'%��	g&\\��F\�?�x\�{�-��{>\�ݸ\�\�tў�ޙ%,\�\�UcĽ\�N�(\�8��x�`\�^����\�\�Q�?�\��ʝ�3n��\�\�c%\�\\�❞q~�p�\�4�\�\�|�\�i>~�/�\\|zʪ}!\����zZe\�Ti��೉_*\�\�6of\�\�|\�\�~u\�\�䂪q�6�=Mw	\�Ф\�=cɲ%\�J\�i\�����\Z�\�y�\�##�\"O$&\�P\�GW�\��S\�W\�GW�\Z\�	�G\�_����$\�\�0)\�|\�$�\�1\��xL������\�!\�^:3�,uk\�(U�e�\�^�d)\�\�k�Vf\�S\Z5\�\\[*�KO\�\��u\����~�gd�r6kP\�뀳�6\�L\�`-�8X\r�V+�\��2`)�X���|��\�\�\0\��l�	h\Z�z`P\�3�@\r0�TU@%0�x�\n�����$`\"0�\�c�\��(�(��B�\0	x�|`p\Z0�\\ \��@`\0\�\�2�~@_�\r�z�� Hz.��\n8�$I�H�x �b\0\rDV�D@8`L�@(\�\�\�.|��p��?~\0�\�\�\�\�\0_����\0>>\0��\0�\0>>��\r�+�!��\�}\�=\�]\�\�m\�\��[����\��k���+�~\�e\�%\�E\�\�y\�9\�Y\�\�i\�)\�O����\��c����G���}�C������}�^`\�	\�\�v;��\�\0|�=�\��]�v`p\'pp;pp+pp3pp#p������\Z�\n�����\�\\�����\0\����\�\�\0����\�0��c�s\���ϱ�9�?\��\�\����c�s\���ϱ�9�?\��\�\����c�s\����qp�g\0\��qp�g\0\��qp�g\0\��qp�g\0\��qp�g\0\��qp�g\0\��qp�g\0\��qp�g\0\��\�\����c\�s\�}��ϱ�9�>\�\�\�\��{�c\�s\��\�����\�{�\�Ɩ,9&1?S�e�\�\�;r\�q���\�eKX�6�\�\��{�\�b롮d[٭\�\�c�?�\�~�߼�	vd�~>3��Y�f�\�`ׁ#����c<���s�tY�>;��ّK�,G:C��Q\���\�?�ᮃx\�\�5D��MБZ�/C�;rϑ\�N�A�f\�\�tV\�jY5�9�\�<\�\�\�Zi\�f\�	��h�\�E\�G[-d�\�\�\rZʖ�\��j�^(��EZy[���l[\�Ngk\�\��\�\nͳ5���J\�v&V\�,�NS�ɳ��\�6`\�6�sع�,�ۭ\�\�y\�|��\�\�\�Ǖ.\�\�\�\�x��K\�e\�\n<W�kN�^���bױ\��̈�K\�^S��~�8\�\�\�f��{�X\�#j�&-����\Z\�\��cfL�[\��3p\�\�\�\�w��u\��X��h�-iZ1\�\�\"q\��\�;�ҥ\����Sye<�9&2Wk%�N��L_Ʈ\���\"�B\�M\�zM뿮�\�V�|��݂��MS�\�s+�m\�v\�\�;\�6�_G����nv��r>\���lۉ���\�f���Tu?\�\����={\�^v��\�>�4�\�Kz��Q\�G\�G\�Q���8{\'\�S\�i�{�=�\�s\�\�(��^b/�\�x8ԋ\�c|f/\�?dl$~�ߋ8_\�f�\�oZ�\�M�\�b\�֮\�Vt}��fM�	\�v�\�Nv>~b_p�%w0�\�/\�\�vv}�N�:����ȍ]�3=N\�%\�K8\�T\��\�6�]\�\�஼��#K�eC��]1EE�~�\"Q�9��q^\�\�)\��]��lV�c:y�������\�~\��sY�\�9��u�g���;\�[�|Κ��������{l�\�[\�u�kw\�`5ds�j\��=a-�%ts�\�w\'>\�~.\���ø��\�\�T�[�\Zjq�\�Tg�\�\�8B<(\�\�#B\�|��\�P��(�MzF(�\�՗~�V\'Q\�p\�O\�֧$F\�\�C�JR|T�\�i��ii\�3�C\�\�Uo\�S\�c\\Kq�7C�\�1�\�QCTrlL�5��[���_\�#\�ZmQC�M\�\�^a4(���Δ��>\�R\�L���\�L\�k�!4\�j\�U4��Ƙ$1FRL�ux㬮\��Y��\�kQߑĆ�;�>\�a\��_\�\��\�\�\Z�ì�G;L\��\�\\�\�Y,����\�r\�}�\���̎�)X��x\��\�\�\��\�~Gj|\'\�\�ђ\Z�\�\��\�l�.�\�\�}v�\�/���=�G\��z�-\"\��\�b*�cKQD�ElufEo�yf�>挧/�P~ًg\�έ.���\�`2D��hҔ\�\r9�\�/�6aI٠\�Pc��\�a\�a���\�ko�\�\�1\�>��\�\�([RtXFVF�Ƈל���#ӳ\�C�)\�oIO\��L�R\�d�\�m/OrRRd|<�o��S�\�,\"ߩX=\�\�\�\��d\�f���\�Fb�md ���\�F�\�\�\Z\�!VƝ=z\�e����[���?�܆\�ud!\�Z|���\�P��\�\�<J\nn\�b�{[\� ��;Z�y\�N\�{gK^y�\�ߒ��\\\�*���\�!9\�\�\�\�*�\"\�1V[�\�u\�\�V\�\�Ps\��\��\�\\�_���\�\�>�\�jՅ�\��զ\�(c\�\�\�\Z\\�\�MSj\�8p\�\�u�ŉF݌\�\�hCzf�\���\�wvQr2_գg�\�j0X���D\'�\'��7\�l�b\�U}u��މ=\�Jl\�:\�ƹ1������vD\�%����,cf||b ԉ�Pk��\"ԉ�P\'�EJ\�9�l6�u4�u4�u4�u4ƣ�q�bE��ϓ�\�9�\��? 3\�ѫ\�\��\�,�g\�\�\�Y�ݴ\0�ٖne\�;-+;ۚ=�\r�#�\� �\�\�\�au�U�\�v;�s\'E�\�\����1!n�͑�\ZmP�d���d[L�ͤ\�\r6gB�3:���\�ٿg|_�\�M������hs\"VN�5t�m	5��:\�.Wv�o\�\�Ӝ\�\��\�T�֔>	��\�\�\�X�	k0���UH�dZsS��c�\�\�\�爸\�@\�t*ٻ{{P\�o\�e\r��5�>\���X\�c�ȑ�i\�\�{[=\�\�;���v��\�\�.N��y�(����\�ߟ\�]w��c�\�yoK����v�\�\�5Su\�ԁ�q)�8vBSԸ\�\�X>(=#=�\�ѣ3�\�z�$�\�L�1�FT[����j3�\�\�F&�[21\�U0=\�9�_/\�\�Ñ\�E�	�\�\�^T_\�@\�\r:]�\�\���\�:�Fw�\�\�p\�\��\�)GΞ4\�\�>q��z&�\�ω\r92>uX)v���j�>��\�D����]팴��#\��\�f����\�T�z\�=\�6>~�\�\�\'�\�s�\�/�\�\�\�o�X\��\�\�\�\��*\�\�a\��`�V>��3\�\�,\�T�\�du\���\�\�\�[ş��ʱ\�Xc�wr�v}\��X�Cz\� \�r�\�\�ee�\�5��b��\�RTPAj9�⿼�Xq��\�n\�F\�-�\�ݢ���[�t���;0�ѣM\'_\���\�9\�&D�/\\qC\�ȅS�řp\�\"�K�ͭ)\�9p�͓��͹�\�=u\��\�����BMYE5C��JX>w�\��l>o\��c�=\�\�x���\�\�J\�)\�Ι8l@���E�\�Μ\�/2�m�\�GGὓ\�JN\�_�6d\���٧�/�\�K\�A�C]:\�\�z�Eb�w\�\�e�\�\�;\�	�Kw\�oJ7v*\�<���\�\'\�{�9*�1�Y\�,v�x?\�`\�	Y��\�G ��-o�ِ=\�߷\�O�\�\�@\�\�\�\����\Z*N���!9\\\�ptq�.5U}3T�����\�\�\�#�\�:ctϤdW�b\�st\����W|�ɠ�U\�᳇\�&F\�\�s؁O\�\�U��>f�U9���\�Q�\��P\�\��#��\'\�\�\�2:��9�PVr~��ܣ�G\�kR�w\�?@з�\��#���\���\"\�79�PK�U\�`l�6\rP�o*\��[\�\�\\\�w\�\�r\ru�!�h�\�(�3\�ګ�������z\�w�\�-��%�֖h\��\�\\ �ۀ�\�-\�Nb0\�q\�\�\�\�e\�INl&�s�\�8v�Ǚ�\�e!yἙ\�qO2\�;\�\�[U-	{�9g�n-�V\���~���ݣ��\���>/��{\Z�P��!\�2Z\�ع�<=tgw\�pS\�H\�s��3\�;�v\�\��\�\�\�g(`v\n:_\�[�\��\�\�c\�ސ�\�\��F�3\�C�U_X\����vb�\�\�?0��> 7A�Q����\���l\Z#]\�\��b\�K�\0\����`5�\�¯u?��O�\�[P\�\�\�\�W<_�|䱍\�R��}\��VO\0�r�\�~���>���ڻU\�O@l\��L`~\�\�J8�SN\0��`a��_D\�\�h�r�o��O�sTݓ�P\0�\�8Y1\�*�nF\���q��Gis\�\�E\�\�[A\" ,8Ӡ\r\��\�\�5`f\�@\���^�n��\�:p�$��I��M�\�y�\�\�\�t\�oмS<=�4\�+�g\�\�\�%2H�T�a���êB$���>�O	~*����~#�̧*7k��\�S�\�t�\nG\�?ee@=qF�\�X	ǜ�+7�\�\�\�G9\\��lp�(Z�J쉿K\��U�m\�zx}��\�\�KS\�c�V��V��B`\��ā\�\�x쵽�v���\��GE��D<�[�n\�p|SN��^@H\�㛾\�\�\�N\�\��\���x��\�\'�4䱟=dG���1\��6��E�N\n\�\0iȐ\�\�	x��L�\�*�R\Z��Y���ܴ\�\�Yv�ߢC\'��k\0\\oP\�Z�ki�g0�r�^�\�\�L��\"�ۙ4����^	H07V!pOt\�s�\�A\�[A�\�r\��1@z���\�%��\�K�\�BOLӄ\�IVr��6�\�Lcӏ�8��|�I�6����\�e_y\0�a	Ͱ�@½\���p\�\�v?\�\�C\������j\�]�\�Y\��\�2����b#�G��cE$�tA�|�`\r@Wx�Q�8P\�4�1t\�N��oP�8�{F\�6��1���o\�\Z�\�VEi\�S�D���K{�����\�ޗ�\�qv_\�t�箕+\�\�\r�{A��/�y�ӑ\�Ň^p�\�\��\�gW>.7&_�\��ֆ�\�)�\�U\��� Id��I�ԋ��\�A\�$\���$\�\�{\�1§\�]֔Gƀp��5�t��\�\�U\�\�č�\��n��쭟?�8\��a�\�Qc�\�_U>\�\�lMKG����ʁ�\�C�\�\�G\�A\�l�\��\rINo�\��\"\�z�\��cL�=f=S\�\'�,,ݎ6PQr\��[�=��M\�LemЅ\��\�2\�˟(*ݜl~#����{�\��\�Nk\�&N��(�\�j�$|�\�Jg��]�\��\�M��;\�\\���JO��\�U	�\�\��V��>Ӽ8\�i��8c2�GY1\���\��hǦ\�\�P{�7*�\�9دB�I\��s�4�M\�3\�b\�\0��\"��ti\�\�g�\�h\�䁕\�	D�\��o����Ln\",��\"&\�6*���u@\Z%��)�V�8��5\��d��2\\�㟗E.1�\�\r�0���O\�b\�\Z6~fY�ھO�l���c}cE_�/\�12:\�Y�]���lpQ�Iy\�\�L�i�:V(��w��\�,Gc=�\�\�.7o୼\�ca<!��h�&�ґ�\�����\�=	^rp�\�E\�8=Nk�ړlN\�\�\�\Z�}݀����zEZ�\��G�\0\���kL�HIM_�\�+0�!\\��Yy�#��\�\�9V	\�Y`N�9\��e\�p8}\��\�\�ކX㿈��E}�N\0v\�%\�P1�\���b;�@��\�\�\�D��$\�rV\�\�]sJ����\�\��k��i��\���y\�\�\�\�6m��\rk���\�kw��7Ծ6�_S\�D�\�\�i��\�\\W=>u\0�,��\�O�/�\�[Wb�{`\�@\�K`\�A�W�6`�?���6+p\0@\�b�K$�,��XŗViŋ~a��]\�\0ų��f��\�\0�ƶz[\�,��\��(��}!��3�\n�%\���\�5� &Er	�f�kO\��F\�9�$0w\�Tb�NU�l�a1�N�}���\Z�a}\�v\�<N�&��c�Ǘ�\�\�g\�\��c����\�\�\�\���9\0ɧϰ4\rz#V����L;\�FL����\�t?6���f�}Kg��sH@c�\�k���1\�S�~�\�זn\�\r�-˂\�3C\�,@�<M�\�ѫ�q\�\"0ǀ\�k�m=�l�ZkP[%�?�\�\n��\n�\�X\�w�(�E�\�Tk\�y����h0Hx\�I�4]b�	$����Jbmt��\�R�Br\�h>��t2\0�5���\�`C��Wd����BIF��D�w�<\�ȩ�\�\�kOk\�\�5�j�mT��(Q\�r\�\\��:�Y\\\���<�\�#	��x\�v9}��#K;w.�h\�����IU��M뻫XbW\�\�\�\��O-�~�\�ű6\�-KZ\'�\�,KQ,;\�\�\�\�\�\�7\�\�\�.ɹ�.\��\�\�q�<b���\�/\�*Z\�ڊ\0�u\0��ȭH\�#/)�Z\ZP�+�\�\�Yf\n��<*�\"���̯!\Zi-\r�\�\�<i\r���R�<�/\�\�@�!s�2Xbl\�0ϛ�\�>�\�\0\Z�ߦ\�7�4O^I�\�K\�p\�\�\�c�c\�\�\�`��\0\�xq�)\r��9��\��rj�h�m�Y�\�\'W\��\�p<�\�\�ځ�\n�\�U3�E�[V\�:�١\�@S�#\�б�\�Yݝ�C���ڰ\�\��;\�a\��:F�z��\�\�\r�\n\�C\�M\�EU�����O	H\"Y\���e隖��\Z\\�\�~@2Q�\�<L9�n9${�\" ��\�l1�\�$S�ga�>K�W\�Nh	e\�ڠ��0�ށ@����\�w�-%w�R�\��\�:Ӥ	31\��5��\���%\��2�f�!�&\0�D���n\�\��%9\�c��##&ބq�\�ͼ#�wA��^\��\�d\�\n\���7&�1k�F��Ɵ�9���vL.-�u�Y��q�\r5C\�\�\�o\���[�L���\n~��M����\��w\r��N+mr��\"\�\Zv�y\��=��q����+\�C\�R}\�\�f=9���܎X����/�\�l6\�rg���D4^��\"\"�K�z�C\�O�6�\Z\�+�\�-7\r�5�\�:���\�\� �Cg\�p{��S�\�\0xb�į\0O@��5�\'\�-	4nF@\�(�Fuh�F\�p4��^M����y5�j\�\�&\��{\�zTo��X�W\�1���@޲|\�\�\�\�?	\�\�5�\\��Sd�>�jY�\�~Xw\�\ZT� �\�A�z�\'*�_\�\�|v\�\�?l�\�\���N\�\�>\�j\�2н�p�l\�\�R����\���\�?��=�\�\�}hC>��Pϡ��\�C\�r��c@�2�	9�\�\�\0D�\Z���^\�T_\�R�\�8�\�l\�$$I�$�\�B��0IH;�\�kr�\'y.\�\�\�\��\�T#M����o�\�u]�SR/�âp\\��Ց$:K�EKͣRL��X\�=\�\�hA�����֣k\�\�\�B�\�^��-��N��/�\���p�9k�P�\�TTqϢ\�[j�\�\�v�t_\'̆��K�G��Q�=\�\�愵\��w�k���?�le\�\�>Ub�g���O\�\�(��tn�J#-�ќ��5#�L5#�ƈP<R`�=Q\�\�\��)�I�؅R\�W!ߩ�\�Q/\����]\�M\�\"\Z�=�Q�Ng󄭎\�\\}h�l���#��y�1���\�$��0�Β꫽~�$����4 \02\�\�)\�8\\�\�3&��X:s{Ф\�ieӽ-��zO���\��\�i\�wEJ[a\�\\\\��W\��襂O\�\�+Yxh`�T<\�\�C�u}}Oَ\�C��-��,L�G��Z\�,Ʀ~Y����DX\'L\n��r�̋�Ho�\�:�l\�R\���\�O������Ԧ~)�\�ˈ�~7\�Z��J����\�.��l�j��N�{�z͡ŕ\�핒����d\�P]Y1\���XZ�%���\�V\Z\�qZO1��\�tY!a�W,+\�PS��\�氄}���]~�9T�f\�`�y�1����5[y��x�����t\�rq��q9��In%\'��ȟ\�|Jz	GV��\"�\�τ\�\��\�-\�snk\�x�\�qb\�8\�\�\�\�\�rGG\�\�-��\\2�nprO\r���_�n\�~���\�0pQN1�F̀C�,�\"0Iu(�4�g\�\�]\��+��\��\�@vo\��+��A��\0�A~\�?�Q>kK�k2���\�e�y\�\�\�b\�G��Mg��z*\�S��f����\�;\�BE�/\�\�\�+٢\�Y�nU\�<R\�\�[1\�/�\�i�s�\�\�˙I�Q�ǑjK\�UoT�\�`:�\��ǳHŒۛI�d�\\}\���}\�\�~�32���mM�q[hJG�I08���\�\\���aq\�=�õ.wUG�\�\��{o\��\�[�\�ۣeM,M���XCN1\�֏�e�i�+�\�c[�g�z䰢?��\�tB��+*4]Q�Y�\nM�V@5\�ڌWB]\�[Wd#Ze��PqVk��\�J�����\�\n6\�\�\�EW)L@kL\�\�/�ܰ\�1�C\r۪\�����c�\�ٯbvOɥ�->\0�k;ma�EG2$�\�\�M7\��\' �%�\'7��C\�@辢D\�\�V�v|r�J(u>c�\�\r~�\\r9�������\�Uمu�4%�\�\�p���\��Hd,sn\�U���V�*S�3J�ޡ�\�\�0ޕ[i\�%�ȹ��Y	%\"��\�\�\'\�C���\�Q\�z3Q*�50�ڸ��\�oJE�w�u-��\�y\�7\'=�m��\0fM�\�ok:\�[\�C�I��0Gn�`�F\�G����\r\�陛 uƆ�KH9�E�Y\��v+\�VĔX%�W\�wN�\0|\Z��4^\���\�\0O ��JN�O��>>#Vt\���;���\0��R|8k\�\�\0�\��\�J|8\�17�\n\�jj\�\�çh�\�j�T�����}ڢ�ۖ\�l\�\�\�B\�T��Y��\��7ހK�\��?�]Y���tZ�\�\�_𽀊\�Q\�\��f�\'\nw\�}:x��P�z\�E%�\ZV��h�A\�Z�l\�\�\�\n��\�\�\Z\�\�8�\�\�FS\r\�\0<m	�\�\0\�W\��Ѱ�q\�\�\0\Z8?�V��]?�(�\�\���\0�E\�\�+\0\�ς��xw�\�\�6��RKj�vrT��\�F*:\�:�t�E(O*d\04�5���9�l\�\�\\\�`m��V`�\�pl�\"atƽ޸\�DL�I��N�\�<!�!�	���^�l^�\���`�,}\�0�F\�Lz|�538\�p�\�N�\�~��1�A�D�\0.au\����9i\�B.\�#����jLX�A�\��w��:\�68P_�C/v\�{��W\�}ʷ\0�$Ur@��\n\�\r�\�Z/��\0��c��\�\�hv6�(*�U�\�X�]TU\�\�0j\�\�\�\�\��a�7haH\�ߧ��\��鳼@��\'\�z|�\�n\"qg��\�~\"\ZH�\0`\�T\�^�\n��\0v\�QE\�\�\�z�k��PɞGo)x�Ȕ\�\��\�\�T!��Rz\�T|{\�\��]�N-Ks����4�/�DYF\�[��#S2������C\ngq0>\�\����\�\�;�d��+\�\��\�\�G\�\�朋��1�\����\���\�\Z_�W^�\�諎F\Z+�:�\�\�0\�T\�;�7D͙��0��X\�`��$��T{�r���f�r��\�Sӟ����R,�ZuZ\��9dD�\�Ƚ��D�=\\\�\�V�\�OJ\�3_`\�\�&�f�U��J\')~R����/\�\�|�IX���%����8�\�Q\���m��N\�ږ��8\�9-\'O}*\�g}\�\�D\�\�)\�_X#A1Tk�*=���c\�@2\ZES����\\�\�\�R)vQ(ܑ$s�?:����� )d����\"\�7s	�+z.\��x\�dbRG�0dO�og�\�J��\�gKq\��R�\�$��\"؂@]8\�O��\�\�̔\�\�\��y�\��F�U1�\�e��mNq��9\�Vm\��z�)����OM\�zn/v\0��\�^e\�\"F NG��L2p�\�t;L,C�\�ؾ�,ޱ�F\�\�ųJݧ��\0�%\"V$�\�>�緒;\�\��V�\�\���@�O$�bA�-��@��0\�nn��u;��Ӏ\�\"��V�a���\�b�\�\��\�\�\�]�?8\�q�\�N�>\'��>aʥ{:1��k`�;\�cq϶\�ȧ�HS��)n�\�\��\�E�������&��\'z�v\�;s^w����#\�Yg+�\�c_��\"�\�E\Z|��\�a@��p��X\�O�\�+\�>�tM�wT�R��l��vs���\�\'\��]\�\�S�\�(�����VUC�K�h����hY\�\�,/�\�J�\'MtV�o,�\�9?c�e��xG<\\\�\��\���\�L_\'\�n\'Ő\�˶UU�n/F�92����/�H\��\�\�P*F�N���:�bۃ�\�;i\�9�\��\��\�\�\�_�\��|F��H�\�\n�!��H���\n�\��JKs�B6{Y4$�\r\����&)\�`�{���\�n���:}q��\�Q&XE�\nz����\�\�yt�Y���u�GW<[v\�\�U�ªj�:�dd��\�w��L��B8\��fGoܜ-D�ʎ\�j�V�樬��G�T\�a)\�0V\�\�\�\�\�35@��\�1(�ȕp\�-9C\�\�R8IzO�&R\�\\\�\�ݒ�\�f�\�1��OHJ\n�}�d�����3\�Va7pk�\ZE�\�\�\\0\�\�l�B.֘�֨O�4�Ƅ\��K�f�\rB�^\'ȧ�\�;����|1�:/r\�D\�	�\�	q\"�$�J\�E�(\�\�_�1,G+X}b\�	\�!D\��8A\'��\�]�!YR\�U�\Z:\�\��Isт\��\�	J/yܺ\�\r\�OiZ�pex\�_\�REb8/\�\r �]��\�=n�=$p�\�\��ا߭\�p�����+\�~\�t�E\�3!ռyЕ�b�Ĥm\�rָA\�M�V\�hX�\�yf\r���U�\�lK7�\�\�~G\�l3��\�\�\rK�$�wY,��\��P��.��5c�T�i�\�T����\�ٝ�@\�\�5X�{k�i;�\���N1��Σ#\��\�I\�NdR-�Di�ګ\�\�\�\�jy$\\����q\�G6�F�Y\\�5��\�-A�;d\�\�/�$û-�\�L\�G1�S��w��ұ�q\�|Q��ա��\n�`W�Vѧi�֝��m\����C\�\�\����\�\�\�\�H��\�#V/�\'�7\�\��w�\�Y�e�ʧ~^/���9\�\�\�sۿ��ۿ\�b<Yf�p(\�	�\�^�\�-0\�1\�A�<v��CI�3\Z�Q\n�8K`4k��C?M\�1\",��y�D��\"�D�<=q��`��|�m���r\r\�\��\��\�ոD\\`\�\�o\���-n3-�:k\�\�\nYu&\���v��\'|���A\�(e��o�f��\0���$]�+T8G�mWf��\'��\�\�TK,ac��b�s�\�w3&\�_(\�\�s�f�+���|��g?j\�S4\�J.�BJ�]A�f)\�qGmc��=a�As�,l�\��$	�k~O\�n0\�cOܡ\�;\�`Ώ\��ϓw̧�+\�\�w�^\�(Tu�kHՋ��Z�ܑ�\�}o3�\�e\�[6{� \�\�p�2�?X\"+�O��\�\��yd\�\�\"GT\�X�֡Ԭu(5k\�in5�J\���\�5k\��[\r���\�2x��k\�r�{_\�\�ݵ�=x~w\�\�p\�]\��w\�\�}w.\�\�5\�ĭ�~nd��W\�a{�\�\�OL\Zo?:<||;h�A�\�`\�\�\�.U�$l�\��A�\��\����\�<�/0u��S�\�|�<Z�\ZE`��10>\�Ѫa��΀)e�A.��\�\�2e��Z0Dsu�\�2k#\���B��T�E+=�I=\�\\\�%��`8#j\��\�U�\�\�c=�\�\0<~R\'4\�h[q�����s\�\�*�\'�\�.3C^�\�\��w\Z\�-i\�\n)�?\�x�|(#\�c}\�\r�ww|�]ۮ�+���mW�mW���\�\�n�����\�o\��J~v����%*�>��ޯ�\'Nڛ6/\�\�coTZ/f�5l�~b���+��h�\���J�\�\�\0\�>\�^�z�\�\�ϥC\�\���\"a�\��d���\�|n�WT�7Rб�<+��T\�0��%�	�\�U|\��\�\0 s&+G\�z�E\�=#U��[�n\�\�IC꥖��-�<pK���k\�\n�\�q�� ��_\�\��\�:�-:R8\�\0�q[\��\r@\���Z�Z?�ZOj�i=��TOj��\�I�K�_�\���\�\\�\�\�&a�\\�sC}q|Q0ֹ����\�����\���G��پ\��o\��\�u��-\�W�6Ѱ\�8�o5�/Id�Fd��\��9\�<T�\�\�j�Jm���;���g����f��\�\�(��\�Z�zF\�\���X\�~�XJ`�:�\���LV#E\�\�\�.���\�*����$\n\�y�\�	5�\�\�\�L8[w�z�d:���8�d�:l\�\'F���r�+\�u*F�`\�ky\�X>;������ي%3\�\��&\�4�lX\r�ӏY\�*l���\0\�+��\�\�F2��MP$>Ł�\�Ƕ�o[\���\�~ۑ�ĳΚ�5-K\�fWnim\�Ҕ�����\�\0\�\�~|h\�k�\r�<��\�;�tk�Y�\ZmE�<53��$�`\��3k1\�\0�\�0ң���*|X�4�w\�_]\�-�F�2\�D��ևr�)\�\�e$|>������\�F�e��\�\�wvw]\���\�\�\�e�zV\�\�H,\�\�;|Wo\0;8�篯�\�է?w�\�ڡ�]��\�\��k�֖��3��ę�\�8�\�B]^eΔ\� s\�OQ\�\�3\�B-�:�hJc\�e\n�\��.��Eo�r\�O`<�Ω+���\�\\+�k�f\�\�u��K�\��Cۚ\�|z4%u޿�\"��j�~��潒\�k�Q�\�wmH&�\��\�<�\�&��Hؙ[���y\�c\�m�	cv�l���$Q���\�XP�)�T��\�����@畳H��\�<X�07n�g/b�\�խ4��\�\�w\���_\�~\0\�\�\�@���=���e\"֭\���dƕ/��\�F�o��=��(w�\\�ǅ�U�v\�\��e5\�\�m�\'\�UθJl\Z��\�p)1�K	�\��֓�<�1Y)��w/hO�\\\�\��\�[�$o\�xW݊�����z\�\��\�^�ұ�J����߽�ֱ\�ї\�#?��>�h͎��T��uc�Mzф�z\�\\�\rR��J���O	\\��ek�\��ob\�\�w �\r\�g~]0\0\�}rv�u \Z\�kCFa����ܿoΛݹ%Y����b�\�\�p\�\�\r*�?8q��x\�uf���x�5�+̿��\'v(\�t^v�9�[�&H@�/�;�\��M\�\Z\�Q$$��\�S��ln�y%7x\Z\�_\�\��L\��Pn�e����,�G%���6�yl�{�D\�Ka;{ܙ.�g\�\�DWe1�)&\�/u�\�m�Z;2���f\�\����E�X\�m��uG�n��\�;S�m3�Q��7�|�|�\"P@�\�cm=c�i�n6�2�Gu�J\ZN��1n��\�ٗe\�Au��\�I\�{D�\�G�N��5M�����\�LSP_h�4T��N�p\�\\\�\�oY\�\�3������Y\�/�J/��\�x\�\�B�=\�h�,g\�%�\�a5�\�W�\�W\�V�B\�T7�Gl;>�I�lA�zށ:Np|!4t�<��G\�\��\�w5LN\�\�2tB?����;n\�꾭\�(\�\�=p{��(����^\��j�\�\�\�K&5|ϒ\�}#ٚU{�\�\�=\�eu\�U9��a��L\�	f\\�,/a�s�\n\nQ\�ކ\�N\�}Y4{�\�>�L���蜐A\'<{B}fщ�-\�8��\�[Yp`���@$\�5֤����&t$N�b�\���KHt׮E=&c\�\�\0舜\�\�1{B\�T�#�\�E�2I�\�\�[̬+SLZ�W,�B��\�\�\�\�I�!X�C\�N#�\�T\�H�\�#�b���!>�c\Z�EZ�Nd\0F\�\"��	\�N\�\0ڧ��m\�m�r�\�}��⓻\�w�׍�\�t]}lR(E�2k\�\��v���\�b\�خ}2\�^�\�\�\�ٱ{�\�=�;�g�l�\�\�Y\�]c����f�Y_�2�v\�߶f�9�jZ�m�n:�qC0��/�/\nj)PZ�[����\nG���ͺ�\��\nQĞv��STx=\�e�31��֦��\��\�\�\�\�t\�ud��K����2��<W����\��l�6�=[]UU�\r\�\�u\'��\�7\���s�\�L&�Ve�U\��\��jx�\n{�g�qp�W\�����\����	p2߶\�oeҹ\�]\�l��2���N\�48y�Y�2�\'@RG�W�s\���\�ޗ�7Qm�\�d_\�t\�\�\�mih\'\���6]�m\�Q�&iH����U\�R�M@Q6}\�C�(�Eʢ�\�*₠\�����l�s\�LҴ\��y��\����C�{\�{�\�|\�\�N:��F41ϥ\�E.\�W\�+U3T�T\�0U�*1�\�wg��\�^�\�f\�dd\�9��s���*J�Vŕ3ھѽ&��S�\�k��vv~��\rm��L��\�c�ssλ���\�/9/�d\�a���\�\�\�o\�P;�)\�^Rq�4�vDJUn�\���2D�Ke\�Z*B&\n�I\�?8��QSNL�W�?�*�!%35�\�\�\n�D�V\�T\�O�/p��	T�����k��\��$\�aN;�\��?1DW���03Ym9yg�*N%�\0�\�\�;\�;-(g��P~\"\",��\��.$��.�\�9�ްzb\�v\�a\'?\�uC\��EGW\�\Z�\�Eq�2d\�Upp��`D��Ctǂj�\�a�����{���A\�P�4*��\�+	\Z�W�7d\�\�̰�\�8�D(���\Z���\Z76wwGΔ�Qa\n��\"Ч�G(TxK�\�C\�#Ce>C3\�e�We����$�@�B/�4\"ch\�Ш8�9\�\�r����?\�\��\�@\�9C|\�\��|IpoLL��7Q\�ߛ⺕�~pp�cr�\�\�^�\�HzM�\�\��^SJ\�\\�s�g�3bo�Ge8\'\�\�QLNUV\����!\�i��#B��H�|���\n���켑�ӗ\�2r\ZWL^9j�B����\�xA\�A\�\�6\�4�\�yy튉���\�\�\�\�\��	�\r�\��v�\�Y#�!qC�6<�\����ӄX��7,�،]�y;���\�\�T���\��\�_\�c\�\�R\�P.}�\�L\���\�\'\�\\�b\�\��3\�\\1��O	!~a^\�oH�w��\"\�KL~L�BEp\0�\�WD�(op\�������\�Ĝρ<8\��Z�\�W�\�O���>W��\�\0\�\�\�E�\�7\�[���\�ᚆ][�\�\�;�;�GY\Z;\�G��\��\'�\�%|H\�;�\��}|�F�§�y;�Xb\�o��u�\�^Ż\�z}�\r��d�ѽ��}\�\\\�M�\�5��w>\�\ni鎤~_��\�Ӯi��D~ޕy\�7\0_6�\�%��\"\"L.+	����\�-�\��\�R�\n��\�,/�\�\�\�\"C���U�7\�_\�\�r�\�\�\�t�ߵ=.\�>\n�咢��\�\��\�t�LGGn	�\�\�ƪ�\\\�>�(+�2\�c~�2�FW��d<\�6�_9\�ߟq\�0y�cws&�\�9F�NB\�z�\�\�\�ۨ\�;\�W�5QQEwTWY4Qw(\�\�4�P\�@\�x\�\����G-#�M�\�\���Ȟ�ج\��VŪ&\�.�voU�jb+�u�\�\�k����\nn��\'�;���\�ڃn|�\��\��.������3N\�u-\�\�\�\��Ɍ�D��\�bG�b\�B/qBnq��nt�\�Ԣ�\�\�a�)8, *2\�����\�ya9\�BR��e\0������\nk����Zfb\�(`M-�\�ysN|\"��\�8�\\R�.u�ay3\n\�L\�\r\rͻ�p\�m����q�C�eF\�}㲆$eFI9�ʹ\�SS�̭�\�D\�S\�f��L(\�\�\�{=0WI�\�	\�6\�D��ޜ�h_��hΉ��9J�\�\�G\�K\�4\� \\�\�\Z�A�\�fyyK}*sb�F��LK���\�:/:�*#D�46w692�a�\�\�p\�ËG�SF��$����>�0:?.:�tr�\r\�\��V\�E\"��>\�e�ݜ`��\�C �2�S�\����X<tR�!}\��P�7\�C�\�7�������\�.�B|l7	�\�M\�hti\����\�\�_�R�Cϗ�\�8��G�K��\�$��\�*nt@tg�\�E\�`}���BN�U\��\��ɭw-#��]\�D�\����\�D�An���\\\\D,?\�&g��T\���kE��ٽ�\�G�\�\�\��W\�\r\�}$\�ZF%}\�\�ʈ����\�\�\�7w0�/\�\'�.���\�\'\�XN\�\0y=xj�!i!��\\�G�[%���<�$��;$l\�W�%\\r��D\�\�_\"% �\�i�\��7�ޠ�\�R��o(\��\�u7���_�I \� j��\�\��oE\�d���I�K\�\�^��G<\��x\�#\�2D\�&o�\�P�G<\��x\�#�\���\�#�G<\��x\�#�&\�?t|��z��\�X�\��x\�#�G<\��x\�#�Gn]x�\������Ή�W.*r�����\�5T\�^�ml�K\����e���\�}Ɩn\�Bb6\�W�,\"��\�a\�b�v�e	g�K_JLn`\�2b��[�{	DN;����\�>;J�\�2I�h�\�!��l�K/`\�<7>!^˖n\�B\"/x3[�)lYL(��d\��ʥ/%��b\�2\" $�-˅ܐL�\�E.A�\�\��\�[\�2\�3Sfxf\�\�L�\�\��̔n\�\�L�\�)3<3e�g�\��̔���\�+�\�a\�\�\��JЄ�ȆR9a$t���v�i \�V\0%aůZh1B\�L$\�5a��jhk$�\��\��n\0\�\��M9Q�zh1��Q	�`�Z��(�Fn�q[0�	J�\�\n~,�\�}��\�f�H�\�PW-�Pb|-�`]\np���\�\��XݱPk�Vt�쳻���v#��tC{\Z01\��p�j1�}dƱ��R�\�갿Nv[��\r�����FA{n+\'J�&Ď�3c^�p\�0̀�X\�\�W��ȩK\�v;�S#\�✽>?\�yXa��v`�\0{cĞ]~h\�z02�h1\�εFD�jA�\��V(9�<\���z(��M6\��\���,S̨\��i\�鰥f�b\��T�g�ZP<�`\�x\\;F\�ÅG�Fղ�f\�ʶ;Q�a\�\�\�Zi��f�ʌi\�L�Y��\�fm8�el7\�A�\�\�F.��t��\��53�kg\\3�1(\�<�Y�,�\�z�\�g��G��6܏�zԓ�\�u�\�x<Z3��\�®Rw��\�gf#�\�̋\rG�3F\rx�Q\�Z]\�066�:v�\�Ɏ\�\0/��\�%-�����\�\�<:�D��u,~2\�.�x�Й\�\�\�\�x=��g\�g\�(��9n\���Ǒ�Pf�\�oe�6O6�qmui�\�ef\��;�w�ēq�k2nX�#�*Kd\�SD1�\n�\���U.��\�ܢ�Ϳ��d6\�R�܎c�G��vhՂ\�\�\�Q�1M\�dA��\�s\�X�Ũǹ�ΰ\�\�fu\n\�`2M;f�a\�\�m��3/\�\�܍V�s���lT�\�i+\�\�\�\�f[ײ9ـ3�{\�XW�\�p\���s�=�������\��2�+\�1�v�a\�\'��t\��ɢ��\'^O�q\�\�zj\�+̈́\���\�=\�\�\�,	��\�/���\�_\�\�}}0�;\�\�\�<s�~�\�@�vŁv\�\�\0��Zp\�J�\�\�C��^3\�#\�z\�Ğ�_T1��¾2^1\��^���\����\�-\�8Hӄ���c�\�\�fvf�Fw��\�UE\�wF�g�\�\�8_\ZX�WN��G�ό�����j`��\�\�ӭ��gͪ\�C��\�<�9c@\�LdWo_�\�\ZpZ�Gv�[\�\r��c�9Ǡ\"\\\�<ژyrF\rsubbw��\�\�\�\�\�rh\�\\+\�\�v-\�\�7�\�\�fvޕ\�g��8�+�\�Fv��q\�ĕ��\�a,��[��tF��\�\�\�\�?a.\\i�\�7#�\��\�Zձ\�\�fl���i\�W\�v���7�[(\��\�\�a�\�8һ}Bp_�<\���Ʃ=xvS\�nN\��6\�O\�~;\�\�\�[5};�s���\��\�\�\"Ċ?�p�5�\�\��\��S��\�\�=�0s�\�θ��\�\�\�K�Ϊ�\�x\�\��\�>&Z1�\��\�<:w��\�a\�\�f��\"\�>^f��\�m\�p\�$3�_�=p\�x���8s56��\�6\�=¹˸>s\��\���\�8W0sU\��=������\�\\\�\�q���\�\�*�\�\'\�5��[	��g+�\"�M�ݲ\Z��BY�\Z\�L�Z!�BK<h԰\�\��LM\��P	\�M�{3F5�V@}\n\�qE�\�6�+`,\�WCL\�\Z�kV\�ˡ�\�5�\�Q\0-��\�\�82xЋ�Q\���\�N�<\�oU)FtZV�j��=���K�x\�~�_�\�.;�XK՘#42\Z�\0,*\�5\�:ޫ@�㫱ό�؇\"8\���� \�d\�WF�3�=�\�\�W\�\�\ZsP��\�\�\0ޫ�r4~1��\�;D%�,Ğ\�`�4,g\�\�2\\\�󊙩\�\rbqP\�r�)vqW�_[�\�F\�\�\�$|�O��O;`\�*q���\\�\�s�\�*ٹ�\�~D��#Q��\�\�\�\ZW�\�\�e�wF\'�Q\�f	��\�\�\�gTS7Y#\�(\��ؙ�-/�u5\�\�U\�B�\�Ȱ6��RiU6Un\�\�,vK��*�ج�\�a���)�\�DU�v�\�`7\�f�\��C�\�\�JUZ\r\�\�v��*Ӷ[Z�\�\�h\�Q:��݆zPhd:�\Z�޲�T�\�dm�J�f�E7Z\�Z�\�TI�ގpj��v\�\�>N�\�F�1֛�:��bA\���\�\�b\�(dn�\�f�Z\�z��r4��\�Z�̨3�\�<\�n0P�\�z�^o\�S&��\�\�:�ъ\�\�z�Ck4ٓ�&c�͈0�T��\��،\rT��\�hj�Z��&\�\�R\�0(�p�\�F0\nT�f\�i\�6��fO�JT�A\�h�\�\�\0^���+){�x\�i�PF]�[L��4�4l�i78�\0v\�j��l kat�\�\�J5���٪\�9(��r ��2\�>�\�\�@\����\�\�\�\�\�Y�d�u3\�N5k\�픮���\�g�mZ�\�f�#F\r\�f�Ŋ``\�Fh�\�u���\\\�R0\�\n]�\��l\�Ն\��\�披\\\'t.����@��\�\�Դ~\�;lZ��Yk����S\�\�F`܊�up\�l4ؓ\�Zt	Z{\"\�\"Ul�XM�՞����\�\�\�\�Ξ\�\�!\�\�n�4ڴ֦�m=\�RMS�Nko���p\�\���X�&#:�LM��\0c\�T��+jFD\�`j%�7ڭ�̄ZmF8��ka\Z\r�f�\�\�շc��\�TA\�Xl\�BBP�\�w�}�ΡD\�8�*Q\'\0\�Ok�Q\�\�fY+�\Z\�:S\�~��3DJ�1�Yn\�0\�ͬeV\�:̻\�a3ꘀt\�8t���H0\n�	�Jlh\�\�-�f�E�\�Ϟ��\n\"܁\�C����ހ\�D:M��?��� vu4!F�N���F\�O�Z0���V2��ZI\�k\�`�\�\�\�\�IH`c�`Nn5\�2Z\rz�6\�bkLA�М�\�D�^x\r�aO��%�c�F\�x\�<\�>!j`-� �a���IDe�D)�W�ɱ\�\�~\��\r\�\�T�\r�Z\"��g\�1p3\n\�)K=$;3\"E��3\�n\�d�\�n�\�Z�\� e�Z&�\ZM�L\Z���T\r��\�I\�\�q6d\�aP=�gQ�[�)\�pC\�;O���6\Z\�\�\�T���P�r���0!\�p\�ބ,]߂�5�Q��\�vJ\���ɨ74�Y�\0\�,\Z�ilDk���&>�e\�b3�1<�\�9\�2Ӡs8�/�!��F��r��46\�\�\�-�d�dnd�1)\�){\�\�\r�V�\�\�Q��; ��0E��\�f��V��j*�j\'��5Ti\rUU]9��PSHūk���&�֖TN��@�Z]Q;��,�\�S�q��JJ3��ZSSCUVS�\�Ue�\Zh+�((�PXZQL��~����\�J�Ak+)\�U��A��k�J��SZVZ;EI�\�V�1�`P5U���--�P����&TWU\�h\0���(�(�M����\�\nh�4�BՔ�\�\�0�zX_�\�+���R]Z\\RK�T�j�q�,S�)\�0P\�TA���\\I�\�\�\�\ZܫF�\�j�u�J4�	�\�及���QPYQ[\rU%xY]\�\�:��F��\�ե5���\�J\�	=*� ЯBÌ����\�����\ZM�-�\Zu�U�:�+\'\�=�<��\0��\�\�m	��\�\Z�\�5�̞\���\���\���\����l\�E\����\�m\�m\�m���\��6�g\r\�z01�\�\�\�\�\'\�x�\�o�\�\��\�Q2	:\�\�[՗ˑ>Gt��\�\�X�\�V�\n����\��� }\�\�[\���}_.z:BD�>z�#���\�x���%�B\�v\"�\�j2�(##�)$l�\�\�\�\�Bb��XH.\'V�k��\�Nb3\�C�@$�r\�G�����g�Ϲ\�\0\�*�$\�GJ�K��W\nx��Wx̀w\�\��\�	�{�^\0���w�>\0��\0\�<\�]\�^\"\�0�_<N��\��^\Z\���\�L��\rx�\0o\��go\�\0�c��	\���+ܳ$��=\�xр�\��[\�\�\rx���xE�7�w\�\�\rxK\��\�x\�^\��\�K����\n�Β^�xJ�\�\�\�\�p\�S\0^\�\�D�k\0<;\�u\�\n�\�\0x[\�5�;x\0\�׀�#y�\�pǒ��xJ�xe�7�?��^\�\�\�$�s\0\�<�{�����\��3�w�\\C\�NRA�����xՀ�<3\�\�x\�\�1��E|R$�vw�\�m	H�(���㱮\�l��I�\�{\n�GAj\�\�\�\��ww�xЧnyG>�X^\'\��\�Q[wwV�\"Tp���RGG��$E��`\�ᰊpWT�\0\�\\R�;ͨ�H��\�\�C+Ny���!\Z�Dګ�|B�\��(Q_1?\�\r�\�\�rIݺu��\\\�f1qE$RP��x\\B\�;M\�F��\�\�x?ĤH��co\��� \� �Ć�O��\r\'ȧ�\�\�UL�b7\�:��\�C\���\'D\�G��I�;fD\�0��1�+#|.!\��@\�\�b!)��{��jbT���jR,\�c}����,�˥���K\�\�]]\�YV��\�*\�B�\�\�J�D\�\��K@w�C( �����+s$$)\�\�\�y\n�6	*\�\�b\�.t�G\nY~p\�S(N�y��\�2�O\�p�A& b��[h��	\�ES�GJ\�\�`}`�bk���GH���tA\"\"%\�Qj��z�Jpu\�ܹP��yO]O�n��Z����!�����N0I���S`\�\�ys�r���\"\�cLJ�R7\�n�2D@���eR�#uR\�r&Ŝ\�\�e`m@U\�X\�2`MC\\��H�8R���\�\�FJŤT\ZE\�u\�0\"��i\�\�\"�rR\�\�\��.a]\��\�%hB\�\�:ER!�r�������\�\�=iଫ\r/gL$8$�21�ܢN8�r\�Ŏ�,̆k�;�\"!)g\"\�\�l)�\�\�EL\'^�,�\�*B�\�mES)\�Ͽ�	\�\�ƣtv��BB$�\�\n\n�{8�����?�2!b{\�X\�4F*n\�Ox�\�k0�r˫LL\�$Q����\�q�:h�\"dR&�\�\�3\'�0G]��\'�C\�E\��\�O�_��\�\�Pّ#�K.\�\'\�\'\�\�^!�� P��\�\��Ďh\�\�9\�\�%i\�c\��\���Ζ�H��G^\�>t4\�E(�48p���~��C\�y=\�:/р�F\�P\�\�/q\�ˏ\�i�O�v*�\�\�֋�eKڤ|ź�Kȗ��ڷ\�	,&\�b�\�u�L�p\�\�\�� 4!\�p\�>ۈ�������L�9\ru\�*�\�9\0\�\n�`�\�:̍�\�4M\�u=ܮѵ3Go27�\�d;S��\�j��^I�m\�f%U\�n3)�b�e~���\�\0et�VI�i\�?��m ���\���\"Vѝ\n\�\�\�\��YN\n9\�:#����C�*)-𓼸�P>Ak�$\�#;�8$o]\r=�V���o�\�\'F`�ĿS�\�\��\�A�BBG�\r\��\�Ƚ{�{�\�O��\�\�y[�֍�w��\�\�	t\'o\�\�ݼ�\�!9�40�@[G&\�j�a�\�r��$\�j\�fr\'�~�	5*?\�UD~�IZ{�\�\�谘U\n\�5\n��\�}�ŬWE\�\�E\�0\�ףT\�t:\��\�;_kl6�qh��TU�����2\�:K�����6�\�nUz\�?\�29-E\�~��ʪjU<=��F��V���\�\Z\r���\�-\�H\�����5<[���\ZB\�2�\�Q\r�\����qg�\�\�Nқ�v	�>\�l�Ɔmz�;�?��}M�	�Z\�|7=�L:�n���$�g�zG^��z\�\���\�~\�r\�\�\��),����;�zt\�ī\�7d���p�?\'��\�\�u\�%ˈ���տ��\�\�%I\��BRO\�_\�\�hk��W�z;\�\Z[���\�?\�gyqyn�g\n\�f[��{\�\n����\�\��\��m]�ŧ\�s��4䝃wx\�mUʵS�L\�F\\\�\�K^\�Κ�\��W,�}i\��\�=\r�\�\�\�^7��OV>q\�\�6��\�R*\���ť\"��\��4K\�w��\�\�~\�z\�љ�٣8\\XG;I10§#�\�/^ \��\�\�~J\�֭��2d\��^S�:�\�-\�1\��;�c\�/}P]d��˿<��m�2vxӵH!�WN��K\��\�\�/`�����|\�\�:ˈZSد\�S\\ӈfO\"De2�Г\"X�|��$ye�X�\�Y�9�G�\0����l7\�A�!{��d�\�9$W4`ArQ���F||~c\�\�3U9�+\�z,\��\�\����|�rӔQ���G�L⭦+�]�m��ɐ\�y���+ΐ;>1*N�L\�X[�U\Z+\�v�k\���g˷?גZ\�_��Dɇ_^^�\r�r�߷\'Mxhm���=t���\�e�\�;��<6CR�Q��\�wBc�Ƌ\��>Q��eQ�\�\'k�ߔe�\�P�\�Ő�.hۘ�\�C>p�d��3|�+�S?�wG\�8\�\'\�;�$\�e)~h}�\��ѩ\�˧\�6~���Jִ\�&\�\�I_�Z݊U\�_~{ag\�/?O�rj\��{�2,\�l�\�_\�N	i\��4\��\�\�Kwέ�\�:Nc�\�I!�\��$�z(�\�\�\�\�\rT��Y&}KT��Y��R�\� \�L6\�Ҏ?\�>�<�\�7u/\��O�lMG{���uWl\�\�_��qU�\�E/n<<caJnZr䊶_\�<\�I\��p\�+\�7��\��\�ϗy\�I�ǘ\�_l�?>����y+պ����\�ߚ�O������[4b�t\�e�#�ó\��\��P`\��,޽�hu.rS\�w�~\�A�[t\�\�\�o���\�-u\�#_})\��U�\�\�ھ��\�[�ީ���\�\�\�x\�\�\�\�g\�u�^\�l\�i\���w ��mf|1E~u\�c����������/g���smy�j\�\�~���.I��z\�\�o{j޼Ϝ8m\��ls\�?v���~\�\�F�\�&\�A\�Ƶ3��H\�J庥�\�\�\�ު\���z\�\�ӏڽ�\�}~�\�jtڇ�\�\�bZ3p�I�SQ��FӪ\�$]6�^�a\�OϩO����=<;-3u�>;CՠMM\�Ho\��K�%f�U�w:�\Z����yӛ-��n�\�P�gA�c�b`�3\�\�p:k8��S�\�-N�\�j\�-j~��o\�e\�p?��\�\�\�Ā\�\�\�䐄 0\�I�W��\�0�\��s����\��z~�%l⹚C\�b�{��\�~v\�i\��\�N\�\�k�N�i\�~�a�G��\�L�}qdl����K?SW>�(�����ׄ\�\�<x�\�\�i?&�/^�lr־��1o*�~�S�Lƅ\�b-��\�\�ŧ\�\�\�4D,�|}�|���u�\�=�#�j\�m�\�KE\�^�\�>?~\�P\�ak�N�o\�ã&��\�.��]qp\���������\�\�|xӓݳN��\��ܷ�j��\�W\�\�YZ|�꧚{\��.\�G:G=#\��\�Q隕�\�|\�x\��\����k�޻�oתL��{W�?\�3�\��ν�\'\�\�,�\�6�\�_�=>:\���_-]\�\�ݔ�\�����_��\�tW{0�<m\�ĺ\��Ǿ�}��\�\�g<Y0덶��\�>\�\�_�}\��ړ�\�s�\�\�h%�b\�}۷���\�\�zx\�wN>\�[\\,����*\�\�)��OeY\�F�X��r�t�{&�t�q���\'V�Zr\�R�iO�\�s\�\�F7��Y�雇gzU\�{-\�\�\�\�Y��M|+\�\�\�?�|sA�Ŏ�d\�as\�;ޙ3:wr�\�\�{K�N�x\�⑷�}6�pE\�++d�;G�\�=1|=�s\�/\�Or\�\�n�M@��yf�h�\�q\�x	;�S������ԓ!�\\�FUԯQ\�\nV\�$&o\��\�\�j��\'�����\�:��\�\�d�\�(�\�Yt:��J\�H�s ���p5�F\��w\�п�\�׮7m?�Q\�\�\�\�J��\�\�>\��\��ت-GOW\�y�?O�O\�M�|\'|����ҕacxn�tz\�Ĭ�\�z�\�B��\�^�\��:��\���lW^�\�\�o��ظ~ol͛Kռ%~���oo\�\��\�_L6���qQͶ�o�P��\��\�	ղ3\\\�\�˗\�\���B?�\�=\�W\��:z\�=����C�bMs��\�kK��\�\r>�\r�V�yG0w\�_���)�w�\�:7�\�\Z�&�J4�P\�E\�^�$�hw\��ڵ[#\�Ԫ\�#��ʻ\���Z\�\���+??�7�h̸\�\����NI��}30�4\�\�\�8|�on�|ЫK��#�y<����B f��\0��\�\�Ln������\�\�\�\�κ���\�weا�����yr�\�I\�����-�\�Ǯ{jK�}�?�~\���\�Ji؇\��S\�}\�\�Ů\�\��\��P\�!�\�Et�ۆ��G���̨�x=\\+V-\�7�[�y�練~t�}|9�=\�qǴf�\�\�{\�Z�+�]\�\rK�\�wM\�����9yg�g�vo��&�\�r���\�..~�l��g{�I����|v�&\�d\�\�\�|�t\�{{�\\yQ�2��͊aq1\�\�?]9\��H��g\�g\�W�+��\��Ю�9�5\�?\�\�\��\�W/�F&M�\�j\�l\�\�$��з֑\�\�I�N�.\�\�\��]A\�U,�wF\�\�_��c\�z�\��=�\�\�6\��id�\�\�\�؇���R\�\�\�S��e\��#\�\'~��u�\�ٜ�wj\�����3��z\�\�8�QƖ�\�u�3b�f�H\�&Y��!Y��l3\'õE\�bH����m*�dK�6[�����A\�\�$u�\���\�\��q_��{~\�\�Y~�\�����\��>\�$r�Jr8v�]]\�G��\0i��y�C���M؝�k\����[�%�d�!\��m�t�k�\�2�Sm�ڟ�x81G\0p�\�\�s��\�H�;uh{h5\��\�{�1ģ2rR��3��Ù\��\�\"d��\�\��Y�FY�\�\�q�`�۾hA�\r�>��x�wŧ�cm	�\�nu\�\"1|. u�R��U�\��w\�:�o����j�Lo��\�S�҃`)1\� 	E\���yv	\��y�Q�YQ��Ks\�\��̮~q��6��׾3�\�h\�g\�;�~#!J�\�M���W;�\�\Z@b\'\0$����;�{,_ˀ�\��\�P��/��;��\�l�`\0%\�/\�P\�H���\�W,$з\�\0��\�`\�\�>�\n�n��\� �\Z\�^�k\r\�\���ٚި��X�\�\�͜�/�{=\�\�,�4e�\�\�T�3�{\��.1\�ROHy�\�`/\�\��G��Wp\�7�\�)!\�,}�n\�q\�Fs�Ƙ\��)N\�0}Fխ\��?@��/=�Y:`O\\֫Uq�\�\�Z�\�̣\�s\�+\�\�\\\�\���X\�\��<h\��T��Չ=��bF��+��Q��X�~:]\'9r\���_��4\�p\�q&Q�E��ͫ$5���\�-�\Z�0=T��\��s\�\�D�wƖ\�p%\�fU_��\�՗yn�,�h��%:�\�B�Y}BjLm<P\�q7\�\�cY��t�\�\�gg\�SJ�%�\�\�8�y�!�َ�\�Ҹ���ك;V�R,];\�\��q<\��B�+Lc�7A$ǡj�P��\�*��&��\�]�{��\�\�\0\�h��\�H�^f\�|��\�Pd\�#,PX|a\�]n\�Ǘen\�\�_\�}t\�),��((;E|2ԱB1\�\�e��\�-ݧqQ�\��&�c�Ѝ{��hKo��b`\�{ߕPqky(\�D\�%\r��Q�e�?\r\�/��\�\�\�ygt��\�&l��\�`\'\�;�ۂ\�wu\�ϛ�A���L\�7��t�����\�@y�\�	P8\0B\'\�\�\�g3�)lEwI\�\�l��m}\n\�$\�(\�0\Z�?M�0��\�g\�%��^Gfs\�0g�\�\�0���0�\�\"r\�;/\�\ZA\"R��\�͗�b\"\"Ϳ�1\��o2k}\'\�\�<|��ΧϠ\�}�C���}�q��L뇇�ǥ9nĥ}�c\�3Rg���t݌3E��|O��Z�\�\�5\�\�P=���$� \�ƜS2�\�ú�I���(y��\�.�\�O�5�9:\�\����.y9\�K(\�q�QIa\��L-�9S\�v\���j�7\�,[�\�`G!�^�\��\�	IU���몇$��aI���ˏd@zrM�5׊\�8�\�\�\�Qiyr�r^\�Xg����\�\"�..\�\���\�Y�4\�+]2Y*K\�|�\�]�� gq�Cw\���5cv�/�(<�˹�U\�x\�\���V\�d\���@\�\�\�*YXx��\�ʰ�q��\n�8w_�\�\��\�Cl\�f9�Ij\�i�\Z��.\�\'v�0x�\�\�f�\�\�\�fger�\�\�\�ʢ \�\�ؓ|r\�\�Yk�r�8\�.\�\�\�tg\�ׇB��G�Z�sî�oȿQ\�\����(zu6K\������D�N&dKyV�\�\�Q��\�W�\�\�ĊW�%�\�rV\�*\r\�\�A��73�3�\r?�Q%݃^�~�x~B��P+�\�Y�^	�qNV\�ʶ46�\'ZIPBwa\��y-�r\�\�돯\�\Z)�YV��\���۳�\�8�^k�\�\�:\�v�3P.�\�4�� �\�13i?\Z\\ߟ\�Zɋx�>��i\�;YМ\�W^�\�JA\�\���\\@r� +�1���\�^X|\��7���I��;\\�\�D[\�yrD\�w���{\"E�(�o=\�|s\'��Wlf%13׿Py7\�\�V<�v0S��c\�Ds�JB�\�\�\��\�\�\�\�\�s\�M\�<h�,0�qI\�#�N���\n�\�\�\�\�X��I\��~�\�b8\�\�:\�>��~1|�\�V2=�\�X�_h3k\�Zm\�6\Z}�%�ip�&\�\�j�Z�w�\�*m�Z���\�M\�^�׏\�\�)�\�J\�ɸ��\�tk\���\�\�˓�\rHו�Yn\Zܖ\�\"�k_�?N\�Y���\�ֺ�Vd��fQ\�\�T[�y\�\�{_\��JS8	w%qj\Z7�����Ƭ��ۓ��\�R\��\Z�؈�y���<�B!�\'R[\��&��vm�\�&Ŀ�Ҷ\�&q�\�\�&	\�Z1df4��Æ\�٘8VF+a\�\�\�7�C�R\�1E�\�\�$��6��d\�\�%�\�mm\r\�Y�\�!\�rDA\�![qK��sg�S�$�1��f9\��\'\ZK{\�\\�Dt\�T\�Vvp\�/\Z�(=\'\�3��Sg\�\�\�~�;)\�9�G�\�\�\�ls\�\�@Tl�.�w\�w�Ӭ�\��9�i�c���M̭��\�-�w\�iX+X�rmxpr��i\�\�\�Y�D�\�\�A�\�\�w���F\�>=�V\�E��<ZUsO\�GY���\'֖A\"f�\�\Zʞ^�㴺hמ]�Nq�A���\�{}k^ բf��\Z\"ُ�X*�9Z\�<_ڄ\\ʏG����\���3\�\r\nendstream\r\nendobj\r\n21 0 obj\r\n<</Type/Metadata/Subtype/XML/Length 3099>>\r\nstream\r\n<?xpacket begin=\"﻿\" id=\"W5M0MpCehiHzreSzNTczkc9d\"?><x:xmpmeta xmlns:x=\"adobe:ns:meta/\" x:xmptk=\"3.1-701\">\n<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">\n<rdf:Description rdf:about=\"\"  xmlns:pdf=\"http://ns.adobe.com/pdf/1.3/\">\n<pdf:Producer>Microsoft® Word עבור Microsoft 365</pdf:Producer></rdf:Description>\n<rdf:Description rdf:about=\"\"  xmlns:dc=\"http://purl.org/dc/elements/1.1/\">\n<dc:creator><rdf:Seq><rdf:li>Oneill Panker</rdf:li></rdf:Seq></dc:creator></rdf:Description>\n<rdf:Description rdf:about=\"\"  xmlns:xmp=\"http://ns.adobe.com/xap/1.0/\">\n<xmp:CreatorTool>Microsoft® Word עבור Microsoft 365</xmp:CreatorTool><xmp:CreateDate>2022-06-07T02:24:42+03:00</xmp:CreateDate><xmp:ModifyDate>2022-06-07T02:24:42+03:00</xmp:ModifyDate></rdf:Description>\n<rdf:Description rdf:about=\"\"  xmlns:xmpMM=\"http://ns.adobe.com/xap/1.0/mm/\">\n<xmpMM:DocumentID>uuid:BD03977F-238F-4D51-A163-F411F534526C</xmpMM:DocumentID><xmpMM:InstanceID>uuid:BD03977F-238F-4D51-A163-F411F534526C</xmpMM:InstanceID></rdf:Description>\n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n</rdf:RDF></x:xmpmeta><?xpacket end=\"w\"?>\r\nendstream\r\nendobj\r\n22 0 obj\r\n<</DisplayDocTitle true>>\r\nendobj\r\n23 0 obj\r\n<</Type/XRef/Size 23/W[ 1 4 2] /Root 1 0 R/Info 9 0 R/ID[<7F9703BD8F23514DA163F411F534526C><7F9703BD8F23514DA163F411F534526C>] /Filter/FlateDecode/Length 90>>\r\nstream\r\nx�c`\0����� �Z�\�)\�7`�9B�S,o���\ng``kg�P,�B�A(&U\�\�\�V\�1y\\`\�8~���Z05\�5\0$9\r\nendstream\r\nendobj\r\nxref\r\n0 24\r\n0000000010 65535 f\r\n0000000017 00000 n\r\n0000000166 00000 n\r\n0000000222 00000 n\r\n0000000492 00000 n\r\n0000000854 00000 n\r\n0000001022 00000 n\r\n0000001261 00000 n\r\n0000001314 00000 n\r\n0000001367 00000 n\r\n0000000011 65535 f\r\n0000000012 65535 f\r\n0000000013 65535 f\r\n0000000014 65535 f\r\n0000000015 65535 f\r\n0000000016 65535 f\r\n0000000017 65535 f\r\n0000000018 65535 f\r\n0000000000 65535 f\r\n0000002058 00000 n\r\n0000002299 00000 n\r\n0000033917 00000 n\r\n0000037099 00000 n\r\n0000037144 00000 n\r\ntrailer\r\n<</Size 24/Root 1 0 R/Info 9 0 R/ID[<7F9703BD8F23514DA163F411F534526C><7F9703BD8F23514DA163F411F534526C>] >>\r\nstartxref\r\n37433\r\n%%EOF\r\nxref\r\n0 0\r\ntrailer\r\n<</Size 24/Root 1 0 R/Info 9 0 R/ID[<7F9703BD8F23514DA163F411F534526C><7F9703BD8F23514DA163F411F534526C>] /Prev 37433/XRefStm 37144>>\r\nstartxref\r\n38069\r\n%%EOF');
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
  `balance` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_customer`
--

LOCK TABLES `user_customer` WRITE;
/*!40000 ALTER TABLE `user_customer` DISABLE KEYS */;
INSERT INTO `user_customer` VALUES (1,0),(8,0),(11,0),(12,10),(13,0),(14,0),(15,0),(16,150.2),(17,135),(20,0),(21,0),(22,0),(23,0),(24,0),(25,0),(31,0),(32,0),(34,0),(35,0),(36,0),(37,0),(38,0),(39,0),(40,0),(41,0),(42,0),(43,0),(44,0),(49,0),(55,0),(56,0);
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
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_store_worker`
--

LOCK TABLES `user_store_worker` WRITE;
/*!40000 ALTER TABLE `user_store_worker` DISABLE KEYS */;
INSERT INTO `user_store_worker` VALUES (2,'Karmiel'),(6,'Karmiel'),(9,'Tel Aviv'),(10,'Tel Aviv'),(18,'Eilat'),(19,'Eilat'),(26,'Kiryat Bialik'),(27,'Tiberias'),(28,'Jerusalem'),(29,'Tel Aviv'),(45,'Tiberias'),(46,'Jerusalem'),(47,'Eilat'),(48,'Tel Aviv'),(50,'Haifa'),(51,'Haifa'),(52,'Jerusalem'),(53,'Tiberias'),(54,'Kiryat Bialik'),(58,'Karmiel'),(59,'Karmiel'),(60,'General');
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
  `CreditCard` varchar(45) DEFAULT NULL,
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
INSERT INTO `users` VALUES (1,'Oneill','Panker','111111111111','052-222','oneill@zli','1','Customer','CONFIRMED',0),(2,'Topaz','Eldori','222222222222','053-333','topaz@zli','1','StoreManager','CONFIRMED',0),(3,'Koral','Biton','333333333333','054-444','koral@zli','1','NetworkManager','CONFIRMED',0),(4,'Adir','Miller','444444444444','055-555','adir@zli','1','CustomerServiceWorker','CONFIRMED',0),(5,'Shahar','Hasson','555555555555','056-666','shahar@zli','1','StoreWorkerWithPermmision','CONFIRMED',0),(6,'Naor','Zion','666666666666','057-777','naor@zli','1','StoreWorker','CONFIRMED',0),(7,'Naruto','Uzumaki','777777777777','058-888','naruto@zli','1','MarketingEmployee','CONFIRMED',0),(8,'Dorin','Beery','222222222222','059-999','dorin@zli','1','Customer','CONFIRMED',0),(9,'Shlomi','Koriat','999999999999','050-000','shlomi@zli','1','StoreManager','CONFIRMED',0),(10,'Yuval','Semo','000000000000','051-111','yuval@zli','1','StoreWorkerWithPermission','CONFIRMED',0),(11,'Udi','Kagan','111111111111','052-111','udi@zli','1','Customer','FREEZED',0),(12,'Assi','Cohen','121212121212','053-222','assi@zli','1','Customer','CONFIRMED',0),(13,'Eran','Zarahovich','131313131313','054-333','eran@zli','1','Customer','CONFIRMED',0),(14,'Hen','Mizrahi','141414141414','055-444','hen@zli','1','Customer','CONFIRMED',0),(15,'Guy','Hochman','151515151515','056-555','guy@zli','1','Customer','CONFIRMED',0),(16,'Shalom','Assayag','161616161616','057-666','shalom@zli','1','Customer','CONFIRMED',0),(17,'Dani','Sender','457093548641','054-578','dani@zli','1','Customer','CONFIRMED',0),(18,'Shani','Shilon','187764890851','050-854','shani@zli','1','StoreManager','CONFIRMED',0),(19,'Shoam','Choen','484124842458','052-123','Shoan@zli','1','StoreWorkerWithPermission','CONFIRMED',0),(20,'Liron','Tal','','054-121','Liron@zli','1','Customer','NOT_CONFIRMED',0),(21,'Lital','Sabach','','053-878','Lital@zli','1','Customer','NOT_CONFIRMED',0),(22,'Danit','Danit',NULL,'056-958','Danit@zli','1','Customer','NOT_CONFIRMED',0),(23,'Avi','Avi',NULL,'052-784','Avi@zli','1','Customer','NOT_CONFIRMED',0),(24,'Sarit','Bonit',NULL,'051-714','Sarit@zli','1','Customer','NOT_CONFIRMED',0),(25,'Orit','Or',NULL,'056-999','Orit@zli','1','Customer','NOT_CONFIRMED',0),(26,'Uri','Dev','21200545861','054-895','Uri@zli','1','StoreManager','CONFIRMED',0),(27,'Shimon','Lev','20261854785','012-781','Shimon@zli','1','StoreManager','CONFIRMED',0),(28,'Rinat','Shir','94214575423','054-771','Rinat@zli','1','StoreManager','CONFIRMED',0),(29,'Daniel','Buri','58487104574','053-333','Daniel@zli','1','StoreWorkerWithPermission','CONFIRMED',0),(30,'Liran','Liran','85489841521','058-785','Liran@zli','1','MarketingEmployee','CONFIRMED',0),(31,'Ronen','Ron','','054-892','Ronen@zli','1','Customer','NOT_CONFIRMED',0),(32,'Refael','Refaelov','','052-789','Rafael@zli','1','Customer','NOT_CONFIRMED',0),(33,'David','David','54842047487','051--812','David@zli','1','ServiceSpecialist','CONFIRMED',0),(34,'Dina','Dina','25812018542','051-888','Dina@zli','1','Customer','CONFIRMED',0),(35,'Shimi','Shimi','54841578848','054-789','Shimi@zli','1','Customer','CONFIRMED',0),(36,'Gila','Gila','41655145454','052-569','Gila@zli','1','Customer','CONFIRMED',0),(37,'Dotan','Dotan','','054-520','Dotan@zli','1','Customer','NOT_CONFIRMED',0),(38,'Izhak','Lir',NULL,'053-547','Izhak@zli','1','Customer','NOT_CONFIRMED',0),(39,'Tzipi','Bres',NULL,'058-789','Tzipi@zli','1','Customer','NOT_CONFIRMED',0),(40,'Dor','Dor',NULL,'054-333','Dor@zli','1','Customer','NOT_CONFIRMED',0),(41,'Dana','Dana',NULL,'050-888','Dana@zli','1','Customer','NOT_CONFIRMED',0),(42,'Solomon','Solom',NULL,'055-747','Solomon@zli','1','Customer','NOT_CONFIRMED',0),(43,'Vered','Veri',NULL,'052-965','Vered@zli','1','Customer','NOT_CONFIRMED',0),(44,'Gal','Gad',NULL,'087-415','Gal@zli','1','Customer','NOT_CONFIRMED',0),(45,'Golan','Tel','54185485484','058-789','Golan@zli','1','StoreWorker','CONFIRMED',0),(46,'Shira','Shtinbuch','54185412158','052-770','Shira@zli','1','StoreWorker','CONFIRMED',0),(47,'Anatoly','Krilanko','98598785447','056-963','Anatoly@zli','1','StoreWorker','CONFIRMED',0),(48,'Nisim','Nisim','41147798524','054-711','Nisim@zli','1','StoreWorker','CONFIRMED',0),(49,'Avihai','Avihai',NULL,'055-699','Avihai@zli','1','Customer','NOT_CONFIRMED',0),(50,'Kfir','Sharoni','47825933641','055-488','Kfir@zli','1','StoreWorker','CONFIRMED',0),(51,'Tal','Tal','84102181185','057-965','Tal@zli','1','StoreWorker','CONFIRMED',0),(52,'Shimrit','Shimrit','54874052184','056-698','Shimrit@zli','1','StoreWorker','CONFIRMED',0),(53,'Lori','Lori','58695874151','058-888','Lori@zli','1','StoreWorker','CONFIRMED',0),(54,'Gidi','Gidi','84544444445','055-883','Gidi@zli','1','StoreWorker','CONFIRMED',0),(55,'Shuni','Shuni',NULL,'025-937','Shuni@zli','1','Customer','NOT_CONFIRMED',0),(56,'Or','Or','','054-854','Or@zli','1','Customer','NOT_CONFIRMED',0),(57,'Lian','Lian','52874185715','059-871','Lian@zli','1','MarketingEmployee','CONFIRMED',0),(58,'Beni','Ben','43056732583','04-1111','beni@zli','1','StoreWorker','CONFIRMED',0),(59,'Vadim','Katz','37128931273','04-2222','vadim@zli','1','StoreWorker','CONFIRMED',0),(60,'Tom','Cruise','14672961927','04-3333','tom@zli','1','StoreManager','CONFIRMED',0),(61,'dilan','Hank','12412443214141','05-4444','dilan@zli','1','Delivery','CONFIRMED',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

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
  `CreditCard` varchar(45) DEFAULT NULL,
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
INSERT INTO `users_system` VALUES (1,'Oneill','Panker','111111111111','052-222','oneill@zli','1','Customer','CONFIRMED',NULL,0),(2,'Topaz','Eldori','222222222222','053-333','topaz@zli','1','StoreManager','CONFIRMED','Karmiel',NULL),(3,'Koral','Biton','333333333333','054-444','koral@zli','1','NetworkManager','CONFIRMED',NULL,NULL),(4,'Adir','Miller','444444444444','055-555','adir@zli','1','CustomerServiceWorker','CONFIRMED',NULL,NULL),(5,'Shahar','Hasson','555555555555','056-666','shahar@zli','1','StoreWorkerWithPermmision','CONFIRMED','Karmiel',NULL),(6,'Naor','Zion','666666666666','057-777','naor@zli','1','StoreWorker','CONFIRMED','Karmiel',NULL),(7,'Naruto','Uzumaki','777777777777','058-888','naruto@zli','1','MarketingEmployee','CONFIRMED',NULL,NULL),(8,'Dorin','Beery','222222222222','059-999','dorin@zli','1','Customer','CONFIRMED',NULL,0),(9,'Shlomi','Koriat','999999999999','050-000','shlomi@zli','1','StoreManager','CONFIRMED','Tel Aviv',NULL),(10,'Yuval','Semo','000000000000','051-111','yuval@zli','1','StoreWorkerWithPermission','CONFIRMED','Tel Aviv',NULL),(11,'Udi','Kagan','111111111111','052-111','udi@zli','1','Customer','FREEZED',NULL,0),(12,'Assi','Cohen','121212121212','053-222','assi@zli','1','Customer','CONFIRMED',NULL,10),(13,'Eran','Zarahovich','131313131313','054-333','eran@zli','1','Customer','CONFIRMED',NULL,0),(14,'Hen','Mizrahi','141414141414','055-444','hen@zli','1','Customer','CONFIRMED',NULL,0),(15,'Guy','Hochman','151515151515','056-555','guy@zli','1','Customer','CONFIRMED',NULL,0),(16,'Shalom','Assayag','161616161616','057-666','shalom@zli','1','Customer','CONFIRMED',NULL,0),(17,'Dani','Sender','457093548641','054-578','dani@zli','1','Customer','CONFIRMED',NULL,0),(18,'Shani','Shilon','187764890851','050-854','shani@zli','1','StoreManager','CONFIRMED','Eilat',NULL),(19,'Shoam','Choen','484124842458','052-123','Shoan@zli','1','StoreWorkerWithPermission','CONFIRMED','Eilat',NULL),(20,'Liron','Tal','','054-121','Liron@zli','1','Customer','NOT_CONFIRMED','Haifa',0),(21,'Lital','Sabach','','053-878','Lital@zli','1','Customer','NOT_CONFIRMED','Eilat',0),(22,'Danit','Danit',NULL,'056-958','Danit@zli','1','Customer','NOT_CONFIRMED','Tel Aviv',0),(23,'Avi','Avi',NULL,'052-784','Avi@zli','1','Customer','NOT_CONFIRMED','Karmiel',0),(24,'Sarit','Bonit',NULL,'051-714','Sarit@zli','1','Customer','NOT_CONFIRMED','Jerusalem',0),(25,'Orit','Or',NULL,'056-999','Orit@zli','1','Customer','NOT_CONFIRMED','Tiberias',0),(26,'Uri','Dev','21200545861','054-895','Uri@zli','1','StoreManager','CONFIRMED','Kiryat Bialik',0),(27,'Shimon','Lev','20261854785','012-781','Shimon@zli','1','StoreManager','CONFIRMED','Tiberias',0),(28,'Rinat','Shir','94214575423','054-771','Rinat@zli','1','StoreManager','CONFIRMED','Jerusalem',0),(29,'Daniel','Buri','58487104574','053-333','Daniel@zli','1','StoreWorkerWithPermission','CONFIRMED','Tel Aviv',0),(30,'Liran','Liran','85489841521','058-785','Liran@zli','1','MarketingEmployee','CONFIRMED',NULL,0),(31,'Ronen','Ron','','054-892','Ronen@zli','1','Customer','NOT_CONFIRMED','Tiberias',0),(32,'Refael','Refaelov','','052-789','Rafael@zli','1','Customer','NOT_CONFIRMED','Kiryat Bialik',0),(33,'David','David','54842047487','051--812','David@zli','1','ServiceSpecialist','CONFIRMED',NULL,0),(34,'Dina','Dina','25812018542','051-888','Dina@zli','1','Customer','CONFIRMED','Haifa',0),(35,'Shimi','Shimi','54841578848','054-789','Shimi@zli','1','Customer','CONFIRMED','Haifa',0),(36,'Gila','Gila','41655145454','052-569','Gila@zli','1','Customer','CONFIRMED','Haifa',0),(37,'Dotan','Dotan','','054-520','Dotan@zli','1','Customer','NOT_CONFIRMED','Haifa',0),(38,'Izhak','Lir',NULL,'053-547','Izhak@zli','1','Customer','NOT_CONFIRMED',NULL,0),(39,'Tzipi','Bres',NULL,'058-789','Tzipi@zli','1','Customer','NOT_CONFIRMED','Karmiel',0),(40,'Dor','Dor',NULL,'054-333','Dor@zli','1','Customer','NOT_CONFIRMED','Karmiel',0),(41,'Dana','Dana',NULL,'050-888','Dana@zli','1','Customer','NOT_CONFIRMED','Karmiel',0),(42,'Solomon','Solom',NULL,'055-747','Solomon@zli','1','Customer','NOT_CONFIRMED','Karmiel',0),(43,'Vered','Veri',NULL,'052-965','Vered@zli','1','Customer','NOT_CONFIRMED','Karmiel',0),(44,'Gal','Gad',NULL,'087-415','Gal@zli','1','Customer','NOT_CONFIRMED','Karmiel',0),(45,'Golan','Tel','54185485484','058-789','Golan@zli','1','StoreWorker','CONFIRMED','Tiberias',0),(46,'Shira','Shtinbuch','54185412158','052-770','Shira@zli','1','StoreWorker','CONFIRMED','Jerusalem',0),(47,'Anatoly','Krilanko','98598785447','056-963','Anatoly@zli','1','StoreWorker','CONFIRMED','Eilat',0),(48,'Nisim','Nisim','41147798524','054-711','Nisim@zli','1','StoreWorker','CONFIRMED','Tel Aviv',0),(49,'Avihai','Avihai',NULL,'055-699','Avihai@zli','1','Customer','NOT_CONFIRMED','Jerusalem',0),(50,'Kfir','Sharoni','47825933641','055-488','Kfir@zli','1','StoreWorker','CONFIRMED','Haifa',0),(51,'Tal','Tal','84102181185','057-965','Tal@zli','1','StoreWorker','CONFIRMED','Haifa',0),(52,'Shimrit','Shimrit','54874052184','056-698','Shimrit@zli','1','StoreWorker','CONFIRMED','Jerusalem',0),(53,'Lori','Lori','58695874151','058-888','Lori@zli','1','StoreWorker','CONFIRMED','Tiberias',0),(54,'Gidi','Gidi','84544444445','055-883','Gidi@zli','1','StoreWorker','CONFIRMED','Kiryat Bialik',0),(55,'Shuni','Shuni',NULL,'025-937','Shuni@zli','1','Customer','NOT_CONFIRMED','Tiberias',0),(56,'Or','Or','','054-854','Or@zli','1','Customer','NOT_CONFIRMED','Kiryat Bialik',0),(57,'Lian','Lian','52874185715','059-871','Lian@zli','1','MarketingEmployee','CONFIRMED',NULL,0),(58,'Beni','Ben','43056732583','04-1111','beni@zli','1','StoreWorker','CONFIRMED','Karmiel',0),(59,'Vadim','Katz','37128931273','04-2222','vadim@zli','1','StoreWorker','CONFIRMED','Karmiel',0),(60,'Tom','Cruise','14672961927','04-3333','tom@zli','1','StoreManager','CONFIRMED','General',0),(61,'dilan','Hank','12412443214141','05-4444','dilan@zli','1','Delivery','CONFIRMED',NULL,0);
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

-- Dump completed on 2022-06-07  4:49:44
