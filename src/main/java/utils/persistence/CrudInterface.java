package utils.persistence;

import io.smallrye.mutiny.Uni;

import java.util.List;

public interface CrudInterface<T, R> {
   Uni<Void> create(T entity);
   Uni<Void> update(T entity);
   Uni<R> read(Long id);
   Uni<List<R>> readAll();
   Uni<Void> delete(T entity);
}
