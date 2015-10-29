CREATE DATABASE buscador_vectorial;
USE buscador_vectorial;

CREATE TABLE documentos(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(60),
    enlace VARCHAR(500),
    PRIMARY KEY(id)
)ENGINE MyISAM;
CREATE TABLE vocabulario(
    id INT NOT NULL AUTO_INCREMENT,
    texto VARCHAR(60),
    nr INT, -- Cantidad de documento en la que aparece.
    maxtf INT, -- Frecuencia máxima de esta palabra en un documento.
    PRIMARY KEY(id)
)ENGINE MyISAM;
CREATE TABLE posteo(
    tf INT NOT NULL,
    id_documento INT NOT NULL,
    id_vocabulario INT NOT NULL,
    PRIMARY KEY(id_documento, id_vocabulario)
)ENGINE MyISAM;

INSERT INTO documentos (nombre, enlace) VALUES ('Google', 'http://www.google.com');
INSERT INTO documentos (nombre, enlace) VALUES ('Facebook', 'http://www.facebook.com');
INSERT INTO documentos (nombre, enlace) VALUES ('Twitter', 'http://www.twitter.com');
INSERT INTO documentos (nombre, enlace) VALUES ('PaBex', 'http://www.pabex.com.ar');
INSERT INTO documentos (nombre, enlace) VALUES ('JavaHispano', 'http://www.javahispano.org');

INSERT INTO vocabulario (texto, nr, maxtf) VALUES ('computadora', 3, 8);
INSERT INTO vocabulario (texto, nr, maxtf) VALUES ('notebook', 2, 6);
INSERT INTO vocabulario (texto, nr, maxtf) VALUES ('problema', 2, 5);
INSERT INTO vocabulario (texto, nr, maxtf) VALUES ('mesa', 4, 5);
INSERT INTO vocabulario (texto, nr, maxtf) VALUES ('votar', 3, 15);

INSERT INTO posteo (tf, id_documento, id_vocabulario) VALUES (5, 1, 1);
INSERT INTO posteo (tf, id_documento, id_vocabulario) VALUES (2, 2, 1);
INSERT INTO posteo (tf, id_documento, id_vocabulario) VALUES (8, 4, 1);
INSERT INTO posteo (tf, id_documento, id_vocabulario) VALUES (6, 1, 2);
INSERT INTO posteo (tf, id_documento, id_vocabulario) VALUES (1, 3, 2);
INSERT INTO posteo (tf, id_documento, id_vocabulario) VALUES (5, 5, 3);
INSERT INTO posteo (tf, id_documento, id_vocabulario) VALUES (4, 4, 3);
INSERT INTO posteo (tf, id_documento, id_vocabulario) VALUES (5, 1, 4);
INSERT INTO posteo (tf, id_documento, id_vocabulario) VALUES (2, 3, 4);
INSERT INTO posteo (tf, id_documento, id_vocabulario) VALUES (5, 1, 5);
INSERT INTO posteo (tf, id_documento, id_vocabulario) VALUES (15, 2, 5);
INSERT INTO posteo (tf, id_documento, id_vocabulario) VALUES (9, 5, 5);

