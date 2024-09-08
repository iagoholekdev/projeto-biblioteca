package autor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AutorDTO {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;

}
