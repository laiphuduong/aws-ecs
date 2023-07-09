FROM openjdk:11

WORKDIR /usr/src/myapp
COPY ./target/aws-ecs-0.0.1-SNAPSHOT.jar /usr/src/myapp/aws-ecs-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "aws-ecs-0.0.1-SNAPSHOT.jar"]