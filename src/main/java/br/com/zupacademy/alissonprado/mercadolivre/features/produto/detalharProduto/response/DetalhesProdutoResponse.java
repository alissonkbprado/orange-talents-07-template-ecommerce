package br.com.zupacademy.alissonprado.mercadolivre.features.produto.detalharProduto.response;

import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DetalhesProdutoResponse {

    private String nome;
    private BigDecimal preco;
    private String descricao;
    private Double mediaNotas;
    private Integer totalNotas;
    private Set<CaracteristicaProdutoResponse> caracteristicas;
    private List<OpiniaoProdutoResponse> opinioes;
    private List<PerguntaProdutoResponse> perguntas;
    private List<ImagemProdutoResponse> imagens;

    public DetalhesProdutoResponse(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.descricao = produto.getDescricao();
        this.mediaNotas = produto.getMediaNotas();
        this.totalNotas = produto.getQuantidadeTotalOpinioes();
        this.caracteristicas = produto.getCaracteristicaProdutoHashSet().stream().map(CaracteristicaProdutoResponse::new).collect(Collectors.toSet());
        this.opinioes = produto.getOpiniaoProdutoList().stream().map(OpiniaoProdutoResponse::new).collect(Collectors.toList());
        this.perguntas = produto.getPerguntaProdutoList().stream().map(PerguntaProdutoResponse::new).collect(Collectors.toList());
        this.imagens = produto.getImagemProdutoList().stream().map(ImagemProdutoResponse::new).collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getTotalNotas() {
        return totalNotas;
    }

    public Set<CaracteristicaProdutoResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public List<OpiniaoProdutoResponse> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaProdutoResponse> getPerguntas() {
        return perguntas;
    }

    public List<ImagemProdutoResponse> getImagens() {
        return imagens;
    }
}
