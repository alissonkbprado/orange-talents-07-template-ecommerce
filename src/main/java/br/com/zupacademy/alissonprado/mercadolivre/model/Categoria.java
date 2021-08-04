package br.com.zupacademy.alissonprado.mercadolivre.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "categoria_mae_id" }) })
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToOne
    Categoria categoriaMae;

    public Categoria(Long id) {
        this.id = id;
    }

    /**
     * Construtor para cadastrar Categoria
     * @param nome NotNull
     */
    public Categoria(@NotBlank String nome) {
        Assert.hasText(nome, "O nome da categoria é obrigatório.");
        this.nome = nome;
    }

    /**
     * Construtor para cadastrar uma SubCategoria
     * @param nome NotNull
     * @param categoriaMae Notnull, Passar uma categoria cadastrada
     */
    public Categoria(@NotBlank String nome, @NotNull Categoria categoriaMae) {
        Assert.hasText(nome, "O nome da categoria é obrigatório.");
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }
}
