-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : mar. 03 fév. 2026 à 09:29
-- Version du serveur : 11.3.2-MariaDB
-- Version de PHP : 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `aragorn`
--

-- --------------------------------------------------------

--
-- Structure de la table `armurerie`
--

DROP TABLE IF EXISTS `armurerie`;
CREATE TABLE IF NOT EXISTS `armurerie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `armurerie`
--

INSERT INTO `armurerie` (`id`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `chevalier`
--

DROP TABLE IF EXISTS `chevalier`;
CREATE TABLE IF NOT EXISTS `chevalier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roles` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`roles`)),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `chevalier`
--

INSERT INTO `chevalier` (`id`, `name`, `password`, `roles`) VALUES
(1, 'Arthur', NULL, NULL),
(2, 'Lancelot', NULL, NULL),
(3, 'Perceval', NULL, NULL),
(4, 'Gauvain', NULL, NULL),
(6, 'admin', '$2a$10$Zz7NuVjZ4IhrTs7oUNGKdOvNivkW49HGLGmvMQLxjfzlOeTj4.Y4q', '[\"ROLE_CHEVALIER\", \"ROLE_CHEVALIER\"]'),
(7, 'test', '$2a$10$C8fbSW5qUVfT0swDvkpHJObLXshbTjCN2.fguR.b0BnwHD7a01.06', '[\"ROLE_CHEVALIER\"]');

-- --------------------------------------------------------

--
-- Structure de la table `chevalier_equipements`
--

DROP TABLE IF EXISTS `chevalier_equipements`;
CREATE TABLE IF NOT EXISTS `chevalier_equipements` (
  `chevalier_id` int(11) NOT NULL,
  `equipement_id` int(11) NOT NULL,
  PRIMARY KEY (`chevalier_id`,`equipement_id`),
  UNIQUE KEY `UKajxheoyp4328ac07dce2086tg` (`equipement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `chevalier_equipements`
--

INSERT INTO `chevalier_equipements` (`chevalier_id`, `equipement_id`) VALUES
(1, 1),
(2, 6),
(1, 20),
(3, 27);

-- --------------------------------------------------------

--
-- Structure de la table `equipement`
--

DROP TABLE IF EXISTS `equipement`;
CREATE TABLE IF NOT EXISTS `equipement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `armurerie_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkrb5bhv5bxnip5a7q0g84wqxn` (`armurerie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `equipement`
--

INSERT INTO `equipement` (`id`, `name`, `type`, `armurerie_id`) VALUES
(1, 'Épée longue', 'arme', 1),
(2, 'Épée longue', 'arme', 1),
(3, 'Épée longue', 'arme', 1),
(4, 'Épée longue', 'arme', 1),
(5, 'Épée longue', 'arme', 1),
(6, 'Hache de guerre', 'arme', 1),
(7, 'Hache de guerre', 'arme', 1),
(8, 'Hache de guerre', 'arme', 1),
(9, 'Bouclier en bois', 'defense', 1),
(10, 'Bouclier en bois', 'defense', 1),
(11, 'Bouclier en bois', 'defense', 1),
(12, 'Bouclier en bois', 'defense', 1),
(13, 'Bouclier en bois', 'defense', 1),
(14, 'Bouclier en bois', 'defense', 1),
(15, 'Bouclier en bois', 'defense', 1),
(16, 'Cotte de maille', 'defense', 1),
(17, 'Cotte de maille', 'defense', 1),
(18, 'Cotte de maille', 'defense', 1),
(19, 'Cotte de maille', 'defense', 1),
(20, 'Casque en fer', 'defense', 1),
(21, 'Casque en fer', 'defense', 1),
(22, 'Casque en fer', 'defense', 1),
(23, 'Casque en fer', 'defense', 1),
(24, 'Casque en fer', 'defense', 1),
(25, 'Casque en fer', 'defense', 1),
(26, 'Dague', 'arme', 1),
(27, 'Dague', 'arme', 1);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `chevalier_equipements`
--
ALTER TABLE `chevalier_equipements`
  ADD CONSTRAINT `FK51dg0yavsfw3jh5wye7dtlmko` FOREIGN KEY (`equipement_id`) REFERENCES `equipement` (`id`),
  ADD CONSTRAINT `FKaf3du2wga0idjji0289l2vr79` FOREIGN KEY (`chevalier_id`) REFERENCES `chevalier` (`id`);

--
-- Contraintes pour la table `equipement`
--
ALTER TABLE `equipement`
  ADD CONSTRAINT `FKkrb5bhv5bxnip5a7q0g84wqxn` FOREIGN KEY (`armurerie_id`) REFERENCES `armurerie` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
