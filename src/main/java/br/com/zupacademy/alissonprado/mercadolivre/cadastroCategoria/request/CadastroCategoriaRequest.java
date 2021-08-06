package br.com.zupacademy.alissonprado.mercadolivre.cadastroCategoria.request;

import br.com.zupacademy.alissonprado.mercadolivre.model.Categoria;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.ExistsId;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CadastroCategoriaRequest {

    @NotBlank @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @ExistsId(domainClass = Categoria.class, idName = "id", optional = true)
    private Long idCategoriaMae;

    public CadastroCategoriaRequest(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toModel(){
        if (idCategoriaMae == null)
            return new Categoria(this.nome.trim());

        return new Categoria(this.nome.trim(), new Categoria(idCategoriaMae));
    }
}
