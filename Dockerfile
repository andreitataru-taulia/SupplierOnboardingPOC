# On mac M1 the platform is linux/amd64
FROM arm64v8/gradle:7.4-jdk17 as build
ARG WORK_DIR=/workspace/bff
WORKDIR ${WORK_DIR}
VOLUME /tmp
#COPY build build
COPY --chown=gradle:gradle *.gradle ./
COPY --chown=gradle:gradle src src
RUN gradle clean build -x test --no-daemon
# use for troubleshooting --stacktrace --debug --scan

FROM arm64v8/openjdk:17-jdk-slim-buster as expander
ARG WORK_DIR=/workspace/bff
ARG JAR_FILE=${WORK_DIR}/build/libs/*.jar
WORKDIR ${WORK_DIR}
VOLUME /tmp
COPY --from=build ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract --destination extracted

FROM arm64v8/openjdk:17-jdk-slim-buster as runner
ARG WORK_DIR=/workspace/bff/extracted
VOLUME /tmp
COPY --from=expander ${WORK_DIR}/dependencies/ ./
COPY --from=expander ${WORK_DIR}/spring-boot-loader/ ./
COPY --from=expander ${WORK_DIR}/snapshot-dependencies/ ./
COPY --from=expander ${WORK_DIR}/application/ ./
HEALTHCHECK --interval=5s --timeout=3s CMD curl -m 5 -f http://localhost:9090/actuator/health || exit 1;
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
ENTRYPOINT java $JAVA_OPTS org.springframework.boot.loader.JarLauncher
