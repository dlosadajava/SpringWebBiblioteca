-- DROP SCHEMA dbname;

CREATE SCHEMA dbname;
USE dbname;

-- libro 1..N <--> 1..N autore 
-- libro N <--> 1 genere

CREATE TABLE genere (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(45) UNIQUE NOT NULL
);

CREATE TABLE libro (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    isbn CHAR(13) UNIQUE NOT NULL,
    titolo VARCHAR(45) NOT NULL,
    descrizione VARCHAR(255) NOT NULL,
    genere_id INT NOT NULL REFERENCES genere(id),
    numero_pagine INT NOT NULL CHECK(numero_pagine>0),
    data_pubblicazione DATE NOT NULL
);

CREATE TABLE autore (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	nome VARCHAR(45) NOT NULL,
    cognome VARCHAR(45) NOT NULL,
    data_nascita DATE NOT NULL
);

CREATE TABLE libro_autore (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    libro_id INT NOT NULL REFERENCES libro(id),
    autore_id INT NOT NULL REFERENCES autore(id),
    UNIQUE(libro_id,autore_id)
);

CREATE TABLE libro_autore (
    libro_id INT NOT NULL REFERENCES libro(id),
    autore_id INT NOT NULL REFERENCES autore(id),
    PRIMARY KEY(libro_id,autore_id)
);