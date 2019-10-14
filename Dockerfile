FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD /build/libs/kotlin-postgresql-1.1-SNAPSHOT.jar kotlin-postgresql-1.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "kotlin-postgresql-1.1-SNAPSHOT.jar"]