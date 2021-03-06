# Builder
FROM gradle:7.3.1-jdk17-alpine AS builder
COPY --chown=gradle:gradle ./app /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

# Deploy
FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
RUN mkdir /app
RUN chown spring:spring /app
USER spring:spring
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]