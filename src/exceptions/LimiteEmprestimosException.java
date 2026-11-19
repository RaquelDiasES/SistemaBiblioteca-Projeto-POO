package exceptions;

//Tratamento de Exceção para o caso de limite de empréstimos ativos atingido.

public class LimiteEmprestimosException extends Exception {

    public LimiteEmprestimosException(String mensagem) {
        super(mensagem);
    }
}
