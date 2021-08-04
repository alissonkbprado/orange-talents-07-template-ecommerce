package br.com.zupacademy.alissonprado.mercadolivre.cadastroCategoria.controller;

import br.com.zupacademy.alissonprado.mercadolivre.cadastroCategoria.request.CategoriaRequest;
import br.com.zupacademy.alissonprado.mercadolivre.model.Categoria;
import br.com.zupacademy.alissonprado.mercadolivre.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaRequest.toModel();

        categoriaRepository.save(categoria);

        return ResponseEntity.ok("Categoria cadastrada com sucesso.");
    }
}
