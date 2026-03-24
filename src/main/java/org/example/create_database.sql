CREATE SCHEMA innlevering_jpa;
SET search_path TO innlevering_jpa;

CREATE TABLE ansatt(

    brukernavn char(4) PRIMARY KEY,
    fornavn VARCHAR(30),
    etternavn VARCHAR(30),
    ansettelsedato DATE,
    stilling varchar(30),
    maanedslonn decimal

)