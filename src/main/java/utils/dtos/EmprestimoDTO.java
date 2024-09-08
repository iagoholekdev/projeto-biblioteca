package utils.dtos;

import livro.dto.LivroDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoDTO {
    private LivroDTO livro;
}
