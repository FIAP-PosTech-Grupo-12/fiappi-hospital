FROM maven:3.9.9-eclipse-temurin-17-alpine AS build
COPY /appointment/src /usr/src/appointment/src
COPY /notification/src /usr/src/notification/src
COPY /appointment/pom.xml /usr/src/appointment
COPY /notification/pom.xml /usr/src/notification
COPY /pom.xml /usr/src/
RUN mvn -f /usr/src/appointment/pom.xml clean package -DskipTests
RUN mvn -f /usr/src/notification/pom.xml clean package -DskipTests

FROM bitnami/java:17-debian-11
COPY --from=build /usr/src/appointment/target/appointment-0.0.1-SNAPSHOT.jar /app/appointment-0.0.1-SNAPSHOT.jar
COPY --from=build /usr/src/notification/target/notification-0.0.1-SNAPSHOT.jar /app/notification-0.0.1-SNAPSHOT.jar
COPY wrapper_script.sh /app/wrapper_script.sh
RUN ["chmod", "+x", "/app/wrapper_script.sh"]
EXPOSE 8080 8081
CMD ./app/wrapper_script.sh