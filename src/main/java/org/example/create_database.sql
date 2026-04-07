DROP SCHEMA innlevering_jpa CASCADE;
CREATE SCHEMA innlevering_jpa;
SET search_path TO innlevering_jpa;


--TABELLER--
CREATE TABLE avdeling
(
    avdeling_id      SERIAL PRIMARY KEY,
    navn    VARCHAR(30),
    sjef_id int
);

CREATE TABLE ansatt
(
    ansatt_id             SERIAL PRIMARY KEY,
    brukernavn     char(4),
    fornavn        VARCHAR(30),
    etternavn      VARCHAR(30),
    ansettelsedato DATE,
    stilling       varchar(30),
    maanedslonn    decimal,
    avdeling_id    int REFERENCES avdeling (avdeling_id)
);
CREATE TABLE prosjekt
(
    prosjekt_id          SERIAL PRIMARY KEY,
    navn        VARCHAR(30),
    beskrivelse VARCHAR(255)
    --PROSJEKT_ANSATTE tabell


);
-- REDIGERER TABELLEN PGA CIRCULAR DEPENDENCY ellers
ALTER TABLE avdeling
    ADD CONSTRAINT fk_sjef
        FOREIGN KEY (sjef_id) REFERENCES ansatt(ansatt_id) ON DELETE SET NULL;
--koblingstabell for prosjekt og medlemmer
CREATE TABLE prosjekt_medlemmer
(

    ansatt_id int REFERENCES ansatt(ansatt_id) ON DELETE CASCADE,
    prosjekt_id int REFERENCES prosjekt(prosjekt_id) ON DELETE CASCADE,

    er_aktiv     BOOLEAN DEFAULT TRUE,
    antall_timer int     DEFAULT 0,
    rolle        VARCHAR(50),
    PRIMARY KEY (ansatt_id, prosjekt_id)
);


-- LEGGE TIL TEST DATA TIL DATABASEN, avdelinger osv

SET search_path TO innlevering_jpa;

ALTER SEQUENCE avdeling_avdeling_id_seq RESTART WITH 1;

-- AVDELING
INSERT INTO avdeling (navn, sjef_id)
VALUES
    ('IT', NULL),
    ('HR', NULL),
    ('Økonomi', NULL),
    ('Design', NULL),
    ('Ledelse', NULL);

-- ANSATT (use IDs 1–5)
INSERT INTO ansatt (brukernavn, fornavn, etternavn, ansettelsedato, stilling, maanedslonn, avdeling_id)
VALUES
    ('AB12', 'Ola', 'Nordmann', '2022-03-01', 'Utvikler', 55000, 1),
    ('CD34', 'Kari', 'Hansen', '2021-06-15', 'HR-konsulent', 52000, 2),
    ('EF56', 'Per', 'Olsen', '2023-01-10', 'Regnskapsfører', 48000, 3),
    ('GH78', 'Anne', 'Larsen', '2020-11-20', 'Designer', 53000, 4),
    ('IJ90', 'Jon', 'Berg', '2024-02-05', 'Leder', 75000, 5);

-- UPDATE (use correct column name: id)
UPDATE avdeling SET sjef_id = 1 WHERE avdeling_id = 1; -- Ola
UPDATE avdeling SET sjef_id = 2 WHERE avdeling_id = 2; -- Kari
UPDATE avdeling SET sjef_id = 3 WHERE avdeling_id = 3; -- Per
UPDATE avdeling SET sjef_id = 4 WHERE avdeling_id = 4; -- Anne
UPDATE avdeling SET sjef_id = 5 WHERE avdeling_id = 5; -- Jon

