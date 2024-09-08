package livro.processos;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import livro.LivroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.dtos.EmprestimoDTO;
import utils.enums.Status;
import utils.exceptions.ExceptionLivroNaoDisponivel;
import utils.exceptions.ExceptionLivroNaoExistente;

@RequestScoped
public class EmprestarLivroDisponivel {

    private final LivroRepository livroRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmprestarLivroDisponivel.class);

    EmprestarLivroDisponivel(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @WithTransaction
    public Uni<Void> processarEmprestimo(EmprestimoDTO emprestimoDTO) {
        return livroRepository.findById(emprestimoDTO.getLivro().getId())
                .onItem().invoke(livro -> {
                    LOGGER.info("Livro encontrado: {}", livro);
                })
                .onItem().ifNull().failWith(new ExceptionLivroNaoExistente())
                .onItem().transform(livro -> {
                    if (!Status.DISPONIVEL.equals(livro.getStatusLivro())) {
                        LOGGER.error("Livro não disponível: {}", livro);
                        throw new RuntimeException(new ExceptionLivroNaoDisponivel());
                    }
                    livro.setStatusLivro(Status.EMPRESTADO);
                    return livro;
                })
                .onFailure().invoke(failure -> {
                    LOGGER.error("Falha ao processar livro: {}", failure.getMessage(), failure);
                })
                .onItem().transformToUni(livro -> livroRepository.persist(livro)
                        .onItem().invoke(() -> LOGGER.info("Livro atualizado: {}", livro))
                        .onFailure().invoke(failure -> LOGGER.error("Falha ao persistir livro: {}", failure.getMessage(), failure)))
                .replaceWithVoid();
    }

}
