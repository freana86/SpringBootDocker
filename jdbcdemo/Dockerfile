FROM openjdk:8
ADD target/docker-pl-mysql.jar docker-pl-mysql.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${ENV}" ,"docker-pl-mysql.jar"]