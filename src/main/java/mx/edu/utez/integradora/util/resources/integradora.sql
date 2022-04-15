-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: integradora
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `cita`
--

DROP TABLE IF EXISTS `cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cita` (
  `idcita` bigint NOT NULL AUTO_INCREMENT,
  `documento_anexos` varchar(255) DEFAULT NULL,
  `estatus` varchar(255) DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `id_servicio` bigint DEFAULT NULL,
  `id_solicitante` bigint DEFAULT NULL,
  `id_atendio` bigint DEFAULT NULL,
  `id_horario_ventanilla` bigint DEFAULT NULL,
  PRIMARY KEY (`idcita`),
  KEY `FK68785k2hlh38mhiq3u2ny82p4` (`id_servicio`),
  KEY `FKqu5j0sc4cq8mo2w201tyhce2s` (`id_solicitante`),
  KEY `FKahnnem2na7peuhuwy3aohv1c2` (`id_atendio`),
  KEY `FKkg6r3t7mwhhb269873n7o4ew8` (`id_horario_ventanilla`),
  CONSTRAINT `FK68785k2hlh38mhiq3u2ny82p4` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id`),
  CONSTRAINT `FKahnnem2na7peuhuwy3aohv1c2` FOREIGN KEY (`id_atendio`) REFERENCES `users` (`id`),
  CONSTRAINT `FKkg6r3t7mwhhb269873n7o4ew8` FOREIGN KEY (`id_horario_ventanilla`) REFERENCES `horario_ventanilla` (`id_horario_ventanilla`),
  CONSTRAINT `FKqu5j0sc4cq8mo2w201tyhce2s` FOREIGN KEY (`id_solicitante`) REFERENCES `solicitante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cita`
--

LOCK TABLES `cita` WRITE;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario_ventanilla`
--

DROP TABLE IF EXISTS `horario_ventanilla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `horario_ventanilla` (
  `id_horario_ventanilla` bigint NOT NULL AUTO_INCREMENT,
  `fecha` varchar(255) DEFAULT NULL,
  `hora_fin` varchar(255) DEFAULT NULL,
  `hora_inicio` varchar(255) DEFAULT NULL,
  `num_ventanilla` int DEFAULT NULL,
  `usuario` bigint DEFAULT NULL,
  PRIMARY KEY (`id_horario_ventanilla`),
  KEY `FK3t4t8okj11nbocdftcfku756d` (`usuario`),
  CONSTRAINT `FK3t4t8okj11nbocdftcfku756d` FOREIGN KEY (`usuario`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario_ventanilla`
--

LOCK TABLES `horario_ventanilla` WRITE;
/*!40000 ALTER TABLE `horario_ventanilla` DISABLE KEYS */;
INSERT INTO `horario_ventanilla` VALUES (1,'2022-04-21','15:05:00','13:05:00',8,2),(2,'2022-05-05','15:05:00','13:05:00',8,2),(3,'2022-05-12','15:05:00','13:05:00',8,2),(4,'2022-04-29','15:15:00','13:15:00',3,2),(5,'2022-05-13','15:15:00','13:15:00',3,2),(6,'2022-05-09','16:16:00','23:16:00',952,2),(7,'2022-05-23','16:16:00','23:16:00',952,2),(8,'2022-05-30','16:16:00','23:16:00',952,2);
/*!40000 ALTER TABLE `horario_ventanilla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intervalo`
--

DROP TABLE IF EXISTS `intervalo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `intervalo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `enable` bit(1) NOT NULL,
  `hora` time DEFAULT NULL,
  `horario_cita` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1sgvv6ywchvllinispbnfehyi` (`horario_cita`),
  CONSTRAINT `FK1sgvv6ywchvllinispbnfehyi` FOREIGN KEY (`horario_cita`) REFERENCES `horario_ventanilla` (`id_horario_ventanilla`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intervalo`
--

LOCK TABLES `intervalo` WRITE;
/*!40000 ALTER TABLE `intervalo` DISABLE KEYS */;
INSERT INTO `intervalo` VALUES (1,_binary '','13:05:00',1),(2,_binary '','13:35:00',1),(3,_binary '','14:05:00',1),(4,_binary '','14:35:00',1),(5,_binary '','15:05:00',1),(6,_binary '','13:05:00',2),(7,_binary '','13:35:00',2),(8,_binary '','14:05:00',2),(9,_binary '','14:35:00',2),(10,_binary '','15:05:00',2),(11,_binary '','13:05:00',3),(12,_binary '','13:35:00',3),(13,_binary '','14:05:00',3),(14,_binary '','14:35:00',3),(15,_binary '','15:05:00',3),(16,_binary '','13:15:00',4),(17,_binary '','13:45:00',4),(18,_binary '','14:15:00',4),(19,_binary '','14:45:00',4),(20,_binary '','15:15:00',4),(21,_binary '','13:15:00',5),(22,_binary '','13:45:00',5),(23,_binary '','14:15:00',5),(24,_binary '','14:45:00',5),(25,_binary '','15:15:00',5),(26,_binary '','23:16:00',6),(27,_binary '','23:16:00',7),(28,_binary '','23:16:00',8);
/*!40000 ALTER TABLE `intervalo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `authority` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_irsamgnera6angm0prq1kemt2` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_USER'),(2,'ROLE_VENTANILLA');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio`
--

DROP TABLE IF EXISTS `servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicio` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `costo` double NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `documentos_requeridos` varchar(255) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitante`
--

DROP TABLE IF EXISTS `solicitante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitante` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `carrera` varchar(255) DEFAULT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3ccp22n8ad4duh5ftk5bxkdkg` (`id_usuario`),
  CONSTRAINT `FK3ccp22n8ad4duh5ftk5bxkdkg` FOREIGN KEY (`id_usuario`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitante`
--

LOCK TABLES `solicitante` WRITE;
/*!40000 ALTER TABLE `solicitante` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(45) NOT NULL,
  `contrasenia` varchar(255) DEFAULT NULL,
  `correo` varchar(45) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qs4hlsdf7l1k1u4on057c0949` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Vazquez','$2a$10$rXmqb0WMsiv0op8C0FRALu/gWgAZHEM6FOc3pO3jatidJmXREiyMy','admin@gmail.com',_binary '','Leo'),(2,'Cortez','$2a$10$CY5A6poEx.8B/PgFGYdDDOKJ0720WPFz5HicWfz6aGmeMstkf2V4a','ventanilla@gmail.com',_binary '','Abraham'),(3,'Herrera','$2a$10$LE67yMIgwmnWNSsawVrfwufCE0xjXXUhmviiF3EcPQzFKTRlOgc3a','user@gmail.com',_binary '','Romario');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'integradora'
--
/*!50003 DROP PROCEDURE IF EXISTS `registroHorario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registroHorario`(
fecha_in DATE, 
horaInicio_in TIME, 
horaFin_in TIME, 
numVentanilla_in int, 
repeticiones_in int,
usuario_in int
)
begin
declare fechaCon date;
set fechaCon= DATE_ADD(fecha_in, INTERVAL 7 DAY);

insert INTO horario_ventanilla(fecha,hora_fin,hora_inicio,num_ventanilla, usuario) values(fecha_in,horaFin_in,horaInicio_in,numVentanilla_in,usuario_in);

call registroIntervalo(horaInicio_in,1,LAST_INSERT_ID(),horaFin_in);

while repeticiones_in>1 DO
set fechaCon= DATE_ADD(fechaCon, INTERVAL 7 DAY);
insert INTO horario_ventanilla(fecha,hora_fin,hora_inicio,num_ventanilla, usuario) values(fechaCon,horaFin_in,horaInicio_in,numVentanilla_in, usuario_in);

call registroIntervalo(horaInicio_in,1,LAST_INSERT_ID(),horaFin_in);

set repeticiones_in= repeticiones_in- 1;
end while;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registroIntervalo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registroIntervalo`(
    horaInicio TIME,
    estado int,
    horario INT,
    horaFin TIME
)
BEGIN
DECLARE horaCon TIME;

SET horaCon=DATE_ADD(horaInicio, INTERVAL 30 MINUTE);

INSERT INTO intervalo(
hora,
enable,
horario_cita) 
VALUES(
horaInicio,
estado,
horario);

WHILE horaFin>=horaCon DO

INSERT INTO intervalo(
hora,
enable,
horario_cita) 
VALUES(
horaCon,
estado,
horario);

set horaCon= DATE_ADD(horaCon, INTERVAL 30 MINUTE);

END WHILE;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-15 13:30:32
