<<<<<<< HEAD
# Curso Java Spring Expert

Esse repositório contém exercícios e projetos desenvolvidos durante o treinamento [Java Spring Expert](https://devsuperior.com.br/curso-java-spring-expert), da plataforma Dev Superior.</br>


### Tópicos abordados
- Tipos de testes
- TDD
- JUnit
- Mockito & MockBean
- Bean Validation
- Spring Security
- Jacoco
- MockMvc
- RestAssured

### Ementa 

- CRUD e estruturação de projeto
- Testes automatizados
- Validação e segurança
- Consultas e finalização do projeto
- Cobertura de testes com Jacoco
- Testes de API com RestAssured

### Desafios e projetos
✅ [TDD Event City](https://github.com/guilchaves/spring-expert-desafio-01)</br>
✅ [Validação e segurança](https://github.com/guilchaves/spring-expert-desafio-02)</br>
✅ [MovieFlix](https://github.com/guilchaves/spring-expert-desafio-03)</br>
✅ [DSMovie Jacoco](https://github.com/guilchaves/spring-expert-desafio-04)</br>
☐ DSMovie RestAssured</br>
=======
# DESAFIO: TDD Event City
##### Java Spring Expert

### Desafio
Implemente as funcionalidades necessárias para que os testes do projeto abaixo passem.</br>

Este é um sistema de eventos e cidades com uma relação N-1 entre eles:</br>

![entities](./entities.jpg)

### Critérios de avalição

- DELELE /cities/{id} deve retornar 404 Not Found quando id não existir</br>
- DELELE /cities/{id} deve retornar 204 No Content quando id for independente</br>
- DELETE /cities/{id} deve retornar 400 Bad Request quando id for dependente</br>
- POST /cities deve inserir recurso</br>
- GET /cities deve retornar recursos ordenados por nome</br>
- PUT /events deve atualizar recurso quando id existir</br>
- PUT /events deve retornar 404 Not Found quando id não existir</br>

### Competências avaliadas

- Desenvolvimento TDD de API Rest com Java e Spring Boot</br>
- Implementação de cenários de busca, inserção, deleção e atualização</br>
- Tratamento de exceções em API com respostas HTTP customizadas</br>
>>>>>>> desafio_01/main
