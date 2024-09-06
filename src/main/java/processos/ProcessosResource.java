package processos;

import autor.Autor;
import autor.dao.AutorDAO;
import io.quarkus.hibernate.reactive.panache.common.WithSessionOnDemand;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jdk.jfr.Description;
import livro.dao.LivroDAO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import utils.dtos.EmprestimoDTO;
import utils.exceptions.ExceptionPadraoSistema;

@RequestScoped
@Path("/livraria")
@Description("Api da livraria")
public class ProcessosResource {

    private final LivroDAO livroDAO;

    private final AutorDAO autorDAO;

    @Inject
    ProcessosResource(LivroDAO livroDAO, AutorDAO autorDAO) {
        this.livroDAO = livroDAO;
        this.autorDAO = autorDAO;
    }

    @POST
    @WithSessionOnDemand
    @Operation(summary = "Empresta um livro", description = "Empresta um livro para algum indivíduo")
    @Path("/emprestar")
    public Uni<Response> emprestarLivro(EmprestimoDTO emprestimo) {
        return Uni.createFrom().item(() -> Response.ok().build())
                .onFailure().transform(ex -> ex);
    }

    @GET
    @WithSessionOnDemand
    @Operation(summary = "Busca livros disponíveis", description = "Busca todos os livros disponíveis")
    @Path("/buscardisponiveis")
    public Uni<Response> buscarLivrosDisponiveis() {
        return this.livroDAO.readAll()
                .onItem().transform(livros -> Response.ok(livros).build())
                .onFailure().transform(ex -> ex);
    }

    @POST
    @WithSessionOnDemand
    @Operation(summary = "Criar autor", description = "Criar autor")
    public Uni<Response> criarAutor(Autor autor) throws ExceptionPadraoSistema {
        return this.autorDAO.create(autor)
                .onItem().transformToUni(v -> Uni.createFrom().item(Response.status(200).build()))
                .onFailure().recoverWithItem(ex -> Response.status(500).entity("Erro ao criar o autor: " + ex.getMessage()).build());
    }

}
