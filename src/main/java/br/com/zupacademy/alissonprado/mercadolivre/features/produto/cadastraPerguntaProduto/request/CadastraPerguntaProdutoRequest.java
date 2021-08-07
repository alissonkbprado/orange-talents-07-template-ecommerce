package br.com.zupacademy.alissonprado.mercadolivre.features.produto.cadastraPerguntaProduto.request;

import br.com.zupacademy.alissonprado.mercadolivre.model.PerguntaProduto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.ExistsId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CadastraPerguntaProdutoRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String pergunta;

    @NotNull @Positive
    @ExistsId(domainClass = Produto.class, idName = "id", optional = false, message = "O Produto passado não está cadastrado no sistema")
    private Long idProduto;

    public CadastraPerguntaProdutoRequest(String titulo, Long idProduto, String pergunta) {
        this.titulo = titulo;
        this.idProduto = idProduto;
        this.pergunta = pergunta;
    }

    public PerguntaProduto toModel(Usuario usuario){
        return new PerguntaProduto(this.titulo, this.pergunta, new Produto(this.idProduto), usuario);
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPergunta() {
        return pergunta;
    }
}
