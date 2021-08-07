package br.com.zupacademy.alissonprado.mercadolivre.features.produto.cadastroProduto.controller;

import br.com.zupacademy.alissonprado.mercadolivre.features.produto.cadastroProduto.request.CadastroProdutoRequest;
import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class CadastrarProdutoController {

    ProdutoRepository produtoRepository;

    public CadastrarProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroProdutoRequest cadastroProdutoRequest, @AuthenticationPrincipal Usuario usuario){

        Produto produto = produtoRepository.save(cadastroProdutoRequest.toModel(usuario));

        produtoRepository.save(produto);

        return ResponseEntity.ok("Produto cadastrado com sucesso");
    }
}
