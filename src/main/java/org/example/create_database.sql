DROP SCHEMA innlevering_jpa CASCADE;
CREATE SCHEMA innlevering_jpa;
SET search_path TO innlevering_jpa;


--TABELLER--
CREATE TABLE avdeling
(
    id      SERIAL PRIMARY KEY,
    navn    VARCHAR(30),
    sjef_id int
);

CREATE TABLE ansatt
(
    id             SERIAL PRIMARY KEY,
    brukernavn     char(4),
    fornavn        VARCHAR(30),
    etternavn      VARCHAR(30),
    ansettelsedato DATE,
    stilling       varchar(30),
    maanedslonn    decimal,
    avdeling_id    int REFERENCES avdeling (id)
);
CREATE TABLE prosjekt
(
    id          SERIAL PRIMARY KEY,
    navn        VARCHAR(30),
    beskrivelse VARCHAR(255)
    --PROSJEKT_ANSATTE tabell


);
-- REDIGERER TABELLEN PGA CIRCULAR DEPENDENCY ellers
ALTER TABLE avdeling
    ADD CONSTRAINT fk_sjef
        FOREIGN KEY (sjef_id) REFERENCES ansatt(id);
--koblingstabell for prosjekt og medlemmer
CREATE TABLE prosjekt_medlemmer
(

    ansatt_id    int REFERENCES ansatt (id),
    prosjekt_id  int REFERENCES prosjekt (id),
    er_aktiv     BOOLEAN DEFAULT TRUE,
    antall_timer int     DEFAULT 0,
    rolle        VARCHAR(50),
    PRIMARY KEY (ansatt_id, prosjekt_id)
);
