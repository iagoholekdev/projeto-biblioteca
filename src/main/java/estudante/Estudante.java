package estudante;

import estudante.enums.SituacaoAluno;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "estudante")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "estudante_seq", sequenceName = "estudante_seq", allocationSize = 1)
public class Estudante {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudante_seq")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao")
    private SituacaoAluno situacao;

}
