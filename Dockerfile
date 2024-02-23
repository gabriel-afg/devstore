FROM ubuntu:latest AS build

RUN apt-get update && apt-get install openjdk-17-jdk maven -y && rm -rf /var/lib/apt/lists/*

COPY . .

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

EXPOSE 8080

# Copie o certificado para o contêiner
COPY --from=build ./certs/producao-542844-Sistema-de-Pagamento.p12 ./certs/producao-542844-Sistema-de-Pagamento.p12

COPY --from=build /target/devstore-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
