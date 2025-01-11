-- Database TubesPP
DROP DATABASE IF EXISTS TubesPP;
CREATE DATABASE TubesPP;
USE TubesPP;

-- Tabel User Mitra Kurir
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    name VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100),
    no_telp VARCHAR(20),
    jenis_kelamin varchar(6),
    alamat VARCHAR(100),
    ktp varchar(255),
    kk varchar(255)
);