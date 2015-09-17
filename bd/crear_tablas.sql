CREATE DATABASE buscador_vectorial;
USE buscador_vectorial;

CREATE TABLE documentos(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(60),
    enlace VARCHAR(500),
    PRIMARY KEY(id)
);
CREATE TABLE vocabulario(
    id INT NOT NULL AUTO_INCREMENT,
    texto VARCHAR(60),
    PRIMARY KEY(id)
);
CREATE TABLE posteo(
    tf INT NOT NULL,
    id_documento INT NOT NULL,
    id_vocabulario INT NOT NULL,
    PRIMARY KEY(id_documento, id_vocabulario)
);

INSERT INTO documentos (nombre, enlace) VALUES ('Google', 'http://www.google.com');
INSERT INTO documentos (nombre, enlace) VALUES ('Facebook', 'http://www.facebook.com');
INSERT INTO documentos (nombre, enlace) VALUES ('Twitter', 'http://www.twitter.com');
INSERT INTO documentos (nombre, enlace) VALUES ('PaBex', 'http://www.pabex.com.ar');
INSERT INTO documentos (nombre, enlace) VALUES ('JavaHispano', 'http://www.javahispano.org');

INSERT INTO vocabulario (texto) VALUES ('computadora');
INSERT INTO vocabulario (texto) VALUES ('notebook');
INSERT INTO vocabulario (texto) VALUES ('problema');
INSERT INTO vocabulario (texto) VALUES ('mesa');
INSERT INTO vocabulario (texto) VALUES ('votar');

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

