package br.com.zupacademy.alissonprado.mercadolivre.model;

import org.hibernate.validator.constraints.URL;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private String url;

    @ManyToOne
    private Produto produto;


    /**
     * Não utilizar.
     * Criado por exigencia da JPA
     */
    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(Long id) {
        this.id = id;
    }

    /**
     *
     * @param nome NotBlank
     * @param produtoUsuarioLogado NotNull, Produto deve pertencer ao Usuario logado.
     */
    public ImagemProduto(@NotBlank String nome, @NotBlank @URL String url, @NotNull Produto produtoUsuarioLogado) {
        Assert.hasText(nome, "Você deve passar o nome da imagem.");
        Assert.hasText(url, "Você deve passar a url da imagem do produto");
        Assert.notNull(produtoUsuarioLogado, "O Produto não pode ser nulo");
        this.nome = nome;
        this.url = url;
        this.produto = produtoUsuarioLogado;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUrl() {
        return url;
    }
}
