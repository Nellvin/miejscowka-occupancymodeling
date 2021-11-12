FROM maven:3.8.3-jdk-11-slim AS build
#RUN addgroup -S springdocker && adduser -S springdocker -G springdocker
#USER springdocker:springdocker
RUN mkdir -p /app-occupancymodeling
#WORKDIR /app-occupancymodeling
COPY src /app-occupancymodeling/src
COPY pom.xml /app-occupancymodeling
RUN mvn -f /app-occupancymodeling/pom.xml clean package -DskipTests

FROM openjdk:11-jre-slim
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
COPY --from=build /app-occupancymodeling/target/miejscowka-occupancymodeling.jar /usr/local/lib/miejscowka-occupancymodeling.jar
EXPOSE 8070
ENTRYPOINT ["java","-jar","/usr/local/lib/miejscowka-occupancymodeling.jar"]
#HEALTHCHECK --interval=25s --timeout=3s --retries=2 CMD wget --spider http://localhost:8070/actuator/health || exit 1
#EXPOSE 8080