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
  `ComplaintID` int NOT NULL AUTO_INCREMENT,
  `OrderNumber` int NOT NULL,
  `CustomerID` int NOT NULL,
  `WorkerID` int NOT NULL,
  `ComplaintDetails` varchar(3000) DEFAULT NULL,
  `RecieveDate` varchar(45) NOT NULL,
  `RecieveTime` varchar(45) NOT NULL,
  `Status` varchar(3500) NOT NULL,
  `IsReminded` int NOT NULL,
  `Refund` float DEFAULT NULL,
  `RefundDetails` varchar(4500) DEFAULT NULL,
  PRIMARY KEY (`ComplaintID`),
  KEY `OrderNumber_idx` (`OrderNumber`),
  KEY `UserID_idx` (`CustomerID`),
  KEY `WorkerID_idx` (`WorkerID`),
  CONSTRAINT `CustomerID` FOREIGN KEY (`CustomerID`) REFERENCES `users` (`UserID`),
  CONSTRAINT `OrderNumber` FOREIGN KEY (`OrderNumber`) REFERENCES `orders` (`orderNumber`),
  CONSTRAINT `WorkerID` FOREIGN KEY (`WorkerID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint`
--

LOCK TABLES `complaint` WRITE;
/*!40000 ALTER TABLE `complaint` DISABLE KEYS */;
INSERT INTO `complaint` VALUES (2,2,1,4,'test','2022-05-31','00:12','OPEN',0,NULL,NULL);
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
  `idSale` int DEFAULT NULL,
  PRIMARY KEY (`itemSerial`),
  KEY `a_idx` (`idSale`),
  CONSTRAINT `a` FOREIGN KEY (`idSale`) REFERENCES `sale` (`idSale`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES ('1','White Rose',5,'Single Roses','/common/Assets/WhiteRose.png',1,NULL),('2','Red Rose',6,'Single Roses','/common/Assets/RedRose.png',1,NULL),('3','Blue Rose',7,'Single Roses','/common/Assets/BlueRose.png',1,NULL),('4','Yellow Rose',7,'Single Roses','/common/Assets/YellowRose.png',1,NULL),('5','Pink Rose',6,'Single Roses','/common/Assets/PinkRose.png',1,NULL);
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
INSERT INTO `item_in_order` VALUES ('2',0,41),('3',0,22),('3',0,24),('3',0,25),('3',0,26),('3',0,29),('3',0,30),('3',0,31),('3',0,32),('3',0,33),('3',0,34),('3',0,35),('3',0,37),('3',0,38),('3',0,40),('3',0,41),('3',0,42),('3',0,52),('3',0,55),('4',0,55),('5',0,55);
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
INSERT INTO `item_in_product` VALUES ('1','1',10),('1','2',10),('2','4',20);
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
  PRIMARY KEY (`itemSerial`,`idSale`),
  KEY `itemSaleID` (`idSale`),
  CONSTRAINT `item_in_sale_ibfk_1` FOREIGN KEY (`itemSerial`) REFERENCES `item` (`itemSerial`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `item_in_sale_ibfk_2` FOREIGN KEY (`idSale`) REFERENCES `sale` (`idSale`) ON DELETE CASCADE ON UPDATE CASCADE
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
  PRIMARY KEY (`orderNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderbydelivery`
--

LOCK TABLES `orderbydelivery` WRITE;
/*!40000 ALTER TABLE `orderbydelivery` DISABLE KEYS */;
INSERT INTO `orderbydelivery` VALUES (41,'qq','q','qqqq'),(42,'q','q','qqqqq'),(43,'q','q','qqqq'),(44,'q','q','qqqq'),(45,'q','q','qqqq'),(46,'q','q','qqqq');
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
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,50.52,'Thanks','OffWhite','Wedding','Tel Aviv','2022-11-23','18:18','316397983','Credit','CONFIRMED',NULL,NULL,'Store',NULL,NULL),(2,110,NULL,'White','Wedding','Karmiel','2022-05-23','20:26','1','Credit','CANCELED',NULL,NULL,'Delivery',NULL,NULL),(5,220,'hi',NULL,'hi','Karmiel','2022-05-31','21:28','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Self Pickup','2022-06-11','11:11'),(6,220,'hi',NULL,'hi','Karmiel','2022-05-31','21:28','111222333','Credit Card','CONFIRMED','2022-06-03T17:39:47.293671300',NULL,'Self Pickup','2022-06-11','11:11'),(7,110,'a',NULL,'a','Karmiel','2022-05-31','22:24','111222333','Credit Card','CONFIRMED','2022-06-03T17:39:49.238079100',NULL,'Self Pickup','2022-06-11','11:11'),(8,110,'',NULL,'','null','2022-06-02','12:37','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-08-06','11:11'),(9,117,'',NULL,'','null','2022-06-02','12:39','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(10,117,'',NULL,'','null','2022-06-02','12:41','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(11,117,'',NULL,'','null','2022-06-02','12:42','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(12,110,'',NULL,'','null','2022-06-02','12:42','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(13,0,'',NULL,'','null','2022-06-02','12:45','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(14,117,'',NULL,'','null','2022-06-02','12:47','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(15,110,'',NULL,'','null','2022-06-02','12:49','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(16,116,'',NULL,'','null','2022-06-02','12:50','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(17,110,'',NULL,'','null','2022-06-02','12:51','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(18,117,'',NULL,'','null','2022-06-02','12:54','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(19,110,'',NULL,'','null','2022-06-02','12:56','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(20,110,'',NULL,'','null','2022-06-02','12:57','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(21,110,'',NULL,'','null','2022-06-02','12:58','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(22,117,'',NULL,'','null','2022-06-02','13:01','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(23,110,'',NULL,'','null','2022-06-02','13:03','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(24,117,'',NULL,'','null','2022-06-02','13:04','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(25,117,'',NULL,'','null','2022-06-02','13:06','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(26,117,'',NULL,'','null','2022-06-02','13:10','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(27,110,'',NULL,'','null','2022-06-02','13:10','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(28,110,'',NULL,'','null','2022-06-02','13:12','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(29,117,'',NULL,'','null','2022-06-02','13:13','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(30,117,'a',NULL,'a','null','2022-06-02','13:16','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(31,117,'',NULL,'','null','2022-06-02','13:17','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(32,117,'',NULL,'','null','2022-06-02','13:22','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(33,147,'',NULL,'','null','2022-06-02','13:23','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(34,117,'',NULL,'','null','2022-06-02','13:25','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(35,117,'',NULL,'','null','2022-06-02','13:26','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(36,110,'',NULL,'','null','2022-06-02','13:28','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(37,117,'',NULL,'','null','2022-06-02','13:30','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(38,117,'',NULL,'','null','2022-06-02','13:31','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(39,13,'',NULL,'','null','2022-06-02','13:34','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(40,117,'',NULL,'','null','2022-06-02','13:35','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(41,153,'',NULL,'','null','2022-06-02','13:36','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(42,117,'',NULL,'','null','2022-06-02','13:41','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(43,110,'',NULL,'','null','2022-06-02','13:48','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(44,110,'',NULL,'','null','2022-06-02','13:49','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(45,110,'',NULL,'','null','2022-06-02','13:50','111222333','Credit Card','WAITING_FOR_CANCELATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(46,110,'',NULL,'','null','2022-06-02','13:51','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Delivery','2022-07-09','11:11'),(47,110,'',NULL,'','Karmiel','2022-06-02','13:54','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-07-09','11:11'),(48,110,'',NULL,'','Karmiel','2022-06-02','13:55','111222333','Credit Card','CONFIRMED','2022-06-03',NULL,'Pickup','2022-07-09','11:11'),(49,7,'',NULL,'','Karmiel','2022-06-02','13:55','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-07-09','11:11'),(50,6,'',NULL,'','Karmiel','2022-06-02','14:15','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-07-09','11:11'),(51,7,'',NULL,'','Karmiel','2022-06-02','14:16','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-07-09','11:11'),(52,7,'',NULL,'','Karmiel','2022-06-02','14:30','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-07-09','11:11'),(53,110,'',NULL,'','Karmiel','2022-06-02','14:31','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-07-09','11:11'),(54,110,'',NULL,'','Karmiel','2022-06-02','14:32','111222333','Credit Card','WAITING_FOR_CONFIRMATION',NULL,NULL,'Pickup','2022-07-09','11:11'),(55,240,'',NULL,'','Karmiel','2022-06-02','14:33','111222333','Credit Card','CANCELED',NULL,NULL,'Delivery','2022-07-09','11:11'),(56,110,'',NULL,'','Karmiel','2022-06-02','15:05','111222333','Credit Card','CANCELED',NULL,NULL,'Pickup','2022-07-09','11:11');
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
  `idSale` int DEFAULT NULL,
  PRIMARY KEY (`productSerial`),
  KEY `a_idx` (`idSale`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`idSale`) REFERENCES `sale` (`idSale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('1','Red and White Bouquet',110,'/common/Assets/RedWhiteBouquet.png',NULL,'Bouqute',NULL),('2','Yellow Bouquet',140,'/common/Assets/YellowBouquet.png',NULL,'Bouqute',NULL);
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
INSERT INTO `product_in_order` VALUES ('1',0,21),('1',0,22),('1',0,23),('1',0,24),('1',0,25),('1',0,26),('1',0,27),('1',0,28),('1',0,29),('1',0,30),('1',0,31),('1',0,32),('1',0,34),('1',0,35),('1',0,36),('1',0,37),('1',0,38),('1',0,40),('1',0,42),('1',0,43),('1',0,44),('1',0,45),('1',0,46),('1',0,47),('1',0,48),('1',0,53),('1',0,54),('1',0,55),('1',0,56),('2',0,33),('2',0,41);
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
  PRIMARY KEY (`productSerial`,`idSale`),
  KEY `productSaleID` (`idSale`),
  CONSTRAINT `product_in_sale_ibfk_1` FOREIGN KEY (`productSerial`) REFERENCES `product` (`productSerial`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_in_sale_ibfk_2` FOREIGN KEY (`idSale`) REFERENCES `sale` (`idSale`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_in_sale`
--

LOCK TABLES `product_in_sale` WRITE;
/*!40000 ALTER TABLE `product_in_sale` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (1,'Karmiel','2022','01',23,35,230.5,2548.2,1,478.5);
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
INSERT INTO `users` VALUES (1,'Oneill','Panker','1234','052-222','oneill@zli','1','Customer','CONFIRMED',0),(2,'Topaz','Eldori','23456','050-000','topaz@zli','1','StoreManager','CONFIRMED',1),(3,'Koral','Biton','5555','054-444','koral@zli','1','NetworkManager','CONFIRMED',0),(4,'Adir','Miller','99999','053-3333','adir@zli','1','CustomerServiceWorker','CONFIRMED',0),(2022,'Naruto','Uzomoki','6666666666666666','0526270996','naruto@zli','1','MarketingEmployee','CONFIRMED',0),(111222333,'Dorin','Beery','1111111111111111','0545344778','dorin@zli','1','Customer','CONFIRMED',0);
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

-- Dump completed on 2022-06-03 17:55:43
