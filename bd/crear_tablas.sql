CREATE DATABASE buscador_vectorial;
USE buscador_vectorial;

CREATE TABLE tipos_archivos(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(60),
    PRIMARY KEY(id)
)ENGINE MyISAM;
CREATE TABLE documentos(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(60),
    enlace VARCHAR(200),
    otro VARCHAR(500),
    id_tipo_archivo INT,
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

INSERT INTO tipos_archivos (nombre) VALUES ('pdf');
INSERT INTO tipos_archivos (nombre) VALUES ('web');
