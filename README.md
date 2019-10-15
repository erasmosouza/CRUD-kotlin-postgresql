# kotlin-postgresql
Segue uma simples implementação utilizando:
- kotlin
- Springboot
- gradle

## Clonar o projeto
> git clone [URL_GIT]


## Build and running
Build:
>./gradlew build

Test it:
>./gradlew test

## Testando o Docker
Agora que temos o jar da aplicação, vamos criar o docker:
>docker build . -t demo-api:1.0

Checar se a criação foi com sucesso:
>docker images

Executar o docker-compose para subir o Postgres e a nossa Demo-Api
>docker-compose up -d


## Testando a aplicação
> curl http://localhost:8080/greeting?name=Kotlin

## Testando o Swagger
Agora pode abrir o abrir no navegador na seguinte url http://localhost:8080/swagger-ui.html e ver o resultado.


Para saber se tudo deu certo, vamos chamar nossa api pela porta exposta do docker (3080):
>$ curl http://localhost:3080/greeting?name=Docker  
{"id":1,"message":"Hello, Docker"}