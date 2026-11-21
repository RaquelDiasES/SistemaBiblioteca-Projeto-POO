package model;

public abstract class Livro {

    private String codigo;
    private String titulo;
    private String autor;
    private boolean disponivel;

    protected Livro(String codigo, String titulo, String autor) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    // Cada tipo de livro decide se pode ou não ser emprestado (polimorfismo)
    public abstract boolean podeSerEmprestado();

    public void atualizarDisponibilidade(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        String statusDisponibilidade;
        if (this instanceof LivroConsultaLocal) {
            statusDisponibilidade = "NÃO (consulta local)";
        } else {
            statusDisponibilidade = disponivel ? "SIM" : "NÃO";
        }

        return "[ Código=" + codigo
                + ", Título=" + titulo + ", Autor=" + autor
                + ", Disponível=" + statusDisponibilidade + " ]";
    }
}

