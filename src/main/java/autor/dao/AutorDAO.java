package autor.dao;

import autor.Autor;
import autor.dto.AutorDTO;
import jakarta.enterprise.context.RequestScoped;
import utils.persistence.CrudAbstract;

@RequestScoped
public class AutorDAO extends CrudAbstract<Autor, AutorDTO> {

    @Override
    protected Class<Autor> getEntityClass() {
        return Autor.class;
    }

}
