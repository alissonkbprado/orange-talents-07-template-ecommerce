package br.com.zupacademy.alissonprado.mercadolivre.model;

import br.com.zupacademy.alissonprado.mercadolivre.features.produto.cadastroProduto.request.CaracteristicaRequest;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CaracteristicaProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private GrupoCaracteristicaProduto grupoCaracteristicaProduto;

    /**
     * Não utilizar.
     * Criado por exigencia da JPA
     */
    @Deprecated
    public CaracteristicaProduto() {
    }

    /**
     *
     * @param nome NotBlank
     * @param descricao NotBlank
     * @param produto NotNull, Deve existir no Banco de dados
     * @param grupoCaracteristicaProduto NotNull, Deve existir no Banco de dados
     */
    public CaracteristicaProduto(@NotBlank String nome,
                                 @NotBlank String descricao,
                                 @NotNull Produto produto,
                                 @NotBlank GrupoCaracteristicaProduto grupoCaracteristicaProduto) {
        Assert.hasText(nome, "Nome da Característica deve ser preenchido");
        Assert.hasText(nome, "Descrição da Característica deve ser preenchido");
        Assert.isNull(produto, "Não pode ser passado um valor Nulo para Produto");
        Assert.isNull(grupoCaracteristicaProduto, "Não pode ser passado um valor Nulo para Grupo de Característica");

        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
        this.grupoCaracteristicaProduto = grupoCaracteristicaProduto;
    }


    /**
     * Pra faciliar a criação de um objeto Caracteristica.
     * Utilizado apenas pela classe CaracteristicaRequest
     * @param caracteristicaRequest
     */
    public CaracteristicaProduto(CaracteristicaRequest caracteristicaRequest) {
        Assert.notNull(caracteristicaRequest, "CaracteristicaRequest não pode ser nulo");
        Assert.hasText(caracteristicaRequest.getNome(), "Campo nome deve ser preenchido");
        Assert.hasText(caracteristicaRequest.getDescricao(), "Campo descricao deve ser preenchido");
        Assert.notNull(caracteristicaRequest.getIdGrupoCaracteristica(), "Campo getIdGrupoCaracteristica deve ser preenchido");

        this.nome = caracteristicaRequest.getNome();
        this.descricao = caracteristicaRequest.getDescricao();
        this.grupoCaracteristicaProduto = new GrupoCaracteristicaProduto(caracteristicaRequest.getIdGrupoCaracteristica());
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
