FROM maven:3.9-eclipse-temurin-21 AS build

LABEL authors="joni"
LABEL description="Backend for Album Collector"

WORKDIR /app
COPY pom.xml ./

RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S albumcollector && adduser -S albumcollector -G albumcollector

COPY --from=build /app/target/AlbumCollector-0.0.1-SNAPSHOT.jar app.jar

RUN chown albumcollector:albumcollector app.jar

USER albumcollector

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]