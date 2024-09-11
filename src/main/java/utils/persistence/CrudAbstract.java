package utils.persistence;

import emprestimo.event.OnLivroEmprestadoComSucesso;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.hibernate.reactive.mutiny.Mutiny;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RequestScoped
public abstract class CrudAbstract<T, R> implements CrudInterface<T, R>, PanacheRepositoryBase<T, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrudAbstract.class);

    @Inject
    Mutiny.SessionFactory sessionFactory;

    @Override
    public Uni<Void> create(T entity) {
        LOGGER.info("nomeEntity Create, {}", this.getEntityClass().getName());
        LOGGER.info("dadosEntity, {}", entity.toString());
        return sessionFactory.withTransaction(session -> {
            LOGGER.info("Iniciando transação para {}", entity.getClass().getSimpleName());
            return session.persist(entity)
                    .onItem().invoke(() -> LOGGER.info("Entidade persistida com sucesso: {}", entity))
                    .replaceWithVoid();
        });
    }

    @Override
    public Uni<Void> update(T entity) {
        return sessionFactory.withTransaction(session ->
                session.merge(entity).replaceWithVoid()
        );
    }

    @Override
    public Uni<R> read(Long id) {
        return sessionFactory.withTransaction(session ->
                session.find(getEntityClass(), id)
                        .map(entity -> (R) entity)
        );
    }

    @Override
    public Uni<List<R>> readAll() {
        return sessionFactory.withTransaction(session ->
                session.createQuery("FROM " + getEntityClass().getName(), getEntityClass())
                        .getResultList()
                        .map(list -> list.stream()
                                .map(entity -> (R) entity)
                                .toList()
                        )
        );
    }

    @Override
    public Uni<Void> delete(T entity) {
        return sessionFactory.withTransaction(session ->
                session.remove(entity).replaceWithVoid()
        );
    }

    protected abstract Class<T> getEntityClass();
}
