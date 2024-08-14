FROM maven:3.9.0-eclipse-temurin-17
COPY . /app
WORKDIR /app
RUN mvn clean install -DskipTests
EXPOSE 8081
CMD ["mvn", "spring-boot:run"]