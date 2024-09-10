package estudante.dao;

import estudante.Estudante;
import estudante.dto.EstudanteDTO;
import jakarta.enterprise.context.RequestScoped;
import utils.persistence.CrudAbstract;

@RequestScoped
public class EstudanteDAO extends CrudAbstract<Estudante, EstudanteDTO> {

    @Override
    protected Class<Estudante> getEntityClass() {
        return Estudante.class;
    }

}
