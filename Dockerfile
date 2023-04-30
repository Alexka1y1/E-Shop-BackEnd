FROM eclipse-temurin:17-jdk-focal

VOLUME /tmp

COPY build/libs/E-shop-1.2.1.jar FSSE2212_Backend.jar

ENTRYPOINT ["java","-jar","/FSSE2212_Backend.jar"]