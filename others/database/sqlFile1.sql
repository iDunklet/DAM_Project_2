CREATE DATABASE IF NOT EXISTS animal_shelter;
USE animal_shelter;

CREATE TABLE IF NOT EXISTS instalacions (
    id_instalacion INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(255) NOT NULL,
    es_permanent BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS provincies (
    id_provincia INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS poblacions (
    id_provincia INT,
    nom_poblacions VARCHAR(30),
    superficie VARCHAR(50),
    PRIMARY KEY (id_provincia, nom_poblacions),
    FOREIGN KEY (id_provincia) REFERENCES provincies(id_provincia)
);

CREATE TABLE IF NOT EXISTS centres (
    id_centre INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(30) NOT NULL,
    telefon CHAR(9) NOT NULL,
    correu VARCHAR(100) NOT NULL,
    id_provincia INT NOT NULL,
    nom_poblacions VARCHAR(30) NOT NULL,
    CONSTRAINT fk_centres FOREIGN KEY (id_provincia, nom_poblacions) REFERENCES poblacions(id_provincia, nom_poblacions)
);

CREATE TABLE IF NOT EXISTS tipus_donants (
    id_tipus INT PRIMARY KEY AUTO_INCREMENT,
    tipus VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS animals (
    id_animal INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS donants (
    id_donant INT PRIMARY KEY AUTO_INCREMENT,
    correu VARCHAR(100) NOT NULL,
    es_habitual BOOLEAN NOT NULL,
    id_tipus INT NOT NULL,
    FOREIGN KEY (id_tipus) REFERENCES tipus_donants(id_tipus)
);

CREATE TABLE IF NOT EXISTS socis (
    id_donant INT PRIMARY KEY,
    num_soci INT NOT NULL,
    FOREIGN KEY (id_donant) REFERENCES donants(id_donant)
);

CREATE TABLE IF NOT EXISTS publics (
    id_donant INT PRIMARY KEY,
    entitat VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_donant) REFERENCES donants(id_donant)
);

CREATE TABLE IF NOT EXISTS privats (
    nom VARCHAR(40) NOT NULL,
    correu VARCHAR(100) NOT NULL,
    te_animals BOOLEAN NOT NULL,
    id_donant INT PRIMARY KEY,
    FOREIGN KEY (id_donant) REFERENCES donants(id_donant)
);

CREATE TABLE IF NOT EXISTS colaborador (
    id_donant INT PRIMARY KEY,
    tipus_colaboracio VARCHAR(30) NOT NULL,
    FOREIGN KEY (id_donant) REFERENCES donants(id_donant)
);

CREATE TABLE IF NOT EXISTS inscripcio (
    data_inscripcio DATE,
    id_donant INT,
    id_centre INT NOT NULL,
    data_baixa DATE NOT NULL,
    PRIMARY KEY (data_inscripcio, id_donant),
    FOREIGN KEY (id_donant) REFERENCES socis(id_donant),
    FOREIGN KEY (id_centre) REFERENCES centres(id_centre)
);
-- relaciones
CREATE TABLE IF NOT EXISTS relacionats (
    id_relacionat INT,
    id_centre INT,
    PRIMARY KEY (id_centre, id_relacionat),
    FOREIGN KEY (id_centre) REFERENCES centres(id_centre),
    FOREIGN KEY (id_relacionat) REFERENCES centres(id_centre)
);

CREATE TABLE IF NOT EXISTS conte (
    categoria VARCHAR(50) NOT NULL,
    id_centre INT,
    id_instalacion INT,
    FOREIGN KEY (id_centre) REFERENCES centres(id_centre),
    FOREIGN KEY (id_instalacion) REFERENCES instalacions(id_instalacion)
);

CREATE TABLE IF NOT EXISTS donatius (
    data_donatius DATE,
    id_donant INT,
    id_centre INT,
    quantitat INT NOT NULL,
    PRIMARY KEY (data_donatius, id_donant, id_centre),
    FOREIGN KEY (id_donant) REFERENCES donants(id_donant),
    FOREIGN KEY (id_centre) REFERENCES centres(id_centre)
);


CREATE TABLE IF NOT EXISTS donatiu_animal (
    quantitat INT NOT NULL,
    id_donant INT,
    id_animal INT,
    PRIMARY KEY (id_donant, id_animal),
    FOREIGN KEY (id_donant) REFERENCES donants(id_donant),
    FOREIGN KEY (id_animal) REFERENCES animals(id_animal)
);
