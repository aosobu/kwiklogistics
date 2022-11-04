FROM openjdk:8
EXPOSE 9092
ADD target/kwiklogistics.jar kwiklogistics.jar
ENTRYPOINT ["java", "-jar", "/kwiklogistics.jar"]