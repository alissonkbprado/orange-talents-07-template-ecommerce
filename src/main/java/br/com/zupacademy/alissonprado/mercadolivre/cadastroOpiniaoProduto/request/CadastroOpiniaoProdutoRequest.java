package br.com.zupacademy.alissonprado.mercadolivre.cadastroOpiniaoProduto.request;

import br.com.zupacademy.alissonprado.mercadolivre.model.OpiniaoProduto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.ExistsId;

import javax.validation.constraints.*;

public class CadastroOpiniaoProdutoRequest {


    @NotNull @Min(1) @Max(5)
    private Byte nota;

    @NotBlank
    private String titulo;

    @NotBlank @Size(max = 500)
    private String descricao;

    @NotNull @Positive @ExistsId(domainClass = Produto.class, idName = "id", optional = false, message = "O Produto passado não está cadastrado no sistema")
    private Long idProduto;

    public CadastroOpiniaoProdutoRequest(Byte nota, String titulo, String descricao, Long idProduto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.idProduto = idProduto;
    }

    public OpiniaoProduto toModel(Usuario usuario){
        return new OpiniaoProduto(this.nota, this.titulo, this.descricao, new Produto(idProduto), usuario);
    }

    public Long getIdProduto() {
        return idProduto;
    }
}
