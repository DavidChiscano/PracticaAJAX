DROP SCHEMA Ofertas;
CREATE SCHEMA IF NOT EXISTS Ofertas;
USE Ofertas;
CREATE TABLE IF NOT EXISTS oferta(id bigint primary key not null auto_increment, nombre varchar(30), fecha varchar(20), prioridad varchar(20), hiperenlace varchar(20), descripcion varchar(50), precio double);

INSERT INTO oferta(nombre,prioridad,hiperenlace,descripcion,precio)VALUES("test","Alta","test.es","es test", 20);