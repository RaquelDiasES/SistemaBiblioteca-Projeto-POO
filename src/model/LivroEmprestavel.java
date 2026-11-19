package model;

import interfaces.Emprestavel;

public class LivroEmprestavel extends Livro implements Emprestavel {

    public LivroEmprestavel(String codigo, String titulo, String autor) {
        super(codigo, titulo, autor);
    }

    @Override
    public boolean podeSerEmprestado() {
        return true; 
    }

    @Override
    public void emprestar() {
        // A lógica de atualizar a disponibilidade será feita na Etapa 2
    }

    @Override
    public void devolver() {
        // A lógica de atualizar a disponibilidade será feita na Etapa 2
    }
}