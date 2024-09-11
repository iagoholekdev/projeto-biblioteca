package livro.processos;

import emprestimo.wrapper.LivroEmprestadoWrapper;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import livro.LivroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.dtos.EmprestimoDTO;
import utils.enums.Status;
import jakarta.enterprise.event.Event;
import utils.exceptions.ExceptionLivroNaoDisponivel;
import utils.exceptions.ExceptionLivroNaoExistente;

@RequestScoped
public class EmprestarLivroDisponivel {

    private final LivroRepository livroRepository;

    private final Event<LivroEmprestadoWrapper> eventLivroEmprestado;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmprestarLivroDisponivel.class);

    EmprestarLivroDisponivel(
            LivroRepository livroRepository,
            Event<LivroEmprestadoWrapper> livroEmprestadoWrapper
    ) {
        this.livroRepository = livroRepository;
        this.eventLivroEmprestado = livroEmprestadoWrapper;
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
                            .onItem().transformToUni(livroUni -> {
                                eventLivroEmprestado.fire(new LivroEmprestadoWrapper(emprestimoDTO));
                                return Uni.createFrom().voidItem();
                            })
                            .onFailure().invoke(failure -> LOGGER.error("Falha ao persistir livro: {}", failure.getMessage(), failure));
                })
                .onFailure().invoke(failure -> {
                    LOGGER.error("Falha ao processar livro: {}", failure.getMessage(), failure);
                });
    }

}
