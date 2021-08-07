package br.com.zupacademy.alissonprado.mercadolivre.model;

import io.jsonwebtoken.lang.Assert;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
public class PerguntaProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String pergunta;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @NotNull
    @ManyToOne
    private Produto produto;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    /**
     * Não utilizar.
     * Criado por exigencia da JPA
     */
    @Deprecated
    public PerguntaProduto() {
    }

    /**
     *
     * @param titulo NotBlank
     * @param pergunta NotBlank
     * @param usuario
     * @param produto
     */
    public PerguntaProduto(@NotBlank String titulo, @NotBlank String pergunta, @NotNull Produto produto, @NotNull Usuario usuario) {
        Assert.hasText(titulo, "O campo Titulo não pode ser vazio.");
        Assert.hasText(pergunta, "O campo Pergunta não pode ser vazio.");
        Assert.notNull(produto, "O Produto não pode ser nulo.");
        Assert.notNull(usuario, "O Usuario não pode ser nulo.");

        this.titulo = titulo.trim();
        this.pergunta = pergunta.trim();
        this.usuario = usuario;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public String getNomeUsuario() {
        String[] email = usuario.getEmail().split("@");
        return email[0];
    }
}
