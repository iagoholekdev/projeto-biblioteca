package processos;

import autor.Autor;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jdk.jfr.Description;
import org.eclipse.microprofile.openapi.annotations.Operation;
import utils.dtos.EmprestimoDTO;
import utils.exceptions.ExceptionPadraoSistema;

@RequestScoped
@Path("/livraria")
@Description("Api da livraria")
public class ProcessosResource {

    private final ProcessosService processosService;

    @Inject
    ProcessosResource(ProcessosService processosService) {
        this.processosService = processosService;
    }

    @POST
    @Operation(summary = "Empresta um livro", description = "Empresta um livro para algum indivíduo")
    @Path("/emprestar")
    public Uni<Response> emprestarLivro(EmprestimoDTO emprestimo) {
        return this.processosService.emprestarLivro(emprestimo);
    }

    @GET
    @Operation(summary = "Busca livros disponíveis", description = "Busca todos os livros disponíveis")
    @Path("/buscardisponiveis")
    public Uni<Response> buscarLivrosDisponiveis() {
        return this.processosService.buscarLivrosDisponiveis();
    }

    @POST
    @Operation(summary = "Criar autor", description = "Criar autor")
    @Path("/criarautor")
    public Uni<Response> criarAutor(Autor autor) throws ExceptionPadraoSistema {
        return this.processosService.criarAutor(autor);
    }

}
