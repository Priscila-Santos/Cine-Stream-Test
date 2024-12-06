# **Cine Stream Testes**

Bem-vindo ao projeto de testes do CineStream! Este projeto contém testes automatizados para a API do CineStream, utilizando ferramentas como RestAssured, WireMock e JUnit.

## **Sumário**
- [Descrição](#descrição)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Projeto Cine Stream Cucumber Test](#Projeto-Cine-Stream-Cucumber-Test)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Como Executar os Testes](#como-executar-os-testes)
    - [Testes Unitários](#testes-unitários)
    - [Testes de Integração](#testes-de-integração)
    - [Testes End-to-End](#testes-end-to-end)
- [Mock de Componentes](#mock-de-componentes)
- [Projeto CineStream](#Projeto-CineStream)



## **Descrição**
Este projeto foi desenvolvido para testar a API do CineStream, garantindo que os endpoints funcionem conforme esperado. Utilizamos uma combinação de testes de unidade, integração e end-to-end para cobrir todos os aspectos da aplicação. Usamos Mockito para criar mocks e simular o comportamento de componentes durante os testes.

## **Tecnologias Utilizadas**
- **Java 21**
- **Spring Boot 3.4.0**
- **RestAssured**: Para testes de API REST.
- **WireMock**: Para simular respostas de APIs externas.
- **Cucumber**: Para testes de aceitação.
- **JUnit 5**: Para execução de testes.
- **Mockito**: Para criação de mocks nos testes unitários.

## **Projeto Cine Stream Cucumber Test**
#### **Repositório do projeto** [Cine Stream](https://github.com/Priscila-Santos/Spring-CineStream)
#### **Repositório do projeto** [Cine Stream Cucumber Testes](https://github.com/Priscila-Santos/CineStream-CucumberTest)
#### **Repositório do projeto** [Cine Stream Front-End](https://github.com/Priscila-Santos/cine-stream-front)


1. Clone o repositório:
   ```bash
   git clone https://github.com/Priscila-Santos/Spring-CineStream
    ```
## Estrutura do Projeto

   ```bash
cine-stream-test/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── test/
│   │               └── cine-stream_test/
│   │                   ├── CineStreamTestApplication.java
│   │                   ├── controller/
│   │                   │   └── AdviceController.java
│   │                   │   ├── SeriesController.java
│   │                   │   ├── FilmesController.java
│   │                   │   └── UsuariosController.java
│   │                   ├── service/
│   │                   │   └── FilmesService.java
│   │                   │   ├── SeriesService.java
│   │                   │   ├── UsuariosService.java
│   │                   │ 
│   │                   ├── repository/
│   │                   │   └── GeneroRepository.java
│   │                   │   ├── SeriesRepository.java
│   │                   │   ├── FilmesRepository.java
│   │                   │   └── UsuariosRepository.java
│   │                   └── client/
│   │                       └── ApiClient.java
│   │                       ├── SeriesClient.java
│   │                       ├── FilmesClient.java
│   │                       └── UsuariosClient.java
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── test/
│       │           └── cine-stream_test/
│       │               ├── CineStreamApplicationTests.java
│       │               ├── controller/
│       │               │   └── AdviceController.java
│       │               │   ├── SeriesControllerTest.java
│       │               │   ├── FilmesControllerTest.java
│       │               │   └── UsuariosControllerTest.java
│       │               ├── service/
│       │               │   └── CineStreamServiceTest.java
│       │               │   ├── SeriesServiceTest.java
│       │               │   ├── FilmesServiceTest.java
│       │               │   └── UsuariosServiceTest.java
│       │               ├── repository/
│       │               │   └── CineStreamRepositoryTest.java
│       │               │   ├── SeriesRepositoryTest.java
│       │               │   ├── FilmesRepositoryTest.java
│       │               │   └── UsuariosRepositoryTest.java
│       │               └── client/
│       │                   └── CineStreamClientTest.java
│       │                   ├── SeriesClientTest.java
│       │                   ├── FilmesClientTest.java
│       │                   └── UsuariosClientTest.java
│       └── resources/
│           └── wiremock/
│               └── responses/
│                   └── search_tv_lost.json
├── pom.xml
└── README.md

   ```
## Como Executar os Testes
Para executar os testes, use os seguintes comandos:

## Testes Unitários
Execute os testes unitários com JUnit:
```bash
  mvn test
```

## Testes de Integração
Execute os testes de integração com JUnit e WireMock:
```bash
  mvn verify -Pintegration
```

## Testes End-to-End
Execute os testes end-to-end com JUnit e RestAssured:

```bash
  mvn verify -Pintegrmvn verify -Pe2e
```

## Mock de Componentes
O Mockito foi utilizado para criar mocks de componentes durante os testes unitários. Isso permite simular o comportamento de componentes como serviços e repositórios sem a necessidade de interagir com o sistema completo.

## Exemplo de Teste com Mock
   ```java
package br.ada.cinestream_test.service;

import br.ada.cinestream_test.repository.CineStreamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class FilmeServiceTest {

    @Mock
    private CineStreamRepository repository;

    @InjectMocks
    private CineStreamService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarFilmePorId() {
        // Configurar o mock
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Filme("Inception", 2010)));

        // Executar o método
        Filme filme = service.buscarFilmePorId(1L);

        // Verificar o resultado
        assertNotNull(filme);
        assertEquals("Inception", filme.getTitle());
    }
}
   ```
## Projeto CineStream

O CineStream é uma aplicação que utiliza a API pública do TMDb para buscar informações de filmes e séries, e armazena dados temporariamente usando o banco de dados em memória H2. Este README explica como foi feito o consumo da API externa e a persistência dos dados.
A aplicação usa o banco de dados H2 para persistir dados em memória durante a execução. No arquivo application.properties, as configurações do H2 estão definidas como segue:
properties

### Configuração do banco de dados H2
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true

URL: jdbc:h2:mem:test - O banco de dados é criado em memória e será descartado ao final da execução.
Console: Está habilitado em /h2, onde é possível acessar a interface do banco.
Consumo da API do TMDb
A aplicação consome dados da API do TMDb através da classe ApiClient. Esta classe utiliza o RestTemplate do Spring para fazer requisições HTTP aos endpoints do TMDb. Abaixo estão alguns dos principais métodos e explicações de como foi feita a integração.
Configurações da API
As configurações da URL base da API e da chave de acesso estão definidas no arquivo application.properties:
properties
## Configuração da API TMDb
api.base.url=https://api.themoviedb.org/3
api.key=<chave_api>

Métodos para Consumo da API do TMDb
Abaixo estão os métodos principais implementados para consumir a API do TMDb, utilizando o RestTemplate do Spring para fazer requisições HTTP e obter dados de filmes e séries.
Buscar Filmes por Título.



    public Page<TmdbFilme> buscarFilmesPorTitulo(String titulo, Integer page) {
    String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
    .path("/search/movie")
    .queryParam("page", page)
    .queryParam("query", titulo)
    .queryParam("language", "pt-BR")
    .toUriString();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", String.format("Bearer %s", apiKey));

    HttpEntity<String> entity = new HttpEntity<>(headers);

    var response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        entity,
        new ParameterizedTypeReference<Page<TmdbFilme>>() {}
    );

    return response.getBody();
    }


**Parâmetros**: titulo (título do filme) e page (número da página).     
**Cabeçalhos de Autenticação**: O token da API é incluído no cabeçalho Authorization usando o formato Bearer.    
**Resposta**: Retorna um objeto Page<TmdbFilme> com os dados dos filmes correspondentes.    
**Buscar Series por Título** O método buscarSeriesPorTitulo é similar ao anterior, mas consulta o endpoint /search/tv para buscar séries de acordo com o título fornecido, retornando um objeto Page<TmdbSerie> com os dados das séries correspondentes.    
**Buscar Filmes Por Gêneros** O método generosFilmes consulta o endpoint /genre/movie/list para obter uma lista de gêneros disponíveis para filmes. Para séries, o método é semelhante.
**Buscar Filmes por Ano de Lançamento** O método buscarFilmesPorAnoLancamento realiza uma busca de filmes com base no ano de lançamento, consultando o mesmo endpoint /search/movie e filtrando os resultados por ano.

Configuração do Banco de Dados H2
A aplicação usa o banco de dados H2 para persistir dados em memória durante a execução. No arquivo application.properties, as configurações do H2 estão definidas como segue:
properties

# Configuração do banco de dados H2
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
URL: jdbc:h2:mem:test - O banco de dados é criado em memória e será descartado ao final da execução.

Console: Está habilitado em /h2, onde é possível acessar a interface do banco.

Consumo da API do TMDb
A aplicação consome dados da API do TMDb através da classe ApiClient. Esta classe utiliza o RestTemplate do Spring para fazer requisições HTTP aos endpoints do TMDb. Abaixo estão alguns dos principais métodos e explicações de como foi feita a integração.

Configurações da API
As configurações da URL base da API e da chave de acesso estão definidas no arquivo application.properties

# Configuração da API TMDb
api.base.url=https://api.themoviedb.org/3
api.key=<chave_api>
Métodos para Consumo da API do TMDb
Abaixo estão os métodos principais implementados para consumir a API do TMDb, utilizando o RestTemplate do Spring para fazer requisições HTTP e obter dados de filmes e séries.

Buscar Filmes por Título Este método, buscarFilmesPorTitulo, consulta o endpoint /search/movie para buscar filmes de acordo com o título fornecido. Ele recebe o título e o número da página como parâmetros.


    public Page<TmdbFilme> buscarFilmesPorTitulo(String titulo, Integer page) {
    String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
    .path("/search/movie")
    .queryParam("page", page)
    .queryParam("query", titulo)
    .queryParam("language", "pt-BR")
    .toUriString();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", String.format("Bearer %s", apiKey));

    HttpEntity<String> entity = new HttpEntity<>(headers);

    var response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        entity,
        new ParameterizedTypeReference<Page<TmdbFilme>>() {}
    );

    return response.getBody();
    }
**Parâmetros**: titulo (título do filme) e page (número da página). Cabeçalhos de Autenticação: O token da API é incluído no cabeçalho Authorization usando o formato Bearer. Resposta: Retorna um objeto Page<TmdbFilme> com os dados dos filmes correspondentes.

**Buscar Series por Título**: O método buscarSeriesPorTitulo é similar ao anterior, mas consulta o endpoint /search/tv para buscar séries de acordo com o título fornecido, retornando um objeto Page<TmdbSerie> com os dados das séries correspondentes.

**Buscar Filmes Por Gêneros**: O método generosFilmes consulta o endpoint /genre/movie/list para obter uma lista de gêneros disponíveis para filmes. Para séries, o método é semelhante.

**Buscar Filmes por Ano de Lançamento**: O método buscarFilmesPorAnoLancamento realiza uma busca de filmes com base no ano de lançamento, consultando o mesmo endpoint /search/movie e filtrando os resultados por ano.

## Data Transfer Objects (DTOs)
No projeto CineStream, os DTOs (Data Transfer Objects) desempenham um papel essencial na comunicação entre as camadas internas da aplicação e a camada de API que interage com o cliente. Eles facilitam o transporte de dados entre a API do TMDb, a lógica de negócios e as respostas enviadas aos usuários.

### Estrutura dos DTOs no CineStream
Para organizar e simplificar o fluxo de dados, os DTOs no CineStream são divididos em duas categorias principais:

`Request DTOs`: Estes DTOs recebem dados do cliente para fazer buscas e consultas na API. Por exemplo, FilmeRequest e SerieRequest capturam parâmetros como o título e o ano para filtrar os filmes e séries específicos na API TMDb.

`Response DTOs`: Estes DTOs estruturam a resposta da aplicação ao cliente, garantindo que apenas as informações relevantes sejam enviadas de volta. Por exemplo, FilmeResponse e SerieResponse contêm dados como o título, descrição, gênero, nota média e ano de lançamento, que são exibidos para o usuário final.

### Funções dos DTOs no Projeto
No contexto do CineStream, os DTOs têm as seguintes funções principais:

**Isolamento dos Dados Sensíveis**: Os DTOs garantem que apenas os dados necessários sejam transmitidos ao cliente, mantendo a lógica e as estruturas internas do projeto encapsuladas e protegidas.

**Facilidade de Manipulação e Validação**: Com os DTOs, a aplicação recebe e processa dados de forma padronizada. Isso simplifica a lógica de negócios, facilitando o processamento e a validação das informações antes de enviar uma resposta ao usuário.

**Padronização de Respostas**: Os Response DTOs asseguram que todas as respostas da API do CineStream tenham uma estrutura consistente, facilitando a compreensão e o uso da API por desenvolvedores e clientes.
