package utils.exceptions;

public class ExceptionLivroNaoDisponivel extends RuntimeException {

    public ExceptionLivroNaoDisponivel() {
        super("Livro não disponível.");
    }

}
