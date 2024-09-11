package livro.processos;

import emprestimo.Emprestimo;
import emprestimo.dao.EmprestimoDAO;
import estudante.Estudante;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import livro.Livro;
import livro.LivroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.dtos.EmprestimoDTO;
import utils.enums.Status;
import utils.exceptions.ExceptionLivroNaoDisponivel;
import utils.exceptions.ExceptionLivroNaoExistente;

import java.time.LocalDate;

@RequestScoped
public class EmprestarLivroDisponivel {

    private final LivroRepository livroRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmprestarLivroDisponivel.class);

    private final EmprestimoDAO emprestimoDAO;

    EmprestarLivroDisponivel(
            LivroRepository livroRepository,
            EmprestimoDAO emprestimoDAO
    ) {
        this.livroRepository = livroRepository;
        this.emprestimoDAO = emprestimoDAO;
    }

    @WithTransaction
    public Uni<Void> processarEmprestimo(EmprestimoDTO emprestimoDTO) {
        return livroRepository.findById(emprestimoDTO.getLivro().getId())
                .onItem().transformToUni(livro -> {
                    if (livro == null) {
                        return Uni.createFrom().failure(new ExceptionLivroNaoExistente());
                    }
                    if (!Status.DISPONIVEL.equals(livro.getStatusLivro())) {
                        return Uni.createFrom().failure(new ExceptionLivroNaoDisponivel());
                    }
                    livro.setStatusLivro(Status.EMPRESTADO);
                    return livroRepository.persist(livro)
                            .onItem().transformToUni(livroPersistido -> {

                                Emprestimo emprestimo = new Emprestimo();
                                emprestimo.setLivro(Livro.builder().id(emprestimoDTO.getLivro().getId()).build());
                                emprestimo.setDataEmprestimo(LocalDate.now());
                                emprestimo.setEstudante(Estudante.builder().id(emprestimoDTO.getEstudante().getId()).build());
                                LOGGER.info("Emprestimo objeto criado: {}", emprestimo);

                                return emprestimoDAO.create(emprestimo)
                                        .onItem().invoke(() -> LOGGER.info("Empréstimo registrado com sucesso."))
                                        .onFailure().invoke(ex -> LOGGER.error("Falha ao registrar o empréstimo: {}", ex.getMessage()))
                                        .replaceWithVoid();
                            })
                            .onFailure().invoke(failure -> LOGGER.error("Falha ao persistir livro: {}", failure.getMessage()));
                })
                .onFailure().invoke(failure -> LOGGER.error("Falha ao processar livro: {}", failure.getMessage()));
    }

}
