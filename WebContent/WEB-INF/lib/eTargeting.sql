-- phpMyAdmin SQL Dump
-- version 4.2.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost:3306
-- Généré le :  Lun 26 Mai 2014 à 21:46
-- Version du serveur :  5.5.34
-- Version de PHP :  5.5.10

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `eTargeting`
--
CREATE DATABASE IF NOT EXISTS `eTargeting` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `eTargeting`;

-- --------------------------------------------------------

--
-- Structure de la table `campaigns`
--

DROP TABLE IF EXISTS `campaigns`;
CREATE TABLE `campaigns` (
`id` int(5) NOT NULL,
  `name` varchar(255) NOT NULL,
  `from_name` varchar(255) NOT NULL,
  `from_email` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `scheduled_at` datetime DEFAULT NULL,
  `sent_on` datetime DEFAULT NULL,
  `list` int(5) NOT NULL,
  `owner` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `lists`
--

DROP TABLE IF EXISTS `lists`;
CREATE TABLE `lists` (
`id` int(5) NOT NULL,
  `name` varchar(255) NOT NULL,
  `subscriber_ids` text,
  `owner` int(5) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

--
-- Contenu de la table `lists`
--

INSERT INTO `lists` (`id`, `name`, `subscriber_ids`, `owner`) VALUES
(1, 'Newsletter', '38,36', 1),
(2, 'Liste 2', '1,2,3,4,5', 1),
(3, 'Liste 3', '', 1),
(4, 'Liste 4', '1,2,3,4,5', 1),
(5, 'Liste 5', '1,2,3,4,5', 1),
(6, 'Liste 6', '1,2,3,4,5', 1),
(7, 'Liste 7', '1,2,3,4,5', 1),
(8, 'Liste 8', '1,2,3,4,5', 1),
(9, 'Liste 9', '1,2,3,4,5', 1),
(10, 'Liste 10', '1,2,3,4,5', 1),
(11, 'Liste 11', '1,2,3,4,5', 1),
(12, 'Liste 12', '1,2,3,4,5', 1),
(13, 'Liste 13', '1,2,3,4,5', 1),
(14, 'Liste 14', '1,2,3,4,5', 1),
(15, 'Liste 15', '1,2,3,4,5', 1),
(16, 'Liste 16', '1,2,3,4,5', 1),
(17, 'Liste 17', '1,2,3,4,5', 1),
(18, 'Liste 18', '1,2,3,4,5', 1),
(19, 'Liste 19', '1,2,3,4,5', 1),
(20, 'Liste 20', '1,2,3,4,5', 1),
(21, 'Liste 21', '1,2,3,4,5', 1),
(22, 'Liste 22', '1,2,3,4,5', 1),
(23, 'Liste 23', '1,2,3,4,5', 1),
(24, 'Liste 24', '1,2,3,4,5', 1),
(25, 'Liste 25', '1,2,3,4,5', 1),
(26, 'Liste 26', '1,2,3,4,5', 1),
(27, 'Liste 27', '1,2,3,4,5', 1),
(28, 'Liste 28', '1,2,3,4,5', 1),
(29, 'Liste 29', '1,2,3,4,5', 1),
(30, 'Liste 30', '1,2,3,4,5', 1),
(31, 'Liste 31', '1,2,3,4,5', 1),
(32, 'Liste 32', '1,2,3,4,5', 1),
(33, 'Liste 33', '1,2,3,4,5', 1),
(34, 'Liste 34', '1,2,3,4,5', 1),
(35, 'Liste 35', '1,2,3,4,5', 1);

-- --------------------------------------------------------

--
-- Structure de la table `reports`
--

DROP TABLE IF EXISTS `reports`;
CREATE TABLE `reports` (
`id` int(5) NOT NULL,
  `campaign` int(5) NOT NULL,
  `subscriber_id` int(5) NOT NULL,
  `opened` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `subscribers`
--

DROP TABLE IF EXISTS `subscribers`;
CREATE TABLE `subscribers` (
`id` int(5) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `age` int(3) NOT NULL DEFAULT '0',
  `gender` varchar(255) DEFAULT NULL,
  `owner` int(5) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=39 ;

--
-- Contenu de la table `subscribers`
--

INSERT INTO `subscribers` (`id`, `first_name`, `last_name`, `email`, `age`, `gender`, `owner`) VALUES
(1, 'Axel', 'Bouaziz', 'axel.bouaziz@hotmail.fr', 0, 'M', 1),
(2, 'José', 'Albea', 'jose.albea@gmail.com', 22, 'F', 1),
(3, 'Philippe', 'Teisseire', 'phiphi.traing@gmail.com', 23, 'M', 1),
(4, 'Yohann', 'Teisseire', 'yohan.teisseire@gmail.com', 22, 'M', 1),
(5, 'Cadman', 'Mills', 'bibendum.fermentum.metus@fermentum.co.uk', 23, 'M', 1),
(6, 'Blaine', 'Raymond', 'tincidunt.tempus@malesuada.co.uk', 18, 'M', 1),
(7, 'Neve', 'Coleman', 'nulla.vulputate.dui@turpisnecmauris.net', 23, 'F', 1),
(8, 'Anthony', 'Williams', 'Aliquam.vulputate@adipiscingelit.net', 22, 'F', 1),
(9, 'Maisie', 'Rush', 'consequat.purus.Maecenas@tinciduntnibh.com', 21, 'M', 1),
(10, 'Hu', 'Mays', 'luctus.et.ultrices@pede.com', 26, 'M', 1),
(11, 'Harriet', 'Lane', 'Integer@auctorquistristique.co.uk', 22, 'F', 1),
(12, 'Ignacia', 'James', 'risus.Morbi.metus@ut.ca', 25, 'F', 1),
(13, 'Amelia', 'Long', 'metus.sit.amet@sit.co.uk', 25, 'M', 1),
(14, 'Alec', 'Frye', 'vulputate.mauris.sagittis@euismod.net', 21, 'M', 1),
(15, 'Cameron', 'Stein', 'ipsum.non@Aliquamauctorvelit.com', 23, 'F', 1),
(16, 'Charity', 'Jimenez', 'est.Nunc@semut.co.uk', 22, 'F', 1),
(17, 'Kuame', 'Obrien', 'Nulla@maurisrhoncus.co.uk', 25, 'M', 1),
(18, 'Ila', 'Alford', 'parturient.montes@dolorvitae.edu', 24, 'M', 1),
(19, 'Alana', 'Matthews', 'per.inceptos.hymenaeos@natoquepenatibuset.ca', 24, 'F', 1),
(20, 'Igor', 'Kim', 'pretium@velitPellentesqueultricies.edu', 25, 'F', 1),
(21, 'Rahim', 'Aguilar', 'erat@commodohendreritDonec.com', 24, 'M', 1),
(22, 'Lilah', 'Nunez', 'varius.Nam.porttitor@pedeNuncsed.com', 19, 'M', 1),
(23, 'Madeson', 'Robertson', 'vitae.posuere@sitamet.org', 26, 'F', 1),
(24, 'Inga', 'Kelley', 'ipsum.Suspendisse.non@nonluctus.net', 23, 'F', 1),
(25, 'Justin', 'Castaneda', 'dui.augue@bibendumsedest.net', 24, 'M', 1),
(26, 'April', 'Wolfe', 'fringilla@arcu.co.uk', 26, 'M', 1),
(27, 'Amity', 'Cunningham', 'sed.tortor.Integer@commodo.com', 20, 'F', 1),
(28, 'Desirae', 'Vance', 'amet@vitae.edu', 21, 'F', 1),
(29, 'Rana', 'Hampton', 'odio@enimSednulla.edu', 20, 'M', 1),
(30, 'Ashton', 'Hale', 'porttitor.eros@justo.ca', 19, 'M', 1),
(31, 'Cody', 'Graham', 'Curae.Phasellus@sociis.org', 24, 'F', 1),
(32, 'Branden', 'Stanton', 'Phasellus.fermentum.convallis@risusvarius.co.uk', 18, 'F', 1),
(33, 'Julian', 'Roy', 'non.justo.Proin@maurissit.org', 20, 'M', 1),
(34, 'Leah', 'Malone', 'tincidunt.tempus.risus@Cras.ca', 22, 'M', 1),
(35, 'Stephanie', 'Monroe', 'orci.in.consequat@metusVivamuseuismod.ca', 24, 'F', 1),
(36, 'Axel', 'Bouaziz', 'axel.bouaziz@calliweb.fr', 22, 'M', 1),
(37, 'Axel', 'Bouaziz', 'axelll.bouaziz@hotmail.fr', 22, 'M', 1),
(38, 'Axel', 'Bouaziz', 'axel.bouaziz29@gmail.com', 22, 'M', 1);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
`id` int(5) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `first_name`, `last_name`) VALUES
(1, 'axel.bouaziz@hotmail.fr', '0976bdd439161deff5797776ec6873ad', 'Axel', 'Bouaziz'),
(2, 'phiphi.traing@gmail.com', 'ab4f63f9ac65152575886860dde480a1', 'Traing', 'Philippe'),
(3, 'jose.albea@gmail.com', 'ab4f63f9ac65152575886860dde480a1', 'Jos&amp;Atilde;&amp;copy;', 'Albea'),
(4, 'yohan.teisseire@gmail.com', 'ab4f63f9ac65152575886860dde480a1', 'Yohann', 'Teisseire');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `campaigns`
--
ALTER TABLE `campaigns`
 ADD PRIMARY KEY (`id`), ADD KEY `list` (`list`), ADD KEY `owner` (`owner`);

--
-- Index pour la table `lists`
--
ALTER TABLE `lists`
 ADD PRIMARY KEY (`id`), ADD KEY `owner` (`owner`);

--
-- Index pour la table `reports`
--
ALTER TABLE `reports`
 ADD PRIMARY KEY (`id`), ADD KEY `campaign` (`campaign`,`subscriber_id`), ADD KEY `user_id` (`subscriber_id`);

--
-- Index pour la table `subscribers`
--
ALTER TABLE `subscribers`
 ADD PRIMARY KEY (`id`), ADD KEY `owner` (`owner`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `campaigns`
--
ALTER TABLE `campaigns`
MODIFY `id` int(5) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `lists`
--
ALTER TABLE `lists`
MODIFY `id` int(5) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT pour la table `reports`
--
ALTER TABLE `reports`
MODIFY `id` int(5) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `subscribers`
--
ALTER TABLE `subscribers`
MODIFY `id` int(5) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=39;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
MODIFY `id` int(5) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `campaigns`
--
ALTER TABLE `campaigns`
ADD CONSTRAINT `campaigns_ibfk_1` FOREIGN KEY (`list`) REFERENCES `lists` (`id`),
ADD CONSTRAINT `FK_USER_ID` FOREIGN KEY (`owner`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `lists`
--
ALTER TABLE `lists`
ADD CONSTRAINT `lists_ibfk_1` FOREIGN KEY (`owner`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reports`
--
ALTER TABLE `reports`
ADD CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`campaign`) REFERENCES `campaigns` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `reports_ibfk_2` FOREIGN KEY (`subscriber_id`) REFERENCES `subscribers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `subscribers`
--
ALTER TABLE `subscribers`
ADD CONSTRAINT `subscribers_ibfk_1` FOREIGN KEY (`owner`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
SET FOREIGN_KEY_CHECKS=1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
