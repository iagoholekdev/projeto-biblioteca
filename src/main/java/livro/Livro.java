package livro;

import autor.Autor;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import utils.enums.Status;

@Entity(name = "livro")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "livro_seq", sequenceName = "livro_seq", allocationSize = 1)
public class Livro extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livro_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @Column(name = "isbn")
    private String isbn;

    @Enumerated(EnumType.STRING)
    @Column(name = "statuslivro")
    private Status statusLivro;

}
