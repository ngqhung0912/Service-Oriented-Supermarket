# Configuration Guide

## MySQL
````
docker run --name mysql-docker -e MYSQL_ROOT_PASSWORD=Aa123456 -p 13306:3306 -d mysql:8

docker exec -i mysql-docker mysql -u root -pAa123456
CREATE DATABASE soa_e_shopping;
CREATE DATABASE soa_payment;
exit

docker exec -i mysql-docker mysql -u root -pAa123456 soa_e_shopping < /Users/yuemingwu/Documents/soa_e_shopping.sql
docker exec -i mysql-docker mysql -u root -pAa123456 soa_payment < /Users/yuemingwu/Documents/soa_payment.sql
````
## Kafka Kubenetes
