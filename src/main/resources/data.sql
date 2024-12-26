
-- Insertion des rôles
INSERT INTO Role (nom) VALUES ('ADMIN');
INSERT INTO Role (nom) VALUES ('USER');
INSERT INTO Role (nom) VALUES ('ADMINCOMPAGNIE');
INSERT INTO Role (nom) VALUES ('PERSONNEL');

-- Insertion des personnes
INSERT INTO Personne (nom, prenom, email, password, numero_de_telephone, adresse, role_id)
VALUES ('Dupont', 'Jean', 'jean.dupont@example.com', '$2a$12$vDyegFIXdomnwp.zcE3fOOpOz0c585DC5vg/LWfpzDp0uBrymhnra', '0123456789', '1', (SELECT id FROM Role WHERE nom = 'ADMIN'));
INSERT INTO Personne (nom, prenom, email, password, numero_de_telephone, adresse, role_id)
VALUES ('Traore', 'Papou', 'papaou1.dupont@example.com', '$2a$12$vDyegFIXdomnwp.zcE3fOOpOz0c585DC5vg/LWfpzDp0uBrymhnra', '0123456789', 'Hamdallaye ACI 2000', (SELECT id FROM Role WHERE nom = 'PERSONNEL'));
INSERT INTO Personne (nom, prenom, email, password, numero_de_telephone, adresse, role_id)
VALUES ('Smith', 'John', 'john.smith@example.com', '$2a$12$vDyegFIXdomnwp.zcE3fOOpOz0c585DC5vg/LWfpzDp0uBrymhnra', '0987654321', '2 Heathrow Road', (SELECT id FROM Role WHERE nom = 'USER'));
INSERT INTO Personne (nom, prenom, email, password, numero_de_telephone, adresse, role_id)
VALUES ('Issa', 'Liam', 'lol@example.com', '$2a$12$vDyegFIXdomnwp.zcE3fOOpOz0c585DC5vg/LWfpzDp0uBrymhnra', '0987654321', 'Bamako Lafiabougou', (SELECT id FROM Role WHERE nom = 'USER'));
INSERT INTO Personne (nom, prenom, email, password, numero_de_telephone, adresse, role_id)
VALUES ('Mamadou', 'Thiam', 'lol1@example.com', '$2a$12$vDyegFIXdomnwp.zcE3fOOpOz0c585DC5vg/LWfpzDp0uBrymhnra', '0987654321', 'Hamdallaye ACI', (SELECT id FROM Role WHERE nom = 'USER'));
INSERT INTO Personne (nom, prenom, email, password, numero_de_telephone, adresse, role_id)
VALUES ('Tienou', 'Ali', 'lol2@example.com', '$2a$12$vDyegFIXdomnwp.zcE3fOOpOz0c585DC5vg/LWfpzDp0uBrymhnra', '0987654321', 'Banconi', (SELECT id FROM Role WHERE nom = 'PERSONNEL'));
INSERT INTO Personne (nom, prenom, email, password, numero_de_telephone, adresse, role_id)
VALUES ('Lead', 'Lo', 'lol67@example.com', '$2a$12$vDyegFIXdomnwp.zcE3fOOpOz0c585DC5vg/LWfpzDp0uBrymhnra', '0987654321', 'Banconi1', (SELECT id FROM Role WHERE nom = 'ADMINCOMPAGNIE'));
INSERT INTO Personne (nom, prenom, email, password, numero_de_telephone, adresse, role_id)
VALUES ('Bamadou', 'Traore', 'mamadou@gmail.com', '$2a$12$vDyegFIXdomnwp.zcE3fOOpOz0c585DC5vg/LWfpzDp0uBrymhnra', '0987654321', 'Banconi1', (SELECT id FROM Role WHERE nom = 'ADMINCOMPAGNIE'));
INSERT INTO Personne (nom, prenom, email, password, numero_de_telephone, adresse, role_id)
VALUES ('Babou', 'Traore', 'babou@example.com', '$2a$12$vDyegFIXdomnwp.zcE3fOOpOz0c585DC5vg/LWfpzDp0uBrymhnra', '0987654321', 'Bamako Lafiabougou', (SELECT id FROM Role WHERE nom = 'ADMINCOMPAGNIE'));
INSERT INTO Personne (nom, prenom, email, password, numero_de_telephone, adresse, role_id)
VALUES ('Madel', 'Tito', 'madel.tito@example.com', '$2a$12$vDyegFIXdomnwp.zcE3fOOpOz0c585DC5vg/LWfpzDp0uBrymhnra', '2237665478', 'Bamako Lafiabougou', (SELECT id FROM Role WHERE nom = 'ADMIN'));


-- Insertion des super administrateurs
INSERT INTO super_admin (id,pseudo) VALUES ((SELECT id FROM personne WHERE email = 'jean.dupont@example.com'),'superadmin1');
INSERT INTO super_admin (id,pseudo) VALUES ((SELECT id FROM personne WHERE email = 'madel.tito@example.com'),'madelTito');

-- Insertion des compagnies
INSERT INTO Compagnie (matricule, nom, logo_url, codeiata, codeicao, numero_telephone, email, site_web, numero_licence, super_admin_id, is_locked)
VALUES ('AF123', 'Air France', 'airfrance_logo.png', 123, 'AFR', '014567890', 'contact@airfrance.com', 'www.airfrance.com', 'AF123456', (SELECT id FROM super_admin WHERE pseudo = 'superadmin1'), false);

INSERT INTO Compagnie (matricule, nom, logo_url, codeiata, codeicao, numero_telephone, email, site_web, numero_licence, super_admin_id, is_locked)
VALUES ('BA456', 'British Airways', 'britishairways_logo.png', 456, 'BAW', '098765432', 'contact@britishairways.com', 'www.britishairways.com', 'BA456789', (SELECT id FROM super_admin WHERE pseudo = 'superadmin1'), false);


-- Insertion des administrateurs de compagnies
INSERT INTO admin_compagnie (id, pseudo, compagnie_id, superadmin_id) VALUES ((SELECT id FROM personne WHERE nom = 'Lead'),'admin1c1', (SELECT id FROM Compagnie WHERE matricule = 'AF123'), (SELECT id FROM super_admin WHERE pseudo = 'superadmin1'));
-- Insertion des administrateurs de compagnies
INSERT INTO admin_compagnie (id, pseudo, compagnie_id, superadmin_id) VALUES ((SELECT id FROM personne WHERE nom = 'Bamadou'),'admin2c1', (SELECT id FROM Compagnie WHERE matricule = 'AF123'), (SELECT id FROM super_admin WHERE pseudo = 'superadmin1'));
-- Insertion des administrateurs de compagnies
INSERT INTO admin_compagnie (id, pseudo, compagnie_id, superadmin_id) VALUES ((SELECT id FROM personne WHERE nom = 'Babou'),'admin1c2', (SELECT id FROM Compagnie WHERE matricule = 'BA456'), (SELECT id FROM super_admin WHERE pseudo = 'superadmin1'));


-- Insertion des pays
INSERT INTO Pays (nom, admin_compagnie_id, pays_image_url) VALUES ('France', (SELECT id FROM personne WHERE nom = 'Lead'),'https://upload.wikimedia.org/wikipedia/commons/6/62/Flag_of_France.png');
INSERT INTO Pays (nom, admin_compagnie_id, pays_image_url) VALUES ('United Kingdom', (SELECT id FROM personne WHERE nom = 'Lead'),'https://upload.wikimedia.org/wikipedia/commons/8/83/Flag_of_the_United_Kingdom_%283-5%29.svg');
INSERT INTO Pays (nom, admin_compagnie_id, pays_image_url) VALUES ('Italy', (SELECT id FROM personne WHERE nom = 'Lead'),'https://img.freepik.com/vecteurs-libre/illustration-du-drapeau-italie_53876-27098.jpg?t=st=1723642493~exp=1723646093~hmac=1464f4ac8cd9ccfe7c409f9ff89db626b579123cf0a5b9a22d0aa4739dbea5ca&w=900');

-- Insertion des villes
INSERT INTO Ville (nom, pays_id, admin_compagnie_id)
VALUES ('Paris', (SELECT id FROM Pays WHERE nom = 'France'), (SELECT id FROM personne WHERE nom = 'Lead'));
INSERT INTO Ville (nom, pays_id, admin_compagnie_id)
VALUES ('London', (SELECT id FROM Pays WHERE nom = 'United Kingdom'), (SELECT id FROM personne WHERE nom = 'Lead'));
INSERT INTO Ville (nom, pays_id, admin_compagnie_id)
VALUES ('Rome', (SELECT id FROM Pays WHERE nom = 'Italy'), (SELECT id FROM personne WHERE nom = 'Lead'));




-- Insertion des aéroports
INSERT INTO Aeroport (nom, codeiata, longitude, latitude, altitude, capacite_parking, nombre_de_pistes, ville_id, admin_compagnie_id)
VALUES ('Charles de Gaulle', 'CDG', 2.55, 49.01, 120, 5000, 4, (SELECT id FROM Ville WHERE nom = 'Paris'), (SELECT id FROM personne WHERE nom = 'Lead'));

INSERT INTO Aeroport (nom, codeiata, longitude, latitude, altitude, capacite_parking, nombre_de_pistes, ville_id, admin_compagnie_id)
VALUES ('Heathrow', 'LHR', -0.45, 51.47, 25, 6000, 2, (SELECT id FROM Ville WHERE nom = 'London'), (SELECT id FROM personne WHERE nom = 'Lead'));

INSERT INTO Aeroport (nom, codeiata, longitude, latitude, altitude, capacite_parking, nombre_de_pistes, ville_id, admin_compagnie_id)
VALUES ('Fiumicino', 'FCO', 12.25, 41.80, 3, 4000, 3, (SELECT id FROM Ville WHERE nom = 'Rome'), (SELECT id FROM personne WHERE nom = 'Lead'));

-- Insertion des sieges
INSERT INTO classe_siege(nom) VALUES ('ECONOMIQUE');
INSERT INTO classe_siege(nom) VALUES ('AFFAIRE');
INSERT INTO classe_siege(nom) VALUES ('BUISSNESS');

INSERT INTO `position_siege` (`id`, `nom`, `tarif`, `classe_siege_id`) VALUES
(14, 'MILIEU', 2000, 1),
(15, 'HUBLOT', 5000, 2),
(16, 'COULOIR', 2000, 1),
(17, 'HUBLOT', 5000, 2),
(18, 'COULOIR', 2000, 1),
(19, 'HUBLOT', 5000000, 1),
(20, 'MILIEU', 5000000, 1),
(21, 'MILIEU', 55000, 1),
(22, 'MILIEU', 55000, 1),
(23, 'MILIEU', 55000, 1),
(24, 'MILIEU', 55000, 1),
(25, 'MILIEU', 55000, 1),
(26, 'MILIEU', 55000, 1),
(27, 'MILIEU', 55000, 1),
(28, 'MILIEU', 55000, 1),
(29, 'MILIEU', 55000, 1),
(30, 'MILIEU', 55000, 1),
(31, 'MILIEU', 55000, 1),
(32, 'HUBLOT', 55000, 1),
(33, 'COULOIR', 55000, 1),
(34, 'HUBLOT', 55000, 1),
(35, 'HUBLOT', 55000, 1),
(36, 'HUBLOT', 55000, 1),
(37, 'HUBLOT', 55000, 1),
(38, 'HUBLOT', 55000, 1),
(39, 'HUBLOT', 55000, 1),
(40, 'HUBLOT', 55000, 1),
(41, 'HUBLOT', 55000, 1),
(42, 'HUBLOT', 55000, 1),
(43, 'COULOIR', 55000, 1),
(44, 'COULOIR', 55000, 1),
(45, 'COULOIR', 55000, 1),
(46, 'COULOIR', 55000, 1),
(47, 'COULOIR', 55000, 1),
(48, 'COULOIR', 55000, 1),
(49, 'COULOIR', 55000, 1),
(50, 'COULOIR', 55000, 1),
(51, 'COULOIR', 55000, 1),
(52, 'COULOIR', 55000, 1),
(53, 'COULOIR', 55000, 1),
(54, 'HUBLOT', 55000, 1),
(55, 'HUBLOT', 55000, 1),
(56, 'HUBLOT', 55000, 1),
(57, 'HUBLOT', 55000, 1),
(58, 'HUBLOT', 55000, 1),
(59, 'HUBLOT', 55000, 1),
(60, 'HUBLOT', 55000, 1),
(61, 'HUBLOT', 55000, 1),
(62, 'HUBLOT', 55000, 1),
(63, 'HUBLOT', 55000, 1),
(64, 'HUBLOT', 55000, 1),
(65, 'HUBLOT', 55000, 1),
(66, 'HUBLOT', 55000, 1),
(67, 'HUBLOT', 55000, 1),
(68, 'MILIEU', 55000, 1),
(69, 'MILIEU', 55000, 1),
(70, 'MILIEU', 55000, 1),
(71, 'MILIEU', 55000, 1),
(72, 'MILIEU', 55000, 1),
(73, 'MILIEU', 55000, 1),
(74, 'MILIEU', 55000, 1),
(75, 'MILIEU', 55000, 1),
(76, 'MILIEU', 55000, 1),
(77, 'MILIEU', 55000, 1),
(78, 'HUBLOT', 55000, 1),
(79, 'MILIEU', 55000, 1),
(80, 'MILIEU', 55000, 1),
(81, 'COULOIR', 55000, 1),
(82, 'COULOIR', 55000, 1),
(83, 'COULOIR', 55000, 1),
(84, 'COULOIR', 55000, 1),
(85, 'COULOIR', 55000, 1),
(86, 'COULOIR', 55000, 1),
(87, 'COULOIR', 55000, 1),
(88, 'COULOIR', 55000, 1),
(89, 'COULOIR', 55000, 1),
(90, 'COULOIR', 55000, 1),
(91, 'COULOIR', 55000, 1),
(92, 'COULOIR', 55000, 1),
(93, 'COULOIR', 55000, 2),
(94, 'COULOIR', 55000, 2),
(95, 'COULOIR', 55000, 2),
(96, 'COULOIR', 55000, 2),
(97, 'COULOIR', 55000, 2),
(98, 'COULOIR', 55000, 2),
(99, 'COULOIR', 55000, 2),
(100, 'HUBLOT', 55000, 2),
(101, 'HUBLOT', 55000, 2),
(102, 'HUBLOT', 55000, 2),
(103, 'HUBLOT', 55000, 2),
(104, 'HUBLOT', 55000, 2),
(105, 'HUBLOT', 55000, 2),
(106, 'HUBLOT', 55000, 2),
(107, 'HUBLOT', 55000, 2),
(108, 'COULOIR', 55000, 2),
(109, 'COULOIR', 55000, 2),
(110, 'COULOIR', 55000, 2);


-- Insertion des a
INSERT INTO vol(aeroportdarrivee_id, aeroport_depart_id, date_et_heure_arrivee, tarif_economique_de_base, date_et_heure_depart, numero_de_vol, satut, admin_compagnie_id) VALUES ((select id from aeroport where codeiata='CDG'), (select id from aeroport where codeiata='LHR'), '2024-11-12', 8000, '2024-11-11', 'b500', 'EN_COURS', (SELECT id FROM personne WHERE nom = 'Lead'));
INSERT INTO vol(aeroportdarrivee_id, aeroport_depart_id, date_et_heure_arrivee, tarif_economique_de_base, date_et_heure_depart, numero_de_vol, satut, admin_compagnie_id) VALUES ((select id from aeroport where codeiata='CDG'), (select id from aeroport where codeiata='LHR'), '2024-11-12', 8000,'2024-11-11', 'b501', 'EN_COURS', (SELECT id FROM personne WHERE nom = 'Lead'));



-- Insertion des avions
INSERT INTO Avion (matricule, capacite_totale, nom, statut, admin_compagnie_id)
VALUES ('AF001', 200, 'Boeing 777', 'DISPONIBLE', (SELECT id FROM personne WHERE nom = 'Lead'));

INSERT INTO Avion (matricule, capacite_totale, nom, statut, admin_compagnie_id)
VALUES ('BA002', 150, 'Airbus A320', 'MAINTENANCE', (SELECT id FROM personne WHERE nom = 'Lead'));


INSERT INTO `siege` (`id`, `disponibilite`, `numero`, `avion_id`, `position_siege_id`) VALUES
                                                                                           (5, 1, '25E', 1, 14),
                                                                                           (6, 1, '26B', 1, 15),
                                                                                           (7, 0, '27D', 1, 16),
                                                                                           (8, 0, '28E', 1, 17),
                                                                                           (9, 0, '98L', 1, 19),
                                                                                           (10, 0, '98L', 1, 20),
                                                                                           (11, 1, '95A', 1, 21),
                                                                                           (12, 0, '95B', 1, 22),
                                                                                           (13, 0, '95C', 1, 23),
                                                                                           (14, 1, '95D', 1, 24),
                                                                                           (15, 0, '95E', 1, 25),
                                                                                           (16, 0, '95F', 1, 26),
                                                                                           (17, 0, '95G', 1, 27),
                                                                                           (18, 0, '95H', 1, 28),
                                                                                           (19, 0, '95I', 1, 29),
                                                                                           (20, 0, '95J', 1, 30),
                                                                                           (21, 0, '95K', 1, 31),
                                                                                           (22, 0, '95L', 1, 32),
                                                                                           (23, 0, '95M', 1, 33),
                                                                                           (24, 0, '10A', 1, 34),
                                                                                           (25, 0, '10B', 1, 35),
                                                                                           (26, 0, '10C', 1, 36),
                                                                                           (27, 0, '10D', 1, 37),
                                                                                           (28, 0, '10E', 1, 38),
                                                                                           (29, 0, '10F', 1, 39),
                                                                                           (30, 0, '10G', 1, 40),
                                                                                           (31, 0, '10H', 1, 41),
                                                                                           (32, 0, '10I', 1, 42),
                                                                                           (33, 0, '11A', 1, 43),
                                                                                           (34, 0, '11B', 1, 44),
                                                                                           (35, 0, '11C', 1, 45),
                                                                                           (36, 0, '11D', 1, 46),
                                                                                           (37, 0, '11E', 1, 47),
                                                                                           (38, 0, '11F', 1, 48),
                                                                                           (39, 0, '11G', 1, 49),
                                                                                           (40, 0, '11H', 1, 50),
                                                                                           (41, 0, '11I', 1, 51),
                                                                                           (42, 0, '11J', 1, 52),
                                                                                           (43, 0, '11K', 1, 53),
                                                                                           (44, 0, '10J', 1, 54),
                                                                                           (45, 0, '10K', 1, 55),
                                                                                           (46, 0, '1A', 2, 56),
                                                                                           (47, 0, '15A', 1, 57),
                                                                                           (48, 0, '15B', 1, 58),
                                                                                           (49, 0, '15C', 1, 59),
                                                                                           (50, 0, '15D', 1, 60),
                                                                                           (51, 0, '15E', 1, 61),
                                                                                           (52, 0, '15F', 1, 62),
                                                                                           (53, 0, '15G', 1, 63),
                                                                                           (54, 0, '15H', 1, 64),
                                                                                           (55, 0, '15I', 1, 65),
                                                                                           (56, 0, '15J', 1, 66),
                                                                                           (57, 0, '15K', 1, 67),
                                                                                           (58, 0, '16A', 1, 68),
                                                                                           (59, 0, '16B', 1, 69),
                                                                                           (60, 0, '16C', 1, 70),
                                                                                           (61, 0, '16D', 1, 71),
                                                                                           (62, 0, '16E', 1, 72),
                                                                                           (63, 0, '16F', 1, 73),
                                                                                           (64, 0, '16G', 1, 74),
                                                                                           (65, 0, '16H', 1, 75),
                                                                                           (66, 0, '16I', 1, 76),
                                                                                           (67, 0, '16J', 1, 77),
                                                                                           (68, 0, '78J', 1, 78),
                                                                                           (69, 0, '95I', 1, 79),
                                                                                           (70, 0, '95J', 1, 80),
                                                                                           (71, 0, '11L', 1, 81),
                                                                                           (72, 0, '11M', 1, 82),
                                                                                           (73, 0, '11N', 1, 83),
                                                                                           (74, 0, '11O', 1, 84),
                                                                                           (75, 0, '11P', 1, 85),
                                                                                           (76, 0, '11Q', 1, 86),
                                                                                           (77, 0, '11R', 1, 87),
                                                                                           (78, 0, '11S', 1, 88),
                                                                                           (79, 0, '11R', 1, 89),
                                                                                           (80, 0, '11T', 1, 90),
                                                                                           (81, 0, '11U', 1, 91),
                                                                                           (82, 0, '11V', 1, 92),
                                                                                           (83, 0, '11A', 1, 93),
                                                                                           (84, 0, '11B', 1, 94),
                                                                                           (85, 0, '11C', 1, 95),
                                                                                           (86, 0, '11D', 1, 96),
                                                                                           (87, 0, '11E', 1, 97),
                                                                                           (88, 0, '11F', 1, 98),
                                                                                           (89, 0, '11G', 1, 99),
                                                                                           (90, 0, '13A', 1, 100),
                                                                                           (91, 0, '13B', 1, 101),
                                                                                           (92, 0, '13C', 1, 102),
                                                                                           (93, 0, '13D', 1, 103),
                                                                                           (94, 0, '13E', 1, 104),
                                                                                           (95, 0, '13F', 1, 105),
                                                                                           (96, 0, '13G', 1, 106),
                                                                                           (97, 0, '13H', 1, 107),
                                                                                           (98, 0, '11H', 1, 108),
                                                                                           (99, 0, '11I', 1, 109),
                                                                                           (100, 0, '11J', 1, 110);
-- Insertion des contrats
INSERT INTO Contrat (type, description, date_debut, date_fin, statut, cgu, commentaires, compagnie_id)
VALUES ('Location', 'Location de matériel', '2024-01-01', '2024-12-31', 'EN_ATTENTE', 'Conditions générales', 'Aucun commentaire', (SELECT id FROM compagnie WHERE matricule = 'AF123'));

INSERT INTO Contrat (type, description, date_debut, date_fin, statut, cgu, commentaires, compagnie_id)
VALUES ('Maintenance', 'Maintenance des avions', '2024-02-01', '2024-11-30', 'CONFIRMER', 'Conditions générales', 'Vérifier les détails', (SELECT id FROM compagnie WHERE matricule = 'AF123'));


-- Insertion des utilisateurs
INSERT INTO Utilisateur (id, point_de_fideliter, date_de_naissance, numero_de_passport, numero_de_visa)
VALUES ((SELECT id FROM personne WHERE nom = 'Issa'), 100, '1990-01-01', 'P9876543', 'V9876543');

INSERT INTO Utilisateur (id, point_de_fideliter, date_de_naissance, numero_de_passport, numero_de_visa)
VALUES ((SELECT id FROM personne WHERE nom = 'Mamadou'), 50, '1985-05-05', 'P1234567', 'V1234567');

-- Insertion de reservation
INSERT INTO reservation(date_reservation, nombre_depassager, statut, utilisateur_id, vol_id) VALUES ('2024-11-1', 1, 'EN_ATTENTE',(select id from utilisateur where numero_de_passport='P9876543'), (select id from vol where numero_de_vol='b500'));
-- Insertion de reservation
INSERT INTO reservation(date_reservation, nombre_depassager, statut, utilisateur_id, vol_id) VALUES ('2024-12-4', 3, 'EN_ATTENTE',(select id from utilisateur where numero_de_passport='P1234567'), (select id from vol where numero_de_vol='b501'));


-- Insertion des Passagers
INSERT INTO passager (nom, numero_de_pass_port, numero_de_visa, prenom, reservation_id, siege_id) VALUES ('TRAORE', 'TRGGE65', 'ERR33', 'FAKORO', 1, (select id from siege where numero='25E'));
INSERT INTO passager (nom, numero_de_pass_port, numero_de_visa, prenom, reservation_id, siege_id) VALUES ('DEMBELE', 'TRGGE667', 'ERR3390', 'MAMADOU', 2, (select id from siege where numero='26B'));
INSERT INTO passager (nom, numero_de_pass_port, numero_de_visa, prenom, reservation_id, siege_id) VALUES ('DIARRE', 'TRGGEUI65', 'ERRNH33', 'ISSA', 2, (select id from siege where numero='27D'));
INSERT INTO passager (nom, numero_de_pass_port, numero_de_visa, prenom, reservation_id, siege_id) VALUES ('DIAKITE', 'TRGGE65B200', 'ERR33HH', 'IBRAHIM', 2, (select id from siege where numero='28E'));


-- Insertion des personnels
INSERT INTO Personnel (id, date_embauche, poste, en_service, admin_compagnie_id)
VALUES ((SELECT id FROM personne WHERE nom = 'Lead'),'2024-01-15', 'Pilot', TRUE, (SELECT id FROM personne WHERE nom = 'Lead'));

-- Affectation d'un vol à un avion
INSERT INTO vol_avion(vol_id, avion_id) VALUES ((select id from vol where numero_de_vol='b500'),(select id from avion where matricule='AF001'));

INSERT INTO faq(question_categorie, reponse, admin_compagnie_id) VALUES ('Cest Quoi le prix de la classe Buissness', 'Le Prix de la classe Buissness est de 100$',(SELECT id FROM personne WHERE nom = 'Lead'));
INSERT INTO faq(question_categorie, reponse, admin_compagnie_id) VALUES ('Combien de passager par reservation', 'Le nombre de passager par reservation peut varier en fonction du nombre de paiement',(SELECT id FROM personne WHERE nom = 'Lead'));
INSERT INTO faq(question_categorie, reponse, admin_compagnie_id) VALUES ('Les toilettes', 'Les toilettes se trouvent derriere les position hubot',(SELECT id FROM personne WHERE nom = 'Babou'));
INSERT INTO faq(question_categorie, reponse, admin_compagnie_id) VALUES ('Reserver via le site', 'Notre site web est disponible pour tout un chacun a ladresse bar.com',(SELECT id FROM personne WHERE nom = 'Lead'));



INSERT INTO permission(end_point,permission) VALUES ('admincompagnie', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('admincompagnie', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('admincompagnie', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('admincompagnie', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('adresse', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('adresse', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('adresse', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('adresse', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('personne', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('personne', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('personne', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('personne', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('faq', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('faq', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('faq', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('faq', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('personnel', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('personnel', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('personnel', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('personnel', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('rapport', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('rapport', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('rapport', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('rapport', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('superadmin', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('superadmin', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('superadmin', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('superadmin', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('utilisateur', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('utilisateur', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('utilisateur', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('utilisateur', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('aeroport', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('aeroport', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('aeroport', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('aeroport', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('avion', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('avion', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('avion', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('avion', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('classe', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('classe', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('classe', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('classe', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('compagnie', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('compagnie', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('compagnie', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('compagnie', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('contrat', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('contrat', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('contrat', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('contrat', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('passager', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('passager', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('passager', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('passager', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('pays', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('pays', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('pays', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('pays', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('position', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('position', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('position', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('position', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('permission', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('permission', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('permission', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('permission', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('reservation', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('reservation', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('reservation', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('reservation', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('role', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('role', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('role', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('role', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('siege', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('siege', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('siege', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('siege', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('typebagage', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('typebagage', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('typebagage', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('typebagage', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('ville', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('ville', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('ville', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('ville', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('vol', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('vol', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('vol', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('vol', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('carteBancaire', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('carteBancaire', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('carteBancaire', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('carteBancaire', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('mobileMoney', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('mobileMoney', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('mobileMoney', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('mobileMoney', 'AFFICHER');

INSERT INTO permission(end_point,permission) VALUES ('bagage', 'AJOUT');
INSERT INTO permission(end_point,permission) VALUES ('bagage', 'MODIFIER');
INSERT INTO permission(end_point,permission) VALUES ('bagage', 'SUPPRIMER');
INSERT INTO permission(end_point,permission) VALUES ('bagage', 'AFFICHER');


INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMIN'),(SELECT id FROM permission WHERE end_point = 'superadmin' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMIN'),(SELECT id FROM permission WHERE end_point = 'superadmin' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMIN'),(SELECT id FROM permission WHERE end_point = 'superadmin' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMIN'),(SELECT id FROM permission WHERE end_point = 'superadmin' AND permission = 'SUPPRIMER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMIN'),(SELECT id FROM permission WHERE end_point = 'admincompagnie' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMIN'),(SELECT id FROM permission WHERE end_point = 'admincompagnie' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMIN'),(SELECT id FROM permission WHERE end_point = 'admincompagnie' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMIN'),(SELECT id FROM permission WHERE end_point = 'admincompagnie' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'admincompagnie' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'admincompagnie' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'admincompagnie' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'admincompagnie' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMIN'),(SELECT id FROM permission WHERE end_point = 'compagnie' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMIN'),(SELECT id FROM permission WHERE end_point = 'compagnie' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMIN'),(SELECT id FROM permission WHERE end_point = 'compagnie' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMIN'),(SELECT id FROM permission WHERE end_point = 'compagnie' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'personnel' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'personnel' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'personnel' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'personnel' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'permission' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'permission' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'permission' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'permission' AND permission = 'SUPPRIMER'));

# INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'vol' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id, permission_id)VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'vol' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'vol' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'vol' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'vol' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'siege' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'siege' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'siege' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'siege' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'carteBancaire' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'carteBancaire' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'carteBancaire' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'carteBancaire' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'bagage' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'bagage' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'bagage' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'bagage' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'mobileMoney' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'mobileMoney' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'mobileMoney' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'mobileMoney' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'aeroport' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'aeroport' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'aeroport' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'aeroport' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'pays' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'pays' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'pays' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'pays' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'ville' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'ville' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'ville' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'ville' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'ville' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'contrat' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'contrat' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'contrat' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'contrat' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'classe' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'classe' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'classe' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'classe' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'position' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'position' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'position' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'position' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'siege' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'siege' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'siege' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'siege' AND permission = 'SUPPRIMER'));

INSERT into role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'avion' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'avion' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'avion' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'avion' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'personne' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'personne' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'personne' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'personne' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'personne' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'vol' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'vol' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'vol' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'typebagage' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'typebagage' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'typebagage' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'typebagage' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'bagage' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'bagage' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'bagage' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'bagage' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMIN'),(SELECT id FROM permission WHERE end_point = 'utilisateur' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'utilisateur' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'utilisateur' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'role' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'role' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'role' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'role' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'adresse' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'adresse' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'adresse' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'adresse' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'passager' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'passager' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'passager' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'passager' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'faq' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'faq' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'faq' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'ADMINCOMPAGNIE'),(SELECT id FROM permission WHERE end_point = 'faq' AND permission = 'SUPPRIMER'));

INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'reservation' AND permission = 'AFFICHER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'reservation' AND permission = 'AJOUT'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'reservation' AND permission = 'MODIFIER'));
INSERT INTO role_permission(role_id,permission_id) VALUES ((SELECT id FROM role WHERE nom = 'USER'),(SELECT id FROM permission WHERE end_point = 'reservation' AND permission = 'SUPPRIMER'));




