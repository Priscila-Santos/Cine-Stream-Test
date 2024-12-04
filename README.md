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