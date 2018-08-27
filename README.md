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
- OAuth2 Server JDBC
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
Header `Authorization: Bearer token` must be included in any request

> Get Token<br>
> Request
> ```
> Http Method : POST
> URL : http://localhost:9000/oauth/token 
> Header : Authorization: Basic auth, Content-Type: application/x-www-form-urlencoded
> Body : grant_type: client_credentials
> ```
> 
> Response
> ```
> {
>      "access_token": "c4472fb0-3401-42fe-91c2-65e5627455ed",
>      "token_type": "bearer",
>      "expires_in": 286,
>      "scope": "read write"
>  }
> ```

> Access Services (Example)
> ```
> Http Method : GET
> Header : Authorization: Bearer c4472fb0-3401-42fe-91c2-65e5627455ed
> URL: http://localhost:9000/api/user-service/getall
> ```
> - localhost:9000 is host api gateway
> - /api/user-service is path between api gateway and user service consumer
> - /getall is path from user service consumer
> - Header from response get token
