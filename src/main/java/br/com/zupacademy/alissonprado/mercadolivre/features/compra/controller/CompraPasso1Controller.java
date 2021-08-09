package br.com.zupacademy.alissonprado.mercadolivre.features.compra.controller;

import br.com.zupacademy.alissonprado.mercadolivre.features.compra.request.CompraPasso1Request;
import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;
import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.repository.CompraRepository;
import br.com.zupacademy.alissonprado.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.alissonprado.mercadolivre.service.formaPagamento.ProcessaPagamento;
import br.com.zupacademy.alissonprado.mercadolivre.service.mensagem.EnviaMensagem;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.ValidaQuantidadeProdutoEmEstoqueValidator;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class CompraPasso1Controller {

    private CompraRepository compraRepository;
    private ProdutoRepository produtoRepository;
    private ValidaQuantidadeProdutoEmEstoqueValidator validaQuantidadeProdutoEmEstoqueValidator;
    private EnviaMensagem enviaMensagem;
    private ProcessaPagamento processaPagamento;

    public CompraPasso1Controller(CompraRepository compraRepository,
                                  ProdutoRepository produtoRepository,
                                  ValidaQuantidadeProdutoEmEstoqueValidator validaQuantidadeProdutoEmEstoqueValidator,
                                  EnviaMensagem enviaMensagem,
                                  ProcessaPagamento processaPagamento) {
        this.compraRepository = compraRepository;
        this.produtoRepository = produtoRepository;
        this.validaQuantidadeProdutoEmEstoqueValidator = validaQuantidadeProdutoEmEstoqueValidator;
        this.enviaMensagem = enviaMensagem;
        this.processaPagamento = processaPagamento;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(validaQuantidadeProdutoEmEstoqueValidator);
    }

    @PostMapping("/api/produtos/compraPasso1")
    @Transactional
    public ResponseEntity<?> passo1(@RequestBody @Valid CompraPasso1Request request,
                                    @AuthenticationPrincipal Usuario comprador,
                                    UriComponentsBuilder uriBuilder){

        if (produtoPercenteAoUsuarioLogado(request.getIdProduto(), comprador.getId()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(geraMapErro("idProduto",
                    "O Produto indicado pertence ao Usuario logado. Não é permitido efetuar uma compra do próprio produto."));

        Compra compra = request.toModel(comprador, produtoRepository);

        if (!abateEstoque(compra.getProduto(), compra.getQuantidade()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(geraMapErro("quantidade",
                    "Não há produtos em estoque em quantidade suficiente para atender a solicitação, por favor informe uma quantidade menor."));

        compraRepository.save(compra);
        enviaEmail(compra);
        String url = processaPagamento.processa(compra);
        URI uri = UriComponentsBuilder.newInstance().scheme("http").host(url).build().toUri();
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(uri)
                .body("Inicio de compra");
    }

    private HashMap<String, String> geraMapErro(String campo, String erro) {
        HashMap<String, String> map = new HashMap<>();
        map.put("campo", campo);
        map.put("erro", erro);
        return map;
    }

    private void enviaEmail(Compra compra) {
        enviaMensagem.envia("naoresponder@mercadolivre.com",
                compra.getProduto().getUsuario().getEmail(),
                "Iniciado processo de compra:"  + " - Produto: " + compra.getProduto().getNome(),
                "Foi iniciado o processo de compra do produto " + compra.getProduto().getNome() + " na " +
                        "quantidade de " + compra.getQuantidade() + " unidade(s)");
    }

    private boolean abateEstoque(Produto produto, Integer quantidade) {
        return produto.abateEstoque(quantidade);
    }

    private Boolean produtoPercenteAoUsuarioLogado(Long idProduto, Long idUsuarioLogado) {
        Optional<Produto> produto = produtoRepository.findByIdAndUsuario_Id(idProduto, idUsuarioLogado);

        return  produto.isPresent();
    }
}
