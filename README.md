# Gestão de Loja de Roupas

## Descrição
Atividade acadêmica desenvolvida no 4º período do curso Engenharia de Software.

Este projeto é uma API RESTful desenvolvida utilizando Spring Boot para gerenciar uma loja de roupas. A API fornece funcionalidades de CRUD (Create, Read, Update, Delete) e utiliza JPQL para consultas. Além disso, o projeto inclui testes unitários e de integração com JUnit e integração com JaCoCo para garantir pelo menos 50% de coverage.

### Regras de Negócio e Validações
- A entidade principal é Venda. O sistema deve permitir salvar a venda com o cliente, o funcionário e os produtos em uma única requisição.
- Todos os atributos das entidades são obrigatórios, exceto endereço de entrega.
- Além de métodos básicos de CRUD (findAll, findById, delete, save e update), implementar ao menos 3 filtros (findBy...), sendo 01 com JPQL e 02 com métodos automáticos para cada repository de cada uma das 4 entidades.
- Todas as entidades devem possuir endpoints, então, haverá controller, service, repository para cada entidade.

## Tecnologias Utilizadas
- Java
- Spring Boot
- JUnit
- JaCoCo
- MySQL

## Instalação
Para instalar e configurar o projeto, siga os passos abaixo:

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/gestao-loja-roupas.git
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd gestao-loja-roupas
    ```

3. Configure o banco de dados MySQL:
    - Crie um banco de dados chamado `gestaoLojaRoupas`.
    - Atualize o arquivo `application.properties` com as credenciais do seu banco de dados.

4. Compile e construa o projeto usando Maven:
    ```bash
    mvn clean install
    ```

5. Execute a aplicação:
    ```bash
    mvn spring-boot:run
    ```

## Uso
Após a instalação, a API estará disponível em `http://localhost:8080`. 
