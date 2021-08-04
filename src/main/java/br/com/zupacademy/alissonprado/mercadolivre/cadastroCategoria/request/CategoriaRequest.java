package br.com.zupacademy.alissonprado.mercadolivre.cadastroCategoria.request;

import br.com.zupacademy.alissonprado.mercadolivre.model.Categoria;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.ExistsId;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @ExistsId(domainClass = Categoria.class, idName = "id", optional = true)
    private Long idCategoriaMae;

    public CategoriaRequest(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toModel(){
        if (idCategoriaMae == null)
            return new Categoria(this.nome);

        return new Categoria(this.nome, new Categoria(idCategoriaMae));
    }
}
