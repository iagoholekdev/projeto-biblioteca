package emprestimo.dao;

import emprestimo.Emprestimo;
import emprestimo.dto.EmprestimoDTO;
import jakarta.enterprise.context.RequestScoped;
import utils.persistence.CrudAbstract;

@RequestScoped
public class EmprestimoDAO extends CrudAbstract<Emprestimo, EmprestimoDTO> {

    @Override
    protected Class<Emprestimo> getEntityClass() {
        return Emprestimo.class;
    }

}
