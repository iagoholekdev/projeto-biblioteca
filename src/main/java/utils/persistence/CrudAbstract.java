package utils.persistence;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;

@RequestScoped
public abstract class CrudAbstract<T, R> implements CrudInterface<T, R>, PanacheRepositoryBase<T, Long> {

    @Inject
    Mutiny.SessionFactory sessionFactory;

    @Override
    public Uni<Void> create(T entity) {
        return sessionFactory.withTransaction(session ->
                session.persist(entity).replaceWithVoid()
        );
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
