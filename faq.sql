-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:8889
-- Généré le :  Jeu 06 Février 2020 à 16:35
-- Version du serveur :  5.5.42
-- Version de PHP :  7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `faq`
--

-- --------------------------------------------------------

--
-- Structure de la table `Category`
--

CREATE TABLE `Category` (
  `codeCategory` int(100) DEFAULT NULL,
  `libelle` varchar(50) NOT NULL,
  `idCategory` int(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Category`
--

INSERT INTO `Category` (`codeCategory`, `libelle`, `idCategory`) VALUES
(234, 'Science', 1),
(2, 'femme enceinte', 2),
(65, 'films', 3);

-- --------------------------------------------------------

--
-- Structure de la table `QuestionAnswer`
--

CREATE TABLE `QuestionAnswer` (
  `question` text NOT NULL,
  `reponse` text NOT NULL,
  `date` date NOT NULL,
  `idqa` int(100) NOT NULL,
  `user_id` int(100) NOT NULL,
  `category_id` int(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `QuestionAnswer`
--

INSERT INTO `QuestionAnswer` (`question`, `reponse`, `date`, `idqa`, `user_id`, `category_id`) VALUES
('peux-t-on manger de la glace etant enceinte?', 'non', '2020-06-02', 2, 1, 2),
('qui a invente l''avion', 'les freres WRIGHT', '2020-06-02', 3, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `USERBO`
--

CREATE TABLE `USERBO` (
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `mail` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `idUser` int(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `USERBO`
--

INSERT INTO `USERBO` (`nom`, `prenom`, `mail`, `password`, `idUser`) VALUES
('Niang', 'Ibou', 'ibou@con.com', 'passer', 1),
('Fall', 'Mane', 'mane@esp.sn', 'passer', 2),
('Fall', 'Mountakha', 'takha@lamp.sn', 'passer', 3),
('admin', 'admin', 'admin', 'admin', 4);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Category`
--
ALTER TABLE `Category`
  ADD PRIMARY KEY (`idCategory`);

--
-- Index pour la table `QuestionAnswer`
--
ALTER TABLE `QuestionAnswer`
  ADD PRIMARY KEY (`idqa`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Index pour la table `USERBO`
--
ALTER TABLE `USERBO`
  ADD PRIMARY KEY (`idUser`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Category`
--
ALTER TABLE `Category`
  MODIFY `idCategory` int(100) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `QuestionAnswer`
--
ALTER TABLE `QuestionAnswer`
  MODIFY `idqa` int(100) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `USERBO`
--
ALTER TABLE `USERBO`
  MODIFY `idUser` int(100) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `QuestionAnswer`
--
ALTER TABLE `QuestionAnswer`
  ADD CONSTRAINT `questionanswer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `UserBo` (`idUser`),
  ADD CONSTRAINT `questionanswer_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `Category` (`idCategory`);
