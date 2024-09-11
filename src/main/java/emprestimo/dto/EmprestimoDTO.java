package emprestimo.dto;

import estudante.dto.EstudanteDTO;
import livro.dto.LivroDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoDTO {

    private Long id;
    private LivroDTO livro;
    private EstudanteDTO estudanteDTO;

}
