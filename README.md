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