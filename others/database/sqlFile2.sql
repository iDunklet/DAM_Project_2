-- inserts
INSERT INTO instalacions (descripcion, es_permanent) VALUES 
('Instalación 1', TRUE), 
('Instalación 2', FALSE);

INSERT INTO provincies (nom) VALUES 
('Barcelona'), 
('Girona'), 
('Lleida'), 
('Tarragona');

INSERT INTO poblacions (id_provincia, nom_poblacions, superficie) VALUES 
(1, 'Barcelona', '101.9 km²'), 
(2, 'Girona', '39.1 km²'), 
(3, 'Lleida', '211.6 km²'), 
(4, 'Tarragona', '63.4 km²');

INSERT INTO centres (nom, telefon, correu, id_provincia, nom_poblacions) VALUES 
('Centre 1', '933112345', 'centre1@example.com', 1, 'Barcelona'), 
('Centre 2', '972223456', 'centre2@example.com', 2, 'Girona');

INSERT INTO tipus_donants (tipus) VALUES 
('Individual'), 
('Empresa');

INSERT INTO animals (nom) VALUES 
('Perro'), 
('Gato');

INSERT INTO donants (correu, es_habitual, id_tipus) VALUES 
('donant1@example.com', TRUE, 1), 
('donant2@example.com', FALSE, 2);

INSERT INTO socis (id_donant, num_soci) VALUES 
(1, 1001);

INSERT INTO publics (id_donant, entitat) VALUES 
(2, 'Entidad Pública');

INSERT INTO privats (nom, correu, te_animals, id_donant) VALUES 
('Donante Privado 1', 'privat1@example.com', TRUE, 1);

INSERT INTO colaborador (id_donant, tipus_colaboracio) VALUES 
(2, 'Financiera');

INSERT INTO inscripcio (data_inscripcio, id_donant, id_centre, data_baixa) VALUES 
('2024-01-01', 1, 1, '2024-12-31');

INSERT INTO relacionats (id_relacionat, id_centre) VALUES 
(1, 1);

INSERT INTO conte (categoria, id_centre, id_instalacion) VALUES 
('Habitación', 1, 1);

INSERT INTO donatius (data_donatius, id_donant, id_centre, quantitat) VALUES 
('2024-01-01', 1, 1, 100);

INSERT INTO donatiu_animal (quantitat, id_donant, id_animal) VALUES 
(1, 1, 1);

-- usuarios
CREATE USER 'usuario1'@'localhost' IDENTIFIED BY 'password1';
GRANT SELECT, INSERT, UPDATE ON animal_shelter.* TO 'usuario1'@'localhost';

CREATE USER 'usuario2'@'localhost' IDENTIFIED BY 'password2';
GRANT SELECT, DELETE ON animal_shelter.* TO 'usuario2'@'localhost';

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'adminpassword';
GRANT ALL PRIVILEGES ON animal_shelter.* TO 'admin'@'localhost';

