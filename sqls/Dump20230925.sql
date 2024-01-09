
DROP TABLE IF EXISTS `categorias`;

CREATE TABLE `categorias` (
  `categoria_id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`categoria_id`)
);



DROP TABLE IF EXISTS `examenes`;

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
);




DROP TABLE IF EXISTS `laboral_herramienta`;

CREATE TABLE `laboral_herramienta` (
  `inf_lab_id` bigint NOT NULL,
  `herr_usr_id` bigint NOT NULL,
  PRIMARY KEY (`inf_lab_id`,`herr_usr_id`),
  KEY `FKq8l14rdptaccivsb4w65l9d1b` (`herr_usr_id`),
  CONSTRAINT `FKq8l14rdptaccivsb4w65l9d1b` FOREIGN KEY (`herr_usr_id`) REFERENCES `tbl_herr_usr` (`herr_usr_id`),
  CONSTRAINT `FKucshowo6wemuwy0iu7ud87i9` FOREIGN KEY (`inf_lab_id`) REFERENCES `tbl_inf_lab` (`inf_lab_id`)
); 




DROP TABLE IF EXISTS `preguntas`;

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
); 


DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `rol_id` bigint NOT NULL,
  `rol_nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rol_id`)
); 




DROP TABLE IF EXISTS `tbl_cat_prod`;

CREATE TABLE `tbl_cat_prod` (
  `cat_prod_id` bigint NOT NULL AUTO_INCREMENT,
  `cat_prod_nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cat_prod_id`)
); 



INSERT INTO `tbl_cat_prod` VALUES (1,'Sistemas Operativos'),(2,'Bases de Datos'),(3,'Lenguajes de programacion'),(4,'BigData/Cloud'),(5,'Office'),(6,'Business Intelligence');





DROP TABLE IF EXISTS `tbl_cert`;

CREATE TABLE `tbl_cert` (
  `cert_id` bigint NOT NULL AUTO_INCREMENT,
  `cert` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cert_id`)
); 




INSERT INTO `tbl_cert` VALUES (1,'SI'),(2,'NO');




DROP TABLE IF EXISTS `tbl_crg_elit`;

CREATE TABLE `tbl_crg_elit` (
  `crg_elit_id` bigint NOT NULL AUTO_INCREMENT,
  `crg_elit_nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`crg_elit_id`)
); 



INSERT INTO `tbl_crg_elit` VALUES (1,'Analista de Datos'),(2,'Desarrollador Full-Stack'),(3,'Analista QA'),(4,'Trainee');




DROP TABLE IF EXISTS `tbl_crg_usr`;

CREATE TABLE `tbl_crg_usr` (
  `crg_usr_id` bigint NOT NULL AUTO_INCREMENT,
  `crg_elit_id` bigint DEFAULT NULL,
  `crg_usr_pret` varchar(255) DEFAULT NULL,
  `usr_id` bigint DEFAULT NULL,
  PRIMARY KEY (`crg_usr_id`),
  KEY `FKqvbhp25v96vqlf94iwicrd3fe` (`usr_id`),
  CONSTRAINT `FKqvbhp25v96vqlf94iwicrd3fe` FOREIGN KEY (`usr_id`) REFERENCES `tbl_usr` (`usr_id`)
); 




INSERT INTO `tbl_crg_usr` VALUES (1,1,'$900.000',4),(2,NULL,'$800.000',4);




DROP TABLE IF EXISTS `tbl_herr_usr`;

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
  PRIMARY KEY (`herr_usr_id`),
  KEY `FK5gqshrxh65vrgpd6wmt86mxfh` (`cert_id`),
  KEY `FKbvvxntumbwanmkkh1jknd0mxs` (`nvl_id`),
  KEY `FKg21a10vegm422sgsdvw17pi63` (`prd_id`),
  KEY `FKqf6443qstxnce5frcsrb0v5rs` (`usr_id`),
  KEY `FKdt6cwc483xm5xhd9wxq3pa9sj` (`inf_lab_id`),
  KEY `FKmid773lu2moriw0xvcnmkj8u0` (`producto_id`),
  KEY `FKa066y2lo6wnil3e8qcmhtslcd` (`id_prod`),
  CONSTRAINT `FK5gqshrxh65vrgpd6wmt86mxfh` FOREIGN KEY (`cert_id`) REFERENCES `tbl_cert` (`cert_id`),
  CONSTRAINT `FKa066y2lo6wnil3e8qcmhtslcd` FOREIGN KEY (`id_prod`) REFERENCES `tbl_prd` (`prd_id`),
  CONSTRAINT `FKbvvxntumbwanmkkh1jknd0mxs` FOREIGN KEY (`nvl_id`) REFERENCES `tbl_nvl` (`nvl_id`),
  CONSTRAINT `FKdt6cwc483xm5xhd9wxq3pa9sj` FOREIGN KEY (`inf_lab_id`) REFERENCES `tbl_inf_lab` (`inf_lab_id`),
  CONSTRAINT `FKg21a10vegm422sgsdvw17pi63` FOREIGN KEY (`prd_id`) REFERENCES `tbl_prd` (`prd_id`),
  CONSTRAINT `FKmid773lu2moriw0xvcnmkj8u0` FOREIGN KEY (`producto_id`) REFERENCES `tbl_prd` (`prd_id`),
  CONSTRAINT `FKqf6443qstxnce5frcsrb0v5rs` FOREIGN KEY (`usr_id`) REFERENCES `tbl_usr` (`usr_id`)
); 



INSERT INTO `tbl_herr_usr` VALUES (1,'10 años','V.1.0',1,1,1,2,NULL,NULL,NULL),(7,NULL,'v5.0',NULL,NULL,NULL,4,NULL,NULL,NULL),(8,NULL,'1.0',NULL,NULL,NULL,4,NULL,NULL,NULL),(9,NULL,'1.5',NULL,NULL,NULL,4,NULL,NULL,NULL),(10,NULL,'1.0',NULL,NULL,NULL,4,NULL,NULL,NULL),(11,NULL,'1.0',NULL,NULL,NULL,4,NULL,NULL,NULL),(12,'2 años','1.6',NULL,NULL,NULL,4,NULL,NULL,NULL),(13,'2 años','1.6',NULL,NULL,NULL,4,NULL,NULL,NULL),(14,'2 años','1.6',NULL,NULL,NULL,4,NULL,NULL,NULL),(15,'2 años','1.6',NULL,NULL,NULL,4,NULL,NULL,NULL),(16,'2 años','1.6',NULL,NULL,NULL,4,NULL,NULL,NULL),(17,'9 años','1.6',1,1,1,4,NULL,NULL,NULL),(18,'555','1.6',1,2,2,4,NULL,NULL,NULL),(19,'2 años','1.5',1,2,17,4,NULL,NULL,NULL);



DROP TABLE IF EXISTS `tbl_inf_acad`;

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
); 




INSERT INTO `tbl_inf_acad` VALUES (1,'En curso','2000-12-29','1995-03-09','Santo tomás','Ingenieria en Informatica',4),(2,'Finalizado','1995-12-29','1990-03-09','Duoc UC','Ingenieria en Informatica',5),(3,'Finalizado','1995-12-29','1990-03-09','Duoc UC','Ingenieria en Informatica',5),(4,'opcion1','2023-08-02','2020-06-17','Universidad Andres Bello','Ingenieria Civil en Informática',4);




DROP TABLE IF EXISTS `tbl_inf_lab`;

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
); 



INSERT INTO `tbl_inf_lab` VALUES (1,'Analisis de datos ','Analista de Datos','Falabella','2022-07-04','2021-02-22',4,19);



DROP TABLE IF EXISTS `tbl_nvl`;

CREATE TABLE `tbl_nvl` (
  `nvl_id` bigint NOT NULL AUTO_INCREMENT,
  `nvl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nvl_id`)
); 




INSERT INTO `tbl_nvl` VALUES (1,'Alto'),(2,'Medio'),(3,'Bajo');




DROP TABLE IF EXISTS `tbl_pais`;

CREATE TABLE `tbl_pais` (
  `pais_id` bigint NOT NULL AUTO_INCREMENT,
  `pais_nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pais_id`)
); 


INSERT INTO `tbl_pais` VALUES (1,'Chile'),(2,'Argentina');


DROP TABLE IF EXISTS `tbl_prd`;

CREATE TABLE `tbl_prd` (
  `prd_id` bigint NOT NULL AUTO_INCREMENT,
  `prd_nom` varchar(255) DEFAULT NULL,
  `cat_prod_id` bigint DEFAULT NULL,
  PRIMARY KEY (`prd_id`),
  KEY `FK5wm64btmmbcjtum7sp87v0s3t` (`cat_prod_id`),
  CONSTRAINT `FK5wm64btmmbcjtum7sp87v0s3t` FOREIGN KEY (`cat_prod_id`) REFERENCES `tbl_cat_prod` (`cat_prod_id`)
); 




INSERT INTO `tbl_prd` VALUES (1,'Windows',1),(2,'Linux',1),(3,'Unix/AIX',1),(4,'Exadata',2),(5,'Teradata',2),(6,'Oracle',2),(7,'Netezza',2),(8,'SQL Server',2),(9,'MySQL',2),(10,'PostgreSQL',2),(11,'BigQuery',2),(12,'BigTable',2),(13,'Hive',2),(14,'DB2',2),(15,'Access',2),(16,'Java',3),(17,'Python',3),(18,'Javascript',3),(19,'React',3),(20,'PHP',3),(21,'C++',3),(22,'Perl',3),(23,'ASP .NET',3),(24,'C# .NET',3),(25,'SHELL/KSHELL',3),(26,'Jenkins',3),(27,'Cloudera',4),(28,'GCP',4),(29,'AWS',4),(30,'Azure',4),(31,'Hortonwork',4),(32,'Word',5),(33,'Excel',5),(34,'SharePoint',5),(35,'PowerPoint',5),(36,'IBM Datastage',6),(37,'Oracle ODI/OBIEE/OSB/SOA',6),(38,'Pentaho',6),(39,'Microstrategy',6),(40,'Unity',6),(41,'Business Objects',6),(42,'Microsoft DTS, SSIS, SSRS y SSAS',6),(43,'CrystalXCelcius',6);





DROP TABLE IF EXISTS `tbl_usr`;

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
  PRIMARY KEY (`usr_id`),
  KEY `FK5th0kry3ke63uineev1ssnq33` (`pais_id`),
  CONSTRAINT `FK5th0kry3ke63uineev1ssnq33` FOREIGN KEY (`pais_id`) REFERENCES `tbl_pais` (`pais_id`)
);



INSERT INTO `tbl_usr` VALUES (1,1,'Rojas','Martinez','maevafrancisca@gmail.com','Maeva Francisca','123','19840596-5','993198793','www.jksd.cl'),(2,2,'Rojas','Martinez','maevofrancisco@gmail.com','Maevo Francisco','3445','19840596-7','993198793','www.jksd.cl'),(4,1,'Soto','Atienzo','','Rebeca','','8996227-7','993198793','www.123.cl'),(5,1,'Bravo','Tapia','','Maria Cecilia','','15.789.654-9','995784512','www.123456.cl');



DROP TABLE IF EXISTS `usuario_rol`;

CREATE TABLE `usuario_rol` (
  `usuario_rol_id` bigint NOT NULL AUTO_INCREMENT,
  `rol_rol_id` bigint DEFAULT NULL,
  `usuario_usr_id` bigint DEFAULT NULL,
  PRIMARY KEY (`usuario_rol_id`),
  KEY `FK7j1tyvjj1tv8gbq7n6f7efccc` (`rol_rol_id`),
  KEY `FKgpfhgvbhlv28ayo63xjr4try0` (`usuario_usr_id`),
  CONSTRAINT `FK7j1tyvjj1tv8gbq7n6f7efccc` FOREIGN KEY (`rol_rol_id`) REFERENCES `roles` (`rol_id`),
  CONSTRAINT `FKgpfhgvbhlv28ayo63xjr4try0` FOREIGN KEY (`usuario_usr_id`) REFERENCES `tbl_usr` (`usr_id`)
);

