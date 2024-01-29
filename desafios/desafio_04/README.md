# DESAFIO: DSMovie Jacoco 

##### Java Spring Expert

### Desafio

Você deve implementar todos os testes unitários de service para o projeto DSMovie.

### Sobre o projeto DSMovie

 Este é um projeto de filmes e avaliações de filmes. A visualização dos dados dos filmes é pública (não necessita login), porém as alterações de filmes (inserir, atualizar, deletar) são permitidas apenas para usuários ADMIN. As avaliações de filmes podem ser registradas por qualquer usuário logado CLIENT ou ADMIN. A entidade Score armazena uma nota de 0 a 5 (score) que cada usuário deu a cada filme. Sempre que um usuário registra uma nota, o sistema calcula a média das notas de todos usuários, e armazena essa nota média (score) na entidade Movie, juntamente com a contagem de votos (count).</br>

![entities](./entities.jpg)

Abaixo estão os testes unitários que você deverá implementar. Com todos os testes, o Jacoco deve reportar 100% de cobertura, mas o mínimo para aprovação no desafio são 12 dos 15 testes.</br>

#### MovieServiceTests:
- findAllShouldReturnPagedMovieDTO</br>
- findByIdShouldReturnMovieDTOWhenIdExists</br>
- findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist</br>
- insertShouldReturnMovieDTO</br>
- updateShouldReturnMovieDTOWhenIdExists</br>
- updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist</br>
- deleteShouldDoNothingWhenIdExists</br>
- deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist</br>
- deleteShouldThrowDatabaseExceptionWhenDependentId</br>
#### ScoreServiceTests:
- saveScoreShouldReturnMovieDTO</br>
- saveScoreShouldThrowResourceNotFoundExceptionWhenNonExistingMovieId</br>
#### UserServiceTests:
- authenticatedShouldReturnUserEntityWhenUserExists</br>
- authenticatedShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists</br>
- loadUserByUsernameShouldReturnUserDetailsWhenUserExists</br>
- loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists</br>
 

### Critérios de avalição

Critérios de correção:
Mínimo para aprovação: 12 de 15

- MovieService.findAll() deve retornar uma página de filmes</br>
- MovieService.findById(id) deve retornar um filme quando o id existir</br>
- MovieService.findById(id) deve lançar ResourceNotFoundException quando o id não existir</br>
- MovieService.insert(dto) deve retornar um filme</br>
- MovieService.update(id, dto) deve retornar um filme quando o id existir</br>
- MovieService.update(id, dto) deve lançar ResourceNotFoundException quando o id não existir</br>
- MovieService.delete(id) deve fazer nada quando o id existir</br>
- MovieService.delete(id) deve lançar ResourceNotFoundException quando o id não existir</br>
- MovieService.delete(id) deve lançar DatabaseException quando o id for dependente</br>
- UserService.authenticated() deve retornar um usuário quando houver usuário logado</br>
- UserService.authenticated() deve lançar UsernameNotFoundException quando não houver usuário logado</br>
- UserService.loadUserByUsername(username) deve retornar um UserDetails quando o username existir</br>
- UserService.loadUserByUsername(username) deve lançar UsernameNotFoundException quando o username não existir</br>
- ScoreService.saveScore(dto) deve retornar os dados do filme quando o id existir</br>
- ScoreService.saveScore(dto) deve lançar ResourceNotFoundException quando o id do filme não existir</br>

### Competências avaliadas

- Testes unitários em projeto Spring Boot com Java</br>
- Implementação de testes unitários com JUnit e Mockito</br>
- Cobertura de código com Jacoco</br>
