package model;

import interfaces.Emprestavel;

public class LivroEmprestavel extends Livro implements Emprestavel {

    public LivroEmprestavel(String codigo, String titulo, String autor) {
        super(codigo, titulo, autor);
    }

    @Override
    public boolean podeSerEmprestado() {
        return isDisponivel(); 
    }

    @Override
    public void emprestar() {
        atualizarDisponibilidade(false);
    }

    @Override
    public void devolver() {
        atualizarDisponibilidade(true);
    }
}
