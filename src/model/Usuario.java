package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {

    public static final int LIMITE_EMPRESTIMOS_ATIVOS = 3;

    // Atributos do usuário
    private String id;
    private String nome;
    private final List<Emprestimo> emprestimos;

    // MÉTODO CONSTRUTOR
    public Usuario(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.emprestimos = new ArrayList<>();
    }

    // Verifica se o usuário ainda não atingiu o limite de empréstimos ativos
    public boolean podeRealizarEmprestimo() {
        return contarEmprestimosAtivos() < LIMITE_EMPRESTIMOS_ATIVOS;
    }

    public int contarEmprestimosAtivos() {
        int ativos = 0;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.estaAtivo()) {
                ativos++;
            }
        }
        return ativos;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    // GETTERS 
    public String getId() { return id; }
    public String getNome() { return nome; }

    // Lista somente leitura, para que ninguém altere os empréstimos por fora
    public List<Emprestimo> getEmprestimos() {
        return Collections.unmodifiableList(emprestimos);
    }

    // SETTERS 
    public void setId(String id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }

    // Método de saída
 @Override
    public String toString() {
        return "Usuario [ID=" + id + ", Nome=" + nome + "]";
    }
}

