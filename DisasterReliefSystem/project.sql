/*
 Each time this file is executed, it will reset the database to the original state defined below.
 You can import this directly in your database by (a) manually entering the first three lines of
 commands form this file, (b) removing the first three lines of commands from this file, and
 (c) \i 'path_to_file\project.sql' (with appropriate use of \ or / based on OS).

 During grading, TAs will assume that these two tables exist, but will enter different values.
 Thus you cannot assume that any of the values provided here exist, but you can assume the tables
 exist.

 You may optionally create additional tables in the ensf380project database with demonstration 
 data, provided that you provide the information in a valid SQL file which TAs can import and
 clearly include this information in your instructions.
 */


DROP DATABASE IF EXISTS ensf380project;
CREATE DATABASE ensf380project;
\c ensf380project


CREATE TABLE INQUIRER (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50),
    phoneNumber VARCHAR(20) NOT NULL
);
INSERT INTO INQUIRER (id, firstName, lastName, phoneNumber) VALUES
(1, 'Dominik', 'Pflug', '123-456-9831'),
(2, 'Yaa', 'Odei', '123-456-8913'),
(3, 'Cecilia', 'Cobos', '123-456-7891'),
(4, 'Hongjoo', 'Park', '123-456-8912');
INSERT INTO INQUIRER (id, firstName, phoneNumber) VALUES
(5, 'Saartje', '123-456-7234'),
(6, 'Urjoshi', '456-123-4281');

CREATE TABLE INQUIRY_LOG (
    id SERIAL PRIMARY KEY,
    inquirer int NOT NULL,
    callDate DATE NOT NULL,
    details VARCHAR(500) NOT NULL,
    foreign key (inquirer) references INQUIRER(id) ON UPDATE CASCADE
);
-- Table definition for disaster_victims
CREATE TABLE disaster_victims (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    date_of_birth DATE,
    approximate_age INT
);

-- Table definition for medical_records
CREATE TABLE medical_records (
    id SERIAL PRIMARY KEY,
    disaster_victim_id INT,
    treatment_details TEXT,
    date_of_treatment DATE,
    FOREIGN KEY (disaster_victim_id) REFERENCES disaster_victims(id)
);

-- Table definition for family_relations
CREATE TABLE family_relations (
    id SERIAL PRIMARY KEY,
    person_one_first_name VARCHAR(50),
    person_one_last_name VARCHAR(50),
    person_two_first_name VARCHAR(50),
    person_two_last_name VARCHAR(50),
	relationship_type VARCHAR(50)
);
-- Table definition for central_relief_workers
CREATE TABLE central_relief_workers (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Table definition for location_based_relief_workers
CREATE TABLE location_based_relief_workers (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE supplies (
    id SERIAL PRIMARY KEY,
    item_name VARCHAR(255) NOT NULL,
    quantity INTEGER
);
CREATE TABLE individual_records (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    date_of_birth DATE,
    approximate_age INTEGER,
    details VARCHAR(255)
);



-- Populate central_relief_workers table
INSERT INTO central_relief_workers (username, password) VALUES
('central_worker1', 'password1'),
('central_worker2', 'password2'),
('central_worker3', 'password3');

-- Populate location_based_relief_workers table
INSERT INTO location_based_relief_workers (username, password) VALUES
('location_worker1', 'password1'),
('location_worker2', 'password2'),
('location_worker3', 'password3');
-- Insert rows into disaster_victims table
INSERT INTO disaster_victims (first_name, last_name, date_of_birth, approximate_age)
VALUES
    ('John', 'Doe', '1990-05-15', NULL), 
    ('Jane', 'Smith', NULL, 36),         
    ('Michael', 'Johnson', '1978-07-03', NULL); 

-- Insert rows into family_relations table with relationship_type
INSERT INTO family_relations (person_one_first_name, person_one_last_name, person_two_first_name, person_two_last_name, relationship_type)
VALUES
    ('John', 'Doe', 'Jane', 'Smith', 'Sibling'),
    ('Michael', 'Johnson', 'Sarah', 'Johnson', 'Spouse'),
    ('Jane', 'Smith', 'Michael', 'Johnson', 'Child-Parent');


INSERT INTO INQUIRY_LOG (id, inquirer, callDate, details) VALUES
(1, 1, '2024-02-28', 'Theresa Pflug'),
(2, 2, '2024-02-28', 'Offer to assist as volunteer'),
(3, 3, '2024-03-01', 'Valesk Souza'),
(4, 1, '2024-03-01', 'Theresa Pflug'),
(5, 1, '2024-03-02', 'Theresa Pflug'),
(6, 4, '2024-03-02', 'Yoyo Jefferson and Roisin Fitzgerald'),
(7, 5, '2024-03-02', 'Henk Wouters'),
(8, 3, '2024-03-03', 'Melinda'),
(9, 6, '2024-03-04', 'Julius');

-- Insert rows into medical_records table using existing disaster_victim_id values
INSERT INTO medical_records (disaster_victim_id, treatment_details, date_of_treatment)
SELECT id, 'Fractured arm', '2024-03-10'
FROM disaster_victims
WHERE first_name = 'John' AND last_name = 'Doe';

-- Find the id of DisasterVictim Jane Smith
INSERT INTO medical_records (disaster_victim_id, treatment_details, date_of_treatment)
SELECT id, 'Flu symptoms', '2024-03-12'
FROM disaster_victims
WHERE first_name = 'Jane' AND last_name = 'Smith';

-- Find the id of DisasterVictim Michael Johnson
INSERT INTO medical_records (disaster_victim_id, treatment_details, date_of_treatment)
SELECT id, 'Surgery for appendicitis', '2024-02-28'
FROM disaster_victims
WHERE first_name = 'Michael' AND last_name = 'Johnson';

INSERT INTO supplies (item_name, quantity)
VALUES ('Food', 100), ('Water', 200);

INSERT INTO individual_records (first_name, last_name, date_of_birth,approximate_age, details)
VALUES ('Alice', 'Johnson', '1985-10-25', null, 'was diagonsed with diabetes');
