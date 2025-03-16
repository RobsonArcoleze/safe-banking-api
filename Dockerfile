# Usar uma imagem base do OpenJDK para Java 11
FROM openjdk:11-jdk-slim

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR da aplicação para o container
COPY target/safe-banking-api-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta em que a API rodará
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]