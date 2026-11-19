package interfaces;

//Livros que podem ser emprestados implementam essa interface. 
//Livros de consulta local não implementam.

public interface Emprestavel {
    void emprestar();
    void devolver();
}