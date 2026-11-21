import exceptions.LimiteEmprestimosException;
import exceptions.MaterialIndisponivelException;
import factory.LivroFactory;
import model.Emprestimo;
import model.Livro;
import model.Usuario;
import service.Biblioteca;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Biblioteca biblioteca = new Biblioteca();
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        carregarLivrosIniciais();
        
        int opcao;
        do {
			limparTela();
            exibirMenu();
            opcao = lerOpcao();
			
			limparTela();
            try {
                executar(opcao);
            } catch (MaterialIndisponivelException | LimiteEmprestimosException e) {
                System.out.println("Empréstimo não realizado: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
			
			if (opcao != 0) {
                pausar();
			}
        } while (opcao != 0);
        System.out.println("Até logo!");
    }
	
	private static void limparTela() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
	
	private static void pausar() {
        System.out.print("\nPressione Enter para continuar...");
        scanner.nextLine();
    }

    // Acervo inicial da biblioteca
    private static void carregarLivrosIniciais() {
        biblioteca.cadastrarLivro(LivroFactory.Tipo.EMPRESTAVEL, "E001", "Java Efetivo", "Joshua Bloch");
        biblioteca.cadastrarLivro(LivroFactory.Tipo.EMPRESTAVEL, "E002", "Código Limpo", "Robert C. Martin");
        biblioteca.cadastrarLivro(LivroFactory.Tipo.EMPRESTAVEL, "E003", "Padrões de Projeto", "Erich Gamma");
        biblioteca.cadastrarLivro(LivroFactory.Tipo.EMPRESTAVEL, "E004", "Estática: Mecânica para Engenharia", "Irving Shames");
        biblioteca.cadastrarLivro(LivroFactory.Tipo.EMPRESTAVEL, "E005", "Introdução à Engenharia", "Mark Holtzapple e W. Dan Reece");
        biblioteca.cadastrarLivro(LivroFactory.Tipo.CONSULTA_LOCAL, "C001", "Enciclopédia de Engenharia", "Vários Autores");
        biblioteca.cadastrarLivro(LivroFactory.Tipo.CONSULTA_LOCAL, "C002", "Normas Técnicas ABNT", "ABNT");
        biblioteca.cadastrarLivro(LivroFactory.Tipo.CONSULTA_LOCAL, "C003", "A Engenharia e os Engenheiros na Sociedade Brasileira", "Pedro Carlos da Silva Telles");
    }

    
    private static void exibirMenu() {
        System.out.println();
        System.out.println("===== SISTEMA DE BIBLIOTECA =====");
        System.out.println("1 - Cadastrar livro");
        System.out.println("2 - Cadastrar usuário");
        System.out.println("3 - Realizar empréstimo");
        System.out.println("4 - Registrar devolução");
        System.out.println("5 - Listar livros disponíveis");
        System.out.println("6 - Listar todos os livros");
        System.out.println("7 - Listar empréstimos");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private static void executar(int opcao)
            throws MaterialIndisponivelException, LimiteEmprestimosException {
        switch (opcao) {
            case 1:
                cadastrarLivro();
                break;
            case 2:
                cadastrarUsuario();
                break;
            case 3:
                realizarEmprestimo();
                break;
            case 4:
                registrarDevolucao();
                break;
            case 5:
                imprimirLivros(biblioteca.listarLivrosDisponiveis());
                break;
            case 6:
                imprimirLivros(biblioteca.getLivros());
                break;
            case 7:
                listarEmprestimos();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarLivro() {
        System.out.print("Tipo (1 - Emprestável, 2 - Consulta local): ");
        int tipoOpcao = lerOpcao();
        LivroFactory.Tipo tipo;
        switch (tipoOpcao) {
            case 1:
                tipo = LivroFactory.Tipo.EMPRESTAVEL;
                break;
            case 2:
                tipo = LivroFactory.Tipo.CONSULTA_LOCAL;
                break;
            default:
                throw new IllegalArgumentException("Tipo de livro inválido. Digite 1 ou 2.");
        }

        String codigo = lerTexto("Código: ");
        String titulo = lerTexto("Título: ");
        String autor = lerTexto("Autor: ");
        biblioteca.cadastrarLivro(tipo, codigo, titulo, autor);
        System.out.println("Livro cadastrado.");
    }

    private static void cadastrarUsuario() {
        String id = lerTexto("ID: ");
        String nome = lerTexto("Nome: ");
        biblioteca.cadastrarUsuario(id, nome);
        System.out.println("Usuário cadastrado.");
    }

    private static void realizarEmprestimo()
            throws MaterialIndisponivelException, LimiteEmprestimosException {
        String idUsuario = lerTexto("ID do usuário: ");
        String codigoLivro = lerTexto("Código do livro: ");
        biblioteca.realizarEmprestimo(idUsuario, codigoLivro);
        System.out.println("Empréstimo realizado.");
    }

    private static void registrarDevolucao() {
        String codigoLivro = lerTexto("Código do livro: ");
        biblioteca.registrarDevolucao(codigoLivro);
        System.out.println("Devolução registrada.");
    }

    private static void imprimirLivros(List<Livro> livros) {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro para mostrar.");
            return;
        }
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    private static void listarEmprestimos() {
        if (biblioteca.getEmprestimos().isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }
        for (Emprestimo e : biblioteca.getEmprestimos()) {
            Usuario usuario = e.getUsuario();
            String situacao;
            if (!e.estaAtivo()) {
                situacao = "devolvido em " + e.getDataDevolucao().format(FORMATO);
            } else if (e.estaAtrasado()) {
                situacao = "ATRASADO (prazo era " + e.getPrazoDevolucao().format(FORMATO) + ")";
            } else {
                situacao = "ativo, devolver até " + e.getPrazoDevolucao().format(FORMATO);
            }
            System.out.println(usuario.getNome() + " - \"" + e.getLivro().getTitulo() + "\" - " + situacao);
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        String texto = scanner.nextLine().trim();
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("O campo não pode ficar vazio.");
        }
        return texto;
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
