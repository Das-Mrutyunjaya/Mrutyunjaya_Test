FROM gradle:jdk17
WORKDIR /selenium
COPY object_repository /selenium
COPY env.json /selenium
COPY build.gradle /selenium
COPY gradlew /selenium
COPY settings.gradle /selenium
COPY Cucumber7X-1.0.0-all.jar /selenium
CMD ["java","-jar","/selenium/Cucumber7X-1.0.0-all.jar"]
