# Service-Oriented-Supermarket

A project for "Service-oriented Architecture with Web Service" class @ University of Twente.


## Configuration Guide

### Kubernetes 

The namespace "supermarket" yaml file is in the kubernetes-central folder. Create a namespace: 
````
kubectl apply -f kubernetes-central/supermarket-namespace.yaml
````
Then, in each microservice, there is a kubernetes folder. Inside the folder, there are two files: A development, and a service
for each, with the port specified in Reports-Documentations/ports.txt. 

CD into the folder, i.e. supermarket API gateway (old): 
````
cd supermarket-api-gateway-old
mvn clean package
docker build -t supermarket/supermarket-api-gateway . 
cd kubernetes 
kubectl apply -f . 
````
Re-do it for stock-microservices, customer-microservice, and barcode-reader. Other services is unavailable in Kubernetes 
due to problem with MySQL on Kubernetes. It can be tested separately using another folder we provided. 

### MySQL
````
docker run --name mysql-docker -e MYSQL_ROOT_PASSWORD=Aa123456 -p 13306:3306 -d mysql:8

docker exec -i mysql-docker mysql -u root -pAa123456
CREATE DATABASE soa_e_shopping;
CREATE DATABASE soa_payment;
exit

docker exec -i mysql-docker mysql -u root -pAa123456 soa_e_shopping < /Users/yuemingwu/Documents/soa_e_shopping.sql
docker exec -i mysql-docker mysql -u root -pAa123456 soa_payment < /Users/yuemingwu/Documents/soa_payment.sql
````
### Kafka Kubenetes

Download strimzi for management for kafka:
````
kubectl apply -f 'https://strimzi.io/install/latest?namespace=supermarket' -n supermarket
````
Apply the kafka configuration file
````
kubectl apply -f https://strimzi.io/examples/latest/kafka/kafka-ephemeral.yaml -n supermarket
````
To wait until Kafka cluster is up and running, you can run the following command:
````
kubectl wait kafka/my-cluster --for=condition=Ready --timeout=300s -n supermarket
````
More information: https://redhat-developer-demos.github.io/kafka-tutorial/kafka-tutorial/1.0.x/07-kubernetes.html