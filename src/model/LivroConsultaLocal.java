package model;

public class LivroConsultaLocal extends Livro {

    public LivroConsultaLocal(String codigo, String titulo, String autor) {
        super(codigo, titulo, autor);
    }

    @Override
    public boolean podeSerEmprestado() {
        return false; // Livros de consulta local não podem ser emprestados
    }
}