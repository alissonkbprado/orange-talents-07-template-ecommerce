package br.com.zupacademy.alissonprado.mercadolivre.features.produto.detalharProduto.response;

import br.com.zupacademy.alissonprado.mercadolivre.model.ImagemProduto;

public class ImagemProdutoResponse {

    private String nome;
    private String url;

    public ImagemProdutoResponse(ImagemProduto imagemProduto) {
        this.nome = imagemProduto.getNome();
        this.url = imagemProduto.getUrl();
    }

    public String getNome() {
        return nome;
    }

    public String getUrl() {
        return url;
    }
}
