# Use a imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim as build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o Maven Wrapper e o arquivo de configuração do Maven (pom.xml)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copia o código fonte da aplicação para o diretório de trabalho
COPY src ./src

# Torna o script mvnw executável
RUN chmod +x mvnw

# Executa o comando Maven para construir o projeto e empacotá-lo em um JAR
RUN ./mvnw clean package -DskipTests

# Segunda etapa: cria uma imagem leve para rodar a aplicação
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR gerado na etapa anterior para o diretório de trabalho
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta que a aplicação Spring Boot vai rodar
EXPOSE 8080

# Comando para rodar a aplicação quando o container for iniciado
ENTRYPOINT ["java", "-jar", "app.jar"]