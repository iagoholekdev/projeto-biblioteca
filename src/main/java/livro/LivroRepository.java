package livro;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class LivroRepository implements PanacheRepository<Livro> {
}
