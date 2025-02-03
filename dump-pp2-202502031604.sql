/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.11.9-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: pp2
-- ------------------------------------------------------
-- Server version	10.11.9-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Medicament`
--

DROP TABLE IF EXISTS `Medicament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Medicament` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `substance_active` varchar(100) DEFAULT NULL,
  `dose_maximale_journalière` float DEFAULT NULL,
  `dose_mortelle` float DEFAULT NULL,
  `populations_cibles` text DEFAULT NULL,
  `populations_contre_indiquees` text DEFAULT NULL,
  `frequence_maximale` varchar(50) DEFAULT NULL,
  `duree_maximale_traitement` int(11) DEFAULT NULL,
  `effets_secondaires` text DEFAULT NULL,
  `interactions` text DEFAULT NULL,
  `forme` varchar(50) DEFAULT NULL,
  `stock_disponible` int(11) DEFAULT NULL,
  `date_expiration` date DEFAULT NULL,
  `prix_unitaire` float DEFAULT NULL,
  `fournisseur` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Medicament`
--

LOCK TABLES `Medicament` WRITE;
/*!40000 ALTER TABLE `Medicament` DISABLE KEYS */;
INSERT INTO `Medicament` VALUES
(1,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(2,'Ibuprofène','Ibuprofen',2400,8000,'Adultes','Femmes enceintes','3 fois par jour',7,'Maux d\'estomac, vertiges','Aspirine','Gelule',150,'2026-05-15',3,'Pfizer'),
(3,'Amoxicilline','Amoxicillin',3000,7000,'Infections bactériennes','Allergiques aux pénicillines','3 fois par jour',14,'Diarrhées, réactions allergiques','Méthotrexate','Sirop',100,'2024-11-30',4.5,'GSK'),
(4,'Oméprazole','Omeprazole',40,200,'Reflux gastriques','Insuffisants rénaux','1 fois par jour',14,'Maux de tête, diarrhée','Clopidogrel','Capsule',80,'2025-08-20',6,'AstraZeneca'),
(5,'Levothyrox','Levothyroxine',200,800,'Hypothyroïdie','Hyperthyroïdie','1 fois par jour',30,'Tachycardie, anxiété','Anticoagulants','Comprimé',120,'2026-10-10',5.5,'Merck'),
(6,'Metformine','Metformin',2000,5000,'Diabète de type 2','Insuffisants rénaux sévères','2 fois par jour',90,'Nausées, ballonnements','Alcool','Comprimé',300,'2025-04-12',3.8,'Novartis'),
(7,'Atorvastatine','Atorvastatin',80,200,'Hypercholestérolémie','Maladies hépatiques','1 fois par jour',30,'Myalgies, fatigue','Antifongiques','Comprimé',90,'2025-07-01',7,'Pfizer'),
(8,'Aspirine','Acide acétylsalicylique',3000,7000,'Douleurs et fièvre','Ulceres gastriques','3 fois par jour',10,'Hémorragies, maux d\'estomac','Ibuprofène','Comprimé',250,'2025-09-18',2,'Bayer'),
(9,'Zolpidem','Zolpidem',10,50,'Insomnies','Apnée du sommeil','1 fois par jour',7,'Somnolence, troubles de mémoire','Alcool','Comprimé',60,'2024-12-15',8.5,'Sanofi'),
(10,'Clopidogrel','Clopidogrel',75,300,'Prévention AVC','Hémophiles','1 fois par jour',30,'Hémorragies, éruptions cutanées','Oméprazole','Comprimé',100,'2026-01-01',6.8,'Bristol-Myers Squibb'),
(11,'Doliprane',NULL,0,0,NULL,NULL,NULL,0,NULL,NULL,NULL,0,NULL,0,NULL),
(12,'Doliprane','Paracetamol',0,0,NULL,NULL,NULL,0,NULL,NULL,NULL,0,NULL,0,NULL),
(13,'Doliprane','Paracetamol',0,0,NULL,NULL,NULL,0,NULL,NULL,NULL,0,NULL,0,NULL),
(14,'Doliprane','Paracetamol',4,0,NULL,NULL,NULL,0,NULL,NULL,NULL,0,NULL,0,NULL),
(15,'Paracétamol','Paracétamol',0,0,NULL,NULL,NULL,0,NULL,NULL,NULL,100,NULL,5,NULL),
(16,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(17,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(18,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(19,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(20,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(21,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(22,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(23,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(24,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(25,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(26,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(27,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(28,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(29,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(30,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(31,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(32,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi'),
(33,'Paracétamol','Paracetamol',4000,10000,'Adultes et enfants','Insuffisants hépatiques','4 fois par jour',10,'Nausées, éruptions cutanées','Alcool','Comprimé',200,'2025-12-31',2.5,'Laboratoires Sanofi');
/*!40000 ALTER TABLE `Medicament` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Patients`
--

DROP TABLE IF EXISTS `Patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Patients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `date_naissance` date DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `date_enregistrement` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Patients`
--

LOCK TABLES `Patients` WRITE;
/*!40000 ALTER TABLE `Patients` DISABLE KEYS */;
INSERT INTO `Patients` VALUES
(1,'Dupont','Jean','1985-06-15','0612345678','jean.dupont@example.com','2025-01-22 12:36:12'),
(2,'Martin','Claire','1990-03-22','0623456789','claire.martin@example.com','2025-01-22 12:36:12'),
(3,'Bernard','Luc','1978-11-09','0634567890','luc.bernard@example.com','2025-01-22 12:36:12'),
(4,'Robert','Sophie','1987-01-19','0645678901','sophie.robert@example.com','2025-01-22 12:36:12'),
(5,'Petit','Julien','2000-07-12','0656789012','julien.petit@example.com','2025-01-22 12:36:12'),
(6,'Durand','Marie','1995-09-05','0667890123','marie.durand@example.com','2025-01-22 12:36:12'),
(7,'Lemoine','Paul','1982-02-28','0678901234','paul.lemoine@example.com','2025-01-22 12:36:12'),
(8,'Garcia','Laura','1999-12-25','0689012345','laura.garcia@example.com','2025-01-22 12:36:12'),
(9,'Moreau','Nicolas','1983-04-13','0690123456','nicolas.moreau@example.com','2025-01-22 12:36:12'),
(10,'Simon','Emma','1996-08-18','0611122233','emma.simon@example.com','2025-01-22 12:36:12');
/*!40000 ALTER TABLE `Patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rendezvous`
--

DROP TABLE IF EXISTS `Rendezvous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rendezvous` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `utilisateur_id` int(11) NOT NULL,
  `date_rendezvous` datetime NOT NULL,
  `motif` text DEFAULT NULL,
  `etat` enum('planifié','effectué','annulé') DEFAULT NULL,
  `date_creation` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rendezvous`
--

LOCK TABLES `Rendezvous` WRITE;
/*!40000 ALTER TABLE `Rendezvous` DISABLE KEYS */;
INSERT INTO `Rendezvous` VALUES
(1,1,2,'2025-02-01 09:00:00','Consultation générale','planifié','2025-01-22 12:37:30'),
(2,2,2,'2025-02-01 10:00:00','Fièvre et toux','planifié','2025-01-22 12:37:30'),
(3,3,5,'2025-02-02 11:00:00','Suivi post-opératoire','effectué','2025-01-22 12:37:30'),
(4,4,7,'2025-02-02 14:00:00','Problèmes de dos','annulé','2025-01-22 12:37:30'),
(5,5,2,'2025-02-03 09:00:00','Vaccination','planifié','2025-01-22 12:37:30'),
(6,6,10,'2025-02-03 11:30:00','Problèmes de sommeil','effectué','2025-01-22 12:37:30'),
(7,7,10,'2025-02-04 10:00:00','Douleurs articulaires','planifié','2025-01-22 12:37:30'),
(8,8,7,'2025-02-04 15:00:00','Renouvellement ordonnance','planifié','2025-01-22 12:37:30'),
(9,9,10,'2025-02-05 09:30:00','Contrôle annuel','planifié','2025-01-22 12:37:30'),
(10,10,4,'2025-02-05 11:00:00','Consultation nutrition','planifié','2025-01-22 12:37:30');
/*!40000 ALTER TABLE `Rendezvous` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Traitements`
--

DROP TABLE IF EXISTS `Traitements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Traitements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `medicament_id` int(11) NOT NULL,
  `duree_jours` int(11) DEFAULT NULL,
  `dosage` float DEFAULT NULL,
  `frequence` varchar(50) DEFAULT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `recommandations` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `medicament_id` (`medicament_id`),
  CONSTRAINT `Traitements_ibfk_1` FOREIGN KEY (`medicament_id`) REFERENCES `Medicament` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Traitements`
--

LOCK TABLES `Traitements` WRITE;
/*!40000 ALTER TABLE `Traitements` DISABLE KEYS */;
INSERT INTO `Traitements` VALUES
(1,1,1,7,1000,'2 fois par jour','2025-01-01','2025-01-07','Prendre après les repas'),
(2,2,2,5,600,'3 fois par jour','2025-01-02','2025-01-06','Éviter les antiacides'),
(3,3,3,14,500,'2 fois par jour','2025-01-03','2025-01-16','Prendre avec beaucoup d\'eau'),
(4,4,5,30,75,'1 fois par jour','2025-01-04','2025-02-02','À jeun'),
(5,5,4,10,20,'1 fois par jour','2025-01-05','2025-01-14','Éviter les repas lourds'),
(6,6,6,90,850,'2 fois par jour','2025-01-06','2025-04-05','Contrôler la glycémie régulièrement'),
(7,7,7,7,300,'3 fois par jour','2025-01-07','2025-01-13','Prendre après les repas'),
(8,8,8,14,10,'1 fois par jour','2025-01-08','2025-01-21','Ne pas conduire après usage'),
(9,9,9,30,75,'1 fois par jour','2025-01-09','2025-02-07','Éviter le pamplemousse'),
(10,10,10,7,1000,'4 fois par jour','2025-01-10','2025-01-16','Éviter l\'alcool pendant le traitement');
/*!40000 ALTER TABLE `Traitements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Utilisateurs`
--

DROP TABLE IF EXISTS `Utilisateurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Utilisateurs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `role` enum('secretaire','medecin','admin') NOT NULL,
  `date_creation` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Utilisateurs`
--

LOCK TABLES `Utilisateurs` WRITE;
/*!40000 ALTER TABLE `Utilisateurs` DISABLE KEYS */;
INSERT INTO `Utilisateurs` VALUES
(1,'MonSuperMotDePasse123!','Alice','alice.medina@example.com','23d4f3b8c2072664030e31179793755dcbab164306841a2d9bdb7d4541d3e6eb','secretaire','2025-01-22 12:36:19'),
(2,'Blanc','David','david.blanc@example.com','hashed_password_2','medecin','2025-01-22 12:36:19'),
(3,'Rousseau','Elodie','elodie.rousseau@example.com','hashed_password_3','admin','2025-01-22 12:36:19'),
(4,'Morel','Thomas','thomas.morel@example.com','hashed_password_4','secretaire','2025-01-22 12:36:19'),
(5,'Dubois','Julie','julie.dubois@example.com','hashed_password_5','medecin','2025-01-22 12:36:19'),
(6,'Fontaine','Hugo','hugo.fontaine@example.com','hashed_password_6','admin','2025-01-22 12:36:19'),
(7,'Perrot','Léa','lea.perrot@example.com','hashed_password_7','medecin','2025-01-22 12:36:19'),
(8,'Leclerc','Mathieu','mathieu.leclerc@example.com','hashed_password_8','secretaire','2025-01-22 12:36:19'),
(9,'Clément','Isabelle','isabelle.clement@example.com','hashed_password_9','admin','2025-01-22 12:36:19'),
(10,'Lopez','Antoine','antoine.lopez@example.com','hashed_password_10','medecin','2025-01-22 12:36:19');
/*!40000 ALTER TABLE `Utilisateurs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'pp2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-03 16:04:27
