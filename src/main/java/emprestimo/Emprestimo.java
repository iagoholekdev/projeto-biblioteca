package emprestimo;

import autor.Autor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import estudante.Estudante;
import jakarta.persistence.*;
import livro.Livro;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Table(name = "emprestimo")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "emprestimo_seq", sequenceName = "emprestimo_seq", allocationSize = 1)
public class Emprestimo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emprestimo_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @Column(name = "dataEmprestimo")
    private LocalDate dataEmprestimo;

    @ManyToOne
    @JoinColumn(name = "estudante_id", nullable = false)
    private Estudante estudante;

}
