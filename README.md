# ofertas-api

Microservice Spring Boot para publicação de ofertas e suas respectivas opções de compra

## Tecnologias

* [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
* [Gradle (build tool)](https://gradle.org/)
* [MongoDB](https://www.mongodb.com)

Neste projeto foi utilizado o MongoDB embarcado, então não há necessidade instalar o MongoDB localmente.

Os dados ficam em memória e são perdidos quando a aplicação é encerrada.

Caso prefira utilizar um MongoDB local:

* Descomentar as configurações do Mongo em /src/main/resources/application.properties.
* Alterar a linha abaixo em dependencies.gradle:
    * de implementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:$flapdoodleEmbeddedMongoDB_version")
    * para testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:$flapdoodleEmbeddedMongoDB_version")

Para clonar o projeto:

`$> git clone https://github.com/vagneroliveirars/ofertas-api.git`

## Build

Utilizar o gradle wrapper presente na raiz do projeto:

`$> ./gradlew build`

Para rodar o projeto:

`$> ./gradlew bootRun`

## Documentação da API

* [Swagger](http://localhost:8080/swagger-ui.html)
