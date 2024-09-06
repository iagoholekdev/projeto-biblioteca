package utils.exceptions;

public class ExceptionPadraoSistema extends Exception {

    private final String mensagem;
    private final String motivo;
    private final int codigo;

    public ExceptionPadraoSistema() {
        this.mensagem = "";
        this.codigo = 0;
        this.motivo = "";
    }

    public ExceptionPadraoSistema(String mensagem, int codigo) {
        this.mensagem = mensagem;
        this.codigo = codigo;
        this.motivo = "";
    }

    public ExceptionPadraoSistema(String mensagem, int codigo, String motivo) {
        this.mensagem = mensagem;
        this.codigo = codigo;
        this.motivo = motivo;
    }

}
