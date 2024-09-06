package utils.enums;

public enum Status {

    DISPONIVEL("Disponível"),
    EMPRESTADO("Emprestado");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

}
