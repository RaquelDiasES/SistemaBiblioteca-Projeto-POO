# Sistema de Biblioteca — Definição Inicial das Classes

**Linguagem:** Java  
**Disciplina:** Programação Orientada a Objetos (POO)  
**Execução:** Console  
**Armazenamento:** Em memória, sem banco de dados

## 1. Objetivo

Desenvolver um sistema simples de biblioteca para cadastrar livros e usuários, realizar empréstimos e registrar devoluções, aplicando os conceitos de Programação Orientada a Objetos.

## 2. Regras de negócio

- Cada livro terá um código único.
- Cada usuário poderá ter até três empréstimos ativos.
- Um livro não poderá ter mais de um empréstimo ativo simultaneamente.
- O prazo de empréstimo será de 15 dias corridos.
- Livros de consulta local não poderão ser emprestados.
- Operações inválidas serão tratadas por exceções personalizadas.

## 3. Classes propostas

### `Biblioteca`

Responsável por coordenar o sistema.

**Atributos:**
- `livros: List<Livro>`
- `usuarios: List<Usuario>`
- `emprestimos: List<Emprestimo>`

**Principais métodos:**
- `cadastrarLivro()`
- `cadastrarUsuario()`
- `realizarEmprestimo()`
- `registrarDevolucao()`

### `Livro` — classe abstrata

Representa os dados comuns dos livros.

**Atributos:**
- `codigo: String`
- `titulo: String`
- `autor: String`
- `disponivel: boolean`

**Comportamentos:**
- Verificar se pode ser emprestado.
- Atualizar a disponibilidade.

### `LivroEmprestavel`

Representa um livro que pode ser levado para casa.

**Relacionamento:** herda de `Livro` e implementa a interface `Emprestavel`.

### `LivroConsultaLocal`

Representa um livro que só pode ser consultado dentro da biblioteca.

**Relacionamento:** herda de `Livro`.

### `Usuario`

Representa uma pessoa cadastrada.

**Atributos:**
- `id: String`
- `nome: String`
- `emprestimos: List<Emprestimo>`

**Comportamento:** verificar se o usuário ainda pode realizar empréstimos.

### `Emprestimo`

Registra um empréstimo.

**Atributos:**
- `usuario: Usuario`
- `livro: Livro`
- `dataEmprestimo: LocalDate`
- `prazoDevolucao: LocalDate`
- `dataDevolucao: LocalDate`

**Comportamentos:**
- Verificar se está ativo.
- Verificar se está atrasado.
- Registrar a devolução.

O prazo será calculado adicionando 15 dias corridos à data do empréstimo.

## 4. Interface e exceções

### `Emprestavel`

Interface que define os métodos de empréstimo e devolução para livros emprestáveis.

### `MaterialIndisponivelException`

Exceção lançada quando um livro não pode ser emprestado.

### `LimiteEmprestimosException`

Exceção lançada quando o usuário já possui três empréstimos ativos.

## 5. Padrão de projeto

O projeto deverá utilizar pelo menos um padrão de projeto, conforme a exigência da atividade. A escolha será definida após verificar qual padrão faz mais sentido para o modelo, sem adicionar complexidade desnecessária.

## 6. Conceitos de POO

O sistema deverá demonstrar:

- Classes, objetos e construtores.
- Abstração e encapsulamento.
- Associação, agregação e composição.
- Herança e polimorfismo.
- Interface e classe abstrata.
- Comunicação entre objetos.
- Exceções personalizadas.
- Um padrão de projeto.
- Interação por meio do console.

## 7. Próximas etapas

1. Validar as classes com a equipe.
2. Elaborar o diagrama UML.
3. Dividir as tarefas de implementação.
4. Desenvolver e testar as funcionalidades.
5. Atualizar a documentação para corresponder ao código final.
