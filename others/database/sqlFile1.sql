create database if not exists animal_shelter;
use animal_shelter;

create table if not exists data_inscripcion (
    data_inscripcio date primary key
);

create table if not exists instalacions (
    id_instalacion int primary key,
    descripcion varchar(255) not null,
    es_permanent boolean not null
);

create table if not exists provincies (
    id_provincia int primary key,
    nom varchar(30) not null
);

create table if not exists poblacions (
    id_provincia int,
    nom_poblacions varchar(30),
    superficie varchar(50),
    primary key (id_provincia, nom_poblacions),
    foreign key (id_provincia) references provincies(id_provincia)
);

create table if not exists centres (
    id_centre int primary key,
    nom varchar(30) not null,
    telefon char(9) not null,
    correu varchar(100) not null,
    id_provincia int not null,
    nom_poblacions varchar(30) not null,
    foreign key (id_provincia) references poblacions(id_provincia),
    foreign key (nom_poblacions) references poblacions(nom_poblacions)
);


create table if not exists data_donatius (
    data_donatius date primary key
);

create table if not exists tipus_donants (
    id_tipus int primary key,
    tipus varchar(40) not null
);

create table if not exists animals (
    id_animal int primary key,
    nom varchar(30) not null
);

create table if not exists donants (
    id_donant int primary key,
    correu varchar(100) not null,
    es_habitual boolean not null,
    id_tipus int not null,
    foreign key (id_tipus) references tipus_donants(id_tipus)
);

create table if not exists socis (
    id_donant int primary key,
    num_soci int not null,
    foreign key (id_donant) references donants(id_donant)
);

create table if not exists publics (
    id_donant int primary key,
    entitat varchar(50) not null,
    foreign key (id_donant) references donants(id_donant)
);

create table if not exists privats (
    nom varchar(40) not null,
    correu varchar(100) not null,
    te_animals boolean not null,
    id_donant int primary key,
    foreign key (id_donant) references donants(id_donant)
);

create table if not exists colaborador (
    id_donant int primary key,
    tipus_colaboracio varchar(30) not null,
    foreign key (id_donant) references donants(id_donant)
);

create table if not exists inscripcio (
    data_inscripcio date,
    id_donant int,
    id_centre int not null,
    primary key (data_inscripcio, id_donant),
    foreign key (data_inscripcio) references data_inscripcion(data_inscripcio),
    foreign key (id_donant) references socis(id_donant),
    foreign key (id_centre) references centres(id_centre)
);

create table if not exists relacionats (
    id_relacionat int,
    id_centre int,
    primary key (id_centre, id_relacionat),
    foreign key (id_centre) references centres(id_centre),
    foreign key (id_relacionat) references centres(id_centre)
);

create table if not exists conte (
    categoria varchar(50) not null,
    id_centre int,
    id_instalacion int,
    foreign key (id_centre) references centres(id_centre),
    foreign key (id_instalacion) references instalacions(id_instalacion)
);

create table if not exists donatius (
    data_donatius date,
    id_donant int,
    id_centre int,
    quantitat int not null,
    primary key (data_donatius, id_donant, id_centre),
    foreign key (data_donatius) references data_donatius(data_donatius),
    foreign key (id_donant) references donants(id_donant),
    foreign key (id_centre) references centres(id_centre)
);

create table if not exists donatiu_animal (
    quantitat int not null,
    id_donant int,
    id_animal int,
    primary key (id_donant, id_animal),
    foreign key (id_donant) references donants(id_donant),
    foreign key (id_animal) references animals(id_animal)
);
