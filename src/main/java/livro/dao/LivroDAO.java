package livro.dao;

import jakarta.enterprise.context.RequestScoped;
import livro.Livro;
import livro.dto.LivroDTO;
import utils.persistence.CrudAbstract;

@RequestScoped
public class LivroDAO extends CrudAbstract<Livro, LivroDTO> {
    @Override
    protected Class<Livro> getEntityClass() {
        return Livro.class;
    }
}
