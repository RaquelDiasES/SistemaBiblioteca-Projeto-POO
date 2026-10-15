# Sistema de Biblioteca — Definição das Classes

**Projeto:** SistemaBiblioteca-Projeto-POO  
**Linguagem:** Java  
**Disciplina:** Programação Orientada a Objetos (POO)  
**Status:** Proposta inicial para discussão em equipe

## 1. Objetivo

Desenvolver uma aplicação Java para gerenciar as operações básicas de uma biblioteca, incluindo cadastro de usuários e livros físicos, realização de empréstimos e registro de devoluções.

O projeto será desenvolvido utilizando Java puro, sem interface gráfica, banco de dados ou API. Os dados serão mantidos em memória durante a execução, e o console será utilizado apenas para interagir com o sistema e testar suas funcionalidades.

O foco principal é aplicar os conceitos de Programação Orientada a Objetos exigidos na atividade acadêmica.

## 2. Escopo e regras de negócio

O sistema deverá permitir:

- Cadastrar usuários.
- Cadastrar livros físicos.
- Consultar os livros cadastrados e disponíveis.
- Realizar empréstimos e registrar devoluções.
- Consultar empréstimos ativos e atrasados.

### Regras de negócio

1. Cada livro deverá possuir um código único.
2. Cada usuário poderá ter, no máximo, três empréstimos ativos.
3. Um livro não poderá possuir mais de um empréstimo ativo simultaneamente.
4. O prazo máximo de empréstimo será de **15 dias corridos**.
5. A data prevista de devolução será calculada automaticamente a partir da data do empréstimo.
6. Um empréstimo será considerado atrasado quando a data atual ultrapassar o prazo previsto e a devolução ainda não tiver sido registrada.
7. Livros destinados à consulta local não poderão ser emprestados para levar para casa.
8. Operações inválidas deverão ser tratadas por exceções personalizadas.

## 3. Definição das classes

### 3.1. `Biblioteca`

**Responsabilidade:** coordenar as principais operações do sistema.

**Atributos:**
- `nome: String`
- `catalogo: Catalogo`
- `usuarios: List<Usuario>`

**Métodos:**
- `cadastrarUsuario(Usuario usuario)`
- `cadastrarLivro(Livro livro)`
- `realizarEmprestimo(Usuario usuario, Livro livro)`
- `registrarDevolucao(Emprestimo emprestimo)`

A classe será responsável por coordenar as validações necessárias antes de realizar empréstimos e registrar devoluções.

### 3.2. `Catalogo`

**Responsabilidade:** armazenar e consultar os livros cadastrados.

**Atributos:**
- `livros: List<Livro>`

**Métodos:**
- `adicionarLivro(Livro livro)`
- `buscarPorCodigo(String codigo)`
- `listarLivros()`
- `listarDisponiveis()`

Os livros serão armazenados em uma coleção em memória, utilizando `ArrayList` ou outra implementação de `List`.

### 3.3. `Livro` — classe abstrata

**Responsabilidade:** representar as características comuns dos livros físicos.

**Atributos:**
- `codigo: String`
- `titulo: String`
- `autor: String`
- `isbn: String`
- `disponivel: boolean`

**Métodos:**
- `emprestar()`
- `devolver()`
- `podeSerEmprestado()`

A classe será abstrata para permitir que diferentes tipos de livros apresentem comportamentos específicos.

### 3.4. `LivroEmprestavel`

**Responsabilidade:** representar um livro físico que pode ser emprestado para levar para casa.

**Relacionamento:** herda de `Livro`.

**Comportamento:** permite empréstimos externos, desde que o livro esteja disponível.

### 3.5. `LivroConsultaLocal`

**Responsabilidade:** representar um livro físico que pode ser consultado apenas dentro da biblioteca.

**Relacionamento:** herda de `Livro`.

**Comportamento:** não permite empréstimos externos. Essa restrição deverá ser validada antes da criação de um empréstimo.

### 3.6. `Usuario`

**Responsabilidade:** representar os usuários cadastrados e controlar seus empréstimos.

**Atributos:**
- `id: String`
- `nome: String`
- `emprestimos: List<Emprestimo>`

**Métodos:**
- `podeEmprestar()`
- `adicionarEmprestimo(Emprestimo emprestimo)`
- `contarEmprestimosAtivos()`

O método `podeEmprestar()` deverá verificar se o usuário ainda está dentro do limite de três empréstimos ativos.

### 3.7. `Emprestimo`

**Responsabilidade:** registrar e controlar os empréstimos realizados.

**Atributos:**
- `usuario: Usuario`
- `livro: Livro`
- `dataEmprestimo: LocalDate`
- `prazoDevolucao: LocalDate`
- `dataDevolucao: LocalDate`

**Métodos:**
- `registrarDevolucao()`
- `estaAtivo()`
- `estaAtrasado()`

**Regras:**
- Cada empréstimo estará associado a um único usuário e a um único livro.
- O prazo de devolução será calculado adicionando 15 dias corridos à data do empréstimo.
- A ausência de `dataDevolucao` indicará que o empréstimo permanece ativo.
- A devolução deverá encerrar o empréstimo e atualizar a disponibilidade do livro.

### 3.8. `LivroFactory`

**Responsabilidade:** centralizar a criação dos objetos de livro.

**Método proposto:**
- `criarLivro(...)`: criar um `LivroEmprestavel` ou um `LivroConsultaLocal`, conforme o tipo informado.

A inclusão dessa classe dependerá da definição do padrão de projeto que será utilizado. Como o trabalho exige pelo menos um padrão, a equipe deverá confirmar se uma fábrica simples atende ao requisito do professor.

### 3.9. `MaterialIndisponivelException`

**Responsabilidade:** representar uma tentativa de emprestar um livro indisponível ou que não permita empréstimo externo.

A aplicação deverá tratar a exceção e apresentar uma mensagem compreensível.

### 3.10. `LimiteEmprestimosException`

**Responsabilidade:** representar uma tentativa de realizar um novo empréstimo quando o usuário já possui três empréstimos ativos.

A exceção deverá ser lançada durante uma operação real e tratada pela aplicação.

## 4. Interface `Emprestavel`

A interface `Emprestavel` definirá o contrato de comportamento para livros que podem ser emprestados.

**Métodos:**
- `emprestar()`
- `devolver()`

A classe `LivroEmprestavel` poderá implementar essa interface. A classe `LivroConsultaLocal` não deverá permitir empréstimos externos.

## 5. Relacionamentos entre as classes

| Relacionamento | Descrição |
|---|---|
| `Biblioteca` — `Catalogo` | Composição: a biblioteca possui um catálogo. |
| `Catalogo` — `Livro` | Agregação: o catálogo organiza os livros cadastrados. |
| `Livro` — `LivroEmprestavel` | Herança. |
| `Livro` — `LivroConsultaLocal` | Herança. |
| `LivroEmprestavel` — `Emprestavel` | Implementação de interface. |
| `Usuario` — `Emprestimo` | Associação: um usuário pode possuir vários empréstimos. |
| `Livro` — `Emprestimo` | Associação: um livro pode participar de vários empréstimos ao longo do tempo. |
| `Biblioteca` — `Usuario` | Associação: a biblioteca mantém os usuários cadastrados. |
| `LivroFactory` — `Livro` | Dependência: a fábrica cria objetos da hierarquia `Livro`. |

## 6. Conceitos de POO contemplados

| Conceito | Aplicação |
|---|---|
| Classes e objetos | Representação dos elementos da biblioteca. |
| Abstração | Classe abstrata `Livro`. |
| Encapsulamento | Atributos privados e acesso controlado por métodos. |
| Construtores | Inicialização dos objetos. |
| Comunicação entre objetos | Colaboração entre as classes do sistema. |
| Associação | Relações entre usuários, livros e empréstimos. |
| Agregação | Catálogo e livros. |
| Composição | Biblioteca e catálogo. |
| Herança | `LivroEmprestavel` e `LivroConsultaLocal`. |
| Polimorfismo | Comportamentos diferentes conforme o tipo concreto de livro. |
| Interface | Contrato `Emprestavel`. |
| Padrão de projeto | A definir e validar conforme o requisito acadêmico. |
| Exceções personalizadas | Tratamento de livros indisponíveis e limite de empréstimos. |
| Interação | Execução de operações pelo console. |
| UML | Diagrama de classes correspondente à implementação final. |

## 7. Organização técnica

A aplicação será desenvolvida com Java puro.

**Tecnologias e recursos:**
- Java.
- `ArrayList` e `List` para armazenamento em memória.
- `LocalDate` para controle das datas.
- Exceções personalizadas para tratar regras de negócio.
- Console para executar e testar as operações.

Não serão utilizados banco de dados, interface gráfica, API REST ou frameworks de backend.

Os dados cadastrados existirão apenas durante a execução. Ao encerrar o programa, serão perdidos.

## 8. Fluxos principais

### 8.1. Realizar empréstimo

1. Identificar o usuário e o livro.
2. Verificar se o livro permite empréstimo e está disponível.
3. Verificar se o usuário possui menos de três empréstimos ativos.
4. Criar o objeto `Emprestimo`.
5. Registrar a data do empréstimo e calcular o prazo de 15 dias corridos.
6. Atualizar a disponibilidade do livro.
7. Exibir a confirmação ou a mensagem de erro.

### 8.2. Registrar devolução

1. Identificar o empréstimo ativo.
2. Registrar a data efetiva de devolução.
3. Encerrar o empréstimo.
4. Atualizar a disponibilidade do livro.
5. Exibir a confirmação da operação.

## 9. Pontos para discussão em equipe

- [ ] Confirmar os tipos de livro: empréstimo externo e consulta local.
- [ ] Definir como serão gerados os códigos dos livros e os identificadores dos usuários.
- [ ] Definir quais dados serão obrigatórios no cadastro.
- [ ] Escolher o padrão de projeto que será utilizado.
- [ ] Definir as operações que serão disponibilizadas para teste.
- [ ] Revisar as responsabilidades de cada classe.
- [ ] Elaborar o diagrama UML definitivo.
- [ ] Dividir as tarefas entre os integrantes da equipe.

## 10. Próximas etapas

1. Revisar e aprovar a proposta de classes.
2. Elaborar o diagrama UML.
3. Implementar as classes em Java.
4. Criar uma classe principal com um menu simples de console para testar as funcionalidades.
5. Testar os empréstimos, as devoluções e as exceções.
6. Atualizar a documentação conforme a implementação final.
