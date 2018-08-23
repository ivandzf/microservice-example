# MICROSERVICE STACK
- Service Discovery
- Api Gateway
- User Service Consumer
- User Service Producer

#### Service Discovery
Service discovery is the automatic detection of devices and services offered by these devices on a computer network.
##### Tech Stack
```
- Netflix Eureka Server
```
##### Build
```
mvn clean install
```
##### Running
```
mvn spring-boot:run
```
> server is running on port 8761

#### Api Gateway
The wrapper of services
##### Tech Stack
```
- Netflix Zuul Proxy
```
##### Build
```
mvn clean install
```
##### Running
```
mvn spring-boot:run
```
> server is running on port 9000

#### User Service Consumer
Consume service from producer using load balancer, monitoring service usage on Hystrix Dashboard, Circuit Breaker when producer is down
##### Tech Stack
```
- Rest Template with Load Balancer
- Hystrix Dashboard
- Circuit Breaker with fallback method
- Cache service
```
##### Build
```
mvn clean install
```
##### Running
```
mvn spring-boot:run
```
> server is running on port 9999

#### User Service Producer
The main service that connected to database for transfering data
##### Tech Stack
```
- Mongo DB
- Mongo Repository like a JPA
- Asynchronous Method
```
##### Build
```
mvn clean install
```
##### Running
```
mvn spring-boot:run
```
> server is running on port 9998

## Consume From Client (Web and Mobile)
> Http Method : GET
> ```
> http://localhost:9000/api/user-service/getall
> ```
> - localhost:9000 is host api gateway
> - /api/user-service is path between api gateway and user service consumer
> - /getall is path from user service consumer