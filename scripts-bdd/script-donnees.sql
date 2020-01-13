-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  ven. 10 jan. 2020 à 15:14
-- Version du serveur :  10.4.6-MariaDB
-- Version de PHP :  7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projet_6_site_com_esc`
--

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id`, `id_site`, `contenu`, `prenom_auteur`, `nom_auteur`) VALUES
(41, 2, 'Commentaire Courchon 1', 'Romain', 'Demellier'),
(43, 2, 'Commentaire Courchon 3', 'Muriel', 'Demellier'),
(46, 2, 'Commentaire Xavier', 'Xavier', 'Dumazel'),
(54, 1, 'Commentaire modifié remodifié par Muriel modif jquery', 'Romain', 'Demellier'),
(59, 1, 'Marche jquery', 'Romain', 'Demellier');

--
-- Déchargement des données de la table `cotations`
--

INSERT INTO `cotations` (`difficulte`) VALUES
('Débutant'),
('Débutant à Moyen'),
('Expert'),
('Moyen'),
('Moyen à Expert'),
('Tous Niveaux');

--
-- Déchargement des données de la table `departement`
--

INSERT INTO `departement` (`id`, `nom`) VALUES
(1, 'Alpes-de-Haute-Provence'),
(2, 'Bouches-du-Rhône'),
(3, 'Finistère'),
(4, 'La Réunion');

--
-- Déchargement des données de la table `pays`
--

INSERT INTO `pays` (`id`, `nom`, `id_pays`) VALUES
(1, 'France', 0),
(2, 'Allemagne', 0),
(3, 'Italie', 0),
(4, 'Autriche', 0);

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id`, `date_debut`, `date_fin`, `emprunteur_id`, `emprunteur_nom`, `emprunteur_prenom`, `topo_id`) VALUES
(2, '2019-12-30', '2019-12-30', 14, 'Demellier', 'Muriel', 4),
(3, '2019-12-30', NULL, 14, 'Demellier', 'Muriel', 5),
(4, '2019-12-30', NULL, 18, 'Dumazel', 'Xavier', 11),
(5, '2020-01-02', NULL, 18, 'Dumazel', 'Xavier', 4);

--
-- Déchargement des données de la table `site`
--

INSERT INTO `site` (`id`, `nom`, `type`, `hauteur`, `orientation`, `voies`, `cotations`, `departement`, `pays`, `id_utilisateur`, `tag`) VALUES
(1, 'Annot', 'Bloc et Falaise', '25m', 'Toutes', '400 voies', 'Moyen à Expert', 'Alpes-de-Haute-Provence', 'France', 1, b'1'),
(2, 'Courchon', 'Falaise', '35m', 'Sud, Sud-Ouest', '80 voies', 'Moyen à Expert', 'Alpes-de-Haute-Provence', 'France', 1, b'1'),
(3, 'Le Furgeret', 'Bloc', '10 à 12m', 'Toutes', 'Environs 1600', 'Tous Niveaux', 'Alpes-de-Haute-Provence', 'France', 1, b'0'),
(4, 'Le Cap Canaille', 'Falaise', '240m', 'Ouest', '50 voies', 'Débutant à Moyen', 'Bouches-du-Rhône', 'France', 1, b'0'),
(5, 'Le Grand Vallon', 'Falaise', '45m', 'Toutes', '140 voies', 'Tous Niveaux', 'Bouches-du-Rhône', 'France', 1, b'0'),
(6, 'Les Calanques', 'Falaise', 'De 10 à 100m', 'Toutes', '3000 voies', 'Tous Niveaux', 'Bouches-du-Rhône', 'France', 1, b'1'),
(7, 'Kerlouan', 'Bloc', '10m', 'Toutes', 'plus de 1000', 'Tous Niveaux', 'Finistère', 'France', 1, b'0'),
(8, 'Pen-Hir', 'Falaise', '80m', 'Ouest', 'Au moins 130', 'Débutant à Moyen', 'Finistère', 'France', 1, b'0'),
(14, 'La Roche', 'Falaise', '30m', 'Ouest', '52', 'Moyen à Expert', 'Alpes-de-Haute-Provence', 'France', 1, b'0'),
(15, 'Mont-Servin', 'Bloc', '30m', 'Ouest', '52', 'Débutant à Moyen', 'Alpes-de-Haute-Provence', 'France', 1, b'0'),
(16, 'Plomeur', 'Bloc et Falaise', '15m', 'Sud-est', '2', 'Débutant à Moyen', 'Finistère', 'France', 1, b'0');

--
-- Déchargement des données de la table `topo`
--

INSERT INTO `topo` (`id`, `date_parution`, `description`, `lieu`, `nom`, `statut`, `auteur_id`, `emprunteur_id`) VALUES
(4, '2019-12-11', 'Un petit secteur avec quelques beaux passages. Idéal pour ceux qui habitent\r\ndans le coin ! Il n’y a pas de marche d’approche et vous pouvez y aller en\r\nfamille. Des secteurs à l’ombre pour grimper tout au long de la journée.', 'La Réunion', 'Ravine Claude', 'Réservé', 12, 18),
(5, '2019-12-11', 'Secteur Terra luna', 'La Réunion', 'Ravine des avirons', 'Réservé', 12, 14),
(6, '2019-12-11', 'La ravine Bernica recèle de nombreux passages\r\ndans tout les niveaux, plutôt dans le 5 et le 6\r\npour le moment. Attention toutefois les réceptions\r\nsont souvent chaotiques et nécessitent un bon\r\nagencement de crashpad ainsi que de bons\r\npareurs.', 'La Réunion', 'Ravine Bernica', 'Disponible', 14, NULL),
(7, '2019-12-11', 'Voici le topo du secteur « le Bloc » à Bassin Plat …. ainsi nommé car il y a UN bloc. Pour s’y rendre c’est facile : vous descendez la grande echelle qui mène aux voies d’escalades, puis continuez à descendre. Le bloc est 20m en amont.', 'La Réunion', 'Bassin Plat', 'Demande de réservation', 14, 18),
(8, '2019-12-11', 'Un nouveau site de bloc a été ouvert par le club 7ALOUEST à Grand Etang, dans les hauts de Saint Leu.', 'La Réunion', 'Ravine Grand Étang', 'Disponible', 13, NULL),
(9, '2019-12-11', 'SECTEUR « PEAU DE LOURS », « BALEINE » ET « HOME SWEET HOME »', 'La Réunion', 'RAVINE GRAND ETANG LES BAS', 'Disponible', 16, NULL),
(10, '2019-12-11', 'Site très sympa. Familial.', 'La Réunion', 'Roche Paté', 'Disponible', 12, NULL),
(11, '2019-12-11', 'Très beau site', 'La Roche', 'Site de la Roche', 'Réservé', 12, 18),
(12, '2019-12-11', 'Un site sympa. Beau panorama.', 'Valcenis', 'Mont-Servin', 'Disponible', 12, NULL),
(19, '2019-12-11', 'Site très agréable. Idéal pour toute la famille.', 'Aurillac', 'Le Puy', 'Disponible', 12, NULL);

--
-- Déchargement des données de la table `type`
--

INSERT INTO `type` (`nom`) VALUES
('Bloc'),
('Bloc et Falaise'),
('Falaise');

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `nom`, `prenom`, `email`, `password`, `role`) VALUES
(12, 'Demellier', 'Romain', 'romain.demellier@gmail.com', '$2a$10$GKD0cWhtYwaSqjgFS/YOUON4RIg64MaUwziQg7vdMkxBqEIu03wQ6', 'SUPER_MEMBER'),
(13, 'Demellier', 'Daniel', 'daniel.demellier@gmail.com', '$2a$10$keEt9/7mnOVHK8vuG/Zh..ZzBBl1vlOO/9IfcvwMyobsOne70Bfmy', 'MEMBER'),
(14, 'Demellier', 'Muriel', 'muriel.demellier@gmail.com', '$2a$10$r2bCfwpglamCvGOyiu9yD.tkwtAox3YubLdCsFsT29aPveV5xIycm', 'MEMBER'),
(15, 'Demellier', 'Antoine', 'antoine.demellier@gmail.com', '$2a$10$.GTm76.aIzsqkT0onZ9faegB/Rea6m8vxMUmMD5RrZWRj.d4xx7LW', 'USER'),
(16, 'Barnett', 'Stan', 'stan.barnett@gmail.com', '$2a$10$pZGAUSAut69VKATdfzkGY.m11EwBOaGCxObd4N8xHc9LM72r7DC0e', 'USER'),
(17, 'Cadiere', 'Alex', 'alex.cadiere@gmail.com', '$2a$10$9mzFEICAg0Zw9z3h29./i.TPSEOsBHIxnX0P6wIh7SIoOMPk3w136', 'USER'),
(18, 'Dumazel', 'Xavier', 'xavier.dumazel@gmail.com', '$2a$10$Sgc4A17EEIk2dCQwpQ/ur.Oq5UZQZivzLrF9QxmWV5rAIe/emljp6', 'USER'),
(19, 'Bance', 'Flora', 'flora.bance@gmail.com', '$2a$10$ppLR5PJWTk9nQQwa2bH45O4hd6IVWz9ZiLpbeaIA3PAyxL98.je42', 'USER');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
