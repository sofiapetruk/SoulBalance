# Etapa 1: Build do projeto
FROM ubuntu:latest AS build

# Instalar dependências do JDK e Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven && \
    apt-get clean

# Definir diretório de trabalho
WORKDIR /app

# Copiar os arquivos necessários
COPY pom.xml .
COPY src ./src

# Fazer o build do projeto sem rodar os testes
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final leve para execução
FROM eclipse-temurin:17-jdk-jammy

# Definir diretório de trabalho
WORKDIR /app

# Copiar o JAR gerado da etapa de build
COPY --from=build /app/target/SoulBalance-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta da aplicação
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]