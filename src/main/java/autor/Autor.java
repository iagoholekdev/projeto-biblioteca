package autor;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import livro.Livro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "autor")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "autor_seq", sequenceName = "autor_seq", allocationSize = 1)
public class Autor  extends PanacheEntityBase {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "datanascimento", nullable = false)
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;

}
