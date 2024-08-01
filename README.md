<div align="center">
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="Java" width="40" height="40"/>
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" alt="Spring" width="40" height="40"/>
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/maven/maven-original.svg" alt="Maven" width="40" height="40"/>
  <img src="https://i.imgur.com/aN921yZ.png" alt="Lombok" width="40" height="40"/>
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/junit/junit-original.svg" alt="JUnit 5" width="40" height="40"/>

  <br>
  <h1>Javer API</h1>
  <p>Um microserviço construído em springboot que realiza requisições REST que para o microserviço <a href="https://github.com/barbarastefany/javer-database">javer-database</a>.</p>
</div>

---
## Tecnologias e Dependências Utilizadas <a id="tecnologias-utilizadas"></a>
- **JDK 22**
- **Spring Boot 3.3.2**
- **Spring Cloud 2023.0.3**
- **OpenFeign**
- **Maven**
- **JUnit 5**
- **Mockito**
- **Springboot Starter Test**
- **Springboot Starter Validation**
- **Lombok**

---
## Como Executar <a id="como-executar"></a>
> [!IMPORTANT]
> Você deve estar com o microserviço [javer-database](https://github.com/barbarastefany/javer-database) em execução para usufruir deste microserviço.
>
> É necessário configurar esta aplicação para rodar na porta 8081 visto que a aplicação javer-database roda na porta 8080.
## Instruções de execução:
1. Clone o repositório:
```
git clone https://github.com/barbarastefany/javer-api
```
2. Importe a aplicação para a sua IDE
3. Execute a classe main:
```
JaverApiApplication
```
4. Realize as requisições POST, GET, PUT E DELETE.
> [!TIP]
> Você pode utilizar o [Postman](https://www.postman.com/downloads), o [Insomnia](https://insomnia.rest/download) ou até o próprio Swagger para enviar suas requisições e o [MySQL Workbench](https://dev.mysql.com/downloads/workbench) para visualizar o banco de dados.

---
## Documentação da API
A documentação da API está disponível através do Swagger UI em:
```
http://localhost:8081/swagger-ui/index.html
```
---
## Autoria
[Barbara Carvalho](https://github.com/barbarastefany)

---
## Licença
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)