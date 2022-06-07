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
INSERT INTO `survey_reports` VALUES (2,'colorsurvey2022.pdf','2022-06-07',_binary '%PDF-1.7\r\n%µµµµ\r\n1 0 obj\r\n<</Type/Catalog/Pages 2 0 R/Lang(he-IL) /StructTreeRoot 10 0 R/MarkInfo<</Marked true>>/Metadata 21 0 R/ViewerPreferences 22 0 R>>\r\nendobj\r\n2 0 obj\r\n<</Type/Pages/Count 1/Kids[ 3 0 R] >>\r\nendobj\r\n3 0 obj\r\n<</Type/Page/Parent 2 0 R/Resources<</Font<</F1 5 0 R>>/ExtGState<</GS7 7 0 R/GS8 8 0 R>>/ProcSet[/PDF/Text/ImageB/ImageC/ImageI] >>/MediaBox[ 0 0 595.32 841.92] /Contents 4 0 R/Group<</Type/Group/S/Transparency/CS/DeviceRGB>>/Tabs/S/StructParents 0>>\r\nendobj\r\n4 0 obj\r\n<</Filter/FlateDecode/Length 288>>\r\nstream\r\nxúµíOK\√0á\ÔÅ|á\ﬂ1ö\Êoì\¬\ÿa\›,ä;h+\ƒ\√¿∫\À\ÏfßÇ\ﬂﬁ¥\Ï\‡\ƒ\ﬁj \·\rºº\œCÚCZ6-f≥t]\\-!”õMªk\⁄\‰æ\‚Û9\ÀoîH!˚ÇWêpπF#X%rçÆ°\‰\·-%ãöíÙRA)!-\ÍJ˙n	ì+°=ºÀÖ\r®_c_Yylèq4∂\√-ún%%è¨\ÿÛƒ≤B}M\…*ŒΩ•d+ùp\Óß«Ä?Q#‘±é+\ÕP}p√∫Oûh\÷ú}Ò\ƒ0\‹Ò¿ö√æØáÆwÙ•\Êû…∏ı\‰∂N\∆	z\Ãvzû	Vd~åásV\Î8èè˙ß¯Xó	o|ˇøΩ\ \ÈSí!≥1ﬁêí\·x\Ê\ ∆®lv\Õqzõ7¶\◊W|\–æ\r\nendstream\r\nendobj\r\n5 0 obj\r\n<</Type/Font/Subtype/TrueType/Name/F1/BaseFont/BCDEEE+Calibri/Encoding/WinAnsiEncoding/FontDescriptor 6 0 R/FirstChar 32/LastChar 121/Widths 19 0 R>>\r\nendobj\r\n6 0 obj\r\n<</Type/FontDescriptor/FontName/BCDEEE+Calibri/Flags 32/ItalicAngle 0/Ascent 750/Descent -250/CapHeight 750/AvgWidth 521/MaxWidth 1743/FontWeight 400/XHeight 250/StemV 52/FontBBox[ -503 -250 1240 750] /FontFile2 20 0 R>>\r\nendobj\r\n7 0 obj\r\n<</Type/ExtGState/BM/Normal/ca 1>>\r\nendobj\r\n8 0 obj\r\n<</Type/ExtGState/BM/Normal/CA 1>>\r\nendobj\r\n9 0 obj\r\n<</Author(Oneill Panker) /Creator(˛ˇ\0M\0i\0c\0r\0o\0s\0o\0f\0t\0Æ\0 \0W\0o\0r\0d\0 \‚\—\’\Ë\0 \0M\0i\0c\0r\0o\0s\0o\0f\0t\0 \03\06\05) /CreationDate(D:20220607022442+03\'00\') /ModDate(D:20220607022442+03\'00\') /Producer(˛ˇ\0M\0i\0c\0r\0o\0s\0o\0f\0t\0Æ\0 \0W\0o\0r\0d\0 \‚\—\’\Ë\0 \0M\0i\0c\0r\0o\0s\0o\0f\0t\0 \03\06\05) >>\r\nendobj\r\n17 0 obj\r\n<</Type/ObjStm/N 8/First 53/Filter/FlateDecode/Length 309>>\r\nstream\r\nxúçR\—j\¬0}¸á˚∑©Và0¶≤!ñ\“\n{ê=\ƒzWãm\"1˝˚›ªv\ÿ1ˆ\–ÙûìsNrì®PSà®1® \‰\‘dJA1\¬¯âô+òú@Na6\√D\‰§òaÇ\€€ô0ÛÆ\…˝≤¢\Z\◊;>\0ìF¢ôœáÉX\‘CK\–Y6oj2˛°SöH•\r˛}@g\Ó	∑é(µ\÷cj+\⁄\Ë≥t\'±âv)≥“®0í6ncz≥1]˝ön†∫\Ëg\Î	cñ\Êp[ñ\Ó\Ì3\ =æí>êkkÒ¸\‘o¶*\reG-;\‚\ŸpÇˆ•5væ¸\‘\\|£w\ÎN{kO˜C\Êr$Ú≤Ièù;\€\√/G{xQ\Í\ =\"´\ ı¥\Ì:,+úÆqUç£Æ◊∏©/;y$ì_ß\Îö.ª˛πÅ\·\‡\ ;¥\r\nendstream\r\nendobj\r\n19 0 obj\r\n[ 226 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 507 0 507 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 533 0 0 0 631 0 0 0 0 0 0 0 0 0 0 543 459 0 0 0 0 0 0 0 0 0 0 0 0 0 479 0 0 525 498 0 0 0 0 0 0 230 0 0 527 525 0 349 391 335 525 452 0 0 453] \r\nendobj\r\n20 0 obj\r\n<</Filter/FlateDecode/Length 31526/Length1 100304>>\r\nstream\r\nxú\Ï}	@SW\⁄ˆ9˜&í\0	Ñ5@ÇP£†¢j%≤π+Q@QêETT‹ójKµjKkó©\›7ª/\⁄6D≠h7\€\È6\›ªO∑ig¶ù\÷n\”m¥\ ˜ú˚\Ê\‡ÚUß\Ì\◊~˝\ÁˇÚ¬ì\Á9\ÔY\Óπ\Ôπ\Á\‹ãïq∆ò:V[\\PT±°\Ó\n\„≥z2∫¥∏`|·à≥˝ùåœòŒò˙\‰§Ú¨Å\◊>\\∑ã1~z\’\÷œØk]ÚÙ:cÛ∂à\Íó/u\Ói}}0c7lgLS\Î\Ï˘gº£\Ê0∂ı\·\Ó\Ÿ-´ö^\÷M˝ä±€ö+547\÷5|?aï\„ô1ﬁêf8\¬\ÔJ:Är\ =õ\Á/]\ŸsW≤\Âèõ≥Ωea}]˚i+è0ˆ]+öOô_∑≤5”îˆ\r\Í1s\Œo\\Zw’∫≠\Àüs\ g/®õ\ﬂx\›¡of2\ﬁ\ÔI\∆˙/i]∏dióùm\ƒ˝\Ì[7∂F\œ\Óë¿ÿö}∏\‹\'L\ƒ\"d\Ë˚=ﬁπˇûôë√øa	&\ÏæO\÷<#¯µ1+&:x∏-\ÏS\√√ò\¬\»\–/Ña¸Q\„\÷Cn\r˚T\ÈK\ÿ.<ˆ>\Ïfa\≈ËßÄ≥\ÿ&∆¢Üh\◊\ÂL’π˘EL\œ˙+ı\Ÿ2ÖX}ÅmTòÅ)ëzEQt™¢˚êev\Ìc=O\◊f\0õP\Ót2¨A\⁄34á\–\Îît\'\„]¢N›≠èw\ l∫à£≥\·œ≥_l∫\"V˜\À{ˇ∏\È\Õl⁄Ø=Êø≥ê\◊\ÿˆ\ﬂb\\]\ÂÒ\„™w±Qø\≈u˛\'¶~çß\ÔóÙ{ö\Õˇ1øÆë\›p\\ª∂\„Àß2\›:\÷ÚK\ÊÚSLy\Ô\«\ÁrÚ˘\È\ÓdM?\Á\Z\ÍcG\«RúáIlÃèˆ©bI\«]s3ª˛\'_\Ô0K=i\›˙øÆ\Í+l˙OΩ\÷/1\› V{\‹ı±ö\ﬂÚzˇåø Æ¸%˝tπ¨˙gµtÙ:\ S\«_SMee?\÷G˜Ò~\Â\Óì?üˇ\ŒÙ\—?Ø/\ÊªÂß∂UØe=NZw˝\…\Î~Ãî\Ì¨®[ˇïçV:O~\∆ÛG\œ7\Âo¨\Ìµ2ˇñµ(˝YˇÄπîè°70GwªçGu–Ç¥†\Ì«î´πÒ§uµ\Ï¿ˇ\Ê\\˛SL\Ã\Œ˚Ω\Á M∑Ö\Õ¨~\ fù≤]\‡≈ßlW\»fk\„ùu\Íº9DiÄˇ7ÁáºLkß¯ÿ∏Sµ˚µ˚*◊≤\\ç\'≥\·øFª†-høØ\È˛yˆq*SCéˇ\ŸS-˙ÒüEuÛ\Ÿ\'˙î´\ŸT•çï¸Zs	Z–Ç¥†-hAZ–Ç¥†˝ˇg?\Á\ÁLa\‚g\Õ\ﬂvFAZ–Ç¥†-hAZ–Ç¥†-hA˚)\∆\—o¿-hAZ–Ç¥†-hAZ–Ç¥†-høù)≠,*¿iø\Ÿ5öÿà\0O˙˘Ωªnˇµ\Á¥†-hAZ–Ç¥†-hAZ–Ç¥†-hAZ–Ç¥†-hA\ZY\◊\ﬁ\ﬂ{A\⁄\Ôlj\0IÙ/IÒxî†T”±\‡ƒúP6®p÷ÉıgY!\≈&∞rV\«\ZYke\À\Ÿ*∂ï˘ì≥ìÛíÛùa\ŒVgõs]\⁄3]⁄øÖ^\ŒczMFØÙZ\»ü¥\Ô˙◊ø_Ωø\Î˘Æ˜∫æ\√7`^™6ªâ]ıj\…\'õ\ƒ◊Åå6~∞\·É\r\Ôû¯7∞˙sW}∫\—˜\‰wÆéU/g\’lO\‰)|-?óü\œ/\‚WÒ]|\·üjmæ<Ò\ﬂ\◊BY	¸k\\\n;µÒcÆrÚI\‡:\Ígˇf$Fø\›\À8\∆\◊˝/Éà{8\≈4¥yä{<ŒªP˚\ƒ]\„Û\Ë}\ﬂˇv6ø∑©ø\ÍhˇáüsOı\∆\rKó,^‘∫p¡¸ñys\Á4\œnjlò5sF\ÕÙi\’UïﬁäÚ\…e•ì&N?n\Ïò—£Jäã\nFzÚGú6|\ÿ–º‹ú!É≥2˚ıÌïû\÷\”\’\√o≥Z\"\√M\∆0Chà^ß*úı-vï\‘:}\Èµ>]∫kÙ\Ë~¢Ï™É£\ÓG≠\œ	W\…Òm|\ŒZ≠ôÛ¯ñ¥l:°•áZz∫[rãs8ﬁØØ≥\ÿ\ÂÙ=[\‰rvÚ\Í≤J\Ë\ÕEÆ*ßÔÄ¶\'hZóÆ\¬QHMEgq|së\”\«kù≈æí\Â\Õ\Ì≈µEØ\√d,t6\Z˚ıeF§	\ \◊\À\’\⁄¡{ç\‡öPz\ÌPò!\\\\÷ß¶\◊5¯J\À*ããÏ©©Uöèjc˘B\n}°\⁄X\Œ9b\Œ\Ï<gG\ﬂ}\Ì\ÁwZÿ¨Z∑π¡\’P7Ω“ß÷°SªZ\‹ﬁæ\…gu˚zªä|ΩWè[nÙıu˚\‹.6nr˜∏Oüfq9€øaòº\Î¿ß\«{\Íûê4\À7LHqã\›aBΩ\‘s\√q©©b.\Áuz\ÿ,|meïTv≤Yv?Ûdπ´|J≠®\Ÿ\'kbº¢¶M\÷twØu•ä•*Æ\r|/oé˜µ\Õrˆ\Îã\Ëk\ﬂi¯FΩ”ß¶\◊Œ™o\\\◊\ÿ\Ó**¢∏UT˙<Eû∫¿ΩwÙ\œB˚∫Z\‹\ƒÜ≤J_ñ´\’gsP8úb\r\ÊîWj]\›|∂B´≠ÙÚeây9ã\€kãhÇb,WY\Âñ\›ı^\« ß}G6éÑ*1_l!%Ω∏Ω≤°\…Á®µ7\‡˘lrV\⁄S}û*ÑØ\ U\ŸX%V\…eÒı~óK’Æ®ı¬Ωù\–Z6wöfpV*vµJ¨\Œ|∏\nÜ£¬Ç\Â“äbEÜ;+πù\…f∏J†ÖP«çÉÇöV8ZT©¢k\·h{jU*\Ÿ)¶d\ÃIü\Ê33ñé\Ó9\—uN:5j-&\‘\€Y\‹Xt\ÃèTò``¥üß\"b∏0z\ƒréñUj\Zv.|\nÜ\—\\b\„ù>V\Í¨t5∫™\\xÜ<•ï\‚\ﬁD¨µıW\Ó\ZWV]©≠v\‡)©8ÆDıπTÚ±TTÀÇRàg∞\ƒmóÀ™ïGi\Â\Ó\‚\Ë™\«\»jóòW{{CS\”ƒ£l\Ô\‡ö\–ûW\Âõ\‰Ær˘fπ]©bû˝˙vò9µ¢∂{µ«ù´§\Œ\Â¥8K\⁄\Î:ª\⁄fµwx<\Ì≠≈µ\ÕC±/\⁄]c\Z\⁄]\Âï\√\Ì\⁄\‰\'WÆµØ◊éb\„¯∏ä•∞Ç?ß¨\√\√\œ)ØÆ\‹ca\ÃyNE•_\·JamAUGO\‘U\Óq2\Ê—ºä\nß(8EAå4É\÷ﬁæ\«\√XõV´\”Zπæì3\Õgê>\Œ\Í;ÚY\ËB\È⁄Ö<»ï\Í;uT„ë≠u\»\◊F≠{ZPc5{^$L´$\Î`\"¿£\ﬁcÑy\ÃJ∏Çê\nóûΩh\∆\Ÿ3\Áˆå9YswÚ∂é0è}è6\“\‰@\À6¥æ∂nf.ö3ÆG7\Ó=z\ﬁ\Í\ fÜÒµO¥(Üß0æ\œ\ﬁ\'\≈\ŒÒ¸≠©jnØ≠ßã≈≥äo\Ó\„ÆÃß∏F`\∆!fü\—\’X\‡3π\nÑ?_¯Û\…\"¸°xÚy,\«bãC∑Ω÷ÖÉ;¶í\Ÿ9\Ì5U\È\Ï\ÏÍ™®L}\÷~†*{i:P]\Ès\„\Â¶Oãv£j\·\Âk´ØÛ`\ﬁJ\—74mL}ˆ•M\∆¯\¬0BX`¥(\—˙à˝ÜNıx\÷\Í\\öÑGG[ïØ\ -.Z9ßJ€Ø\Ì\Z\ÍIß1ı\È\‚BYU\ÌQÆÅ\⁄·ÉΩnL\€$(sc\Âï‰±£àãUQêBÕòyΩUıµNzF ±ó\Èea¥ìßgæ.ΩQÉ\—®d\‚∂\‘4S∏\—ñâÒ-¥)Sú9˙¥–™*öºV\⁄hÄk[|&\Ã(˝òP: :®\Z#\ÊÇ\ÔMò™h˙∞¶¨ìMv≠\ƒ\—)&≠çäj_x⁄ò:º›®ø	WÆ\Ïlá†)0∆£\‰\rwnF\‹q$tv\›\ÊZïzå\·\Ïo?Ò¸1˚lTV\’~¢\√7\Õ›ØØ\·Do∏\Êno7Ñˇxäó!ºõ5ßíV/\ﬁ\n`Ò¿iœõ≥Xº*]c;îânçπ\∆\Ìc]xÉ(iHtTlüTgCïhÖ)ójg\ŸIÒc\Zâ◊¥6xªeò,Ò@â≥\›7˚¯bsw±D\0\…`Z&\Â∏q\÷\‚Yôk˜µ\‡…îMƒä8€ù\◊Pó¯\–:è®\≈\"uo<˛x\Íƒ¶i´wV\Œ\¬√éKj\€K\⁄EäZ_[\‡Jæ\Ó\„Üƒæ\‡xx0ê∏_[©≥∂\ Yã‘îóU¶¶⁄±¡\Œ&‰©Æ:Ò*(•˚)≠\÷Rï∫vÒà3d*Uv_(^LMuçÆTºA|\‚¢\Ëã9\Í€Ü\Ÿ\€\€]\Ì>mﬂñ†1ÜO«∂#ﬂ≠nW]£H°õD›®ı-¡tµ\Ëà\—\Ï\≈.\Ï\ÂF∏µX\"p8˙fâè˙vë†\◊‘∫	k{Tª3ØGp\r\ﬁ∫Ù˙)µxUâ7íS[\Í:;J\¬Q™\¬@\‘0,M4§- f3\ﬂ\›Qöv‘£}/tScÉ6*f6π\“W*õh˚IàEnüóãJqÛ|ru•<ßTQ=\·ı‡©≤ã\ﬁNüRQX≠ˇ\—\’.å∫¡£ΩC˚´˚m#\ﬂC\”\Ìà\ÈI˝x9®#Àï\'ï\«Y.s(O¯mñ´º…º\ \‡\◊¿Ø¯U+\‡˝\‡ó¡/Å_?~¸\0¯~\Êe:\Â-6®\0\‘n\’\0\‹\ÏÙlF\‚ÃÑ˛úŸîGX\–\0,∂\0z¥}u7cDŒú\ \Ÿ;\√\‚˘X,\Ëz)\÷IqñmRú)\≈R¨ïbçßK±ZäUR¨îbÖÀ•X&\≈R)ñH±HäV)J±@ä˘R¥H1OäπRÃë¢Yä\ŸR4I\—(EÉıRÃí¢NäZ)fJ1Cä\Z)¶K1Mäj)™§®îb™S§JQ!Eπì•(ì¢TäIRLîbÇ\„•\'\≈X)\∆H1ZäQRîHQ,EëÖRH1R\nè˘Råê\‚4)ÜK1Lä°R\‰Ië+EéC§,\≈ )≤•(\≈\0)˙Kë%E¶˝§\Ë+Ö[ä>RÙñ¢óR§Kë&EO)\\RÙê\"U\nß)R§Hñ\"I\nªâR$H/Eú±R\ƒHaì\"Zä()¨RX§àî\"Bäp)\ÃRò§0J&ÖAäP)B§\–K°ìBïBëÇK¡ÇwIqDä\√R¸ \≈!)JÒ/)æó\‚;)æï\‚)æñ\‚üR|%≈óR|!\≈\ÁR|&\≈)>ï\‚)˛!\≈\«R|$\≈ﬂ•¯õï\‚C)>ê\‚/Rº/\≈{Rº+\≈;Rº-≈ü•xKä7•xCä◊•xMäW•xEä˝Rº,\≈KRº(\≈R</\≈sR<+\≈3R<-\≈SR¸Iä\'•xBä«•xLäG•¯£èHÒ∞˚§xHä•x@ä˚•∏OäΩRÏë¢Sä\›R\‹+\≈.)vJ±C\nøR¯§∏Gäª•∏Kä\ÌRlì\‚N)\Óê\‚v)nì\‚V)në\‚f)ní\‚F)nêb´\◊Kqù\◊JqçWKqïWJqÖóKqôóJ±EäK§¯ÉKqëJqÅõ•8_äÛ§hó\‚\\)Œëbì•\ÿ ÖL{∏L{∏L{∏L{∏L{∏L{∏L{∏L{∏L{∏L{∏L{∏L{∏L{∏L{∏L{∏L{∏L{∏L{¯b)d˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e˛\√e\⁄\√e\⁄\√e\⁄\√e∂\√e∂\√e∂\√e∂\√e∂\√e∂\√e∂\√e∂\√e∂\√w—©ú\ÌO\·@\Œ\ÏOâ≠£\“Y˛î°†6*ùItÜ?\≈ZK•5Dß≠&Z\ÂO	Z\ÈO.≠ ZN¥å\ÍñRi	\—br.Ú\'ÄZâ-†&ÛâZà\Ê˘ìäAsâ\Ê5\Õ&jÚ\'Å\Z©\‘@TO4ã®é®ñh&\—\ÍWC•\ÈD”à™â™à*â¶M!ÚUïM&*#*%öD4ëh\—x¢qDc˝ˆ1†1D£˝ˆ±†QD%~˚8P±\ﬂ>TDTHT@u#©üá(ü˙ç :çh8µF4î∫\Á\Â\Â\r!\ZLÉ\r\" ¶Q\r \ÍOÉeeRø~D}â\‹D}àzı\" †°”â\“hÃûD.¢4t*ëì˙9àRàíâíà\ÏDâ˛ƒâ†¢x\‚$PQ,9càl\‰å&ä\"≤RùÖ(íúD\·Df™3â¬®\Œ@J\‚O(\È˝	e ëJNÖJúàiƒªàéhM¯a*˝@tà\Ë \’˝ãJ\ﬂ}GÙ-\—7˛¯\n\–\◊˛¯r\–?©Ù—óD_P\›\ÁT˙å\Ë\0—ßT˜	\—?\»˘1\—GD\'˙5˘+ï>§\“T˙\—˚D\ÔQ›ªD\ÔêÛm¢?ΩEÙ&5yÉJØΩÊèõ\nz\’7Ù\n\—~ræLÙ—ãD/PìÁâû#\Á≥D\œ=MÙ5˘—ì\‰|Ç\Ëq¢«à%˙#µ|ÑJ\Ì#zà\Í$zÄú˜\›G¥óhQ\'µ\‹M•{âv\Ì$\⁄\·è\Õ˘˝±\”@D>¢{à\Ó&∫ãh;\—6¢;˝±8Ø˘4\ \ÌD∑Q›≠D∑\›Lt—çD7m%∫ûªéFπñ\Ë\Z™ªö\Ë*¢+âÆ†óS\È2¢Kâ∂P\›%4\ à.¶∫ãà.$∫Äh3\—˘\‘Ú<*µùKt\—&¢ç˛ò:\–\Ã,\–\ŸD\Î˝1M†uDg˘cº†6c~¶?f\Ë¢µ\‘}\rı;ùhµ?¶¥ä∫Ø$ZA¥úh\—R¢%4ÙbÍæà®\’SZHÉ-†ñÛâZà\Ê\Õ%öC˝öâf\”Ãö®{#Qµ¨\'öETGTK4ìh\›t\r\Õl:\—4∫\Èj\Z∫ä.TI4ï¶;Ö.\‰•Q*à â&ï˘mP©\ﬂ&Æ0\…oè˜Døm=hÇ\ﬂ\÷4ûöå#\Z\Î∑!/\‡c®4öh9K¸∂3@\≈~\€&Pë\ﬂv&®\–ok¯£J@#â<D˘D#¸QxøÛ”®4\‹o≠\r#\ZÍ∑äG#è(\◊o\ Ò[+AC¸\÷j\–`™Dî\Ì∑ˆ\r§ñ¸Vqc˝˝V±7≥à2©{?∫B_\"7\r÷á®7\r÷ã(É(ù(\ÕoQ\ÍI\‰¢1{–ò©4òìFq•Pød¢$\";Q\"QÇ\ﬂRä˜[fÄ\‚¸ñô†X¢\"Q4Qu∞R9#â\"à¬â\Ã\‘\“D-ç\‰#2ÖÖPK=µ‘ëS%Rà8ÛtE\Œrâ¨wélp¸\0}8¸æ\Ô\·˚¯¯¯\Z˛_°\ÓKîø\0>>¿ˇ)	\Í˛ÅÚ\«¿G¿ﬂÅøE\Ãv¸5¢\ŸÒ!\‡}¯\ﬁøººçÚü¡ooo\0Øá\œsº>¿Ò*¯ï\«˛t\«\À¿K\–/Üª/\0\œœ°˛Y¯û	ü\Ôx\Z˙)\Ë?A?>\◊ÒD¯\«\„\·Õé\«\¬g;E\ﬂ?bºGÄáO\◊>|><<`^\‰∏ﬂº\ÿqüyâcØy©c\–	\ÏÜˇ^`\Ív¢n|~†˜òV9\Ó6≠v\‹eZ\„\ÿnZ\Î\ÿf:\√q\'pp;pp+pã©ü\„fM¿ç\Ësx´iû\„z\ËÎ†ØÆÅæ\Zc]Ö±Æ\ƒXW¿w9pp)∞∏¯˙]åÒ.2Nt\\hú\‰∏¿8€±\Ÿxã\„|\„mé\rjö\„l5◊±û\Á:\÷y€ºgmkÛû\È]\Î=c\€ZØi-7≠µØ∑ˆÙµ\€÷æµ\÷b\\\„]\Ì=}\€j\Ô*\Ô\n\Ô\ m+º{ïç¨I\Ÿ\‡\Ó]æmôW∑Ã∂l\È2ı\Îe|\€2^¥å˜_\∆∂Ã≤ÃπL5/ı.ˆ.Ÿ∂\ÿ\Àó.n[\Ï[¨\Ê[¸\ﬁbÖ-\Ê\∆ŒÆ};\€SJ¿û5ã\√-%ãºΩ≠\€z4\Õ˜\Œ\≈\Á\‰\Œˆ6oõ\Ìm\ m6nk\÷\Á\ŒÚ\÷\Â\÷zg\Ê\÷xgl´ÒNœ≠ˆN\€V\Ì≠ ≠ÙNE˚)π^\Ô∂\noynôwÚ∂2\Ô§‹âﬁâO\»\Áømúwl\Óh\Ôòm£Ω£rKº≈∏yñdIr&©1ÅâIò	≥ÛÇ˛vè˝=˚v≥˚\Ï˚\ÏjTd¢#Q\Èô¿\'%Ö	g&\\ò†F\∆?Øx\‚{˜-âå{>\Ó›∏\œ\„t—û∏ﬁô%,\÷\ÎåUcƒΩ\≈N®(\—8øàx¿`\Ì^±ÆÙí\»\„àQä?è\·ô ùú3n©¥\Ÿ\…c%\Í\\¸‚ùûq~´pè\Î4∞\…\„|Ü\“i>~é/≠\\|z ™}!\Á¯ò∑zZe\ÁTiøì‡≥â_*\—\ 6of\…\„|\…\Âï~u\Î\÷‰Ç™qæ6°=Mw	\Õ–§\ =c…≤%\ÓJ\œi\Ã˙ûı´\ZÛê\Âyã\…##ª\"O$&\·àP\ƒGWÑ\ÍâêS\ÓW\ƒGW∏\Z\Î	áG\‹_Üπ¥¢$\“\‰0)\ﬁ|\”$ì\‚1\ÂñxL˝˙ó¸∑˚\‹!ÓìÆ\Ï^:3ñ,uk\ﬂ(UÒe¢\Ë^ÒΩd)\ \‚kôVf\ÓS\Z5\Õ\\[*ùKO\›\Îˇu\„ø˜˛Ûç~ìgdór6kP\÷ÎÄ≥Ä6\‡L\‡`-∞8X\r¨V+Ä\Â¿2`)∞X¥Å¿|†ò\Ã\Ê\0\Õ¿l†	h\ZÄz`P\‘3Å@\r0òTU@%0òxÅ\n†òî•¿$`\"0å\∆cÄ\—¿(†(äÄB†\0	xÄ|`p\Z0ÚÄ\\ \Ÿ¿@`\0\–\»2Å~@_¿\rÙzΩÄ Hz.†ê\n8ê$IÄHÄx àb\0\rDV¿D@8`LÄ@(\Ë\›\».|™ÄpÄ±?~\0Å\ﬂ\ﬂ\ﬂ\ﬂ\0_ˇæææ\0>>\0üü\0ˇ\0>>˛¸\r¯+!\‡}\‡=\‡]\‡\‡m\‡\œ¿[¿õ¿¿\Î¿k¿´¿+¿~\‡e\‡%\‡E\‡\‡y\‡9\‡Y\‡\‡i\‡)\‡O¿ì¿¿\„¿c¿£¿ÅGÄáÅ}¿C¿É¿¿˝¿}¿^`\–	\Ï\Óv;ÅÄ\Ë\0|¿=¿\›¿]¿v`p\'pp;pp+pp3pp#p∞∏∏∏∏∏\Z∏\n∏∏∏∏∏\ÿ\\¸∏∏∏∏\0\ÿúú¥\Á\Á\0õÄç¿\÷0≤çcˇs\Ïé˝œ±ˇ9ˆ?\«˛\Á\ÿˇ˚ücˇs\Ïé˝œ±ˇ9ˆ?\«˛\Á\ÿˇ˚ücˇs\Ïæ¿¿qpúg\0\«¿qpúg\0\«¿qpúg\0\«¿qpúg\0\«¿qpúg\0\«¿qpúg\0\«¿qpúg\0\«¿qpúg\0\«˛\Á\ÿˇ˚üc\Ôs\Ï}éΩœ±˜9ˆ>\«\ﬁ\Á\ÿ˚{üc\Ôs\Ï˝\ﬂ˚˛∑™\ﬂ{ˇ\·∆ñ,9&1?S¸e¢\–\Î;r\…qè§î\ÕeKXæ6≤\Õ\Ïˆ{ã\ÕbÎ°Æd[Ÿ≠\Ï\Êc≥?±\◊~Úﬂº˘	vdï~>3´ªYãf¨\Î`◊Å#∑ù˙àc<ó†≠sıtY∫>;¡˜ŸëK∫,G:C¢òQ\ÎÆº\Ô?˘·ÆÉx\Â¢\‹5DîïM–ëZè/CØ;rœë\€NàA´f\”\ÿtV\√jYÓøÅ5≥9à\Ã<\÷\¬\Ê≥Zi\Íf\„≥	•ôhÖ\„E\”G[-d≠\⁄\ﬂ\rZ ñ±\Â¯jÖ^(â∫EZy[ÅØïl[\ÕNgk\ÿ\⁄¿\Á\nÕ≥5´µÚJ\‡v&V\Ê,∂NSí…≥ûù\Õ6`\’6±sÿπß,ù€≠\⁄\Ÿy\Ï|¨Û\Ï¬ì\Í\Õ«ï.\¬\◊\≈\Ïx∂∞K\Ÿe\Ï\n<W≥kN^Æ˘Øb◊±\ÎÒÃà∫K\·π^S¢ˆ~ˆ8\€\≈\Óf˜∞{µX\÷#jó&-Ü≠à¡\Z\‹\·˙cfLÒ[\—≠3p\Ô\‚\ﬁ\⁄w∫˛u\«ÙXà£hπ-iZ1\ \⁄\"q\ÓÅÙ\—;¢“•\⁄˝ıïSye<Æ9&2Wk%°NÙûL_∆Æ\≈ºü\"™B\›M\ÍzMÎøÆª\ÌV≠|ªô›Çµ∏MSí\…s+Ùm\Ïv\Ï\Ì;\Ÿ6∂_Gı±ä¯nvó∂r>\÷¡¸l€âïºó\ÌfùöˇTu?\Ê\ﬂ˚ª={\ÿ^vûê\Ÿ>ú4è\‡KzÄÔ°Ä˜Q\ÕG\ÂG\ÿQ≠®Ù8{\'\‘S\Ïiˆ{û=Ü\“s\⁄\Áì(Ω¿^b/≥\◊x8‘ã\Ïc|f/\Ë?dl$~¸ﬂã8_\√fú\ÍoZ˛\œMü\»b\ÿ÷Æ\ÔªVt}ØéfMº	\‰v¨\“Nv>~b_p¥%w0£\Ó/\Ã\∆vv}´N˜:¸¶æ˘»ç]ü3=N\Õ%\ÍK8\ÂT\ Ú\ÿ6ë]\Ó\€‡ÆºüÖ#KâeC˘Æ]1EEÜ~°\"Qò9åÅq^\Ëâ\‘)\·ªÛ]ªálV≠c:yøù˘°õëù\Á~\ÁsYá\ﬂ9ïóuÄgΩ˝˛;\Ô[æ|Œöóï˝˛˛˜Ù∑{lâ\·ª[\–u∞kw\À`5dsãj\Õ˝=a-˘%tsâ\œw\'>\Á~.\À˝ú√∏˚®\‚\÷T´[Ñ\Zjqı\»Tg§\…\Œ8B<(\›\’#B\—|ÉÜ\‰åP≥¶(™MzF(¢\Ã’ó~®V\'Q\Œp\ÂO\…÷ß$F\⁄\¬CÙJR|Tø\·iñÚii\√3ìC\’\–Uo\ÌïS\–c\\Kqè7C≠\…1±\…QCTrlL≤5Ù[˙àÉ_\È#\ÍZmQCÜM\œ\Ô©^a4(∫êêŒî¯Ñ>\√R\«Lâå∂\ËL\—k¨!4\ j\ÓU4˝∆ò$1FRLçux„¨Æ\ÎùYüÇ\»kQﬂëƒÜπ;ª>\⁄a\·¿_\Ïà\‘¯\”\·\Z∂√¨ÒG;L\‡Ò\„\\ã\ÁY,ï•Ûæ˛\Ër\›}º\Ã˙ÛÃé∞)XÜ˝x\÷˚\⁄\À\⁄Ú\Í£~Gj|\'\œ\⁄—í\Zù\ﬁ\…˚\Ólâ.¨\Î\‰}v¥\Î/˛¯Ω=˚G\›àzö-\"\‰òÜ\ƒb*¢cKQDElufEo∞yfû>Êåß/úP~Ÿãg\ÊŒ≠.±Ù™\Œ`2Dú¥h“î\Õ\r9É\Î/ö6aIŸ†\»Pcà∫\€a\ÎùaØ∏˘\Àko¯\·û\È1\Œ>ˆà\Ë\ƒ([RtXFVFÒ∆á◊ú˛¿ô#”≥\“C¨)\‚oIO\Î˙L∑R\Ôd˘\Ïm/OrRRd|<¢oâàSº\—,\"ﬂ©X=\·\Ï°\Ó\Ãd\‘f®ëÅ\ÿFbàmd ∂ëÅ\ÿFä\ﬂ\Õ\Z\ƒ!V∆ù=z\‰eç∏è±[åº∑?Ø‹Ü\»ud!\¬Z|≠Ùò\◊Pî˜\◊\‘<J\nn\Ôbå{[\ƒ ˙ê;ZÙy\∆N\ﬁ{gK^yñ\…ﬂí•Ö\\\‹*üıˇ\Â!9\÷\‘\È\⁄*§\"\‹1V[Ñ\Óòu\—\ÈV\Í\ÊPs\Óåı\’Û\Ó\\û_º˙é\∆\·ß>≤\ﬂj’Öô\√¯’¶\ÿ(c\‘\–\È≥\Z\\ˆ\ÈMSj\Ó8p\—\ÿuç≈âF›å\Ë\‰hCzf˙\ƒˆÆ\ŸwvQr2_’£g¥\›j0Xí¢éD\'¶\'˜à7\◊lˇb\ÀU}uâÆﬁâ=\ƒJl\Ô:\‚∆π1úΩ™≠Ñ•vD\Î%ºˇ∏¨,cf||b ‘âÅPkÆÒ∑\"‘âÅP\'äEJ\È9¿l6äu4äu4äu4äu4∆£âqØbEñµœìÄ\Î9§\Ãû? 3\ƒ—´\Ã\·çÚ\ÍΩ,óg\Õ\Œ\ÁY˚›¥\0≠Ÿñne\Õ;-+;€ö=†\r†#˛\Ë Ù\‡\”\·auÒU®\Ó≤v;âs\'Eâ\„\Ÿáçê1!nÉÕëó\ZmPéd´¶òd[LäÕ§\≈\r6gBº3:¥ØΩ\ŸŸøg|_°\ÁMâéÙÑ˘ëˆhs\"VNØ5t≥m	5Ü™:\Ï.Wv˚o\Ì\””ú\ÿ\À˛\√Tı÷î>	¶∞\Ë\‰\∆Xù	k0Ñ±¥UH±dZsSéàcé\≈\Œ\«Áà∏\Êà@\Êt*Ÿª{{P\Ïùo\Îe\r¨è5∞>\÷¿˙X\Îcø»ëîi\È\‰Ü{[=\‹\„â;≠ìõv•ñ\≈\¬.N˘öyÚî(£ïóá\«ﬂü\È]wµ†c™\ËyoK†´à∂v¥\Á\Ï5Su\Â‘Å±q)™8vBS‘∏\Ë\ÿX>(=#=≠\ƒ—£3Ö\ÿz¶$¶\⁄L∫1˝FT[ùöêêj3Ùé\„\—F&é[21\√U0=\œ9®_/\€\“√ë\√E•	˘\Ÿ\ﬂ^T_\‡@\‡\r:]ò\≈\Ãöö\Ô:¸Fw¿\Ô\Œp\Ë\’\‹)GŒû4\‘\·>q¿ëz&´\∆œâ\r92>uX)v¡®ÆjΩ>ïç\·ΩD¸˜∞ë]Ìå¥Ò#\·≥\∆fçµ∞é\ÏT˙z\‹=\—6>~†\«\ \'Ù\ÿs†\Ÿ/˙\⁄\≈\„o∑X\ƒ∫\ÿ\≈\“\Ÿ˜*\ƒ\ÿa\«ü`ÒΩëV>ûô3\Ô\„,\«T∫\«du\Êè\…\Ã\«[≈üÒÖ ±\ÊXcáwrÛÆëv}\ÔÚXúCz\Ì \√r∞\Ê\Âeeπ\›5ññb¡∫\◊RTPAj9ô‚øº∂Xqê•\Ôn\—F\Ì-Ü\››¢ç´˚[ÙtÆ°∑;0Ù—£M\'_\ Ùˆ\Œ9\…&D≠/\\qC\Õ»ÖSá≈ôp\ƒ\"≤KçÕ≠)\Ï9pÚúÕì≥áÕπ∏\¬=u\¬\Ëù¢ÜòBMYE5CáîJX>w¡\‹Úl>o\⁄ıcù=\‚\”xçáˆ\Ë\ÂJ\…)\ÕŒô8l@ˆàäEì\ Œú\“/2¡m≤\∆GG·Ωì\‰JN\Ó_ê6d\‚ÅŸßï/ˇ\ÔK\◊AıC]:\Î\…z±Ebùw\≈\«eò\”\√;\Ó	ãKw\¬oJ7v*\√<ñûñ\‹\'\„{≥9*π1™Y\ﬂ,vàx?\‡`\Á	YÒ˚\ﬂG ¢Ú-oìŸê=\Ãﬂ∑\ÌOù\ƒ\€@\Ïç\ÿ\ÿ\Ìúëë\Z*N¢ÙÙ!9\\\Àptq°.5U}3Tµ§ß¶¶\Ÿ\Í\‘#û\…:ctœ§dWÑb\‡st\Ê¯åîW|î…†ÆU\Ó·≥á\«&F\Ë\‘sÿÅO\¬\ÃUë£>fäU9í≥°\ÌàQú\ÓÛªæP\◊\Î˙#ôò\'\Ó\◊\œ2:ï£9ˆPVr~≤í‹£ìG\·kRæw\Ë?@–∑ì\Óù#íéö\⁄Ñ˝\"\·∏79ˆPK≤U\Î`l±6\rPæo*\⁄˚[\–\·Ñ\\\„∏w\⁄\…r\ruΩ!Òøh˚\Ë(Æ3\›⁄´´ª∫´™´ªz\ﬂwµ\‘-µ∫%µ÷ñh\Ìà\Õ\\ ±€Ä¡\‡-\ÿNb0\¬q\‚\Ã\ƒ\Ôe\‚INl&±sí\„8vå«ôì\‰e!y·ºô\ƒqO2\…;\È\›[U-	{ú9gßn-˜V\ﬂ˚ˇ~ˇñ≤›£µÚ\Èù˜>/ßá{\ZúPö¥!\⁄2Z\Ëÿπ¥<=tgw\”pS\‹H\‰sûÄ3\‡;øv\Ë\‡ü\È\·\›g(`v\n:_\ÿ[ª\È¯\ËÜ\„c\’ﬁêó\‹\ÌßøF¯3\‚CöU_X\ƒÚ¿èvbñ\√\ÿ?0çπ> 7A°Q©∏¥¨\…˛Ål\Z#]\»\‡ëb\ÕKº\0\”ô£≥`5™\Ê¬Øu?¸˝Oˇ\’[P\·\·\Ô\ﬁW<_Òê|‰±ç\ﬁRé˘}\„¡VO\0ˇr¿\”~ˇ∑˜>∫©˛⁄ªU\„O@l\‡¸L`~\Â\»J8ªSN\0ç•`a¸¢_D\Á\’hîrºoãΩO©sT›ìãP\0˘\À8Y1\Íº*ÉnF\«˚≤qåºGis\÷\‹E\Ê\Õ[A\" ,8”†\r\‘ı\ﬂ\¬5`f\⁄@\‡öû^án¢ª\·:p˛$˙êI¢®M´\Î°yó\Ÿ\Ï\‡t\”o–ºS<=˝4\ÔÄ+ìg\ﬁ\≈\ﬂ%2HôTºaØó≥√™B$≤∫Ç>˙O	~*ı∞¨¢~#ûÃß*7k´Ñ\ÎSπ\»tΩ\nG\Ë?ee@=qFÆ\ﬂX	«úñ+7ó\÷\’\“G9\\≥ºlp∏(ZıJÏâøK\“¡Uım\Ìzx}∂ˆ\÷\√KS\ÎcˇV¢∫VÚÛB`\…Ú°ƒÅ\◊\ÌxÏµΩãv¨®µ\ËÒGEØÛD<ç[¶n\Ÿp|SN≤¢^@H\⁄„õæ\’\‚°\ÕN\—\–˜\Ë˜ˆxÛ±´\œ\'˙4‰±ü=dG≤™µ1\ÀÑ6éÉEôN\n\‹\0i»ê\œ\«	x˜¥L®\–*®R\Z®™Y±üÒ‹¥\œ\÷Yváﬂ¢C\'†˝k\0\\oP\ÍZ®ki¡g0Ûr^≤\Œ\‡Lπµ\"¸€ô4¶ı¡¨^	H07V!pOt\ÃsØ\ÈA\Ë[A˜\Ïr\∆ú1@z¿éÇ\‚%∏Ö\›Kæ\ÿBOL”Ñ\ÁâIVrú˝6á\ﬂLc”è°8àº|˙Iå6˚üôé\⁄e_y\0∏a	Õ∞é@¬Ω\—∂ïp\√\Ôºv?\À\‚C\·˚ÆûΩ˚j\–]∞\ÎY\Ï˚\ﬁ2ß¡Ñ∫b#àGäÄcE$ÜtAä|±`\r@Wx¡Qè8P\Ó4∑1t\ÂNëõoPß8∏{F\Ê6í1∞ïõo\‘\ZÛ\¬VEi\ÃSÅD±ı¿K{ˆº∞∑±\Ìﬁóˆ\‹qv_\·t†ÁÆï+\Ô\Ó\r˘{Aªß/Äy˝”ë\≈≈á^pˇ\≈\«¸\ﬁgW>.7&_∫\Í¯÷Ü∂\…)®\ÁÄU\€∞Ù Id±¢I¢‘ãò¿\‰A\‹$\ƒ˛ì$\Ÿ\»{\÷1¬ß\Èë]÷îG∆Äpë¨5Úû¨t±∑\Ã\ÍU\Ô\›ƒç£\Á˚n¯ñÏ≠ü?¶8\«¿aã\⁄Qc®\ËØ_U>\ﬂ\÷lMKGˇÆ°ª Å˝\Î≤C´\“\”G\ÊA\—lı\‚ÒûÆ\rINoı\’ˆ\"\⁄zû\Î©˙cLï=f=S\≈\'Ö,,›é6PQr\Ó§õÜ[˛=ˇòM\„Lem–Ö\Õ¸\Ë2\‡Àü(*›úl~#Éû˛¸{≤\÷˜\ÊNk\Ï&N´™(°\Àjì$|û\⁄Jgç∏]´\‚¬ï≠\ŸM•ı;\‡\\˜¿™JOÆØ\ U	∑\Ë\È≥Vˆ¶>”º8\„i¿ê8c2¸GY1\Ìúò•\«ûh«¶\÷\ÏP{Ü7*Ò\ﬂ9ÿØBçI\«Ùsé4¸M\›3\Ôb\◊\0óˆ\"˜´ti\√\Ãg£\Ÿh\÷‰Åï\Íà	D˛\∆ìo˛¿≥àLn\",úÛãï\"&\Ÿ6*¨´òu@\Z%ü†)ùV¯8Øå5\ ˘dÛ≤2\\Ñ„üóE.1ˆ\’\rº0èø©O\Íb\◊\Z6~fYı⁄æOìlã°¢c}cE_≠/\Ÿ12:\“Yñ]ΩØ´lpQïIy\Œ\–L¢i∞:V(∑ówé¨\È,Gc=ª\ \Õ.7o‡≠º\≈ca<!èîhà&ö“ë≤\Íˆı≠Ö\Õ=	^rp¿\‰E\‡8=Nk§⁄ìlN\≈\‚ô\‚\Z®}›Äøö˘ëzEZ∞\”ÛGÄ\0\«ÙºkLØHIM_¯\„+0Ú!\\¡YyÖ#£¨\Ë\‡9V	\ÃY`Nõ9\”Ùe\∆p8}\›Ù\Âí\≈ﬁÜX„øàÆöE}øN\0v\‘%\–P1É\Ÿ˝Ωb;£@ñ™\œ\‚\«D†á$\ÃrV\Á\«]sJ®•§Ñ\Œ\¬§kæ˛iôØ\ÊÖ¶y\Ê\Ó\‡\Ô6m˚ª\rküö®\ÏkwÇò7‘æ6ü_S\ËDø\›\„iÙÛªû\ÿ\\W=>u\0õ,ôë\ÎO≠/É\≈[Wb•{`\Ó@\ŸK`\ÓA§WÒ6`ù?¥˘ı6+p\0@\ÏbÛåK$ò,§±X≈óVi≈ã~aˆπ]\È\0≈≥µπfûÖ\»\0ö∆∂z[\‘,≥≥\ƒÙ(Ç˚}!Ç3∞\nå%\ËÒå\Ì5ò &Er	úfôkO\„´ıF\Ë9ª$0w\ÏTbÉNU∂l¿a1≤N¯}Ñ∞Å\Záa}\„v\ <Nï&ü˛c˛«ó¡\Ã\·ºg\Õ\Œùcèπ©ã\’\‚\Ï\‘å¥9\0…ßœ∞4\rz#VÜ∏Ü˝L;\‰FL§ùöû\Ât?6†˙¸f∫}Kg†ÇsH@cÆ\—k˛∫¢1\ﬂSÚ~Æ\Õ◊ñn\√\rå-ÀÇ\ÿ3C\—,@≥<M≥\Á—´ãq\ \"0«Ä\‘këm=ålçZkP[%Æ?è\È\n¡ˆ\ní\Â≥X\√w≤(íE≥\ŸTk\Ÿyòë∑Çh0Hx\ﬁIı4]b˚	$≠˘¨£Jbmt˚ö\—RÛBr\Õh>≠¶t2\0Ù5£ÆÇ\—`C≥∂Wd¯æ†ÚBIFÇ®DÄw¶<\Ô»©∂\Èí\ﬂkOk\Ó\⁄5£jûmTıâ(Q\Âr\‘\\ö≥:ßY\\\Ì°∞<≠\Í#	∫Üx\Ôv9}¶Ü#K;w.≠h\ﬁıèõ˜IUãÛMÎª´XbW\€\–\∆\Ï˙O-è~˘\”≈±6\ﬂ-KZ\'ö\Ï,KQ,;\“\“\È\ÿ\ÿ\⁄7\Ÿ\È\».…πÄ.\“Ò\Œ\·qÜ<b˘ä˝\À/\ÿ*Z\À⁄ä\0£u\0£ß»≠H\…#/)˘Z\ZPÉ+ë\…\√Yf\nÚã<*ˇ\"˙¸ÙÃØ!\Zi-\ró\÷\“<i\r≠¥ÜR˙<¶/\Ë\≈@á!s¶2Xbl\Ô0œõ˙\…>®\—\0\Zäﬂ¶\Â7µ4O^I§\ÈK\Ìp\‰\Ÿ\ﬁcÇc\œ\»\ `®Ù\0\…xqÛ)\rúÇ9ïç\Œ˜rjÒßh¡mÅYÛ\Œ\'W\›˙\Ëp<≥\·\»⁄Å˚\n¥\≈U3ÛïE˜[V\÷:¨Ÿ°\÷@S°#\Ê–±ê\ÂY›ù˝C˝˜ù⁄∞\Î\≈˚;\€a\⁄ì:F˙z˚≤\·\∆\r˚\n\≈C\„M\Ê≤EUÄ∫£Ä∫O	H\"Y\‰Ö∫eÈöñöâ\Z\\Ù\Íâ~@2Qî\√<L9§n9${π\" Ñ˝\‡l1˘\Â$Sıga™>KúW\…Nh	e\Â⁄†¥™0êﬁÅ@˘´˜è\ÿwÙ-%w˙R¥\«˛\Œ:”§	31\Ô∏˚5É´\»¡ˆ%\»¸2©fô!˜&\0ÇD˘´Ún\Â\—Ù%9\⁄c≤ø##&ﬁÑq∏\…Õº#ÉwAÛô^\·˛\—d\…\n\Õ®≠7&˘1k¨F¡Ç∆üå9ÆüˆvL.-åußY†Æqß\r5C\€\œ\Ï®o\‹˛•[∑L≠´¯\n~˜ùM´õÉÜ\≈Ωw\r•¨N+mròç\"\«\Zv±y\œ˘=ªæq∞Ω∏Û+\≈C\«R}\„µ\–f=9Û¥ô‹éXë•êˆ/¥\ÿl6\—rgàñäD4^Ö˜\"\"K˝zæC\·Oç6ê\Z\œ+∑\Ï-7\r•5°\Õ:≥öÜ\—\‰ ùCg\–p{Ç¨SÅ\‡\0xbƒØ\0O@ü˛5Ö\'\‹-	4nF@\—(ãFuhîF\Àp4Å°^M¿ºöÄy5j\‡\’&\ÔÖ¿{\”zToÅ˘XîW\‰1ãÙ≤@ﬁ≤|\”\√\ﬂ\“?	\»\„Ä5˝\\à∞Sdø>åjY∏\“~Xw\Èü\ZT† ®\ËÅAåzˇ\'*_\’\Ô|v\«\ƒ?l´\…\Ô¸˙N\–\÷>\Áj\ﬁ2–Ωπpµl\Ë\⁄RÙ£ˇ∫\Ìˆ∂\Ì?≥¥=†\›\◊}hC>ªˆPœ°ı˘\ÏöC\–r˙˛c@Ω2§	9•\‰\ \0DÅ\ZΩÜ©^\√T_\¬RØ\—8ç\Ôl\÷$$Ií$©\‰Bìê0IH;±\ÍkrÇ\'y.\⁄\„\Í\ÊÚ\‡T#Mã¢¶†o™\Ïu]ûSR/®√¢p\\Åë’ë$:K†EKÕ£RL¯˚X\’=\≈\›hAí∫˝∏˙÷£k\‚\≈\÷B∏\ƒ^î®-óôNÙı/≠\ÿp¸9kıP¡\ﬂTTqœ¢\Ê[jù\Ë\Ôvøt_\'ÃÜ¶õKúG¸éQ˝=\Ê\Ó≤ÊÑµ\Ô˛w¥kã™¶?øle\„\ÿ>Ub∞gã˝†O\Ê\–(ßëtnÛJ#-ß—úÉ§5#àL5#ê∆àP<R`í=Q\Œ\Í\Ô∂ˆ)æI≤ÿÖR\ﬁW!ﬂ©§\“Q/\œı¥´]\ÊM\Ëè\"\ZÖ=ÉQåNgÛÑ≠é\ \\}h•líáß#≠ıyè1ˆ∞é\‚$Ø¿0åŒíÍ´Ω~≤$£¯˝Ä4 \02\Ë\Ó´)\∆8\\ß\◊3&òìX:s{–§\Âie”Ω-ΩΩzOÙíÛ\“\Ôi\ÈwEJ[a\Í\\\\êûW\“ÚË•ÇO\Õ\≈+Yxh`µT<\Ã\¬C©u}}OŸé\“C˜á-Ä˚,L∞G¡˚Z\ÿ,∆¶~Y´ˇΩ∞DX\'L\n∏örˇÃã˜Ho´\Ã:õl\◊R\Ì£¿Æ\ŒOµ´ß™Üè‘¶~)˙\ﬂÀà¿~7\·Z∫˝JÆΩáî\ﬁ.±Òl¢j˚ˇNÆ{≥zÕ°≈ï\√ÌïíûÄπÙd\ÀP]Y1\„äñ¨XZà%˜Üª\ÍV\Z\«qZO1¡ö\ÓtY!açW,+\ƒPSª∏\ƒÊ∞Ñ}¢ìß]~ó9Tâf\„æ`≤y®1∑æªú5[yñìxò˘ìí™t\«rq∞¨q9¢¢In%\'ê£»ü\‘|Jz	GVö∑\"ì\ËØœÑ\‚\ﬁÄ\È-\‘snk\Îx´\»qb\Î8\—\È\ﬂ\€\ÂªrGG\›\Í-Ωø\\2∏nprO\r¶á´_ãn\È~ª£ˇÓä£\Î0pQN1™FÕÄCı,Ç\"0Iu(Ñ4¯g\Œ\√]\ƒˇ+ò¯\‡ï\ƒ@vo\◊æ+≤˙AÉΩ\0ôA~\–?êQ>kKık2¯¥é\·∑ey\Óä\Ï\Ëb\‡GûñMg¿°z*\ÂSµùfàÖê∂\Ÿ;\ÎãBEΩ/\Î\«\‚+Ÿ¢\—YãnU\Ì<R\…\Óì[1\‡/˘\‚i©s¨\‡\›ÀôIùQ∑«ëjK\ƒUoTá\√`:ò\ÎôÚ«≥H≈í€õI≥d´\\}\ﬂÚ¡}\À\À~£32Ñô˚mMóq[hJG´I08ÜäÙ\Ó\\åô¸aq\”=ç√µ.wUG¢\–\„ˆ{o\¬ı\œ[ı\Î€£eÔä∂M,M•ÜÆXCN1\Ïü÷èÆeÙi≤+ë\Êøc[âgëz‰∞¢?à™\–tBÖ¶+*4]Q°Yˆ\nMØV@5\ ⁄åWB]\„[Wd#Ze£ãPqVkæÒ\≈JÜº˙ä˙\⁄\n6\„\Ÿ\÷EW)L@kL\‡\‰/∂‹∞\Î1ÛèC\r€™\„˝âî≠c¨\‡ŸØbvO…•˘->\0µk;ma∑EG2$±\ \‰M7\“˙\' é%Ü\'7•ÒC\‚ß@Ëæ¢D\„\ÈV¸v|rÒJ(u>cõ\—\r~ê\\r9≤∏´µ´°¡\ﬂUŸÖu≠4%Ø\‰∫\Ãpâë˛\’ÛHd,sn\–UªêÆVú*Só3Jöﬁ°æ\È\‚ª0ﬁï[i\ %Ø»πÆàY	%\"˝Ù\Í\‰\'\√CèæÛ\ÊQ\…z3Q*ë50ó⁄∏Öâ\ÍoJEãw∫u-ÅÛ\Ày\„7\'=˙mò˙\0fM˜\”ok:\À[\ÀCºIØÅ0Gn≥`åF\„G°É¢•\r\ÈÈôõ u∆Ü¯KH9ØEÜY\‘Év+\ÌVƒîX%≠W\‡wNı\0|\ZØ˚4^\Ì˚ä\œ\0O ≥˚JNÑO≥å>>#Vt\«§£;ºßπ\0™ØR|8k\»\‘\0ë\—ò\¬J|8\¬17ã\n\‰újj\Á\‚√ßh≥\«jÛTˇÒ˛ëΩ}⁄¢í€ñ\Ó™l\ﬁ\€\‚B\‡TòôYØ˛\Œã7ﬁÄKé\√ı?¨]Yπª£tZú\‡\Ã_ΩÄä\Â®Q\Õ\‡ÑfÄ\'\nw\À}:xå¯PØz\‚E%ç\ZV≠µh∫A\‘ZÛl\’\ƒ\Ã\nµ∞\‰\ƒ\Z\„\—8â\„\‡FS\r\—\0<m	†\·\0\ÍW\Ó˙—∞çq\Ë\Ó\0\Z8?ÛVÅ¨]?(¿\’\€®°\0åE\·\ƒ+\0\ﬂœÇÅÅxw¿\‡\Ï6ÙïRKj•vrTâí\ÍF*:\‡:ôtùE(O*d\04˚5ï¢•9Ùl\È\À\\\‰`mµ¢V`∑\≈pl˙\"at∆Ωﬁ∏\√DLøIê®NÙ\Ÿ<!ë!¶	¸Øò^∏l^Å\∆ˇû`Ù,}\Ì´0ßF\ËLz|ò538\‡pò\ÎNñ\≈~˜§1ùA¡D˚\0.au\œ˝à¨9i\ÍB.\‘#É®çöjLXåAù\–´w¢é:\–68P_∑C/v\Î{â§W\›} ∑\0¢$Ur@≤∏\n\Ï\rù\ÏZ/∞ˆ\0Æ≤c≠ç\∆\–hv6Å(*ÜU≤\–Xı]TU\∆\È0j\√\„\”\ﬂ\÷ÒaØ7haH\≈ﬂßÑ†\ﬂ®È≥º@≤ö\'\Ãz|µ\’n\"qgºû\¬~\"\ZH®\0`\ÃT\ƒ^¡\n§©\0v\ÈQE\Á\“\÷z¯kêêP…ûGo)x∏»î\ﬂ\Ô≤Òß\–\ T!Ö•Rz\◊T|{\Ì\„˙]¯N-Ks¿ìÅª4ó/¿DYF\Ÿ[ã¯#S2ú≤ëëü˙C\ngq0>Óöí\„\€ıµè\À\ ;¥dçñ+\Ì\„¿\‰\ÿG\Â\∆Êúã˘©1¨\‡Úúë\—˙Ú\ﬁ\Z_ºW^¥\‹Ë´éF\Z+º:£\Ÿ\‘0\÷T\Õ;å7DÕôÚÚñ0ˆˇX\÷`¨å$§Úñ≤T{Örïπçf´rãØ\›S”üæóï¸R,éZuZ\Ì°¿9dD°\„»ΩàÆD§=\\\‡\ﬂVÉ\«OJ\€3_`\Á\—&ØfΩUíàJ\')~Rñ∂≥ô/\»\Ï|®IX≠æÙ%´¿¢˜8Ç\ƒQ\Èıçm´ÚN\Î⁄ñ™¡8\Õ9-\'O}*\ﬁg}\Î\ÕD\√\›)\Ï_X#A1Tk∫*=∞π±c\Á@2\ZES§é¿Å\\ê\”\ÀR)vQ(‹ë$sê?:¡ö∑˛à )dü≤˜ê\"\‡Ø7s	Ç+z.\ÿóx\ÃdbRG¸0dOıog¶\ÏªJ˚ˇ\€gKq\Õ˘RÄ\œ$ì¡\"ÿÇ@]8\ÁOï˝\€\ÌÃî\∆\⁄\ÁÒÉyé\ÊÚFíU1º\—eç∞mNq˙à9\—Vm\…Ùzù)ò¨™ıOM\≈zn/v\0õ˙\—^e\√\"F NG¨©L2p¨\Ët;L,Cù\Íÿæ∏,ﬁ±¶F\Ë\Ëµ≈≥J›ßå˝\0˘%\"V$¨\Í>£Á∑í;\’\‹ÒVÆ\‘\‰è¯°@ÙO$ØbA©-â˝@ÄÄ0\‡nnÅ§u;†ı”Ä\÷\"†∂V˝aÅ¯Ò\ÓbÙé\„\‹ˆ\–\Á\»]™?8\«qô\€NÜ>\'ìª>a •{:1∞£k`≤;\Îªcqœ∂\Ó»ßπHS™¨)nÅ\Ì\‚¯\’EìÉ±æ≠ùã&ññ\'z∑v\«;s^w∂≥º¨#\ÎYg+£\Ôc_≥ç\"µ\»E\Z|ï˙\—a@®˙p¡ÇX\ÀO∑\Î+\”>ÇtMÚwTüRßÆl≠óvsëêÇ\Â\'\Â˘]\…\ÍS•\≈(Ωììë¨VUC™K§h´ö¡æhY\”\‰,/ã\€JÙ\'MtVØo,å\‘9?cÙe¬ëÆäxG<\\\Ì\„Ò´ù\€íå\Ë±L_\'\·n\'≈ê\ÿÀ∂UU¶n/Fä92˚≠äî/ªH\ÂÙáä\Ê\ÏP*FÇNÑÉ¡:ıb€Éú\’;i\›9ó\Ÿ¯\„µ\‰\ \”_ê\ÁûÇ|Fç≤Hµ\Ë\n˝!àöHùÅ≥\nú\€íJKsÑB6{Y4$ö\r\‰üªâ&)\“`è{¶ˇ±\ƒnòùˇ:}qõé\–Q&XEˆ\nz¨¢˘î\ \›yt˘YπøúuúGW<[v\‰\◊U®¬™jé:Úddª˛\»wÑ∑LêéB8\ÁˆfGo‹ú-D™ é\»jÅV§Ê®¨åÙGîT\–a)\’0V\ƒ\ \Ì\Ë\Õ35@ÒÆ\Ã1(†»ïp\À-9C\ \«R8IzOº&R\—\\\÷\‹›í\Áófº\’1ßÅOHJ\nß}ôd≤•ß•ø3\ŸVa7pk≥\ZEñ\‰\Õ\\0\Ê\ÿlÒB.÷òî÷®Oñ4Ú∆Ñ\”≤Këf®\rBÄ^\'»ßÅ\ﬂ;§†éÑ|1à:/r\ﬂD\Ï	á\·	q\"˘$≠J\ÈE•(\Ê\¬_˘1,G+X}b\Ï	\Ÿ!D\√≤8A\'üî\È]≥!YR\€Uõ\Z:\‘\‹êIs—Ç\¬Û\Ë	J/y‹∫\Âã\r\€OiZˇpex\ƒ_\ÊåREb8/\Ÿ\r ¯]Ωç\⁄=n˚=$p∏\«\Óˆÿßﬂ≠\ pÑ¡¨Ù+\ÿ~\“t˝E\◊3!’ºy–ïìbƒ§m\“r÷∏A\‘M§V\“hXˇ\…yf\rˆπâUõ\ÁlK7¯\⁄\ÿ~G\»l3íï\„\’\rK´$äwY,û™\Õ∫PÉä.éû5côT∏i∞\Ì£Të•¶ø\ﬂŸùÆ@\Â\“5Xì{käi;º\ÊÄ˛¢N1†ÑŒ£#\Ìå\ŒI\„NdR-ôDiß⁄´\Ÿ\ﬁ\Ÿ\Ájy$\\ò™Ö¢q\ÕG6˘FÛY\\5øâ\Î-A∑;d\’\„/í$√ª-í\€L\·G1¸Sòéwëå“±úq\⁄|QòÅ’°ˇ¡\n¨`W≥V—ßiˇ÷ùΩäm\Î®æ¥C\Áû\◊\ÈÙ∂Û\Ë\·≥\…\œHñÛ\Ë#V/π\'≠7\…\Ï¿w´\ÎY∞e≠ ß~^/˚¨Û©9\’\÷\‚s€øµ≥€ø\Ëb<YfÛp(\—	∏\Á^ß\›-0\ƒ1\ÏAå<vªóCIå3\ZùQ\nì8K`4kòæC?M\Î1\",™üyΩDÆÜ\"ÅDî<=qıÛ`∂ø|ñmíëÇr\r\Ê\Â¸\ÂõÛ\Õ’∏D\\`\‡\–o\—˚-n3-†:k\»\Ì\nYu&\∆˜˘vÜ±\'|æ∏ÉA\Ô(eºÒo≤fñ§\0©ˇö$]É+T8G¥mWfÆ†\'àµ\ \ÎTK,acà±b˘sæ\Ãw3&\À_(\Ÿ\·sf¡+ú˛|ãÖg?j\“S4\Á≤J.ûBJª]Aëf)\ÏqGmcã∫=aâAs∞,lÜ\Âı$	ñk~O\Ãn0\ÿcO‹°\◊;\‚`Œè\‡±œìwÃß™+\⁄\…w™^\Ã(TuîkH’ãô®ZÚ‹ë¨\ÿ}o3õ\Ìe\”[6{¿¬†\”\›pØ2ä?X\"+˙O•≥\È™\ÔÒyd\Ì\Ã\"GT\ﬂX≥÷°‘¨u(5k\“in5êJ\Èπˆ\√5k\“ô[\r∑ó§\”2x˛âk\÷rç{_\‹\ËÖ›µ∞=x~w\Ì\Èp\ﬂ]\À˙w\ƒ\√}w.\Î\ﬂ5\«ƒ≠Ø~ndÒW\Ôêa{Ù\’\√OL\Zo?:<||;hÚ∑A\√`\Ó\Õ\».U˛$lÛπ\Í¯AÚ\Á±˚\œ¸˛º\Î<ö/0uÇÑS©\’|˛<Zä\ZE`¶∏10>\Î—™a¡¢ŒÄ)eàA.ç°\‡†\”2eáπZ0Dsu±\Í2k#\Õ¯ÇB™∫T˜E+=áI=\«\\\œ%ìé`8#j\Ì©\ÌUΩ\’\Õc=ï\ \0<~R\'4\Ôh[qˇ™¥≥∏s\Â\Ô±*ß\'ª\Ã.3C^ª\’\Ôôw\Z\◊-i\ƒ\n)ß?\Êßx∑|(#\⁄c}\Ÿ\rõww|õ]€Æô+¯´±mW∞mW∞µ©\ÿ\⁄nä≠≠Ñ≠\Ìo\¬µJ~vˇ¡Øé%*∑>ªˇﬁØé\'N⁄õ6/\Ìπ≠\’coTZ/fñ5l∑~b˚˝˝+üòh©\ﬂÚ¯J≠\ÿ\Ó\0\ÿ>\Ê^Øz≤\€\€œ•C\‡\…¡ø\"aê\“òd˛˘≤\’|n™WT˚7R–±ˆ<+ÉÆT\Ó0ì™%º	í\—U|\Ã˘\…\0 s&+G\„zéE\Ì=#U¸˙[õn\Ì\ÕICÍ•ñëù-∑<pKπ£∏k\‰\nñ\’qÜÖ ∂¨_\“\ÓÒ\«:¡-:R8\‰\0Òq[\Ì¯\r@\Ÿª¥øZøZ?ìZOj∫i=©©TOj˙õ\ÍIÒK˘_õ\ÿ˙ø\Â\\˝\‰\◊&a˚\\ºsC}q|Q0÷π°∂ò˝\‡õèıµ\ﬁˇÉGæ˘Ÿæ\¬˝o\€ı\≈uÒ˙-\«WÉ6—∞\Â8¿o5¿/IdêFd¢Ñ\ﬂ˙9\Ÿ<Tí\Õ\ÏπjâJmÑÇô;•î®g¥˝∞õf∂¿\»\Í(ñπ\”Z≠zF\€\Ì˙∞X\÷~ºXJ`í:£\Ó˙ßLV#E\Í\Õ\∆.Æ¨∂\ÿ*•™ó∑$\n\Ëyå\–	5ã\◊\ÁÜ\ÓL8[w≠zΩd:ßô°8ódı:l\∆\'F˙¡Ürª+\ËÑu*Fã`\‰Ωky\ÔX>;∂ÛˇÇŸä%3\Ô\‚Ä&\–4ólX\r¿”èY\Î∫*låºø\0\ +∏Æ\ \¬F2Ú˛MP$>≈Å≤\’«∂¥o[\ﬁ´é\›~€ëëƒ≥Œö•5-K\“fWnim\À“îÄô˜æ˛\ÿ\0\ \Ó~|h\ﬂkè\rÙ<Ú˙\√;øtk™Yû\ZmEì<53Éå$≥`\÷˛3k1\»\0¸\Ê0“£Æ¬ä•¡*|X˙4¢w\¬_]\»-ÉF˚2\‹Dæ¨÷ár)\‡\Àe$|>˚ù†õÒ•ı\√F£eˇ∑\Ó\Ÿwvw]\”˛ó\‹\⁄\”eªzV\ﬁ\’H,\Ÿ\Ÿ;|Wo\0;8ıÁØØ˙\Í’ß?wı\ƒ⁄°Ø]˝˚\ÿ\Î˙kª÷ñ¨ò3≤∑ƒô´\Ê8Û\ﬁB]^eŒî¬ú\À s\∆OQ\À\Á3\ÁB-ÛÇ:≤hJc\—e\nã\∆ã.øÅEo¶r\ƒO`<†Œ©+èø¨\‹\\+∏kóf\Î\◊u•ıKì\≈ıC€ö\◊|z4%uﬁøı\"ñÜjß~ãâÊΩí\≈k≥Q˝\Í£wmH&˚\ÎÉ¡\–<´\…&ò¯Hÿô[ΩßΩy\ﬂc\œmˇ	cvïl†íà$QÉ≤ü\‡XPÙ)ΩT¿˝\ÓáıØ´@Áï≥H∞ú\Ó<X¸07nág/bê\›’≠4ø∂\’\⁄w\‡ˆÉ_\€~\0\Ì\€\Á@ˆ£Ø±=¸πáe\"÷≠\ÈÙ¢d∆ï/àÇ\œFîoµ¡=¢Ø(w©\\ô«ÖÇUøv\”\ﬁ¸e5\Œ\œmÆ\'\ÁUŒ∏Jl\Z˘Ø\ÿp)1èK	¸\’Ù÷ì˜<≥1Y)üºw/hOö\\\…\∆˛\ [ö$o\ÎxW›ä¶∏ù¡ûz\Ô\‘˙\·Ø^˝“±´J˚ııüﬂΩ¢÷±\‰—ó\‰#?∏∑>ºhÕéêíT∏ıuc¢Mz—Ñçz\—\\¥\rRæÄJêÚíÚO	\\§õekï\‡˘ob\√\Ãw ë\r\Ág~]0\0\Í}rváu \Z\«kCFaˆ¸û˘‹øoŒõ›π%Y•¿ñ∂b§\Œ\ﬁp\À\Ì\r*Û?8q´˛x\ÊèufØïìxì5≤+Ãø˜≥\'v(\Ãt^v˚9˘[å&H@ˇ/Ä;Ω\ÿ±M\ \Z\ﬁQ$$Å˝\ÎS¨Ûlny%7x\Z\Ê_\Ê\ÓΩL\Ó˚PneôªõΩ,ÉG%¶ä¸6˚ylû°{óD\„Ka;{‹ô.ñg\⁄\ÀDWe1ô)&\Õ/uü\œmöZ;2µ•æf\”\‘¯≤âEûX\◊m≠†uGªnÉº\‘;SÉm3ÆQıˆ7¿|ˇ|∂\"P@™\œcm=c˚i¸n6˚2æGu•J\ZNôπ1n˚©\„Ÿóe\–Auü¿\‹I\Â{DÛ≠\ÌG∫N∂â5MóâÉÄÙ\…LSP_hà4T¯ÅN£p\ \\\÷\–oY\€\Ï3¶Üª∑†ãY\Ó≥/¡J/âÇ\·x\Â\‚Bç=\›hë,g\„%ó\Ÿa5˘\ÎWÑ\⁄W\‹V˜B\€T7ÛGl;>áI¡lAºzﬁÅ:Np|!4tÇ<∑¸G\”\ﬂ˝\—w5LN\»\‹2tB?©üÑç;n\ÔÍæ≠\Õ(\ﬁ\ﬁ=p{¡˘(®âÑ≤^\ÂÇÒjü\Ì\Ï\ﬂK&5|œí\Ó}#ŸöU{∫\ÎÜ\Î=\Ó∫eu\≈U9´∑a¿§L\€	f\\É,/aÚ£sì\n\nQ\‡ﬁÜ\⁄N\ƒ}Y4{ø\Œ>£Lû˘ÅËúêA\'<{B}f—â¸-\Ë8ç˙\È[Yp`î¡@$\„5÷§Çπòì&t$Nöbπ\÷òªKHt◊ÆE=&c\Œ\Ì\0Ëàú\’\Ã1{B\ŸTπ#ñ\·Eû2IÇ\≈\¬[Ã¨+SLZıW,ÒB©™\√\Ó\¬\∆Iê!XıC\‡N#ˆ\ÊT\ÓH\¬#ëb±°±!>Äc\Z©EZêNd\0F\÷\"õê	\‰N\‰\0⁄ß†ªm\…mÚrπ\ÓÆ}ç˚‚ìª\ w˘◊çÖ\«t]}lR(Eæ2k\…\ ˚vçı≥\Ÿb\ﬂÿÆ}2\Ì^π\⁄\Ó\ÓŸ±{Ò\Ó∂=˚;ˆg∂l´\Ÿ\ÊY\„]cíÜ∞˙f™Y_ñ2•v\Ôﬂ∂f®9ïjZ≥mˇn:∫qC0ä§/¶/\nj)PZ©[∏ò˘¯\nGòˇñÕ∫ˇ\ﬁ¸\nQƒûv˛≠STx=\Ãe´31≠µ÷¶µ•\ÁÙÇ\ÎÖ\Ì\¬\Á¥t\„ud¡˚Küáˇ®2õ≠<W´´™´\¬l∫6˛=[]UUç\r\¬\„u\'ºÅ\›7\€˜˙sï\ŸL&åVe≥U\Ë´\·Ùjxº\n{Ég¯qp®W\”ˇß∫∫\ÍˇÇÙ	p2ﬂ∂\–oe“π\Î]\‡l™≤2ã˘µN\”48y˚Y∂2õ\'@RG∞W∞s\‰êˇ\œﬁó¿7Qmˇ\œd_\€t\ﬂ\€\Èmih\'\›ñÜ6]†m\ŸQö&iHìò§¥U\‘RîM@Q6}\‚C¢(äE ¢\–*‚Ç†\‚Çºß¢lˇs\ÔL“¥\ƒ˜y˛ˇ\Ô˝˛ôCì{\Ôú{ø\Á|\Ôπ\ÁN:ôíF41œ•\›E.\ W\»+U3TßT\‹0Uò*1∫\◊wg˛é\ƒ^Û\Îf\Ê´dd\ 9¸Ùsæü∑*JµV≈ï3⁄æ—Ω&§øSî\ÿk±øvv~å˘\rmøßLÉÒ\ÔõcssŒª¨∞≤\—/9/àd\¬a£´î\”\“\“o\Î¨P;Ü)\ƒ^Rq®4¥vDJUn¥\’ëï2D¶Ke\‹Z*B&\nÚI\”?8£˛QSNL¨Wå?©*®!%35ã\Áã\Â\n°DàV\ÓT\ÿOÚ/p¢ó	TøüÛ˜ˇk®˜\‡˙$\ŒaN;ˇ\‘†?1DWπ¸ÛÑÜ03Ym9ygú*N%á\0Ú\ÂÑ\‹;\Õ;-(g˚àP~\"\",àΩ\Â˙.$ôÚ.Û\Ô9úﬁ∞zb\Œv\€a\'?\»uC\ ıEGW\÷\Zü\ÃEqÖ2d\ÓUpp˚˚`D¢ìCt«Çjú\‹a•Üº∑Ö{˘äπA\“Pô4*ûé\»+	\ZöWí7d\Ã\‘Ã∞å\‰8©D(í˙è\Zûôü\Z76wwGŒîëQa\nëó\"–ß¿G(TxKÚ\“C\„#Ce>C3\∆e¶WeÜãº˝$í@ˇB/æ4\"ch\Ë–®8ó9\«\Êrúâ˘´?\·\ﬁ¡\Ëø@\œ9C|\…\“˘|IpoLLä§7Q\Âﬂõ‚∫ïã~ppùcrˆ\Âò\‡^®\ÁHzM®\√\Œˇ^SJ\ﬂ\\ÙsÉgú3boˆGe8\'\—\›QLNUV\ÓÙ¸ò!\Íiôë#BüïH∏|ëÄî\nºΩÚ∏Ïºëë”ó\Î2r\ZWL^9jòB¿Øï˙\ xA\·A\√\∆6\Ê4ñ\ƒyyÌäâÒˆó\‚\œ\€\◊\ﬁ	ˆ\rÙ\Âòv˚\ÍY#Å!qCÄ6¬à<¢\ﬂ˜Ç˛”ÑXÙ∞7,Ωÿå]úy;ìÉ§\‹\»Tä¥˚\ÿÒ\›_\◊c\≈\ÁR\ÁP.}â\»L\”˝â\‚æ\'\‹\\˜b\›\Ó≥3\œ\\1∑˚O	!~a^\¬oH±w†∑\"\–KL~LíBEp\0∫\ÈWDÖ(op\ﬂ˙Ñ¯éï¯\…ƒúœÅ<8\‡ìZ˛\’W∏\ËO¿ÉÚ>W˚Ò\–\0\¬\Á\ÍEé\‹7\‘[¿ó˘\»·öÜ][¯\Ô∫\‡;Ç;íGY\Z;\√G˚†\ÔÉá\'è\Ó%|H\Ê;¡\ËØÛ}|ÇFı¬ßßy;ÇXb\ÿoÑ¿u¸\’^≈ª\Áz}\r¢àdü—Ω¶˛}\≈\\\ËMç\Í5ııw>\È\niÈé§~_çˇ\Ì”Æi˝˛D~ﬁïy\Ã7\0_6µ\√%ÉØ\"\"L.+	äûçæ\Í-Û\ﬁô\ËùR¢\nˆí\Î,/Û\ƒ\ƒ\À\"Cä´£Uî7\Á_\»\Ár˘\—\À\·t¨ﬂµ=.\Ê>\nˆÂí¢òå\¬\ƒ¯\—túLGGn	Ù\Â\ƒ∆™§\\\Óª>â(+ı2\ƒc~ô2ÖFWéæd<\Á6û_9\‚ﬂüq\Ô0yÛcws&Ç\œ9FåNB\ﬂz¯\›\À\¬€®\‚;\∆Wô5QQEwTWY4Qw(\’\ 4ıP\Ô@\Âx\‚\≈˘˚åG-#≥Mè\È\Íµå»ûıÿ¨\È˜V≈™&\Œ.övoUújb+≤uô\√\«kÖ´ü®\nnå∂\'Ü;ú±á\À⁄Én|ë\ÏÉ¸\Èô˝.˙˛ôÛè3N\Óu-\ƒ\«\œ\Àˇ…å¢Dü±\„bG™b\≈B/qBnqÇ∫ntî\⁄‘¢•\‰\›a§)8, *2\÷˜©åâ\≈ya9\„BR˝Çe¬Ä\0Ôòú≤§¯ä©≥\nk£à≠∞Zfb\÷(`M-¡\ÊysN|\"ñ•\Ó8¡\\Rˇ.uÒay3\n\∆L\À\r\rÕª≠p\Ãmπ°çæqôCÜeF\…}„≤Ü$eFI9Ú π\”SS¶Ã≠©\ÏD\ÔùS\ féâL(\—\Âñ\—{=0WIº\ 	\‚6\…D¸Ûﬁú†h_Ü≤hŒâøâ9J¶\¬\·ºG\‡K\‰4\ƒ \\ñ\‹\ZÅAÅ\◊fyyK}*sbºFéäLKà˘§\¬:/:≥*#Dë46w692´aä\–\–p\ÔÖ√ãG®SF˙¯$˙¯ãæ>≤0:?.:øtré\r\≈\„ıV\ﬁE\"Ñê>\€eú›ú`ôå\”C ≤2¢Sò\Ìü˜π∑X<tRù!}\·ÜüPÆ7\ÊCê\◊7ÒóÚ¸ãÑî\⁄.êB|l7	¢\ÃM\Èhti\≈˝ßó\ﬂ\Á_èR•CœóÖ\À8¥GÇKà∂\√$¶§\—*nt@tgˆ\’E\¬`}ºˆ˚BN∫U\·Ñˇ\ÀÚ…≠w-#º˛]¬ó\ﬁD∫\…åäï\„DÑAn≤∂ü\\\\D,?\‰&gëåT\÷ıìkE˙ˆŸΩå\»G˝\«\À\¬ãW\¬\r\‰}$\ﬁZF%}\‚\” àØ¯¶Ú\…\‡\‚7w0Ò/\Ô\'Ø.ì±¨\È\'\›XN\Õ\0y=xj!i!´∞\\ÒàG˛[%¥ºü<˝$ø¸;$l\»W˝%\\r∞øD\Ÿ\⁄_\"% æ\ƒiã\‚ê7¢ﬁ†∫\»Rêáo(\Á©Û\—u7îó¢_äI \Ÿ jè¸\Ôí\ÿ˚oE\‚d¨¯ªI∏K\‚\‚^ˆàG<\‚èx\ƒ#\Ó2D\„&oˆ\…PãG<\‚èx\ƒ#Ò\»ˇπ\€#ÒàG<\‚èx\ƒ#ˇ&\Ÿ?t|ízêë\„X˛\·èx\ƒ#ÒàG<\‚èx\ƒ#ÒàGn]xÒ\»ˇøÇüªŒâÅW.*r∏Öãü˜\¬5T\Ê^ºmlôK\ƒÒˆ∞eûõü\Ê}∆ñn\ÌBb6\ÔW∂,\"ÜÒ\Ôa\ÀbÇv≤e	gùK_JLn`\À2bò[ñ{	DN;Ωà±†\√>;Jä\ÿ2IÉh∂\Ã!Ñ¡lôK/`\À<7>!^Àñn\ÌB\"/x3[Å)lYL(Çød\À≤ •/%íÇb\À2\" $ö-ÀÖ‹êL∂\ÏE.AÚ\–\ÏÚ\Â[\Ÿ2\√3Sfxf\ \œLô\Á¶\√Ãîn\Ì\œLô\·ô)3<3eÜg¶\ÃÃîûô≤\‹+ò\ a\À\œ\œëJ–Ñä»ÜR9a$tÑç∞v¯i \–V\0%a≈ØZh1B\…L$\√5a°àjhk$ö\‡ú\◊n\0\Ì\Ÿ™M9Q•zh1≠†Q	£`åZ¢ó(¢Fnáq[0¢	Jç\ÿ\n~,†\”}ùî\ÀföHÉ\“PW-ãPb|-å`]\npµÄÉ\∆\–≥X›±PkÇVt∂Ï≥ª¸©Öv#ˆ¡tC{\Z01\Íıpµj1˝}d∆±∞ûR•\ŒÍ∞øNv[°Ø\r∑¥Äñ≥FA{n+\'J¡&ƒé˜3c^Ûp\÷0ÕÄâX\÷\„Wäµ»©K\·v;ûS#\ÿ‚úΩ>?\–yXaÑûv`°\0{cƒû]~h\·ßz02˛h1\≈ŒµFD£jAç\’µV(9<\ÿ¡øz(õ∞M6\ÃÚ\◊Øç,SÃ®\ÏÉi\∆È∞•fåb\«ÛTäg•ZP<∂`\Ìx\\;F\Ï√ÖGÖF’≤Òäf\Ã ∂;Qöa\Ê\«\ ZiÜñfå åi\«LıYÄ≠\ÿfm8πel7\·®Aë\–\ƒF.≤™tµÄ\Ô¿53ûkg\\3ú1(\Ã<öYø,ò\€z¨\Ÿg±ªGàµ6‹èÒz‘ìÒ\⁄uü\Õx<Z3°Û\–¬ÆRwæù\—gf#˘\œÃã\rGÉ3F\rxÆQ\‰Z]\ﬁ066≤:v®\›…é\Ó\0/òö\Ìö%-é¥ö˚˘\Â\Ã<:∞DãÒu,~2\Œ.çxÆ–ô\ﬂ\Ê´\‹\ﬂx=ëçg\‰g\¬(©ê9n\Èå©«ëàPfπ\Ê†oe˛6O6≤qmui£\»ef\‹˙;ˇwÚ≠ƒìqˇk2nX¢#*Kd\œSD1é\n∂\ÃÇÚU.ë¢\«‹¢ûÕøâûd6\ÊR†‹éc®Göõvh’Ç\Ì\«\ŒQô1M\ÿdA∂ñ\…s\ÃXÉ≈®«π˚Œ∞\‡\Ïáfu\n\∆`2M;föa\∆\·ömß∂3/\Ë\ÿ‹çVπsÄÙ¨lT∏\Ái+\Ê\’\Ã\Êf[◊≤9ŸÄ3ä{\»XWè\Ìp\ŒÚ¿s∞=ò¯±˝¶•¡\ÂÉÚñ2≥+\Ë1ßv˜a\÷\'É´t\·ÙÄ…¢≠ò\'^OÉq\÷\ zj\ƒ+ÕÑ\◊≥Ú\À=\Í\√\Ï,	†ü\ÿ/Çù±\·_\Â\÷}}0ª;\≈\Ó\œ<s∫~˚\‰@˙v≈Åv\Âπ\≈\0ÚÑÒÖπZp\ÊJõ\Î\ Cè˜^3\Œ#\⁄z\ ƒû∂_T1˘¿¬æ2^1\Âº^ò¸§\«˚òë\Õ-\Ã8H”Ñ≥ˇçcî\…\‚fvf˙FwÆ£\€UE\ŒwFñgî\’\Â8_\ZXúWNñ˚GµœåóıÑÛ˙j`û∏\‰\Œ”≠¯ä¬àgÕ™\⁄Cç†\·<ó¬é9c@\ÓLdWo_∂\Ëª\ZpZÛGvß[\‹\r®cî9«†\"\\\—<⁄òyrF\rsubbwëæ\Ëæ\Ÿ\Áå\ \Ôrh\Ê™\\+\«\Óv-\¬\Ã7ã\…\ÿfvﬁï\ÿgª˚8Ø+ò\Î¢Fvûùq\ÃƒïïΩ\ﬁa,¯∫[ã˝tFäñ\Ë\€\Â\Ê≥?a.\\i±\Ôà7#õ\Îı\ÏZ’±\◊\⁄fl´˚ûi\ƒW\„võ¨ç7û[(\◊Ù\ﬂ\Áa∂\›8“ª}Bp_∑<\—˜©∆©=xvS\»nN\Óˆ6\·O\∆~;\Ì\Íª\Î[5};ësïÑÛ\”˙\Ê¨\‹\"ƒä?ôpº5π\Ì∞å\’ı\ÿªSµ∏\Ê\“=ó0sò\¬Œ∏Øì\À\Á∫\ÓK∑Œ™˚\œx\Èæ\”Ùè\È>&Z1è\Õˇ\‚<:wÉ¸\Èía\∆\‡fÅø\"\Ã>^fÇÜ\Œm\Ôp\‹$3ô_è=p\Óxπ˝≤8s56óª\Í6\„=¬πÀ∏>s\ÓÉ\Âî˛Ω\Ï8W0sU\œ˙=¯û´Ω¡å\⁄\\\ﬁ\€qîöÒ\Ë\Ã*˙\Ì\'\ﬂ5ú˚[	°¡g+â\"®MÇ›≤\Z∑îBY¥\Z\ŒLÑZ!¥BK<h‘∞\Á\„ÒLM\¬˚P	\ËM¿{3F5ºV@}\n\ŒqEÖ\Î®6Ù+`,\‘WCL\∆\Z≠kV\„±À°µ\ﬁ5¨\ÍQ\0-†é\ \≈82x–ã˘Q\ Óâå•µ\–Nπ<\ÏoU)FtZVµjøÑ=´Ü±KÒx\»~Ñ_Ñ\À.;ãXK’ò#42\Z≥\0,*\√5\‘:ﬁ´@Ø„´±œåµÿá\"8\œ¯¢¡ \‰d\÷WFÒ3ë=É\Ê\ŸW\“\Áï\ZsPÇ≠\È\„Ø\0ﬁ´¿r4~1ú≠\≈;D%Ù,ƒû\÷`ˆ4,g\»\€2\\\ÎÛäô©\Ï\rbqP\Âr¯)vqWç_[™\›F\Î\œ\›$|æOãÒOÕæ`\Ê*qçôç\\´\≈sÖ\Œ*Ÿπ¨\∆~DùÑ#QÉµ\‘\ÿ\„\ZWÑ\·\Ëe¨wF\'ÉQ\Èf	Éá\Ê\÷\›gTS7Y#\Ã(\ŒÛÿô˛-/àu5\Ê\ŸU\„Bæ\—»∞6ü°RiU6Un\‘\Ÿ,vKÉÉ*∞ÿ¨õ\÷a¥òì)µ\…DUõv™\⁄`7\ÿfÙ\…ÚCΩ\Õ\–JUZ\r\Ê\⁄v´Å*”∂[Zî\…\“h\‘Q:ãµ›ÜzPhd:ç\Zäﬁ≤îTµ\÷dm¢J¥fùE7Z\«Zö\ÃTIãﬁépjõåv\ \‰>NÉ\≈Fç1÷õå:≠âbA\«†î\›\“b\”(dn´\÷f†Z\ÃzÉçr4®Ú\“Z™Ã®3ò\ÌÜ<\ n0PÜ\ÊzÉ^o\–S&¶ï\“\Ï:õ—ä\‹\√zÉCk4Ÿì¥&cΩÕà0¥T≥≠\Ÿ£ÿå\rTÉ∂\ŸhjßZçé&\ \ﬁR\Ô0(õpç\ÊF0\nTÜf\Ëi\÷6≥¡fO¶JTÉA\Îh±\Ïî\Õ\0^Ä°≥+){≥x\’i≠PF]ö[L£Ü4∑4l†i78\0v\ j≥¿l katì\…\“J5πî±Ÿ™\’9(£ôr Æ¡2\Ë>ö\À\“@\’Ò¿ê\√\–\ÊÄ\Œ\∆YÜdäu3\ﬁN5k\ÕÌîÆ¶î±\—gímZ\≈f¥#F\r\⁄f™≈ä``\ƒFh±\Ôuáöç\\\“R0\Õ\n]ì\÷Ül\…’Ü\∆ì\÷Êä´\\\'t.äáåâ@öÇ\Ã\‰‘¥~\‘;lZΩ°YkõÖ¸¿S\Íä\ÃF`‹äöup\ﬂl4ÿì\ÀZt	Z{\"\Ã\"Ul≥XMá’ûõí¢∑\Ë\Ï\…\ÕŒû\…\–!\≈\—nµ4⁄¥÷¶ˆm=\ƒRMSãNko∞òÅp\–\Í≥∑X≠&#:óLM±¥\0c\ÌTÑê+jFD\Ë`j%•7⁄≠¿ÃÑZmF8´ºka\Z\r∂f£\√\√’∑cØú\·TA\‹Xl\ŒBBP˛\÷wà}ãŒ°D\·8˙*Q\'\0\ÃOkìQ\◊\‰fY+Ä\Z\Õ:S\ƒ~üı3DJÇ1ëYn\Í0\¬Õ¨eV\ƒ:Ãª\›a3ÍòÄt\‡8téïáH0\n¨	îJlh\Â\Ë-≠fìE´\Ôœûñ°\n\"‹Å\ÈCÖá≤ÄﬁÄ\‹D:Mìµ?£êó vu4!FºNöåıF\ OÚZ0π¡ÇV2ô•ZI\’k\Ì`´\≈\Ï\ \ŒIH`c¡`Nn5\Œ2Z\rz£6\ŸbkLAµ–ú¡\ÊîDò^x\r†aOÇÉ%Øc¨F\“x\—<\”>!j`-ô ±a∫˚ßIDeøD)óW°…±\„\≈~\ËÅ\r\Ã\ËïTÉ\ríZ\"∞¡g\ƒ1p3\n\›)K=$;3\"Eãµ3\Œn\›dê\÷n∑\ËåZ∞\Œ eôZ&ü\ZM¿L\Z±ü∑T\rõ©\ﬂI\ƒ\Èq6d\ÊaP=úgQ≥[∏)\ŸpC\÷;Oõåß6\Z\À\∆\ÏTÄÄÚPârπ±Ω0!\÷p\»ﬁÑ,]ﬂÇØ5≤Q¶Ä\„vJ\—´ë…®74ïY\0\…,\ZñilDkì•˘&>¢e\–b3É1<Ä\ﬁ9\€2”†s8¨/é!¯ıFºrôá46\€\‡∂\·ö-¥dòdndó1)\Ï){\⁄\Í\r˝VÆ\÷\ÕQÇ∑; òå0EÆù\Áf†ıV¢°j*ãj\'©´5Ti\rUU]9±¥PSH≈´k†Ø§&ï÷ñTN®•@£Z]Q;Ö™,¢\‘S®q•ÖJJ3π™ZSSCUVS•\ÂUe•\Zh+≠((õPXZQLçÅ~ï∞Øó\¬JÑAk+)\»U™©AÉïk™J†™SZVZ;EIï\÷V†1ã`P5U•ÆÆ--òP¶Æ¶™&TWU\÷h\0æÜ≠(≠(™Mπ¶¢∂\‹\nh£4°B’î®\À\ 0îzX_ç\Ì+®¨öR]Z\\RKïTñj†qå,Sè)\”0P\‡TAô∫¥\\I™\À\’\≈\Z‹´F©\∆j¨uìJ4∏	\‘Ø†∂¥≤πQPYQ[\rU%xY]\Î\Í:©¥F£§\‘’•5àê¢\ÍJ\—	=*Ò –ØB√åÇ®¶˙\Õ®†˙Ñ\ZMü-Ö\ZuåUÉ:ª+\'\À=∑<∑˛\0∑û\€\ﬁm	˛Ò\‹\Z¯\Ôº5¿Ãû\ÁˆÄ\ÁˆÄ\ÁˆÄ\Áˆ¿¿l\ÓπE\–ˇÅì\œm\œm\œmÇˇ∏\€∞6ôg\r\‚z01ü\Ï\‡∞\ﬂ\»\'\»x∑\‚oˆ\ﬂ\Ï\ÂQ2	:\‰\Í[’óÀë>Gt´˙\ﬁ\ﬁXø\ÌVı\n¨ˇı≠\Í˚¯ }\Ó\‰[\’˜Û}_.z:BD∞>zö#êÖ\◊x†πê%¶B\Èv\"ù\‰j2î(##â)$lÚ\‰\›\ƒ\‰BbπÑXH.\'Víkàµ\‰Nb3\ŸCº@$ˆr\«Gπ´àè∏gâœπ\ﬂ\0\Â*ò$\ËèGJ¡KºÄW\nxµÄWxÕÄw\‡\Õº\Ô	¿{^\0ºΩÄw>\0ºØ\0\Ô<\‡]\·^\"\≈0æ_<NÑû\‡Ö^\Z\‡©Ø¶\ﬁL¿õ\rxÛ\0o\‡≠ºgo\‡\0ºcÄ˜	\‡ùº+‹≥$ü˚=\Èx—Äó\‘è[\‚Ü\Á\rxëÄóxEÄ7¥Äw\‡\›\rxK\Ô¿\€x\œ^\‡º\ÔK¿˚ëª\n∞Œí^ÄxJ¿\À\Óè\«\Îp\√S\0^\‡\ﬁD¿k\0<;\‡u\ﬁ\n¿\€\0x[\Ô5¿;x\0\ﬁ◊Ä˜#yê\‰p«íæÄxJ¿xeÄ7π?ˇÑ^\‡\Â\ﬁ$¿s\0\ﬁ<¿{ûºÄ∑\ﬁº3Äwë\\C\‰NRAˆêëÄóx’Äß<3\‡\›x\À\Ô1¥éE|R$∞vw¿\—m	Hë(ª∞£„±Æ\¬lëàIˆ\Ì{\néGAj\¬\›\›\›ñwwãx–ßnyG>•X^\'\‡ì\·Q[wwV°\"Tpªµ˚RGGõà$Eºˆ`\€·∞äpWTº\0\Ì\\R¿;Õ®HÅ¿\⁄\—C+NyÑêó!\Z˜D⁄´õ|B¿\ÔÓÆ™¢(Q_1?\ﬂ\râ\Ë\‡rI›∫u†Å\\\Ïf1qE$RP˘óx\\B\ƒ;M\ÂüFéë\„\‡x?ƒ§H∫∑co\«êï \› ∑ƒÜòOäÅ\r\'»ß∫\Â\‡®ULíb7\Ê:∑ı\‡C\ƒãÛ\'D\–Gà¿Ià;fD\Ã0ˆ˜1Ç+#|.!\·ı@\œ\”b!)çÉ{éÅjbTªø≥jR,\ÎÅc}˛˙¸∞,†À•ÆÆÆK\›\›]]\ÿYVë®\€*\‰ìBÑ\“\∆J§D\ƒ\„ÒK@wâC( Ö¢∂ÆÆ+s$$)\È\„¶\√y\né6	*\Ô\ÍÖb\Ô.täG\nY~p\Í®S(NãyÑò\œ2îO\„p¿A& bÄâ[híê	\ﬂESèGJ\À\·`}`ôbk¿¶äGH˘ß°tA\"\"%\‚Qj¶ªz™Jpu\Ÿ‹πPïíyO]OønµÇZÇ˛â∫!∫ÄØÆÆN0IÇ˘™S`\¬\◊ys¿r©Äî\"\¬cLJíR7\∆nô2D@ûúˇeRí#uR\∆r&≈ú\·\Ÿe`m@U\ÿX\–2`MC\\êäH©8Rõèá\»\◊FJ≈§T\ZE\‘u\‰0\"±åi\Ô®\Îà\"§rR\Í\›\‹º.a]\¬Úí\Â%hB\Êâ\Êâ:ER!år©´≥ì°±ì\’\÷=i‡¨´\r/gL$8$í21é‹¢N8är\Ÿ≈é®,ÃÜkô;ó\"!)g\"\’\¬l)™\Ï\ÍEL\'^¯,ù\Ï*Bì\◊mES)\»œøÑ	\Õ\œ∆£tv≤ÄBB$Ç\·\n\n©{8ëëô†£?´2!b{\…X\‚4F*n\ÎOxà\◊k0∑rÀ´LL\ $QÑµ£é\Ëqì:hâ\"dR&ã\Í∞\Ê3\'ˆ0G]èµ\'™C\ÊE\ ß\√Oá_Ò∂ÚÑ\ÈÑ\ÈPŸë#ΩK.\Ÿ\'\€\'\√\√^!˚∞ Pô©\Ì\Ì¿ƒéh\Ë\È9\›\Ó%i\√c\ŒÅ\‡ûéŒñãHπÑG^\„>t4\ÊâE(£48p≠ßß~ÑúC\ y=\Ó:/—ÄµF\»P\Ì\„/q\ÌÀè\—i¥Oúv*£\Ã\”÷ã¿eK⁄§|≈∫∫KuÃëçá⁄∑\œ	,&\ƒbî\—uíLêp\Ÿ\‡\Õ¡ 4!\Áp\‰Ç>€àûæÄîãé†Lì9\ru\⁄*ì\Ã9\0\Œ\n≤`∫\Ó:Ãçó\‡4M\”u=‹Æ—µ3Go27≤\Âd;Sûà\ jõ∂^I©m\Õf%U\–n3)©bÉe~µ¡´\Õ\0etßVIïi\Ê?¶çm ±±\ﬁ˝ì\"V—ù\n\ƒ\√\Êó\ÃˇYN\n9\Î:#∫†©ÉCí*)-ìº∏úP>Akí$\…#;≥8$o]\r=ûV∫µÑoà\Ï\'F`©ƒøS∞\‡\ﬂÚ°\ﬂAçBBGª\r\∆Û\ﬂ»Ω{Û{µ\œOºı\⁄\√y[ü÷çüw˜∫\Œ\‡	t\'o\›\…›ºé\À!9ø40Ò@[G&\Ÿj¥aÉ\–róµ$\Ïj\≈fr\'~ú	5*?\⁄UD~íIZ{ì\—\‹Ë∞òU\n\⁄5\n˝Ñ\’}≥≈¨WE\“\·®E\‚0\Ë◊£T\—t:\œı\Ó;_kl6Øqhõ≠TUÅöéí´2\È:Kïïëùë6™\ŸnUz\Óé?\≈29-E\Á•~ºÚ ™jU<=Ñ©FöåVÙµâ\¬\Z\r•©©\»-\ H\Õûñïï5<[ùï©\ZB\«2Ö\ÍQ\rÛ\Â∫ìåqgò\‰\‹N“õÄv	ß>\·lë∆Ümz≥;¡?ÛÛ}M∑	∫Z\‘|7=ˆL:ßn˝ñ¢$ÚgüzG^§˘z\Î\·ˇ∞\ﬂ~\›r\ÂÖ\’\√˙),∂˚ßÒ;æzt\“ƒ´\Âá7dºÙÖˆp£?\'®\“¬Ä\‚u\√%Àà≠áÙå’øëΩ\Á\”%I\ﬂÓõüˆBRO\Ë∂_\‚\◊hkˆ©W¸z;\ﬁ\Z[∑˙é\œ?\›gyqynÒg\n\Èf[˜Ù{\‚\nºéˇı\È\ËÙ\Óüm]˛≈ß\ﬁsöª4‰ùÉwx\ÍßmU µSèL\›F\\\Ÿ\ŸK^\‡Œö˜\√W,∫}i\÷Ò\⁄=\rß\Õ\Õ\Ôù^7ˆ£OV>q\Á\›6Ùê\√R*\„ù˙≈•\"æÛ\‚˝4K\Èwè˛\·è\ﬁ~\Èz\——ôØŸ£8\\XG;I10¬ß#Ä\“/^ \œˇ\›\◊~J\›÷≠Ú˛2d\Â£^S˝:ç\„-\∆1\À¶;¸c\”/}P]dïúÀø<˚Úé§m˚2vx”µH!äWNè£K\◊Ø\”\Ã/`øØ¢≥ô|\…\…:ÀàZSÿØ\ŸS\\”àfO\"De2®–ì\"Xò|æê$yeÙX∫\ƒYß9ÛG∞\0≠≠≠Él7\ŸA˚!{ád¥\ƒ9$W4`ArQî¨ûF||~c\…\‚3U9ç+\„z,\Àˆ\‰ü\ ˘ã≤|°r”îQ©íôGÆL‚≠¶+è]ómò˜…ê\◊yπ¢ü+Œê;>1*NèL\÷X[éU\Z+\€vΩk\‘˘êgÀ∑?◊íZ\«_µ¸D…á_^^Æ\rúr˚ﬂ∑\'MxhmıÙΩ=tº˚\„eÒ\Ì;ˆ˝<6CRæQµˇ\„wBcñ∆ã\”Û≥é>Qæ®eQ¡\„\'küﬂîeÚ\‚Põ\È≈êø.h€ò•\ﬂC>pˆd˛Ω3|µ+˘S?ºwG\¬8\ﬂ\'\“;ß$\‘e)~h}∑\”˛—©\‘Àß\“6~ûü˝J÷¥\‘&\À\·I_ìZ›äU\›_~{ag\Î/?Oørj\ÓæÙ{û2,\Ílı\Ÿ_\ÈN	i\Ï∑4\÷˚\Õ\¬KwŒ≠˙\Ê:NcΩ\Ó¨I!ç\›Ûß$ãz(≥\Ë£\‹\œ\Î\rTç±Y&}KTÖ≥Yù≠R•\“ \ÈL6\Î´“é?\≈>ˆ<˜\Á7u/\⁄∑O∏lMG{¿ï°uWl\›\ _ˇπqU˜\√E/n<<caJnZr‰ä∂_\Á<\’I\ÓºÛp\Ë+\‹7ãæ\€ˇ\»œóy\ÁIÆ«ò\◊_lπ?>¯ãÑ®y+’∫≥üø∞\‰úﬂöåO≤≠µñº≥[4b∫t\ÔûeÙ#≤√≥\ﬂ¯\Ÿ˛P`\Îˇ,ﬁΩÚ†hu.rS\∆wº~\⁄Aå[t\Ï\„\ﬂoª∂Ù\◊-u\›#_})\Íπ˙UØ\Ì\Ô⁄æ¸π\„[ìﬁ©ΩúÒ\·\ﬂ\Ôx\‡\À\»\Îg\Ôòu¯^\—l\«i\≈¯íw ïîmf|1E~u\Œcáæú˙˘ºèØÒé∫ˇ/g∫Çˆsmyj\…\”~§≠ä.IΩÙz\‹\‚o{jﬁºœú8m\Ó˘ls\«?vüıì~\Á\ÃF¿\»&\›A\È∆µ3óâH\◊JÂ∫•´\√\«\Îªﬁ™\À˘ˆz\„\Î”è⁄Ω˘\≈}~´\Èjt⁄áπ\Ë\…bZ3pßIßSQïÔóîöF”™\‘$]6ù^üa\–Oœ©Oûûöñ=<;-3u∏>;C’†MM\ÕHo\–ıKÅ%f˝U¸w:ˇ\Zîï≥≥y”õ-úánú\ÕP´gAàcàb`ø3\–\Àp:k8ùçS†\÷-N†\·j\≈-j~¿ôo\·†e\»p?íº\Œ\„\–ƒÄ\Â\Ã\Ì‰êÑ 0\Í£IØWä≠\‹0æ\Ì˝sóÆ˛˝\’˜z~¯%l‚πöC\∆b˛{Ωá\œ~v\Âëi\Õ\…N\Ë\·k¸NØi\Ô~•aÛGªø\„Là}qdlõ∫˘πK?SW>≤(¸à¯°∑◊Ñ\“\œ<x\Â\‚i?&•/^ªlr÷æä≠1o*˛~¢SÒL∆Ö\Áb-ã˚\À\‹≈ß\‚\√\œ4D,ï|}∑|Ø˘æu©\ﬂ=ø#•j\‚mÇ\ÌKE\Ë^¥\À>?~\ÁP\ÔakûNΩo\‘√£&ï∂\∆.º∂]qp\—¢ÄÒ˚ì¶™¶\Â\Ã|x”ì›≥N∞¸\–˚‹∑ØjÇé\‘W\Ã\›YZ|ˇÍßö{\ÃÒ.\≈G:G=#\›˛\√QÈöïü\Õ|\‹x\ﬂ˙\Ã˜õ©kÛﬁªæo◊™LÒµë˛{W˚?\”3ˇ\»˘ŒΩõ\'\ƒ\Ô,ô\◊6ˇ\Ì_é=>:\‰ˇÖ_-]\€\◊›î˜\Ã¡éä°_â¢\ÀtW{0†<m\Áƒ∫\ ˜«æî}ˇı\‰ì\€g<Y0Îç∂∑∂Ôûµ\Ï>\”\€_ø}\ÍÚ⁄ì°\«sÆ\Ë\ﬂh%˙b\Œ}€∑º≤Ò\Âª\ﬁzx\‚ìwN>\Ï[\\,˙¸ïΩ*\È\œ)£ÙOeY\Í™FøX∏ºrùtÒû{&ˇt∞qÅˆ£\'V˜Zr\ÿR¸iOÚ\ s\€\⁄F7üùY∫ÈõágzU\‘{-\Ô\«\Á\ÏYÇøM|+\‰\›\›?Æ|sA¯≈éôd\Âas\Ì;ﬁô3:wr©\Ó\Ô{KüN˘x\»‚ë∑ø}6ΩpE\ƒ++d≥;Gù\Ô=1|=ès\…/\ÁOr\ﬁ\‚nÄM@õ¿yfêhõ\“q\Óx	;ßSâ¯Å°º®‘ì!Å\\àFU‘ØQ\Ï\nV\√$&o\∆ı\Â\Õjãí\'ÑÆ±¡®\”:î∫\≈\—d±\Ì(π\”Yt:ù¶J\ÕH£s πß™p5çF\’ˇw\◊–øó\ﬂ◊Æ7m?ıQ\…\√\Ê\ÃJ˘Ù\’\œ>\ﬂˇ\»¯ÿ™-GOW\ƒyˇ?OˇO\ŸM˘|\'|Øˆ°Ä“ïacxnıtz\Ëáƒ¨Ø\Ôzı\ÏB°˜\œ^º\’â:ú∑\‡ÒãˇlW^π\Î´\Óàoø™ÿ∏~olÕõK’º%~˚ˆ≠oo\√\€\À_L6æüqQÕ∂˘oëPîˇ\Ï¸\ 	’≤3\\\Â\ÂôÀó\”\ÊˇòB?˛\Î=\«W\Ì¯:z\’=óé˘˝CÙbMsıÛö\ÂkKà±\≈\r>Òâ\rõVùyG0w\ÏÜ_∫ûˆ)ˆwÆ\Ì:7°\Ì\Zπ&¢J4èP\–E\Á^¸$∂hw\Ô⁄µ[#\€‘™\÷#èû ª\Ô¡ıZ\Œ\Œ˘ˆ+??˙7ÚhÃ∏\⁄\Îø˜ΩNIù˘}30Ú4\Ì\Ì\ 8|öon˘|–´Kîæ#ºy<àø˘¥B f˜Ñ\0µÙ\‹\’Lnûªúûª¥\√\ﬂ\Î\ŸŒ∫¸âÒ´æ\‚weÿßíöá¶úyrΩ\ÓI\Ìüûùäˆ-Å\Î«Æ{jKô}Ú?Ö~\…∫ä\ŸJiÿá\÷¨S\œ}\Î\◊≈Æ\”\Ë©î\ ÒÜP\Î∂!î\–Et°€Üê˝GÆâëÃ®∑x=\\+V-\⁄7ù[òyÚõÁ∑¥~t¥}|9π=\Ÿq«¥fô\ﬂ\Ê£{\ÓZ∂+˘]\ﬂ\rKö\ÎwM\‚Æ†¸™9yg˛gìvoùº&¸\”r˛≥ª\€..~˚l˘˝g{ñI¯áññ|v°&\‡d\Â\Ê\Œ|µt\Ê{{ø\\yQê2è˚Õäaq1\÷\À?]9\”ˆH≤¸g\·g\÷WÇ+øñ\ƒˆ–Æı9è5\ﬂ?\ﬁ\Î\€˙\È£W/¶F&M˝\Âàj\Ïl\’\»$õÙ–∑÷ë\◊\ÁI¸NΩ.\—\ﬁ\·˝]A\ﬂU,æwF\“\Ì_˚Óïª•c\Óz∑\∆˝=˝\Ê\Ó6\√Ùidê\ƒ\ﬂ\Îÿá˛´ÒR\√\‰\√Sæ˙e\ﬁ¸#\„\'~˝∏u•\ÈŸú≤wj\ÌØ¡ˇßò3èáz\›\„8ÉQ∆ñ¡\Èåu¨3b¸fäH\»&Yíù!Y≤ãl3\'√µE\·bHñ©êîm*≤dKñ6[≤ú¨â°A\Ë\‰$uÜ\Œ¡≠\ÓΩ\Á˛q_˝˛{~\œ\ÎY~ø\ﬂ˜˚ºü\Á˘>\·$r˛Jr8v≤]]\‘Gå¥\0iïØy¶Cù¸˝Mÿùâk\◊ï™å[¸%˘dÇ!\Õ¸mıt¯k©\‘2£Smπ⁄üâx81G\0põ\÷\ÊsÄµ\ÂH¿;uh{h5\Àè\Â{˚1ƒ£2rRé∂3ñÛ√ô\Ÿ¸\Í\"d¡ª\ÊÇ\·ıY§FYÛª\Âû\Íqî`ß€æhA˝\r˝>øµxåw≈ßëcm	í\Ìnu\Ÿ\"1|. uÖRõãUØ\‡ìw\ :úoáö≥ıj°Lo¶ñ\ÂáSÛ“É`)1\– 	E\Ãıæyv	\“ıyÛQæYQìˆKs\ÿ\—˜ÃÆ~qê∞6è∂◊æ3Ö\‰ßh\‰g\Ó;˚~#!Jˇ\≈MîÖ†W;Ù\Í\Z@b\'\0$∂ì°Ä;©{,_ÀÄà\ÿˇ\ÀPåÄ/â¸;π•\–l®`\0%\’/\–P\ﬁH¢Åı\‰W,$–∑\Ï\0≠≥\ƒ`\√\Áä>\n£nı˚\ﬁ Ò\Z\Ì´^ºk\r\œ\’íÛ¢Ÿöﬁ®´¿X±\’\·Õú¢/˜{=\‰\Îá,®4eÇ\À\⁄Tü3Û£µ{\‚∏.1\ÁROHyó\Ê`/\”\‹∫G≤éWp\»7ó\Ì)!\Ï,}ën\”q\∆Fsû∆ò\…)N\Ô0}F’≠\ƒı?@±ª/=ÚY:`O\\÷´Uqπ\È\Î¢Zê\ÁÃ£\–s\Ëü+\√\Ï\\\œ\ÌÒ˘X\‰\◊˝<h\»˝Tı˘’â=∂ºbFñà+ÑÄQæïXá~:]\'9r\‡óä_¢Ö4\ p\”q&Q∞Eä¢Õ´$5ÖíΩ\÷-ï\Zü0=TıÚä\“ïs\›\ŸD˘w∆ñ\…p%\ÈfU_ó\„’óyn˝,ıhπö%:ÒΩ\„BßY}BjLm<P\⁄q7\‚\ÓcYÑätÜ\Í\Âgg\ÀSJÑ%ã\‹\ﬁ8âyé!∞Ÿé±\„“∏∏°ÜŸÉ;VöR,];\≈\Áíßq<\«ÙB®+Lcµ7A$«°jùPØÖ\·î*Öá&â≠\›]•{ˆ´\∆\Ê\0\¬h¿î\‘HΩ^f\À|ì∞\’Pd\‚#,PX|a\‰ç]n\È«óen\„ç\‰à_\Ë}t\√),≤ä((;E|2‘±B1\ÍÖ\’e˚˙\‚-›ßqQ˛\‚°˝&çcˇ–ç{∞ÛhKoæéb`\⁄{ﬂïPqky(\ÓD\⁄%\rìΩQÉe±?\r\Á/ßó\’\Í\Âygtèˆ\≈&l≤ì\Œ`\'\Ì;¯€Ç\Áwu\…œõ¯A¨ú¢L\«7Ç•tò¥˛ï´\ﬂ@yª\‚	P8\0B\'\È\‹\„g3õ)lEwI\∆\Ìlø¿m}\n\’$\œ(\œ0\Z˚?M˙0¸ñ\·µg\›%é¿^Gfs\€0gò\∆\€0ß˝˜0˜\Í\"r\◊;/\Œ\ZA\"RÅà\‰ÕóÑb\"\"Õøö1\Ó˝o2k}\'\„\…<|úŒßœ†\‹}ÄCõÄÄ}¢q¶£LÎáá≠«•9nƒ•}âc\ƒ3Rg˛å∞t›å3Eâã|OàùZå\Œ\œ5\«\√P=˝Åß$≤ \Èª∆úS2µ\”√∫ÒúIçÆé(yçï\ÊÄ.ü\»O˜5ß9:\‘\ÍıãÆ.y9\◊K(\ÂìqÆQIa\ÁıL-˙9S\Œv\√Öójü7\Î,[Ûö\–`G!≥^´\Â˜\ﬁ	IUßπ¥Î™á$ó†aIÅëâÀèd@zrMÒº5◊ä\ÿ8≥\Ë\Ó\‹Qiyrör^\÷Xg±ùæ∂\ÈØ\"ó..\È\Ì˛®\÷Yß4\Ô+]2Y*K\Ô|π\ƒ]öâ gq´Cw\ƒıâ5cvè/¥(<µÀπçU\Âx\»\—ÙV\…d\≈¿ê@\Ï±\√\÷*YXx˘≤\Ï ∞¸qèå\nõ8w_ø\¬\ ¿\ÊCl\‡f9ÑIj\‰i†\ZΩª.\Ï\'v∏0xÚêú\Î\’fú\Ÿ\…\ËfgerÙ\»\‡\“ ¢ \Âí\Ïÿì|r\Á\ŒYk¬é˝rå8\‹.\„ø\Ô\‰tg\·◊áB¨˜G¥Zπs√Æäo»øQ\Ï\”˚ô˙(zu6K\‰¸ùÜºôD±N&dKyVæ\Ê\·Q•á\›WÆ\‰\“ƒäWı%â\ÔrV\ÍΩ*\r\…\„≥A°∞73˚3Òª\r?˜Q%›É^ó~¯x~BúÒP+˝\–Yè^	ÚqNV\Ô ∂46©\'ZIPBwa\‡Ñy-ér\Õ\’ÎèØ\·\Z)±YV˛ñ\∆á¥€≥Ç\Ì8à^k¯\‹\∆:\œv≥3P.Ç\È4âµ ±\ﬁ13i?\Z\\ﬂü\‹Z…ãx∞>¯¸i\ƒ;Y–ú\€W^Ω\ÿJA\–\‹¿ˆ\\@r´ +ö1¥≠•\Í^X|\€¡7Ç¨ÛIä∫;\\∂\·D[\ÊyrD\ƒw∑øò{\"EÜ(ıo=\€|s\'Æ¯Wlf%13◊øPy7\«\œV<Ñv0S¨°c\◊DsãJBÙ\Õ\Ì\Î˜\Ô\„\Ÿ\œ\€s\‹M\ <hñ,0ùqI\–#¿NæÑ˙\nÖ\‰ï\Ê\÷\„XıàI\—˜~ò\‚b8\‘\œ:\‚>èé~1|ª\„V2=±\‡X∏_h3k\ÌZm\ÂΩ6\Z}≠%Üip™&\€\Âj∑Z´w´\„*mµZ†ì¨\‚M\ﬂ^ú◊è\Ÿ\⁄)Ú\ŸJ\Ì…∏µ®\Âtk\Ïæ¶\Ô\ÃÀì´\rH◊ïÉYn\Z‹ñ\–\"¿k_Û?N\“Yµ¢õ\Ô÷∫±Vd¿ØfQ\Â\ŸT[Äy\È\Ã{_\Ÿ˙JS8	w%qj\Z7ùöÒˇõ∆¨∞â€ìπ£\÷R\∆˝\Zóÿàåyø°º<ûB!ø\'R[\ﬂå&Å∑vmò\ÊÖ&ƒøø“∂\Õ&q¿\Ó\Ì&	\ŸZ1df4æô√Ü\ÊŸò8VF+a\–\Îó\Ì7©CãR\À1E¥\Œ\ $¯ˆ6∏ãd\›\≈%ô\÷mm\r\≈Y±\€!\ŒrDA\ÓÖ![qKÉãsgãS≥$ß1ß¯f9\«ü\'\ZK{\ \\πDt\»T\ËVvp\Â/\Zò(=\'\Ë3£ıSg\‡\À\œ~Û;)\⁄9ãG¸\√\Â\Ãls\ƒ\Ê@Tl™.ºw\Ówª”¨˛\‹¸9Úi®cû´ÇMÃ≠µ¢\Õ-ªw\ŒiX+XørmxprçÙi\“\Ÿ\ÊYıDô\À\„A∑\⁄\€w¡∫˜F\‡ª>=ΩV\…E≥ü<ZUsO\ÃGYä¢•\'÷ñA\"f°\Ÿ\Z û^ó„¥∫h◊û]•NqÜA≠˚µ\Â{}k^ ’¢fµπ\Z\"ŸèçX*∂9Z\Ã<_⁄Ñ\\ èG´˛ö®\ÀÙˇ3\Îª\r\nendstream\r\nendobj\r\n21 0 obj\r\n<</Type/Metadata/Subtype/XML/Length 3099>>\r\nstream\r\n<?xpacket begin=\"Ôªø\" id=\"W5M0MpCehiHzreSzNTczkc9d\"?><x:xmpmeta xmlns:x=\"adobe:ns:meta/\" x:xmptk=\"3.1-701\">\n<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">\n<rdf:Description rdf:about=\"\"  xmlns:pdf=\"http://ns.adobe.com/pdf/1.3/\">\n<pdf:Producer>Microsoft¬Æ Word ◊¢◊ë◊ï◊® Microsoft 365</pdf:Producer></rdf:Description>\n<rdf:Description rdf:about=\"\"  xmlns:dc=\"http://purl.org/dc/elements/1.1/\">\n<dc:creator><rdf:Seq><rdf:li>Oneill Panker</rdf:li></rdf:Seq></dc:creator></rdf:Description>\n<rdf:Description rdf:about=\"\"  xmlns:xmp=\"http://ns.adobe.com/xap/1.0/\">\n<xmp:CreatorTool>Microsoft¬Æ Word ◊¢◊ë◊ï◊® Microsoft 365</xmp:CreatorTool><xmp:CreateDate>2022-06-07T02:24:42+03:00</xmp:CreateDate><xmp:ModifyDate>2022-06-07T02:24:42+03:00</xmp:ModifyDate></rdf:Description>\n<rdf:Description rdf:about=\"\"  xmlns:xmpMM=\"http://ns.adobe.com/xap/1.0/mm/\">\n<xmpMM:DocumentID>uuid:BD03977F-238F-4D51-A163-F411F534526C</xmpMM:DocumentID><xmpMM:InstanceID>uuid:BD03977F-238F-4D51-A163-F411F534526C</xmpMM:InstanceID></rdf:Description>\n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n                                                                                                    \n</rdf:RDF></x:xmpmeta><?xpacket end=\"w\"?>\r\nendstream\r\nendobj\r\n22 0 obj\r\n<</DisplayDocTitle true>>\r\nendobj\r\n23 0 obj\r\n<</Type/XRef/Size 23/W[ 1 4 2] /Root 1 0 R/Info 9 0 R/ID[<7F9703BD8F23514DA163F411F534526C><7F9703BD8F23514DA163F411F534526C>] /Filter/FlateDecode/Length 90>>\r\nstream\r\nxúc`\0ÇˇˇÅ§ àZ°\ÓÅ)\∆7`ä9B˝S,o¡´Ñ\ng``kgÜP,äB±A(&U\…\‘\«V\„1y\\`\√8~É©ñZ05\·5\0$9\r\nendstream\r\nendobj\r\nxref\r\n0 24\r\n0000000010 65535 f\r\n0000000017 00000 n\r\n0000000166 00000 n\r\n0000000222 00000 n\r\n0000000492 00000 n\r\n0000000854 00000 n\r\n0000001022 00000 n\r\n0000001261 00000 n\r\n0000001314 00000 n\r\n0000001367 00000 n\r\n0000000011 65535 f\r\n0000000012 65535 f\r\n0000000013 65535 f\r\n0000000014 65535 f\r\n0000000015 65535 f\r\n0000000016 65535 f\r\n0000000017 65535 f\r\n0000000018 65535 f\r\n0000000000 65535 f\r\n0000002058 00000 n\r\n0000002299 00000 n\r\n0000033917 00000 n\r\n0000037099 00000 n\r\n0000037144 00000 n\r\ntrailer\r\n<</Size 24/Root 1 0 R/Info 9 0 R/ID[<7F9703BD8F23514DA163F411F534526C><7F9703BD8F23514DA163F411F534526C>] >>\r\nstartxref\r\n37433\r\n%%EOF\r\nxref\r\n0 0\r\ntrailer\r\n<</Size 24/Root 1 0 R/Info 9 0 R/ID[<7F9703BD8F23514DA163F411F534526C><7F9703BD8F23514DA163F411F534526C>] /Prev 37433/XRefStm 37144>>\r\nstartxref\r\n38069\r\n%%EOF');
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
