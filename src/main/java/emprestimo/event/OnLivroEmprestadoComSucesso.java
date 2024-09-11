package emprestimo.event;

import emprestimo.Emprestimo;
import emprestimo.dao.EmprestimoDAO;
import emprestimo.wrapper.LivroEmprestadoWrapper;
import estudante.Estudante;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.ObservesAsync;
import livro.Livro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.dtos.EmprestimoDTO;

import java.time.LocalDate;

@ApplicationScoped
public class OnLivroEmprestadoComSucesso {

    private final EmprestimoDAO emprestimoDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(OnLivroEmprestadoComSucesso.class);

    OnLivroEmprestadoComSucesso(EmprestimoDAO emprestimoDAO) {
        this.emprestimoDAO = emprestimoDAO;
    }

    public void onLivroEmprestado(@ObservesAsync LivroEmprestadoWrapper wrapper) {
        LOGGER.info("onLivroEmprestado, {}", wrapper);
        createAndPersistEmprestimo(wrapper.getEmprestimoDTO())
                .subscribe()
                .with(
                        success -> LOGGER.info("Empréstimo registrado com sucesso."),
                        failure -> LOGGER.error("Falha ao registrar o empréstimo: {}", failure.getMessage())
                );
    }

    @WithTransaction
    public Uni<Void> createAndPersistEmprestimo(EmprestimoDTO emprestimoDTO) {
        LOGGER.info("createAndPersistEmprestimo, {}", emprestimoDTO);
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(Livro.builder().id(emprestimoDTO.getLivro().getId()).build());
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setEstudante(Estudante.builder().id(emprestimoDTO.getEstudante().getId()).build());
        LOGGER.info("objeto Settado, {}", emprestimo);

        return emprestimoDAO.create(emprestimo)
                .onItem().invoke(() -> LOGGER.info("Emprestimo registrado com sucesso."))
                .onFailure().invoke(ex -> LOGGER.error("Falha ao registrar o emprestimo: {}", ex.getMessage()))
                .replaceWithVoid();
    }

}
