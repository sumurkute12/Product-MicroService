
FROM openjdk:11
EXPOSE 8001
ADD ./target/rpm-product-microservice.jar rpm-product-microservice.jar
ENTRYPOINT ["java","-jar","rpm-product-microservice.jar"]