FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .
RUN ./gradlew build

ARG JAR_FILE=build/libs/tracker-0.0.1.jar
COPY ${JAR_FILE} application.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "application.jar"]

###############################################################################

#FROM eclipse-temurin:17-jre as builder
#WORKDIR application
#ARG JAR_FILE=build/libs/tracker-0.0.1.jar
#COPY ${JAR_FILE} application.jar
#RUN java -jar application.jar
#
#FROM eclipse-temurin:17-jre
#WORKDIR application
#COPY --from=builder application/dependencies/ ./
#COPY --from=builder application/spring-boot-loader/ ./
#COPY --from=builder application/snapshot-dependencies/ ./
#COPY --from=builder application/application/ ./
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

###############################################################################

#FROM eclipse-temurin:17-jdk-alpine AS builder
#ENV APP_HOME=/app/
#WORKDIR $APP_HOME
#COPY build.gradle gradlew gradlew.bat $APP_HOME
#COPY gradle $APP_HOME/gradle
#COPY . .
#RUN ./gradlew build
#
#FROM eclipse-temurin:17-jre-alpine
#WORKDIR /app
#
#COPY --from=builder /app/build/libs/tracker-0.0.1.jar application.jar
#
#EXPOSE 8081
#
#ENTRYPOINT ["java", "-jar", "/app/application.jar"]

###############################################################################