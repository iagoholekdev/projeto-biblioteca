package processos;

import autor.Autor;
import autor.dao.AutorDAO;
import io.quarkus.hibernate.reactive.panache.common.WithSessionOnDemand;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.Response;
import livro.dao.LivroDAO;
import utils.dtos.EmprestimoDTO;
import utils.exceptions.ExceptionPadraoSistema;

@RequestScoped
public class ProcessosService {

    private final LivroDAO livroDAO;
    private final AutorDAO autorDAO;

    ProcessosService(LivroDAO livroDAO, AutorDAO autorDAO) {
        this.livroDAO = livroDAO;
        this.autorDAO = autorDAO;
    }

    @WithSessionOnDemand
    public Uni<Response> emprestarLivro(EmprestimoDTO emprestimo) {
        return Uni.createFrom().item(() -> Response.ok().build())
                .onFailure().transform(ex -> ex);
    }

    @WithSessionOnDemand
    public Uni<Response> buscarLivrosDisponiveis() {
        return this.livroDAO.readAll()
                .onItem().transform(livros -> Response.ok(livros).build())
                .onFailure().transform(ex -> ex);
    }


    @WithSessionOnDemand
    public Uni<Response> criarAutor(Autor autor) throws ExceptionPadraoSistema {
        autor.getLivros().forEach(livro -> livro.setAutor(autor));
        return this.autorDAO.create(autor)
                .onItem().transformToUni(v -> Uni.createFrom().item(Response.status(200).build()))
                .onFailure().recoverWithItem(ex -> Response.status(500).entity("Erro ao criar o autor: " + ex.getMessage()).build());
    }

}
