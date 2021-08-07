package br.com.zupacademy.alissonprado.mercadolivre.features.produto.cadastraPerguntaProduto.controller;

import br.com.zupacademy.alissonprado.mercadolivre.features.produto.cadastraPerguntaProduto.request.CadastraPerguntaProdutoRequest;
import br.com.zupacademy.alissonprado.mercadolivre.model.PerguntaProduto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.repository.PerguntaProdutoRepository;
import br.com.zupacademy.alissonprado.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.alissonprado.mercadolivre.service.mensagem.EnviaMensagem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos/perguntas")
public class CadastraPerguntaController {

    PerguntaProdutoRepository perguntaProdutoRepository;
    ProdutoRepository produtoRepository;
    EnviaMensagem enviaMensagem;

    public CadastraPerguntaController(PerguntaProdutoRepository perguntaProdutoRepository,
                                      ProdutoRepository produtoRepository,
                                      EnviaMensagem enviaMensagem) {
        this.perguntaProdutoRepository = perguntaProdutoRepository;
        this.produtoRepository = produtoRepository;
        this.enviaMensagem = enviaMensagem;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastraPerguntaProdutoRequest request,
                                       @AuthenticationPrincipal Usuario usuario){

        if (produtoPercenteAoUsuarioLogado(request.getIdProduto(), usuario.getId()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("O Produto indicado pertence ao Usuario logado. " +
                    "Não é permitido enviar pergunta para o próprio produto");

        PerguntaProduto perguntaProduto = request.toModel(usuario);
        perguntaProdutoRepository.save(perguntaProduto);

        enviaEmail(request);

        return ResponseEntity.status(HttpStatus.OK).body("A pergunta foi cadastrada com sucesso.");
    }


    private Boolean produtoPercenteAoUsuarioLogado(Long idProduto, Long idUsuarioLogado) {
        Optional<Produto> produto = produtoRepository.findByIdAndUsuario_Id(idProduto, idUsuarioLogado);

        return  produto.isPresent();
    }

    private void enviaEmail(CadastraPerguntaProdutoRequest request) {
        Produto produtoAvaliado = produtoRepository.findById(request.getIdProduto()).get();
        enviaMensagem.envia("naoresponder@mercadolivre.com",
                produtoAvaliado.getUsuario().getEmail(),
                request.getTitulo() + " - Produto: " + produtoAvaliado.getNome(),
                request.getPergunta());
    }
}
