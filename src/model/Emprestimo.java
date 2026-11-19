package model;

import java.time.LocalDate;

public class Emprestimo {
    
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate prazoDevolucao;
    private LocalDate dataDevolucao;

    public Emprestimo(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.prazoDevolucao = this.dataEmprestimo.plusDays(15);
        this.dataDevolucao = null;
    }

    public boolean estaAtivo() {
        return this.dataDevolucao == null;
    }

    public boolean estaAtrasado() {
        if (!estaAtivo()) {
            return false;
        }
        return LocalDate.now().isAfter(this.prazoDevolucao);
    }

    public void registrarDevolucao() {
        if (estaAtivo()) {
            this.dataDevolucao = LocalDate.now();
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getPrazoDevolucao() {
        return prazoDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
}