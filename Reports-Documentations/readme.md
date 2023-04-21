# Configuration Guide

## MySQL for docker
````
docker run --name mysql-docker -e MYSQL_ROOT_PASSWORD=Aa123456 -p 13306:3306 -d mysql:8

docker exec -i mysql-docker mysql -u root -pAa123456
CREATE DATABASE soa_e_shopping;
CREATE DATABASE soa_payment;
exit

docker exec -i mysql-docker mysql -u root -pAa123456 soa_e_shopping < /Users/yuemingwu/Documents/soa_e_shopping.sql
docker exec -i mysql-docker mysql -u root -pAa123456 soa_payment < /Users/yuemingwu/Documents/soa_payment.sql
````
## Kafka Kubernetes
download strimzi for management for kafka
````
apply -f 'https://strimzi.io/install/latest?namespace=supermarket' -n supermarket
````
apply the kafka configuration file
````
kubectl apply -f https://strimzi.io/examples/latest/kafka/kafka-ephemeral.yaml -n default
````
To wait until Kafka cluster is up and running, you can run the following command:
````
kubectl wait kafka/my-cluster --for=condition=Ready --timeout=300s -n default
````

