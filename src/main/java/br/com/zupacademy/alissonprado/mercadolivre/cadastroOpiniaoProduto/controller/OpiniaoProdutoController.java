package br.com.zupacademy.alissonprado.mercadolivre.cadastroOpiniaoProduto.controller;

import br.com.zupacademy.alissonprado.mercadolivre.cadastroOpiniaoProduto.request.CadastroOpiniaoProdutoRequest;
import br.com.zupacademy.alissonprado.mercadolivre.model.OpiniaoProduto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.repository.OpiniaoProdutoRepository;
import br.com.zupacademy.alissonprado.mercadolivre.repository.ProdutoRepository;
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
@RequestMapping("/api/produtos/opinioes")
public class OpiniaoProdutoController {

    OpiniaoProdutoRepository opiniaoProdutoRepository;

    ProdutoRepository produtoRepository;

    public OpiniaoProdutoController(OpiniaoProdutoRepository opiniaoProdutoRepository, ProdutoRepository produtoRepository) {
        this.opiniaoProdutoRepository = opiniaoProdutoRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroOpiniaoProdutoRequest cadastroOpiniaoProdutoRequest,
                                       @AuthenticationPrincipal Usuario usuario){

        if (produtoNaoEhDoUsuarioLogado(cadastroOpiniaoProdutoRequest.getIdProduto(), usuario.getId()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("O Produto indicado não pertence ao Usuario logado.");

        OpiniaoProduto opiniaoProduto = cadastroOpiniaoProdutoRequest.toModel(usuario);

        opiniaoProdutoRepository.save(opiniaoProduto);

        return ResponseEntity.status(HttpStatus.OK).body("A Opiniao foi cadastrada com sucesso.");
    }

    private Boolean produtoNaoEhDoUsuarioLogado(Long idProduto, Long idUsuarioLogado) {
        Optional<Produto> produto = produtoRepository.findByIdAndUsuario_Id(idProduto, idUsuarioLogado);

        return  produto.isEmpty();
    }
}
