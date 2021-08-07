package br.com.zupacademy.alissonprado.mercadolivre.features.produto.detalharProduto.controller;

import br.com.zupacademy.alissonprado.mercadolivre.features.produto.detalharProduto.response.DetalhesProdutoResponse;
import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;
import br.com.zupacademy.alissonprado.mercadolivre.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DetalhesProdutoController {

    ProdutoRepository produtoRepository;

    public DetalhesProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/api/produtos/{id}")
    public ResponseEntity<?> detalhar(@PathVariable String id){

        if (!id.matches("[0-9]*"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato de id inválido");

        Optional<Produto> optionalProduto = produtoRepository.findById(Long.valueOf(id));

        if (optionalProduto.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O Produto buscado não existe.");

        DetalhesProdutoResponse detalhesProdutoResponse = new DetalhesProdutoResponse(optionalProduto.get());

        return ResponseEntity.status(HttpStatus.OK).body(detalhesProdutoResponse);

    }
}
