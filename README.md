# java-spring-boot-tests

https://github.com/danvega/spring-boot-testing/blob/main/src/test/java/dev/danvega/testdemo/todo/TodoControllerTest.java
https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing
https://martinfowler.com/articles/practical-test-pyramid.html
https://digma.ai/integration-testing-in-java/
https://rieckpil.de/guide-to-testing-with-spring-boot-starter-test/
https://rieckpil.de/common-pitfalls-when-testing-spring-boot-applications/
https://www.youtube.com/watch?v=hR0bbk2tsF0
https://www.youtube.com/watch?v=EysfXKdZpXQ
https://www.youtube.com/watch?v=e3kgfcO0af4
Dan Vega https://www.youtube.com/watch?v=B1d95I7-zsw
Amigos code - https://www.youtube.com/watch?v=9SGDpanrc8U
Gateway - https://spring.io/guides/gs/gateway
JMeter -> https://qaautomation.expert/2023/07/24/how-to-use-the-jmeter-maven-plugin/#step-1-add-thread-group
Cucumber -> https://github.com/mechero/spring-boot-cucumber
https://thepracticaldeveloper.com/cucumber-tests-spring-boot-dependency-injection/

Tests

AspectJ
https://maven.apache.org/surefire/maven-surefire-report-plugin/usage.html

mvn jacoco:report
mvn failsafe:integration-test
mvn surefire-report:failsafe-report-only

mvn site
mvn site:run


docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=Tropicalflix -e MYSQL_USER=root -e MYSQL_PASSWORD=password --name=mysql mysql/mysql-server:8.0.32
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=Tropicalflix -e MYSQL_USER=root -e MYSQL_PASSWORD=password mysql/mysql-server:8.0.32


docker build -t tropicalflix_db:0.0.1 .
docker run -d -p 3306:3306 --name tropicalflixDB -e MYSQL_USER=root -e MYSQL_ROOT_PASSWORD=password tropicalflix_db:0.0.1

https://serverfault.com/questions/793058/can-not-access-mysql-docker

Gatling - mvn gatling:test

https://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html
https://reflectoring.io/assertj-lists/