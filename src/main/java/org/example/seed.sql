SET search_path TO innlevering_jpa;
TRUNCATE TABLE avdeling, ansatt RESTART IDENTITY CASCADE;


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
UPDATE avdeling SET sjef_id = 5 WHERE avdeling_id = 1;
UPDATE avdeling SET sjef_id = 4 WHERE avdeling_id = 2;
UPDATE avdeling SET sjef_id = 3 WHERE avdeling_id = 3;
UPDATE avdeling SET sjef_id = 4 WHERE avdeling_id = 4;
UPDATE avdeling SET sjef_id = 5 WHERE avdeling_id = 5;
