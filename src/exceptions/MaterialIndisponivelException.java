package exceptions;

//Tratamento de Exceção para o caso de livro não poder ser emprestado 
//(já está emprestado ou é de consulta local).
 
public class MaterialIndisponivelException extends Exception {

    public MaterialIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
