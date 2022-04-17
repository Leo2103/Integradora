CREATE DATABASE  IF NOT EXISTS `integradora` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `integradora`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: integradora
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `binnacle`
--

DROP TABLE IF EXISTS `binnacle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `binnacle` (
  `activity` varchar(200) NOT NULL,
  `dateActivity` datetime DEFAULT CURRENT_TIMESTAMP,
  `tableActivity` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `binnacle`
--

LOCK TABLES `binnacle` WRITE;
/*!40000 ALTER TABLE `binnacle` DISABLE KEYS */;
INSERT INTO `binnacle` VALUES ('Se eliminó un servicio:  asadasd con id: 10','2022-04-17 00:52:26','servicio'),('Se eliminó un servicio:  sasasa con id: 11','2022-04-17 00:52:26','servicio');
/*!40000 ALTER TABLE `binnacle` ENABLE KEYS */;
UNLOCK TABLES;

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
  `id_usuario` bigint DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`idcita`),
  KEY `FK68785k2hlh38mhiq3u2ny82p4` (`id_servicio`),
  KEY `FKqu5j0sc4cq8mo2w201tyhce2s` (`id_solicitante`),
  KEY `FKahnnem2na7peuhuwy3aohv1c2` (`id_atendio`),
  KEY `FKkg6r3t7mwhhb269873n7o4ew8` (`id_horario_ventanilla`),
  KEY `FKeg9rfkmxnd08k80u9ugdag3wn` (`id_usuario`),
  CONSTRAINT `FK68785k2hlh38mhiq3u2ny82p4` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id`),
  CONSTRAINT `FKahnnem2na7peuhuwy3aohv1c2` FOREIGN KEY (`id_atendio`) REFERENCES `users` (`id`),
  CONSTRAINT `FKeg9rfkmxnd08k80u9ugdag3wn` FOREIGN KEY (`id_usuario`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKkg6r3t7mwhhb269873n7o4ew8` FOREIGN KEY (`id_horario_ventanilla`) REFERENCES `horario_ventanilla` (`id_horario_ventanilla`),
  CONSTRAINT `FKqu5j0sc4cq8mo2w201tyhce2s` FOREIGN KEY (`id_solicitante`) REFERENCES `solicitante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cita`
--

LOCK TABLES `cita` WRITE;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trigger_after_insert_cita` AFTER INSERT ON `cita` FOR EACH ROW BEGIN 
		INSERt INTO binnacle(activity,dateActivity,tableActivity) values (CONCAT('Se registró una cita nueva con id: ', NEW.idcita),now(),"cita");
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
  CONSTRAINT `FK3t4t8okj11nbocdftcfku756d` FOREIGN KEY (`usuario`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario_ventanilla`
--

LOCK TABLES `horario_ventanilla` WRITE;
/*!40000 ALTER TABLE `horario_ventanilla` DISABLE KEYS */;
/*!40000 ALTER TABLE `horario_ventanilla` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trigger_after_insert_horario` AFTER INSERT ON `horario_ventanilla` FOR EACH ROW BEGIN 
		INSERt INTO binnacle(activity,dateActivity,tableActivity) values (CONCAT('Se registró un horario nuevo con id: ', NEW.id_horario_ventanilla),now(),"horario_ventanilla");
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trigger_after_delete_horario` AFTER DELETE ON `horario_ventanilla` FOR EACH ROW BEGIN 
		INSERt INTO binnacle(activity,dateActivity,tableActivity) values (CONCAT('Se eliminó un servicio con id: ', OLD.id_horario_ventanilla),now(),"horario_ventanilla");
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
  CONSTRAINT `FK1sgvv6ywchvllinispbnfehyi` FOREIGN KEY (`horario_cita`) REFERENCES `horario_ventanilla` (`id_horario_ventanilla`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intervalo`
--

LOCK TABLES `intervalo` WRITE;
/*!40000 ALTER TABLE `intervalo` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trigger_after_insert_service` AFTER INSERT ON `servicio` FOR EACH ROW BEGIN 
		INSERt INTO binnacle(activity,dateActivity,tableActivity) values (CONCAT('Se registró un servicio nuevo: ', NEW.nombre,' con id: ', NEW.id),now(),"servicio");
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trigger_after_update_service` AFTER UPDATE ON `servicio` FOR EACH ROW BEGIN 
		INSERt INTO binnacle(activity,dateActivity,tableActivity) values (CONCAT('Se modificó el servicio ', OLD.id, ' con nombre  ', OLD.nombre," con un costo de ",OLD.costo," y la descripcion ",OLD.descripcion," a nombre: ",NEW.nombre," costo: ",NEW.costo," descripcion: ",NEW.descripcion),now(),"servicio");
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trigger_after_delete_service` AFTER DELETE ON `servicio` FOR EACH ROW BEGIN 
		INSERt INTO binnacle(activity,dateActivity,tableActivity) values (CONCAT('Se eliminó un servicio:  ', OLD.nombre,' con id: ', OLD.id),now(),"servicio");
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
  CONSTRAINT `FK3ccp22n8ad4duh5ftk5bxkdkg` FOREIGN KEY (`id_usuario`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Vazquez','$2a$10$jBwQ3SpcM8f/40LGMmQeLudx2TzPHrbm5/cJVbHhCBUXHM5wQSXh6','admin@gmail.com',_binary '','Leo'),(2,'Cortes','$2a$10$iu5ng8agEWwhfbFveBdUfu3DUGkXtJ1z6P0JGwN/SPghMslrfwIzG','ventanilla@gmail.com',_binary '','Rashid'),(3,'Herrera','$2a$10$nFy.PsCn5DeV0PYOQmg1euebEgDVU64848abq6ZwjN0ngGRG35bCm','user@gmail.com',_binary '','Romario');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trigger_after_insert_users` AFTER INSERT ON `users` FOR EACH ROW BEGIN 
		INSERt INTO binnacle(activity,dateActivity,tableActivity) values (CONCAT('Se registró un usuario nuevo: ', NEW.nombre,' con id: ', NEW.id),now(),"users");
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trigger_after_update_users` AFTER UPDATE ON `users` FOR EACH ROW BEGIN 
		INSERt INTO binnacle(activity,dateActivity,tableActivity) values (CONCAT('Se modificó el usuario ', OLD.id, ' con nombre  ', OLD.nombre," con apellidos ",OLD.apellidos," y correo ",OLD.correo," a nombre: ",NEW.nombre," apellidos: ",NEW.apellidos," correo: ",NEW.correo),now(),"users");
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trigger_after_delete_users` AFTER DELETE ON `users` FOR EACH ROW BEGIN 
		INSERt INTO binnacle(activity,dateActivity,tableActivity) values (CONCAT('Se eliminó un usuario:  ', OLD.nombre,' con id: ', OLD.id),now(),"users");
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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

-- Dump completed on 2022-04-17  0:55:10
