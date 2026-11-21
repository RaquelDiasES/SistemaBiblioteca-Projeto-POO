package service;

import exceptions.LimiteEmprestimosException;
import exceptions.MaterialIndisponivelException;
import factory.LivroFactory;
import interfaces.Emprestavel;
import model.Emprestimo;
import model.Livro;
import model.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Coordena o sistema: guarda livros, usuários e empréstimos
 * e faz os objetos colaborarem entre si.
 */
public class Biblioteca {

    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void cadastrarLivro(LivroFactory.Tipo tipo, String codigo, String titulo, String autor) {
        if (buscarLivroOuNulo(codigo) != null) {
            throw new IllegalArgumentException("Já existe um livro com o código " + codigo + ".");
        }
        livros.add(LivroFactory.criarLivro(tipo, codigo, titulo, autor));
    }

    public void cadastrarUsuario(String id, String nome) {
        if (buscarUsuarioOuNulo(id) != null) {
            throw new IllegalArgumentException("Já existe um usuário com o ID " + id + ".");
        }
        usuarios.add(new Usuario(id, nome));
    }

    public void realizarEmprestimo(String idUsuario, String codigoLivro)
            throws MaterialIndisponivelException, LimiteEmprestimosException {

        Usuario usuario = buscarUsuario(idUsuario);
        Livro livro = buscarLivro(codigoLivro);

        // Polimorfismo: cada tipo de Livro responde de um jeito
        if (!livro.podeSerEmprestado()) {
            throw new MaterialIndisponivelException(
                    "O livro \"" + livro.getTitulo() + "\" não pode ser emprestado "
                    + "(já está emprestado ou é de consulta local).");
        }
        if (!usuario.podeRealizarEmprestimo()) {
            throw new LimiteEmprestimosException(
                    usuario.getNome() + " já possui "
                    + Usuario.LIMITE_EMPRESTIMOS_ATIVOS + " empréstimos ativos.");
        }

        // Só chega aqui um livro que pode ser emprestado, ou seja, um Emprestavel
        ((Emprestavel) livro).emprestar();

        Emprestimo emprestimo = new Emprestimo(usuario, livro);
        emprestimos.add(emprestimo);
        usuario.adicionarEmprestimo(emprestimo);
    }

    public void registrarDevolucao(String codigoLivro) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.estaAtivo() && emprestimo.getLivro().getCodigo().equals(codigoLivro)) {
                emprestimo.registrarDevolucao();
                ((Emprestavel) emprestimo.getLivro()).devolver();
                return;
            }
        }
        throw new IllegalArgumentException("Não há empréstimo ativo para o livro de código " + codigoLivro + ".");
    }

    public List<Livro> listarLivrosDisponiveis() {
        List<Livro> disponiveis = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.podeSerEmprestado()) {
                disponiveis.add(livro);
            }
        }
        return disponiveis;
    }

    public List<Livro> getLivros() {
        return Collections.unmodifiableList(livros);
    }

    public List<Usuario> getUsuarios() {
        return Collections.unmodifiableList(usuarios);
    }

    public List<Emprestimo> getEmprestimos() {
        return Collections.unmodifiableList(emprestimos);
    }

    private Livro buscarLivro(String codigo) {
        Livro livro = buscarLivroOuNulo(codigo);
        if (livro == null) {
            throw new IllegalArgumentException("Livro não encontrado: " + codigo + ".");
        }
        return livro;
    }

    private Usuario buscarUsuario(String id) {
        Usuario usuario = buscarUsuarioOuNulo(id);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado: " + id + ".");
        }
        return usuario;
    }

    private Livro buscarLivroOuNulo(String codigo) {
        for (Livro livro : livros) {
            if (livro.getCodigo().equals(codigo)) {
                return livro;
            }
        }
        return null;
    }

    private Usuario buscarUsuarioOuNulo(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }
}
