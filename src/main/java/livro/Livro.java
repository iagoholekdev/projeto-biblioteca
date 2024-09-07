package livro;

import autor.Autor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;
import utils.enums.Status;

@Entity(name = "livro")
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "livro_seq", sequenceName = "livro_seq", allocationSize = 1)
public class Livro extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livro_seq")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "nomelivro")
    private String nomeLivro;

    @Enumerated(EnumType.STRING)
    @Column(name = "statuslivro")
    private Status statusLivro;


}
