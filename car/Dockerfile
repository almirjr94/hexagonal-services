FROM adoptopenjdk/openjdk16:alpine-jre as builder
WORKDIR source
ARG JAR_FILE=target/car-*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk/openjdk16:alpine-jre
EXPOSE 8080
WORKDIR application
COPY --from=builder source/dependencies/ ./
COPY --from=builder source/spring-boot-loader/ ./
COPY --from=builder source/snapshot-dependencies/ ./
COPY --from=builder source/application/ ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]