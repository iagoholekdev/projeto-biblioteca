package autor.dto;

import livro.dto.LivroDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AutorDTO {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private List<LivroDTO> livros;


}
