# Etapa de Build
FROM maven:3.9.5-eclipse-temurin-21-alpine AS build

# Copiar os arquivos necessários para o container
COPY src /app/src
COPY pom.xml /app

WORKDIR /app
# Executar a construção do projeto
RUN mvn clean install

# Etapa de Execução
FROM openjdk:11-jdk-slim

# Copiar o JAR gerado do estágio de build
COPY --from=build /app/target/safe-banking-api-0.0.1-SNAPSHOT.jar /app/app.jar

# Garantir que o diretório /app exista
WORKDIR /app

# Definir a porta padrão
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]