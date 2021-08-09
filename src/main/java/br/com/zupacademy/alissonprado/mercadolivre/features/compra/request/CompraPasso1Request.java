package br.com.zupacademy.alissonprado.mercadolivre.features.compra.request;

import br.com.zupacademy.alissonprado.mercadolivre.features.FormaPagamento;
import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;
import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.EnumNamePattern;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.ExistsId;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.ExistsQuantityProduto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class CompraPasso1Request {

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull @EnumNamePattern(regexp = "PAYPAL|PAGSEGURO")
    private FormaPagamento formaPagamento;

    @NotNull @Positive
    @ExistsId(domainClass = Produto.class, idName = "id", optional = false, message = "O Produto passado não está cadastrado no sistema")
    Long idProduto;


    public CompraPasso1Request(Integer quantidade, FormaPagamento formaPagamento, Long idProduto) {
        this.quantidade = quantidade;
        this.formaPagamento = formaPagamento;
        this.idProduto = idProduto;
    }

    public Compra toModel(Usuario comprador, ProdutoRepository produtoRepository){

        Optional<Produto> produto = produtoRepository.findById(this.idProduto);

        return new Compra(this.quantidade, this.formaPagamento, produto.get(), comprador);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }
}
