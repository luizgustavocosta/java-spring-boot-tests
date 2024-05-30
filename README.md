# java-spring-boot-tests

## 🏗️ Modules

| # | Name              | Description             |
|---|-------------------|-------------------------|
| 1 | tropicalflix      | The movie system        |
| 2 | financial-service | The fake payment system |

## 🧮 Tech stack

| # | Tech           | Version |
|---|----------------|---------|
| 1 | Java           | 21      |
| 2 | Spring Boot    | 3.2.5   |
| 3 | MySQL          | 8.3.0   |
| 4 | JUnit          | 5.10.2  |
| 5 | Gatling        | 3.11.2  |
| 6 | JaCoCo         | 0.8.12  |
| 7 | H2             | 2.2.224 |
| 8 | Testcontainers | 1.19.8  |

## ❓ Why?

To demonstrate how to use the libraries to test a Spring Boot application.

## 👉 Features

- List of movies, genres, and actors
- Edit of genres and actors
- Fake subscription

## ⚡ Quick Start
- Clone the project and change to project directory
```shell
git clone https://github.com/luizgustavocosta/java-spring-boot-tests.git
cd java-spring-boot-tests
```

- Start the financial-service. This service runs on port 8081
```shell
cd financial-service &&  mvn spring-boot:run
```

- Start the Tropicalflix service using the in-memory database *. This service runs on port 8080
```shell
cd tropicalflix && mvn spring-boot:run -Dspring-boot.run.profiles=h2
```
* You can choose 3 profiles.
- h2 (in-memory database)
- mysql (create a new MySQL database and add data)
- mysql-image (Use a MySQL image that contains data)

## Commands

### Maven

#### Run any module

```shell
mvn spring-boot:run
````

#### Run using in-memory database

```shell
mvn spring-boot:run -Dspring-boot.run.profiles=h2
```

#### Run using MySQL database

```shell
mvn spring-boot:run -Dspring-boot.run.profiles=h2
```

#### Generate JaCoCo report

```shell
mvn jacoco:report
````

#### Fire the performance test

```shell
mvn gatling:test
````

#### Performe the Cucumber and IT tests

```shell
mvn failsafe:integration-test
```

### Docker

#### Run

```shell
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=Tropicalflix -e MYSQL_USER=root -e MYSQL_PASSWORD=password --name=mysql mysql/mysql-server:8.0.32
```

#### Run

##### H2

```shell
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=h2 16bits/tropicalflix:0.0.1
```

##### MySQL

```shell
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=mysql-image 16bits/tropicalflix:0.0.1
```

#### Build and run
##### Tropicalflix
```shell
docker build -t 16bits/tropicalflix:0.0.1 .
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=h2 16bits/tropicalflix:0.0.1
```
##### MySQL
```shell
docker build -t 16bits/tropicalflix_db:0.0.1 .
docker run -d -p 3306:3306 --name tropicalflixDB -e MYSQL_USER=root -e MYSQL_ROOT_PASSWORD=password tropicalflix_db:0.0.1
```

#### Push to Docker hub (repo)

```shell
docker push  16bits/financial-service:0.0.1
docker push  16bits/tropicalflix:0.0.1
docker build 16bits/tropicalflix_db:0.0.1
```

#### K6

```shell
k6 run --out json=test.json load_test.js
```

## 📚 References

| Subject                             | Link                                                                                                                        |
|-------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| Dan Vega repo                       | https://github.com/danvega/spring-boot-testing                                                                              |
| Testing                             | https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing                               |
| Test pyramid                        | https://martinfowler.com/articles/practical-test-pyramid.html                                                               |
| Digma blog                          | https://digma.ai/integration-testing-in-java/                                                                               |
| Testing                             | https://rieckpil.de/guide-to-testing-with-spring-boot-starter-test/                                                         |
| Testing Pitfalls                    | https://rieckpil.de/common-pitfalls-when-testing-spring-boot-applications/                                                  |
| Philip Riecks Spring I/O            | https://www.youtube.com/watch?v=hR0bbk2tsF0                                                                                 |
| Testing with JUnit5 and Spring      | https://www.youtube.com/watch?v=EysfXKdZpXQ                                                                                 |
| Spring for Architects               | https://www.youtube.com/watch?v=e3kgfcO0af4                                                                                 |
| Package by layer / feature Dan Vega | https://www.youtube.com/watch?v=B1d95I7-zsw                                                                                 |
| JMeter and Maven plugin             | https://qaautomation.expert/2023/07/24/how-to-use-the-jmeter-maven-plugin/#step-1-add-thread-group                          |
| Cucumber                            | https://github.com/mechero/spring-boot-cucumber                                                                             |
| Dependency Injection                | https://thepracticaldeveloper.com/cucumber-tests-spring-boot-dependency-injection/                                          |
| Surefire                            | https://maven.apache.org/surefire/maven-surefire-report-plugin/usage.html                                                   |
| AssertJ                             | https://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html                                             |
| AssertJ  Lists                      | https://reflectoring.io/assertj-lists/                                                                                      |
| MySQL error                         | https://serverfault.com/questions/793058/can-not-access-mysql-docker                                                        |
| K6                                  | https://eltonminetto.dev/post/2024-01-11-load-test-k6/                                                                      |
| Swagger                             | http://localhost:8080/swagger-ui/index.html                                                                                 |
| Digma                               | https://digma.ai/the-spring-way-of-doing-things-9-ways-to-improve-your-spring-boot-skills/                                  |
| Rest-Assured                        | https://github.com/rest-assured/rest-assured/wiki/GettingStarted#rest-assured                                               |
| WireMock                            | https://github.com/maciejwalkowiak/wiremock-spring-boot                                                                     |
| WireMock Fix                        | https://stackoverflow.com/questions/78151993/eof-reached-while-reading-with-spring-restclient-and-wiremock-using-jdkclienth |
| Emojis                              | https://github.com/ikatyang/emoji-cheat-sheet/blob/master/README.md                                                         |