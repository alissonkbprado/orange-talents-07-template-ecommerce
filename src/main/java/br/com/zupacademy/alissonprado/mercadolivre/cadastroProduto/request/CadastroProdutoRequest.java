package br.com.zupacademy.alissonprado.mercadolivre.cadastroProduto.request;

import br.com.zupacademy.alissonprado.mercadolivre.model.CaracteristicaProduto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Categoria;
import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.ExistsId;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.ExistsRepetitionCaracteristica;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CadastroProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull @Positive
    private BigDecimal preco;

    @NotNull @Positive
    private Integer quantidade;

    @NotBlank @Size(max = 1000)
    private String descricao;

    @NotNull @Positive
    @ExistsId(domainClass = Categoria.class, idName = "id", optional = false)
    private Long idCategoria;

    @Valid
    @Size(min = 3, message = "É obrigatório cadastrar no mínimo 3 Características diferentes.")
    @ExistsRepetitionCaracteristica(message = "Não podem ser cadastradas Características com nome repetido.")
    private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();


    public CadastroProdutoRequest(String nome, BigDecimal preco, Integer quantidade, String descricao, Long idCategoria, List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Produto toModel(Usuario usuario){
        return new Produto(this.nome.trim(),
                this.preco,
                this.quantidade,
                this.descricao.trim(),
                new Categoria(this.idCategoria),
                usuario,
                this.caracteristicas.stream().map(CaracteristicaProduto::new).collect(Collectors.toSet()));
    }
}
