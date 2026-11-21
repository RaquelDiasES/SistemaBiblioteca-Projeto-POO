# Sistema de Biblioteca — Projeto POO

## Sobre o projeto

Projeto acadêmico desenvolvido para a disciplina de
Programação Orientada a Objetos (POO), com o objetivo
de aplicar os principais conceitos de orientação a
objetos por meio de um sistema de gestão de biblioteca.

## Objetivos

- Modelar entidades por meio de classes e objetos.
- Aplicar encapsulamento, herança e polimorfismo.
- Utilizar classes abstratas e interfaces.
- Implementar relacionamentos entre classes.
- Aplicar um padrão de projeto.
- Implementar exceções específicas e seu tratamento.

## Tecnologias

- Java
- Git e GitHub
- UML para o diagrama de classes

## Estrutura do projeto

- `src/exceptions/`: exceções personalizadas (`LimiteEmprestimosException`, `MaterialIndisponivelException`).
- `src/factory/`: padrão de projeto Factory para criação de livros (`LivroFactory`).
- `src/interfaces/`: contratos de comportamento (`Emprestavel`).
- `src/model/`: entidades do domínio (`Livro`, `LivroEmprestavel`, `LivroConsultaLocal`, `Usuario`, `Emprestimo`).
- `src/service/`: regras de negócio e controle do acervo (`Biblioteca`).
- `src/Main.java`: ponto de entrada e interface de menu no terminal.
- `docs/`: diagrama UML e documentação.

## Pré-requisitos (O que precisa estar instalado)

Para compilar e executar este projeto, a máquina precisa ter:
- **Java Development Kit (JDK):** Versão 17 ou superior instalada e configurada nas variáveis de ambiente (`PATH`).
- **Git:** Para clonar o repositório (opcional, caso baixe o `.zip`).
- **IDE (Recomendado):** Visual Studio Code (com o pacote *Extension Pack for Java*), IntelliJ IDEA ou Eclipse.

## Execução

### Opção 1: Pelo Terminal / Prompt de Comando (CMD)
1. Clone o repositório e acesse a pasta do projeto:
   ```bash
   git clone https://github.com/RaquelDiasES/SistemaBiblioteca-Projeto-POO.git
   ```
2. Compile todos os arquivos Java para a pasta `bin`:
   ```bash
   javac -d bin src/exceptions/*.java src/interfaces/*.java src/model/*.java src/factory/*.java src/service/*.java src/Main.java
   ```
3. Execute a classe principal:
   ```bash
   java -cp bin Main
   ```

### Opção 2: Pelo Visual Studio Code (VS Code)
1. Abra a pasta do projeto no VS Code.
2. Abra o arquivo `src/Main.java`.
3. Clique no botão **Run** (acima do método `main`) ou pressione `F5`.
   > **Nota sobre acentuação:** Caso os acentos apareçam desconfigurados no *Debug Console*, abra as configurações (`Ctrl + ,`), pesquise por `java.debug.settings.console` e altere para `integratedTerminal`.

## Funcionalidades Implementadas

- **Acervo Inicial:** Carga automática de livros pré-cadastrados ao iniciar o sistema para facilitar testes e demonstrações.
- **Cadastro de Livros e Usuários:** Suporte para livros emprestáveis e livros exclusivos de consulta local.
- **Empréstimos e Devoluções:** Validação automática de disponibilidade do exemplar, regra de consulta local, prazo de 15 dias e limite máximo de 3 livros simultâneos por usuário.
- **Consultas:** Listagem de livros disponíveis, acervo completo e histórico de empréstimos ativos.

## Equipe

Integrantes:

- Eduardo Quintiliano
- João Victor Nunes
- Marcelo Porto
- Raquel Dias
- Ricardo Galvão