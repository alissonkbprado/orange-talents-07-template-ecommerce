package br.com.zupacademy.alissonprado.mercadolivre.features.produto.detalharProduto.response;

import br.com.zupacademy.alissonprado.mercadolivre.model.CaracteristicaProduto;

public class CaracteristicaProdutoResponse {

    private String nome;
    private String descricao;

    public CaracteristicaProdutoResponse(CaracteristicaProduto caracteristicaProduto) {
        this.nome = caracteristicaProduto.getNome();
        this.descricao = caracteristicaProduto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
