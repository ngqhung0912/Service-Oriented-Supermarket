CREATE SEQUENCE public.hibernate_sequence START WITH 100 INCREMENT 1;
CREATE TABLE customer (
                         id INT PRIMARY KEY ,
                         username VARCHAR(20) UNIQUE ,
                         name VARCHAR(30) NOT NULL,
                         age INT NOT NULL,
                         email VARCHAR(50) NOT NULL,
                         address VARCHAR(100) NOT NULL
);
