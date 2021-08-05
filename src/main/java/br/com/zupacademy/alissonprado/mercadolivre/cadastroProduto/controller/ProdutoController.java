package br.com.zupacademy.alissonprado.mercadolivre.cadastroProduto.controller;

import br.com.zupacademy.alissonprado.mercadolivre.cadastroProduto.request.ProdutoRequest;
import br.com.zupacademy.alissonprado.mercadolivre.model.CaracteristicaProduto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal Usuario usuario){

        Produto produto = produtoRepository.save(produtoRequest.toModel(usuario));

        // Como só possuímos o Produto criado após persistir,
        // Adicionamos o produto em cada Caracteristica para que persistam os objetos Característica completos no Banco
        for (CaracteristicaProduto c : produto.getCaracteristicaList()) {
            c.setProduto(produto);
        }

        produtoRepository.save(produto);

        return ResponseEntity.ok("Produto cadastrado com sucesso");
    }
}
