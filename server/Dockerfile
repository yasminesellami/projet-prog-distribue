FROM openjdk:11-jdk-slim as build
WORKDIR /workspace/app

# Copier le wrapper Maven et accorder les permissions d'exécution
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN chmod +x ./mvnw

# Maintenant, exécuter le wrapper Maven pour construire le projet
RUN ./mvnw install -DskipTests

FROM openjdk:11-jre-slim
VOLUME /tmp
COPY --from=build /workspace/app/target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
