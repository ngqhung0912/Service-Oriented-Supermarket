# Service-Oriented-Supermarket

An ongoing project for "Service-oriented Architecture with Web Service" class @ University of Twente. More details to be update soon.
## Test without Kubernetes
start consul
````
consul agent dev
````
start kafka
````
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
````
Configure the mysql
````
docker run --name mysql-docker -e MYSQL_ROOT_PASSWORD=Aa123456 -p 13306:3306 -d mysql:8
````
Create The database
````
docker exec -i mysql-docker mysql -u root -pAa123456
CREATE DATABASE soa_e_shopping;
CREATE DATABASE soa_payment;
exit
````
Import The database configuration
````
docker exec -i mysql-docker mysql -u root -pAa123456 soa_e_shopping < [path]/soa_e_shopping.sql
docker exec -i mysql-docker mysql -u root -pAa123456 soa_payment < [path]/soa_payment.sql
````
start application
````
Discounted Good Reservation Application
E Shopping Application
Payment Application
Stock Microservices Application
````

view product information:
http://localhost:8091/stock-microservices/overview

view cart information
http://localhost:8082/overview