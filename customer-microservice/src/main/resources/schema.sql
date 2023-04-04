CREATE TABLE customer (
                         id INT AUTO_INCREMENT  PRIMARY KEY ,
                         username VARCHAR(20) UNIQUE ,
                         name VARCHAR(30) NOT NULL,
                         age INT NOT NULL,
                         email VARCHAR(50) NOT NULL,
                         address VARCHAR(100) NOT NULL
);
