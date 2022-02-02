CREATE SCHEMA IF NOT EXISTS Ofertas;
USE Ofertas;
CREATE TABLE IF NOT EXISTS Oferta(id bigint primary key not null auto_increment, nombre varchar(30) not null, fecha varchar(20)not null, prioridad varchar(20)not null, hiperenlace varchar(20) not null, descripcion varchar(50) not null, precio double not null );
