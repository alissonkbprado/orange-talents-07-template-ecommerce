package br.com.zupacademy.alissonprado.mercadolivre.model;

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

    private String imagemUrl;

    @ManyToOne
    private Produto produto;


    @Deprecated
    public ImagemProduto(Long id) {
        this.id = id;
    }

    /**
     *
     * @param nome NotBlank
     * @param produtoUsuarioLocado NotNull, Produto deve pertencer ao Usuario logado.
     */
    public ImagemProduto(@NotBlank String nome, String imagemUrlm, @NotNull Produto produtoUsuarioLocado) {
        Assert.hasText(nome, "Você deve passar o nome da imagem.");
        Assert.hasText(imagemUrlm, "Você deve passar a url da imagem do produto");
        Assert.notNull(produtoUsuarioLocado, "O Produto não pode ser nulo");
        this.nome = nome;
        this.imagemUrl = imagemUrlm;
        this.produto = produtoUsuarioLocado;
    }

    public Long getId() {
        return id;
    }
}
