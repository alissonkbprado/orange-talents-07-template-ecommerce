package br.com.zupacademy.alissonprado.mercadolivre.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class GrupoCaracteristicaProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    @Deprecated
    public GrupoCaracteristicaProduto() {
    }

    /**
     *
     * @param nome NotBlank, Unique
     */
    public GrupoCaracteristicaProduto(@NotBlank String nome) {
        Assert.hasText(nome, "Nome de Grupo de Categorias obrigatório.");
        this.nome = nome;
    }

    /**
     *
     * @param id NotNull
     */
    public GrupoCaracteristicaProduto(@NotBlank Long id) {
        Assert.notNull(id, "Id de Grupo de Categorias não pode ser nulo.");
        this.id = id;
    }
}
