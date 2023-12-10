# redis-login

Redis Login is a simple login application that uses Redis as a cache to store user sessions.

    https://dip-mazumder.medium.com/spring-boot-redis-how-did-i-improved-user-login-experience-17922a5def68
## Tech Stack

- Java 17
- Spring Boot 3.0
- Spring Data JPA
- Redis
- PostgreSQL
- Docker
- Lombok

## Requirements

For building and running the application you need:

- [JDK 17 or newer](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org)
- [Redis](https://redis.io/)
- [PostgreSQL](https://www.postgresql.org/)
- [Lombok](https://projectlombok.org/)
- [Docker](https://www.docker.com/)

## Build & Run

```shell
  docker-compose -f docker-compose.yml up -d
```

```shell
  mvn clean install && mvn --projects backend spring-boot:run
```