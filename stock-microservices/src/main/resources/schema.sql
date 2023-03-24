CREATE TABLE product (
--                          id INT AUTO_INCREMENT PRIMARY KEY,
                         product_id INT UNIQUE PRIMARY KEY ,
                         name VARCHAR(250) NOT NULL,
                         description VARCHAR(250) NOT NULL,
                         price DECIMAL DEFAULT NULL,
                         count INT NOT NULL
);
