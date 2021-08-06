package br.com.zupacademy.alissonprado.mercadolivre.cadastroProduto.controller;

import br.com.zupacademy.alissonprado.mercadolivre.cadastroProduto.request.CadastroProdutoRequest;
import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroProdutoRequest cadastroProdutoRequest, @AuthenticationPrincipal Usuario usuario){

        Produto produto = produtoRepository.save(cadastroProdutoRequest.toModel(usuario));

        produtoRepository.save(produto);

        return ResponseEntity.ok("Produto cadastrado com sucesso");
    }
}
