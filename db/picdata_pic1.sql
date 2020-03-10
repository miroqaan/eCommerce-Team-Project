-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: picdata
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `pic1`
--

DROP TABLE IF EXISTS `pic1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pic1` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `price` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `imgFile` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pic1`
--

LOCK TABLES `pic1` WRITE;
/*!40000 ALTER TABLE `pic1` DISABLE KEYS */;
INSERT INTO `pic1` VALUES ('1','가죽시계1','10000','시계','가죽시계1.png'),('10','가죽시계10','10000','시계','가죽시계10.png'),('11','가죽시계11','70000','시계','가죽시계11.png'),('12','가죽시계12','20000','시계','가죽시계12.png'),('13','가죽시계13','80000','시계','가죽시계13.png'),('14','가죽시계14','40000','시계','가죽시계14.png'),('15','메탈시계1','20000','시계','메탈시계1.png'),('16','메탈시계2','40000','시계','메탈시계2.png'),('17','메탈시계3','70000','시계','메탈시계3.png'),('18','메탈시계4','40000','시계','메탈시계4.png'),('19','메탈시계5','30000','시계','메탈시계5.png'),('2','가죽시계2','20000','시계','가죽시계2.png'),('20','메탈시계6','70000','시계','메탈시계6.png'),('21','메탈시계7','60000','시계','메탈시계7.png'),('22','메탈시계8','10000','시계','메탈시계8.png'),('23','메탈시계9','30000','시계','메탈시계9.png'),('24','메탈시계10','50000','시계','메탈시계10.png'),('25','메탈시계11','60000','시계','메탈시계11.png'),('26','금시계1','90000','시계','금시계1.png'),('27','무궁화랑1','30000','반지','무궁화랑1.png'),('28','무궁화랑2','40000','반지','무궁화랑2.png'),('29','무궁화랑3','20000','반지','무궁화랑3.png'),('3','가죽시계3','30000','시계','가죽시계3.png'),('30','무궁화랑4','50000','반지','무궁화랑4.png'),('31','무궁화랑5','60000','반지','무궁화랑5.png'),('32','무궁화랑6','30000','반지','무궁화랑6.png'),('33','무궁화랑7','80000','반지','무궁화랑7.png'),('34','무궁화랑8','20000','반지','무궁화랑8.png'),('35','무궁화랑9','30000','반지','무궁화랑9.png'),('36','무궁화랑10','40000','반지','무궁화랑10.png'),('37','무궁화랑11','20000','반지','무궁화랑11.png'),('38','무궁화랑12','40000','반지','무궁화랑12.png'),('39','무궁화랑13','60000','반지','무궁화랑13.png'),('4','가죽시계4','40000','시계','가죽시계4.png'),('40','무궁화랑14','20000','반지','무궁화랑14.png'),('41','목걸이1','30000','목걸이','목걸이1.png'),('42','목걸이2','60000','목걸이','목걸이2.png'),('43','목걸이3','30000','목걸이','목걸이3.png'),('44','목걸이4','50000','목걸이','목걸이4.png'),('45','목걸이5','20000','목걸이','목걸이5.png'),('46','목걸이6','70000','목걸이','목걸이6.png'),('47','목걸이7','40000','목걸이','목걸이7.png'),('48','목걸이8','70000','목걸이','목걸이8.png'),('49','목걸이9','50000','목걸이','목걸이9.png'),('5','가죽시계5','50000','시계','가죽시계5.png'),('50','목걸이10','70000','목걸이','목걸이10.png'),('51','목걸이11','30000','목걸이','목걸이11.png'),('52','목걸이12','80000','목걸이','목걸이12.png'),('53','목걸이13','20000','목걸이','목걸이13.png'),('54','목걸이14','40000','목걸이','목걸이14.png'),('55','목걸이15','50000','목걸이','목걸이15.png'),('56','목걸이16','10000','목걸이','목걸이16.png'),('57','팔찌1','20000','팔찌','팔찌1.png'),('58','팔찌2','10000','팔찌','팔찌2.png'),('59','팔찌3','40000','팔찌','팔찌3.png'),('6','가죽시계6','50000','시계','가죽시계6.png'),('7','가죽시계7','50000','시계','가죽시계7.png'),('8','가죽시계8','30000','시계','가죽시계8.png'),('9','가죽시계9','20000','시계','가죽시계9.png');
/*!40000 ALTER TABLE `pic1` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-04  0:12:42
