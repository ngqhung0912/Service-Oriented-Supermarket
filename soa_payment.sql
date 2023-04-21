-- MySQL dump 10.13  Distrib 8.0.32, for macos13 (arm64)
--
-- Host: localhost    Database: soa_payment
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `payment_log`
--

DROP TABLE IF EXISTS `payment_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_ids` varchar(100) DEFAULT NULL,
  `event_id` int DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `uuid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `payment_log_uuid_index` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_log`
--

LOCK TABLES `payment_log` WRITE;
/*!40000 ALTER TABLE `payment_log` DISABLE KEYS */;
INSERT INTO `payment_log` VALUES (1,'100',1,'Error','3adc354d-0e73-458e-bb25-d0650131a90e'),(2,NULL,0,'Error','c35945be-0b57-4317-834f-66670364252b'),(3,'100,101',1,'Error','c35945be-0b57-4317-834f-66670364252b'),(4,'100,101',2,'Error','c35945be-0b57-4317-834f-66670364252b'),(5,'100',1,'Error','c35945be-0b57-4317-834f-66670364252b'),(52,NULL,0,'created','6c40db0a-2ff8-4807-8adc-f5ff70e5914b'),(53,'100,101',1,'Completed','6c40db0a-2ff8-4807-8adc-f5ff70e5914b'),(54,'100,101',2,'Completed','6c40db0a-2ff8-4807-8adc-f5ff70e5914b'),(55,NULL,0,'Error','ab2a4293-489e-4fc4-8f6b-65e49916685d'),(56,'100',1,'Error','ab2a4293-489e-4fc4-8f6b-65e49916685d'),(57,'100',2,'Error','ab2a4293-489e-4fc4-8f6b-65e49916685d'),(58,'100',1,'Error','ab2a4293-489e-4fc4-8f6b-65e49916685d'),(59,NULL,0,'Error','7d241179-e73b-40aa-a598-132ce55b0f3f'),(60,'100',1,'Error','7d241179-e73b-40aa-a598-132ce55b0f3f'),(61,NULL,0,'Error','3adc354d-0e73-458e-bb25-d0650131a90e'),(62,'100',1,'Error','3adc354d-0e73-458e-bb25-d0650131a90e'),(63,'100',2,'Error','3adc354d-0e73-458e-bb25-d0650131a90e'),(64,'100',2,'Error','7d241179-e73b-40aa-a598-132ce55b0f3f'),(65,'100',1,'Error','7d241179-e73b-40aa-a598-132ce55b0f3f'),(66,NULL,0,'created','492db6d9-bbea-46aa-b2ac-df1776bfc1e5'),(67,'100',1,'Completed','492db6d9-bbea-46aa-b2ac-df1776bfc1e5'),(68,'100',2,'Completed','492db6d9-bbea-46aa-b2ac-df1776bfc1e5');
/*!40000 ALTER TABLE `payment_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_log_seq`
--

DROP TABLE IF EXISTS `payment_log_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_log_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_log_seq`
--

LOCK TABLES `payment_log_seq` WRITE;
/*!40000 ALTER TABLE `payment_log_seq` DISABLE KEYS */;
INSERT INTO `payment_log_seq` VALUES (151);
/*!40000 ALTER TABLE `payment_log_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-20 11:31:55
