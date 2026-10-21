FROM maven:3.9.16-eclipse-temurin-27

WORKDIR /spring
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run
