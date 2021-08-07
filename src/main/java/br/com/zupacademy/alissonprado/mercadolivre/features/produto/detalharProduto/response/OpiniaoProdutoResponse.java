package br.com.zupacademy.alissonprado.mercadolivre.features.produto.detalharProduto.response;

import br.com.zupacademy.alissonprado.mercadolivre.model.OpiniaoProduto;

public class OpiniaoProdutoResponse {

    private Byte nota;
    private String titulo;
    private String descricao;

    public OpiniaoProdutoResponse(OpiniaoProduto opiniaoProduto) {
        this.nota = opiniaoProduto.getNota();
        this.titulo = opiniaoProduto.getTitulo();
        this.descricao = opiniaoProduto.getDescricao();
    }

    public Byte getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
