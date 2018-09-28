# GENERIC API #

### Projeto ###

WebService Generic
Projeto Baseado em Spring boot com Rest e JPA e Banco de dados Postgresql


### Depêndencias ###

JDK 1.8_52
Maven 3.5 ou superior
PostgreSQL como banco de dados


### Configurações do Banco de Dados ###

    DB: postgres
    usuario: postgres
    senha: postgres

    Você pode alterar as configurações no arquivo:
    src/main/resources/application.properties



### Build ###

Apos o clone na pasta do projeto faça:
    mvn clean package

Caso não possua o banco de dados configurado:
    mvn clean package -DskipTests=true

Execute o projeto com:
    java -jar springbaseapi-0.1-SNAPSHOT.jar


### Endpoints para test via Swagger ###

toda a documentação pode ser encontrada aqui no proprio sistema

http://localhost:8080/swagger-ui.html


usuario padrão cadastrado no sistema
username: admin
password: 123456

