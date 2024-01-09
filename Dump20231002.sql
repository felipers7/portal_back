-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: cuestionarios
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `categoria_id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`categoria_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,NULL,NULL),(2,NULL,NULL),(3,NULL,NULL),(4,NULL,NULL);
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examenes`
--

DROP TABLE IF EXISTS `examenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examenes` (
  `examen_id` bigint NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `numero_de_preguntas` varchar(255) DEFAULT NULL,
  `puntos_maximos` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `categoria_categoria_id` bigint DEFAULT NULL,
  PRIMARY KEY (`examen_id`),
  KEY `FK9e3vkr595xf5ntcw0ih72lifw` (`categoria_categoria_id`),
  CONSTRAINT `FK9e3vkr595xf5ntcw0ih72lifw` FOREIGN KEY (`categoria_categoria_id`) REFERENCES `categorias` (`categoria_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examenes`
--

LOCK TABLES `examenes` WRITE;
/*!40000 ALTER TABLE `examenes` DISABLE KEYS */;
/*!40000 ALTER TABLE `examenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboral_herramienta`
--

DROP TABLE IF EXISTS `laboral_herramienta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laboral_herramienta` (
  `inf_lab_id` bigint NOT NULL,
  `herr_usr_id` bigint NOT NULL,
  PRIMARY KEY (`inf_lab_id`,`herr_usr_id`),
  KEY `FKq8l14rdptaccivsb4w65l9d1b` (`herr_usr_id`),
  CONSTRAINT `FKq8l14rdptaccivsb4w65l9d1b` FOREIGN KEY (`herr_usr_id`) REFERENCES `tbl_herr_usr` (`herr_usr_id`),
  CONSTRAINT `FKucshowo6wemuwy0iu7ud87i9` FOREIGN KEY (`inf_lab_id`) REFERENCES `tbl_inf_lab` (`inf_lab_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboral_herramienta`
--

LOCK TABLES `laboral_herramienta` WRITE;
/*!40000 ALTER TABLE `laboral_herramienta` DISABLE KEYS */;
/*!40000 ALTER TABLE `laboral_herramienta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preguntas`
--

DROP TABLE IF EXISTS `preguntas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preguntas` (
  `pregunta_id` bigint NOT NULL AUTO_INCREMENT,
  `contenido` varchar(5000) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `opcion1` varchar(255) DEFAULT NULL,
  `opcion2` varchar(255) DEFAULT NULL,
  `opcion3` varchar(255) DEFAULT NULL,
  `opcion4` varchar(255) DEFAULT NULL,
  `respuesta` varchar(255) DEFAULT NULL,
  `examen_examen_id` bigint DEFAULT NULL,
  PRIMARY KEY (`pregunta_id`),
  KEY `FK9g0sx7pv0vsvc4uksis4egp4j` (`examen_examen_id`),
  CONSTRAINT `FK9g0sx7pv0vsvc4uksis4egp4j` FOREIGN KEY (`examen_examen_id`) REFERENCES `examenes` (`examen_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preguntas`
--

LOCK TABLES `preguntas` WRITE;
/*!40000 ALTER TABLE `preguntas` DISABLE KEYS */;
/*!40000 ALTER TABLE `preguntas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `rol_id` bigint NOT NULL,
  `rol_nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_cat_prod`
--

DROP TABLE IF EXISTS `tbl_cat_prod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_cat_prod` (
  `cat_prod_id` bigint NOT NULL AUTO_INCREMENT,
  `cat_prod_nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cat_prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cat_prod`
--

LOCK TABLES `tbl_cat_prod` WRITE;
/*!40000 ALTER TABLE `tbl_cat_prod` DISABLE KEYS */;
INSERT INTO `tbl_cat_prod` VALUES (1,'Sistemas Operativos'),(2,'Bases de Datos'),(3,'Lenguajes de programacion'),(4,'BigData/Cloud'),(5,'Office'),(6,'Business Intelligence');
/*!40000 ALTER TABLE `tbl_cat_prod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_cert`
--

DROP TABLE IF EXISTS `tbl_cert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_cert` (
  `cert_id` bigint NOT NULL AUTO_INCREMENT,
  `cert` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cert_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cert`
--

LOCK TABLES `tbl_cert` WRITE;
/*!40000 ALTER TABLE `tbl_cert` DISABLE KEYS */;
INSERT INTO `tbl_cert` VALUES (1,'SI'),(2,'NO');
/*!40000 ALTER TABLE `tbl_cert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_crg_elit`
--

DROP TABLE IF EXISTS `tbl_crg_elit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_crg_elit` (
  `crg_elit_id` bigint NOT NULL AUTO_INCREMENT,
  `crg_elit_nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`crg_elit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_crg_elit`
--

LOCK TABLES `tbl_crg_elit` WRITE;
/*!40000 ALTER TABLE `tbl_crg_elit` DISABLE KEYS */;
INSERT INTO `tbl_crg_elit` VALUES (1,'Analista de Datos'),(2,'Desarrollador Full-Stack'),(3,'Analista QA'),(4,'Trainee');
/*!40000 ALTER TABLE `tbl_crg_elit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_crg_usr`
--

DROP TABLE IF EXISTS `tbl_crg_usr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_crg_usr` (
  `crg_usr_id` bigint NOT NULL AUTO_INCREMENT,
  `crg_elit_id` bigint DEFAULT NULL,
  `crg_usr_pret` varchar(255) DEFAULT NULL,
  `usr_id` bigint DEFAULT NULL,
  PRIMARY KEY (`crg_usr_id`),
  KEY `FKqvbhp25v96vqlf94iwicrd3fe` (`usr_id`),
  CONSTRAINT `FKqvbhp25v96vqlf94iwicrd3fe` FOREIGN KEY (`usr_id`) REFERENCES `tbl_usr` (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_crg_usr`
--

LOCK TABLES `tbl_crg_usr` WRITE;
/*!40000 ALTER TABLE `tbl_crg_usr` DISABLE KEYS */;
INSERT INTO `tbl_crg_usr` VALUES (1,1,'$900.000',4),(2,NULL,'$800.000',4);
/*!40000 ALTER TABLE `tbl_crg_usr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_herr_usr`
--

DROP TABLE IF EXISTS `tbl_herr_usr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_herr_usr` (
  `herr_usr_id` bigint NOT NULL AUTO_INCREMENT,
  `herr_usr_anos_exp` varchar(255) DEFAULT NULL,
  `herr_usr_vrs` varchar(255) DEFAULT NULL,
  `cert_id` bigint DEFAULT NULL,
  `nvl_id` bigint DEFAULT NULL,
  `prd_id` bigint DEFAULT NULL,
  `usr_id` bigint DEFAULT NULL,
  `inf_lab_id` bigint DEFAULT NULL,
  `producto_id` bigint DEFAULT NULL,
  `id_prod` bigint DEFAULT NULL,
  `vrs_prd_id` bigint DEFAULT NULL,
  `herr_is_cert` bit(1) DEFAULT NULL,
  `herr_nvl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`herr_usr_id`),
  KEY `FK5gqshrxh65vrgpd6wmt86mxfh` (`cert_id`),
  KEY `FKbvvxntumbwanmkkh1jknd0mxs` (`nvl_id`),
  KEY `FKg21a10vegm422sgsdvw17pi63` (`prd_id`),
  KEY `FKqf6443qstxnce5frcsrb0v5rs` (`usr_id`),
  KEY `FKdt6cwc483xm5xhd9wxq3pa9sj` (`inf_lab_id`),
  KEY `FKmid773lu2moriw0xvcnmkj8u0` (`producto_id`),
  KEY `FKa066y2lo6wnil3e8qcmhtslcd` (`id_prod`),
  KEY `forkey` (`vrs_prd_id`),
  CONSTRAINT `FK5gqshrxh65vrgpd6wmt86mxfh` FOREIGN KEY (`cert_id`) REFERENCES `tbl_cert` (`cert_id`),
  CONSTRAINT `FKa066y2lo6wnil3e8qcmhtslcd` FOREIGN KEY (`id_prod`) REFERENCES `tbl_prd` (`prd_id`),
  CONSTRAINT `FKbvvxntumbwanmkkh1jknd0mxs` FOREIGN KEY (`nvl_id`) REFERENCES `tbl_nvl` (`nvl_id`),
  CONSTRAINT `FKdt6cwc483xm5xhd9wxq3pa9sj` FOREIGN KEY (`inf_lab_id`) REFERENCES `tbl_inf_lab` (`inf_lab_id`),
  CONSTRAINT `FKg21a10vegm422sgsdvw17pi63` FOREIGN KEY (`prd_id`) REFERENCES `tbl_prd` (`prd_id`),
  CONSTRAINT `FKmid773lu2moriw0xvcnmkj8u0` FOREIGN KEY (`producto_id`) REFERENCES `tbl_prd` (`prd_id`),
  CONSTRAINT `FKqf6443qstxnce5frcsrb0v5rs` FOREIGN KEY (`usr_id`) REFERENCES `tbl_usr` (`usr_id`),
  CONSTRAINT `forkey` FOREIGN KEY (`vrs_prd_id`) REFERENCES `tbl_vrs_prd` (`vrs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_herr_usr`
--

LOCK TABLES `tbl_herr_usr` WRITE;
/*!40000 ALTER TABLE `tbl_herr_usr` DISABLE KEYS */;
INSERT INTO `tbl_herr_usr` VALUES (1,'10 años','V.1.0',1,1,1,2,NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,'v5.0',NULL,NULL,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,'1.0',NULL,NULL,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL),(9,NULL,'1.5',NULL,NULL,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL),(10,NULL,'1.0',NULL,NULL,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL),(11,NULL,'1.0',NULL,NULL,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL),(12,'2 años','1.6',NULL,NULL,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL),(13,'2 años','1.6',NULL,NULL,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL),(14,'2 años','1.6',NULL,NULL,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL),(15,'2 años','1.6',NULL,NULL,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL),(16,'2 años','1.6',NULL,NULL,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL),(17,'9 años','1.6',1,1,1,4,NULL,NULL,NULL,NULL,NULL,NULL),(18,'555','1.6',1,2,2,4,NULL,NULL,NULL,NULL,NULL,NULL),(19,'2 años','1.5',1,2,17,4,NULL,NULL,NULL,NULL,NULL,NULL),(32,'30 años',NULL,NULL,NULL,NULL,19,NULL,NULL,NULL,2,_binary '','alto'),(33,'40 años',NULL,NULL,NULL,NULL,19,NULL,NULL,NULL,1,_binary '\0','medio');
/*!40000 ALTER TABLE `tbl_herr_usr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_inf_acad`
--

DROP TABLE IF EXISTS `tbl_inf_acad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_inf_acad` (
  `inf_acad_id` bigint NOT NULL AUTO_INCREMENT,
  `inf_acad_est` varchar(255) DEFAULT NULL,
  `inf_acad_fec_fin` date DEFAULT NULL,
  `inf_acad_fec_ini` date DEFAULT NULL,
  `inf_acad_nom_esc` varchar(255) DEFAULT NULL,
  `titl` varchar(255) DEFAULT NULL,
  `usr_id` bigint DEFAULT NULL,
  PRIMARY KEY (`inf_acad_id`),
  KEY `FKdrn7vlx7ta75ibtt7b1vgsby2` (`usr_id`),
  CONSTRAINT `FKdrn7vlx7ta75ibtt7b1vgsby2` FOREIGN KEY (`usr_id`) REFERENCES `tbl_usr` (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_inf_acad`
--

LOCK TABLES `tbl_inf_acad` WRITE;
/*!40000 ALTER TABLE `tbl_inf_acad` DISABLE KEYS */;
INSERT INTO `tbl_inf_acad` VALUES (1,'En curso','2000-12-29','1995-03-09','Santo tomás','Ingenieria en Informatica',4),(2,'Finalizado','1995-12-29','1990-03-09','Duoc UC','Ingenieria en Informatica',5),(3,'Finalizado','1995-12-29','1990-03-09','Duoc UC','Ingenieria en Informatica',5),(4,'opcion1','2023-08-02','2020-06-17','Universidad Andres Bello','Ingenieria Civil en Informática',4);
/*!40000 ALTER TABLE `tbl_inf_acad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_inf_lab`
--

DROP TABLE IF EXISTS `tbl_inf_lab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_inf_lab` (
  `inf_lab_id` bigint NOT NULL AUTO_INCREMENT,
  `inf_lab_act` varchar(255) DEFAULT NULL,
  `inf_lab_crg_emp` varchar(255) DEFAULT NULL,
  `inf_lab_emp` varchar(255) DEFAULT NULL,
  `inf_lab_fec_fin` date DEFAULT NULL,
  `inf_lab_fec_ini` date DEFAULT NULL,
  `usr_id` bigint DEFAULT NULL,
  `herr_usr_id` bigint DEFAULT NULL,
  PRIMARY KEY (`inf_lab_id`),
  KEY `FKjko1tew8l1axagxe6057tnodq` (`usr_id`),
  KEY `FKjtymet4td0la7j1hyxl9wu0wu` (`herr_usr_id`),
  CONSTRAINT `FKjko1tew8l1axagxe6057tnodq` FOREIGN KEY (`usr_id`) REFERENCES `tbl_usr` (`usr_id`),
  CONSTRAINT `FKjtymet4td0la7j1hyxl9wu0wu` FOREIGN KEY (`herr_usr_id`) REFERENCES `tbl_herr_usr` (`herr_usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_inf_lab`
--

LOCK TABLES `tbl_inf_lab` WRITE;
/*!40000 ALTER TABLE `tbl_inf_lab` DISABLE KEYS */;
INSERT INTO `tbl_inf_lab` VALUES (1,'Analisis de datos ','Analista de Datos','Falabella','2022-07-04','2021-02-22',4,19);
/*!40000 ALTER TABLE `tbl_inf_lab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_nvl`
--

DROP TABLE IF EXISTS `tbl_nvl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_nvl` (
  `nvl_id` bigint NOT NULL AUTO_INCREMENT,
  `nvl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nvl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_nvl`
--

LOCK TABLES `tbl_nvl` WRITE;
/*!40000 ALTER TABLE `tbl_nvl` DISABLE KEYS */;
INSERT INTO `tbl_nvl` VALUES (1,'Alto'),(2,'Medio'),(3,'Bajo');
/*!40000 ALTER TABLE `tbl_nvl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_pais`
--

DROP TABLE IF EXISTS `tbl_pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_pais` (
  `pais_id` bigint NOT NULL AUTO_INCREMENT,
  `pais_nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pais_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pais`
--

LOCK TABLES `tbl_pais` WRITE;
/*!40000 ALTER TABLE `tbl_pais` DISABLE KEYS */;
INSERT INTO `tbl_pais` VALUES (1,'Chile'),(2,'Argentina'),(3,'Otro');
/*!40000 ALTER TABLE `tbl_pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_prd`
--

DROP TABLE IF EXISTS `tbl_prd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_prd` (
  `prd_id` bigint NOT NULL AUTO_INCREMENT,
  `prd_nom` varchar(255) DEFAULT NULL,
  `cat_prod_id` bigint DEFAULT NULL,
  PRIMARY KEY (`prd_id`),
  KEY `FK5wm64btmmbcjtum7sp87v0s3t` (`cat_prod_id`),
  CONSTRAINT `FK5wm64btmmbcjtum7sp87v0s3t` FOREIGN KEY (`cat_prod_id`) REFERENCES `tbl_cat_prod` (`cat_prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_prd`
--

LOCK TABLES `tbl_prd` WRITE;
/*!40000 ALTER TABLE `tbl_prd` DISABLE KEYS */;
INSERT INTO `tbl_prd` VALUES (1,'Windows',1),(2,'Linux',1),(3,'Unix/AIX',1),(4,'Exadata',2),(5,'Teradata',2),(6,'Oracle',2),(7,'Netezza',2),(8,'SQL Server',2),(9,'MySQL',2),(10,'PostgreSQL',2),(11,'BigQuery',2),(12,'BigTable',2),(13,'Hive',2),(14,'DB2',2),(15,'Access',2),(16,'Java',3),(17,'Python',3),(18,'Javascript',3),(19,'React',3),(20,'PHP',3),(21,'C++',3),(22,'Perl',3),(23,'ASP .NET',3),(24,'C# .NET',3),(25,'SHELL/KSHELL',3),(26,'Jenkins',3),(27,'Cloudera',4),(28,'GCP',4),(29,'AWS',4),(30,'Azure',4),(31,'Hortonwork',4),(32,'Word',5),(33,'Excel',5),(34,'SharePoint',5),(35,'PowerPoint',5),(36,'IBM Datastage',6),(37,'Oracle ODI/OBIEE/OSB/SOA',6),(38,'Pentaho',6),(39,'Microstrategy',6),(40,'Unity',6),(41,'Business Objects',6),(42,'Microsoft DTS, SSIS, SSRS y SSAS',6),(43,'CrystalXCelcius',6);
/*!40000 ALTER TABLE `tbl_prd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_usr`
--

DROP TABLE IF EXISTS `tbl_usr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_usr` (
  `usr_id` bigint NOT NULL AUTO_INCREMENT,
  `pais_id` bigint NOT NULL,
  `usr_ap_mat` varchar(255) DEFAULT NULL,
  `usr_ap_pat` varchar(255) DEFAULT NULL,
  `usr_email` varchar(255) DEFAULT NULL,
  `usr_nom` varchar(255) DEFAULT NULL,
  `usr_pass` varchar(255) DEFAULT NULL,
  `usr_rut` varchar(255) DEFAULT NULL,
  `usr_tel` varchar(255) DEFAULT NULL,
  `usr_url_link` varchar(255) DEFAULT NULL,
  `usr_rol` varchar(255) DEFAULT NULL,
  `usr_ver_code` varchar(255) DEFAULT NULL,
  `usr_is_ver` bit(1) DEFAULT NULL,
  `usr_rec_tkn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`usr_id`),
  KEY `FK5th0kry3ke63uineev1ssnq33` (`pais_id`),
  CONSTRAINT `FK5th0kry3ke63uineev1ssnq33` FOREIGN KEY (`pais_id`) REFERENCES `tbl_pais` (`pais_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usr`
--

LOCK TABLES `tbl_usr` WRITE;
/*!40000 ALTER TABLE `tbl_usr` DISABLE KEYS */;
INSERT INTO `tbl_usr` VALUES (1,1,'Rojas','Martinez','maevafrancisca@gmail.com','Maeva Francisca','123','19840596-5','993198793','www.jksd.cl',NULL,NULL,NULL,NULL),(2,2,'Rojas','Martinez','maevofrancisco@gmail.com','Maevo Francisco','3445','19840596-7','993198793','www.jksd.cl',NULL,NULL,NULL,NULL),(4,1,'Soto','Atienzo','','Rebeca','','8996227-7','993198793','www.123.cl',NULL,NULL,NULL,NULL),(5,1,'Bravo','Tapia','','Maria Cecilia','','15.789.654-9','995784512','www.123456.cl',NULL,NULL,NULL,NULL),(6,1,'a','L','','Pepe','','19551728-2','235','235',NULL,NULL,NULL,NULL),(7,1,'f','ff','','f','','15','e','re',NULL,'aeerkro223',NULL,NULL),(8,1,'F','F','','F','','19551728-2','465465','asfas',NULL,'aeerkro223',NULL,NULL),(9,1,NULL,NULL,'aaa@aaa.cl',NULL,'$2a$12$cOst1Y0InBY/HSKWXo.2POB9mkMYKQ/gfebjLFC.BeElFb7RfhMDK',NULL,NULL,NULL,'ADMIN','aeerkro223',NULL,NULL),(10,1,NULL,NULL,'aaa@aaa.cl',NULL,'1324',NULL,NULL,NULL,NULL,'aeerkro223',NULL,NULL),(11,1,NULL,NULL,'aaa@aaa.cl',NULL,'$2a$12$Nd7rgVVVres/qxHrzztHVeWZGVJxarl4M/Q1gBiY5Lp2XuNn/0eo2',NULL,NULL,NULL,NULL,'aeerkro223',NULL,NULL),(12,1,NULL,NULL,'aaa@aaa.cl',NULL,'$2a$12$GQGKMUhBYSg7N.QEu8vGH.B/xkCB108rsbkgtQk2cshlrC/QpNAQ2',NULL,NULL,NULL,NULL,'d4c01720-eaf5-40f3-9c80-ef886a1f1e7d',NULL,NULL),(13,1,NULL,NULL,'aaa@aaa.cl',NULL,'$2a$12$qoUcvrlPRJGpi83RmrJx0OlxGD9kqOc0KZuLgbp1s/xrMohEPB7ki',NULL,NULL,NULL,NULL,'143057b2-c443-44af-99a4-30892ad17ca3',NULL,NULL),(14,1,NULL,NULL,'felipe.diaz@elitsoft-chile.com',NULL,'$2a$12$gOHOqvQ8ZH/NuF3i0qu9WO/6on4Pce.bhhl/nqtPv8Li8GgcHtYUq',NULL,NULL,NULL,NULL,'ae6aa285-57f3-4d74-82b9-f880c4b493c4',NULL,NULL),(15,1,NULL,NULL,'felipe.diaz@elitsoft-chile.com',NULL,'$2a$12$.nMsYSp4VZxfrriXogmZB.FeltjGF7gBZcI10sMdaFcARYG9ea5qW',NULL,NULL,NULL,NULL,'31c7e6f3-e41e-4e12-b1ac-7ad1e01ebe41',NULL,NULL),(16,1,NULL,NULL,'felipe.diaz@elitsoft-chile.com',NULL,'$2a$12$SpQPsCSO.eqy9QB.vI3nHORpsQ7AUWYNszFBOQD412BqCrDTseWaC',NULL,NULL,NULL,NULL,'c07a442c-ca4b-4347-aef2-bd75323ba7ae',NULL,NULL),(17,1,NULL,NULL,'felipe.diaz@elitsoft-chile.com',NULL,'$2a$12$yJYNtD.pY9LVY95FyEXZvur2yFqCVpoRg4.GP4VbbL2F81Kg1/yPK',NULL,NULL,NULL,NULL,'a32d1737-d098-4ace-842c-3393f349dc48',NULL,NULL),(18,1,NULL,NULL,'o@gmail.com',NULL,'$2a$12$RKiEGv0IcuqlyDOy8c8lxu6Y4xgrTh3TmliEPVI5KhuwLXUmzDEH2',NULL,NULL,NULL,'ADMIN',NULL,NULL,NULL),(19,1,'Peralte','Piña','owa@gmail.com','Pepe','$2a$12$RKiEGv0IcuqlyDOy8c8lxu6Y4xgrTh3TmliEPVI5KhuwLXUmzDEH2','23521829-1','1516133','','GUEST',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tbl_usr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_vrs_prd`
--

DROP TABLE IF EXISTS `tbl_vrs_prd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_vrs_prd` (
  `vrs_id` bigint NOT NULL AUTO_INCREMENT,
  `vrs_name` varchar(255) NOT NULL,
  `prd_id` bigint NOT NULL,
  PRIMARY KEY (`vrs_id`),
  KEY `prd_id` (`prd_id`),
  CONSTRAINT `tbl_vrs_prd_ibfk_1` FOREIGN KEY (`prd_id`) REFERENCES `tbl_prd` (`prd_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_vrs_prd`
--

LOCK TABLES `tbl_vrs_prd` WRITE;
/*!40000 ALTER TABLE `tbl_vrs_prd` DISABLE KEYS */;
INSERT INTO `tbl_vrs_prd` VALUES (1,'8',16),(2,'11',16),(3,'16',16),(4,'8',16),(5,'11',16),(6,'16',16);
/*!40000 ALTER TABLE `tbl_vrs_prd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_rol` (
  `usuario_rol_id` bigint NOT NULL AUTO_INCREMENT,
  `rol_rol_id` bigint DEFAULT NULL,
  `usuario_usr_id` bigint DEFAULT NULL,
  PRIMARY KEY (`usuario_rol_id`),
  KEY `FK7j1tyvjj1tv8gbq7n6f7efccc` (`rol_rol_id`),
  KEY `FKgpfhgvbhlv28ayo63xjr4try0` (`usuario_usr_id`),
  CONSTRAINT `FK7j1tyvjj1tv8gbq7n6f7efccc` FOREIGN KEY (`rol_rol_id`) REFERENCES `roles` (`rol_id`),
  CONSTRAINT `FKgpfhgvbhlv28ayo63xjr4try0` FOREIGN KEY (`usuario_usr_id`) REFERENCES `tbl_usr` (`usr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-02 16:01:50
