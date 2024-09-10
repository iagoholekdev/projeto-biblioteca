package processos;

import autor.Autor;
import estudante.Estudante;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jdk.jfr.Description;
import livro.Livro;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.dtos.EmprestimoDTO;
import utils.exceptions.ExceptionPadraoSistema;

import java.util.List;

@RequestScoped
@Path("/livraria")
@Description("Api da livraria")
public class ProcessosResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessosResource.class);

    private final ProcessosService processosService;

    @Inject
    ProcessosResource(ProcessosService processosService) {
        this.processosService = processosService;
    }

    @POST
    @Operation(summary = "Empresta um livro", description = "Empresta um livro para algum indivíduo")
    @Path("/emprestar")
    public Uni<Response> emprestarLivro(EmprestimoDTO emprestimo) throws Exception {
        return this.processosService.emprestarLivro(emprestimo)
                .onItem().transform(response -> Response.ok().build())
                .onFailure().recoverWithItem(ex -> {
                    LOGGER.error("Erro ao processar o empréstimo: {}", ex.getMessage(), ex);
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("Erro ao emprestar livro: " + ex.getMessage())
                            .build();
                });
    }

    @GET
    @Operation(summary = "Busca livros disponíveis", description = "Busca todos os livros disponíveis")
    @Path("/buscardisponiveis")
    public Uni<List<Livro>> buscarLivrosDisponiveis() {
        return this.processosService.buscarLivrosDisponiveis();
    }

    @POST
    @Operation(summary = "Criar autor", description = "Criar autor")
    @Path("/criarautor")
    public Uni<Response> criarAutor(Autor autor) throws ExceptionPadraoSistema {
        return this.processosService.criarAutor(autor);
    }

    @POST
    @Operation(summary = "Criar estudante", description = "Criar estudante")
    @Path("/criarestudante")
    public Uni<Response> criarEstudante(Estudante estudante) throws ExceptionPadraoSistema {
        return this.processosService.criarEstudante(estudante);
    }


}
