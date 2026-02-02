INSERT INTO armurerie (id) VALUES
(1);

INSERT INTO equipement (name, type, armurerie_id) VALUES
('Épée longue', 'arme', 1),
('Épée longue', 'arme', 1),
('Épée longue', 'arme', 1),
('Épée longue', 'arme', 1),
('Épée longue', 'arme', 1),
('Hache de guerre', 'arme', 1),
('Hache de guerre', 'arme', 1),
('Hache de guerre', 'arme', 1),
('Bouclier en bois', 'defense', 1),
('Bouclier en bois', 'defense', 1),
('Bouclier en bois', 'defense', 1),
('Bouclier en bois', 'defense', 1),
('Bouclier en bois', 'defense', 1),
('Bouclier en bois', 'defense', 1),
('Bouclier en bois', 'defense', 1),
('Cotte de maille', 'defense', 1),
('Cotte de maille', 'defense', 1),
('Cotte de maille', 'defense', 1),
('Cotte de maille', 'defense', 1),
('Casque en fer', 'defense', 1),
('Casque en fer', 'defense', 1),
('Casque en fer', 'defense', 1),
('Casque en fer', 'defense', 1),
('Casque en fer', 'defense', 1),
('Casque en fer', 'defense', 1),
('Dague', 'arme', 1),
('Dague', 'arme', 1);

INSERT INTO chevalier (id, name) VALUES
(1, 'Arthur'),
(2, 'Lancelot'),
(3, 'Perceval'),
(4, 'Gauvain');

INSERT INTO chevalier_equipements (chevalier_id, equipement_id) VALUES
(1, 1),
(1, 20),
(2, 6),
(3, 27);
