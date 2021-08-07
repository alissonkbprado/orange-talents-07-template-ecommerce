package br.com.zupacademy.alissonprado.mercadolivre.features.produto.cadastroProduto.request;

import br.com.zupacademy.alissonprado.mercadolivre.model.GrupoCaracteristicaProduto;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.ExistsId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CaracteristicaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull @Positive @ExistsId(domainClass = GrupoCaracteristicaProduto.class, idName = "id", optional = false)
    private Long idGrupoCaracteristica;

    public CaracteristicaRequest(String nome, String descricao, Long idGrupoCaracteristica) {
        this.nome = nome.trim();
        this.descricao = descricao.trim();
        this.idGrupoCaracteristica = idGrupoCaracteristica;
    }

    public String getNome() {
        return nome.trim();
    }

    public String getDescricao() {
        return descricao.trim();
    }

    public Long getIdGrupoCaracteristica() {
        return idGrupoCaracteristica;
    }
}
