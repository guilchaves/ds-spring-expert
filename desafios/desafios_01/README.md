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
