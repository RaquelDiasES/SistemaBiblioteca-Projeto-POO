package factory;

import model.Livro;
import model.LivroConsultaLocal;
import model.LivroEmprestavel;

// Classe responsável por centralizar a criação dos livros, para que quem cadastra um livro
// não precise conhecer as subclasses concretas de Livro.
 
public class LivroFactory {

    public enum Tipo {
        EMPRESTAVEL,
        CONSULTA_LOCAL
    }

    private LivroFactory() {
    }

    public static Livro criarLivro(Tipo tipo, String codigo, String titulo, String autor) {
        switch (tipo) {
            case EMPRESTAVEL:
                return new LivroEmprestavel(codigo, titulo, autor);
            case CONSULTA_LOCAL:
                return new LivroConsultaLocal(codigo, titulo, autor);
            default:
                throw new IllegalArgumentException("Tipo de livro inválido.");
        }
    }
}
