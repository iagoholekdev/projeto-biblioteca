package emprestimo.wrapper;


import lombok.Getter;
import utils.dtos.EmprestimoDTO;

@Getter
public class LivroEmprestadoWrapper {

    private final EmprestimoDTO emprestimoDTO;

    public LivroEmprestadoWrapper(EmprestimoDTO emprestimoDTO) {
        this.emprestimoDTO = emprestimoDTO;
    }

}
