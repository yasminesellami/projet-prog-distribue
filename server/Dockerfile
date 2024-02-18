# Utilisez une image de base avec Java
FROM openjdk:17-alpine

# Définissez le répertoire de travail
WORKDIR /app

# Copiez le fichier JAR généré par Maven dans le conteneur
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
# Exposez le port sur lequel l'application Spring Boot écoute
EXPOSE 8080

# Commande de démarrage de l'application
CMD ["java", "-jar", "app.jar"]
