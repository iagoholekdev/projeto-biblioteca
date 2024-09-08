package processos;

import autor.Autor;
import autor.dao.AutorDAO;
import io.quarkus.hibernate.reactive.panache.common.WithSessionOnDemand;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.Response;
import livro.Livro;
import livro.LivroRepository;
import livro.dao.LivroDAO;
import livro.processos.EmprestarLivroDisponivel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.dtos.EmprestimoDTO;
import utils.enums.Status;
import utils.exceptions.ExceptionPadraoSistema;

import java.util.List;

@RequestScoped
public class ProcessosService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessosService.class);

    private final LivroDAO livroDAO;
    private final AutorDAO autorDAO;
    private final LivroRepository livroRepository;
    private final EmprestarLivroDisponivel emprestarLivroDisponivel;

    ProcessosService(LivroDAO livroDAO,
                     AutorDAO autorDAO,
                     LivroRepository livroRepository,
                     EmprestarLivroDisponivel emprestarLivroDisponivel) {
        this.livroDAO = livroDAO;
        this.autorDAO = autorDAO;
        this.livroRepository = livroRepository;
        this.emprestarLivroDisponivel = emprestarLivroDisponivel;
    }

    @WithSessionOnDemand
    public Uni<Void> emprestarLivro(EmprestimoDTO emprestimo) {
        LOGGER.info("método: emprestarLivro");
        return this.emprestarLivroDisponivel.processarEmprestimo(emprestimo)
                .onItem().invoke(() -> {
                    LOGGER.info("Processamento do empréstimo concluído com sucesso.");
                })
                .onFailure().invoke(ex -> {
                    LOGGER.info("Falha ao processar o empréstimo: {}", ex.getMessage());
                })
                .replaceWithVoid();
    }

    @WithSessionOnDemand
    public Uni<List<Livro>> buscarLivrosDisponiveis() {
        return this.livroRepository.list("statusLivro", Status.DISPONIVEL);
    }


    @WithSessionOnDemand
    public Uni<Response> criarAutor(Autor autor) throws ExceptionPadraoSistema {
        autor.getLivros().forEach(livro -> livro.setAutor(autor));
        return this.autorDAO.create(autor)
                .onItem().transformToUni(v -> Uni.createFrom().item(Response.status(200).build()))
                .onFailure().recoverWithItem(ex -> Response.status(500).entity("Erro ao criar o autor: " + ex.getMessage()).build());
    }

}
