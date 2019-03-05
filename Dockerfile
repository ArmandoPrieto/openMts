FROM openjdk:8-jdk-alpine 
RUN apk --no-cache add curl
COPY build/libs/*.jar mts-gorm.jar
CMD java ${JAVA_OPTS} -jar mts-gorm.jar