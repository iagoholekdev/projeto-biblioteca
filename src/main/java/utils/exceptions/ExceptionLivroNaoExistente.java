package utils.exceptions;

public class ExceptionLivroNaoExistente extends RuntimeException {

    private final String mensagem;

    public ExceptionLivroNaoExistente() {
        this.mensagem = "Livro não cadastrado no sistema";
    }

}
