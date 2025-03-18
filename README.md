# safe-banking-api
Safe Banking é um projeto desenvolvido usando java 11 e spring boot 2.7.18 Consiste em aplicação de agendamento de transferência bancária e taxação de acordo com a data informada pelo cliente.
- **YOUTUBE** - Video explicativo: [Safe-Bank](https://youtu.be/TRtNo_0U-K4)
- OBS: Este projeto roda juntamento com uma aplicação em Angular neste [LINK](https://github.com/RobsonArcoleze/safe-banking-app)

## Arquitetura: Clean Architecture

A arquitetura **Clean Architecture** foi escolhida para garantir a separação de responsabilidades, modularidade e facilitar a manutenção da aplicação ao longo do tempo. A principal ideia da Clean Architecture é isolar o domínio (regras de negócio) das dependências externas, como frameworks, bibliotecas e banco de dados. Esse isolamento proporciona:

- **Facilidade de testes:** Com a separação das camadas, é possível testar cada componente isoladamente, sem se preocupar com integrações externas.
- **Escalabilidade:** A arquitetura facilita a adição de novas funcionalidades sem afetar partes já existentes da aplicação.
- **Independência de frameworks:** A estrutura do código é projetada de forma que você pode mudar de frameworks, bancos de dados ou qualquer outra tecnologia sem impactar diretamente o núcleo da aplicação.

### Camadas da aplicação

- **Domain:** Contém as regras de negócio puras, sem dependência de bibliotecas ou frameworks.
- **Application:** Representa a lógica de interação entre as entidades do domínio.
- **Interface:** Interface do sistema (exemplo: controllers REST).
- **Infrastructure:** Implementação das interfaces, como repositórios, serviços externos, etc.

---
## Ferramentas Utilizadas

- **Spring Boot 2.7.18:** Framework principal para a construção da aplicação.
- **Spring Data JPA:** Para abstração do acesso ao banco de dados.
- **Spring Web:** Para criação de endpoints RESTful.
- **Spring Validation:** Para validação de dados de entrada.
- **MapStruct:** Para mapeamento de objetos entre camadas da aplicação.
- **Lombok:** Para reduzir a verbosidade do código, gerando automaticamente getters, setters e outros métodos.
- **Swagger:** Para documentação e testes interativos da API.
- **H2 Database:** Banco de dados em memória.

---
## Banco de Dados

Utilizamos o **H2** como banco de dados em memória, requisito para este projeto

---
## Testes Unitários

Os testes unitários são implementados na camada de domínio para garantir que as regras de negócio funcionem como esperado. As dependências são isoladas utilizando mocks, permitindo que o domínio seja testado de forma independente de outras camadas e tecnologias.

---
## Docker

A aplicação foi configurada para rodar em containers Docker, facilitando a execução em qualquer ambiente. O Dockerfile está presente no repositório e permite a construção de uma imagem Docker da aplicação.

### Dockerfile

```dockerfile
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

```
---
## Pipeline

A aplicação inclui uma pipeline para testes e deploy. A pipeline executa os seguintes passos:

- **Build:** Compila o código e gera o arquivo .jar.
- **Testes Unitários:** Executa os testes na camada de domínio.
- **Deploy:** Realiza o deploy da imagem no DockerHub
---

## Como usar

Clone o repositório
```
git@github.com:RobsonArcoleze/safe-banking-api.git
cd safe-banking-api
```

Executa
```
Pode se executado em uma ide da sua preferência
Usando o docker: 
    docker build -t robsonarcoleze/safe-banking-api:latest .
    docker run -p 8080:8080 nome-da-imagem
```
--- 
## Swagger

Acesse o Swagger:
`http://localhost:8080/swagger-ui.html
`
---

## Autor
Robson Arcoleze
[Linkedin](https://www.linkedin.com/in/robsonarcoleze/)