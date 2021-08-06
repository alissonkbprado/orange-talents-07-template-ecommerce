package br.com.zupacademy.alissonprado.mercadolivre.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class OpiniaoProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Min(1) @Max(5)
    @Column(nullable = false)
    private Byte nota;

    @NotBlank
    @Column(nullable = false)
    private String titulo;

    @Lob
    @NotBlank @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String descricao;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuario;

    /**
     * Não utilizar.
     * Criado por exigencia da JPA
     */
    @Deprecated
    public OpiniaoProduto() {
    }

    /**
     *
     * @param nota NotBlank, Size(min = 1, max = 5)
     * @param titulo NotBlank
     * @param descricao NotBlank, Size(max = 500)
     * @param produto NotNull
     * @param usuario NotNull
     */
    public OpiniaoProduto(@NotBlank @NotNull @Min(1) @Max(5) Byte nota,
                          @NotBlank String titulo,
                          @NotBlank @Size(max = 500) String descricao,
                          @NotNull Produto produto,
                          @NotNull Usuario usuario) {
        Assert.isTrue((nota >= 1 && nota <=5), "A Nota deve ter o valor entre 1 e 5.");
        Assert.hasText(titulo, "O campo Titulo não pode ser vazio.");
        Assert.hasText(descricao, "O campo Descrição não pode ser vazio.");
        Assert.isTrue((descricao.length() < 500), "O campo Descrição não pode ter mais do que 500 caracteres.");
        Assert.notNull(produto, "O Produto não pode ser nulo.");
        Assert.notNull(usuario, "O Usuario não pode ser nulo.");


        this.nota = nota;
        this.titulo = titulo.trim();
        this.descricao = descricao.trim();
        this.produto = produto;
        this.usuario = usuario;
    }
}
