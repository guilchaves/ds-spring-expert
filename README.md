# DESAFIO: Validação e Segurança

##### Java Spring Expert

### Desafio

Implemente as funcionalidades necessárias para que os testes do projeto abaixo passem.</br>

Este é um sistema de eventos e cidades com uma relação N-1 entre eles:</br>

![entities](./entities.jpg)

### Regras de controle de acesso

- Somente rotas de leitura (GET) de eventos e cidades são públicas (não requer login).</br>
- Usuários CLIENT e/ou ADMIN podem inserir novos eventos (POST).</br>
- Os demais acessos são permitidos apenas a usuários ADMIN.</br>

### Regras de validação de _City_

- Nome não pode ser vazio</br>

### Regras de validação de _Event_

- Nome não pode ser vazio</br>
- Data não pode ser passada</br>
- Cidade não pode ser nula</br>

### Critérios de avalição

- POST /events deve retornar 401 Unauthorized para usuário não logado</br>
- POST /events deve retornar 201 Created para CLIENT logado e dados corretos</br>
- POST /events deve retornar 201 Created para ADMIN logado e dados corretos</br>
- POST /events deve retornar 422 Unproccessable Entity para ADMIN logado e nome em branco</br>
- POST /events deve retornar 422 Unproccessable Entity para ADMIN logado e data no passado</br>
- POST /events deve retornar 422 Unproccessable Entity para ADMIN logado e cidade nula</br>
- GET /events deve retornar 200 Ok com página de recursos</br>
- POST /cities deve retornar 401 Unauthorized para usuário não logado</br>
- POST /cities deve retornar 403 Forbidden para CLIENT logado</br>
- POST /cities deve retornar 201 Created para ADMIN logado e dados corretos</br>
- POST /cities deve retornar 422 Unprocessable Entity para ADMIN logado e nome em branco</br>
- GET /cities deve retornar 200 Ok com todos recursos ordenados por nome</br>

### Competências avaliadas

- Desenvolvimento TDD de API Rest com Java e Spring Boot</br>
- Implementação de segurança com Spring Security e OAuth2</br>
- Controle de acesso por rotas e perfis de usuário</br>
- Validação de dados com Bean Validation</br>