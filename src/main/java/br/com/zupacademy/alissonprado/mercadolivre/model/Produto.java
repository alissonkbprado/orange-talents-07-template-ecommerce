package br.com.zupacademy.alissonprado.mercadolivre.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull @Positive
    @Column(nullable = false)
    private BigDecimal preco;

    @NotNull @PositiveOrZero
    @Column(nullable = false)
    private Integer quantidade;

    @NotBlank @Size(max = 1000)
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(length = 1000, nullable = false)
    private String descricao;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Categoria categoria;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "produto_id")
    private Set<CaracteristicaProduto> caracteristicaProdutoHashSet = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "produto_id")
    private List<ImagemProduto> imagemProdutoList = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "produto_id")
    private List<OpiniaoProduto> opiniaoProdutoList = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "produto_id")
    private List<PerguntaProduto> perguntaProdutoList = new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    private List<Compra> compraList = new ArrayList<>();

    /**
     * Não utilizar.
     * Criado por exigencia da JPA
     */
    @Deprecated
    public Produto() {
    }

    /**
     *
     * @param id NoNull
     */
    public Produto(Long id) {
        Assert.notNull(id, "Id de Produto não pode ser nulo.");
        this.id = id;
    }

    /**
     *
     * @param nome NotBlank
     * @param preco NotBlank
     * @param quantidade NotNull, Positive
     * @param descricao NotBlank, Size(max = 1000)
     * @param categoria NotNull, Deve existir no Banco de dados
     * @param caracteristicaProdutoHashSet NotNull, @Size(min = 3)
     */
    public Produto(@NotBlank String nome,
                   @NotNull @Positive BigDecimal preco,
                   @NotNull @Positive Integer quantidade,
                   @NotBlank @Size(max = 1000) String descricao,
                   @NotNull Categoria categoria,
                   @NotNull Usuario usuario,
                   @NotBlank @Size(min = 3) Set<CaracteristicaProduto> caracteristicaProdutoHashSet) {
        Assert.hasText(nome, "Campo nome deve ser preenchido");
        Assert.notNull(preco, "Campo preço deve ser preenchido");
        Assert.isTrue((preco.signum() > 0), "Campo preço deve ser maior que zero");
        Assert.notNull(quantidade, "Campo Quantidade deve ser preenchido");
        Assert.isTrue((quantidade >= 0.0), "Quantidade preço deve ser maior que ou igual a zero");
        Assert.hasText(descricao, "Campo descricao deve ser preenchido");
        Assert.notNull(categoria, "Campo categoria não pode ser nulo");
        Assert.notNull(usuario, "Campo usuario não pode ser nulo");
        Assert.isTrue(caracteristicaProdutoHashSet.size() >= 3 , "A lista de categorias deve possuir no mínimo 3 elementos");

        this.nome = nome.trim();
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao.trim();
        this.categoria = categoria;
        this.usuario= usuario;
        this.caracteristicaProdutoHashSet.addAll(caracteristicaProdutoHashSet);
    }

    public Long getId() {
        return id;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public List<CaracteristicaProduto> getCaracteristicaList() {
        return caracteristicaProdutoHashSet.stream().collect(Collectors.toList());
    }

    public Set<CaracteristicaProduto> getCaracteristicaProdutoHashSet() {
        return caracteristicaProdutoHashSet;
    }

    public List<OpiniaoProduto> getOpiniaoProdutoList() {
        return opiniaoProdutoList;
    }

    public List<PerguntaProduto> getPerguntaProdutoList() {
        return perguntaProdutoList;
    }

    public List<ImagemProduto> getImagemProdutoList() {
        return imagemProdutoList;
    }

    // COMO TESTAR
    public Double getMediaNotas(){
        return this.opiniaoProdutoList
                .stream()
                .mapToDouble(OpiniaoProduto::getNota)
                .average()
                .orElse(0.0);
    }

    public Integer getQuantidadeTotalOpinioes() {
        return this.opiniaoProdutoList.size();
    }


    public Boolean abateEstoque(Integer quantidade) {
        Assert.isTrue(quantidade > 0, "A quantidade para abater em estoque deve ser maior que zero.");

        if(this.quantidade < quantidade)
            return false;

        this.quantidade -= quantidade;
        return true;
    }
}
