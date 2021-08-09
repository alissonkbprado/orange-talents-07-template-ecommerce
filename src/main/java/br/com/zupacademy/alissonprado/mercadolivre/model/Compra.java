package br.com.zupacademy.alissonprado.mercadolivre.model;

import br.com.zupacademy.alissonprado.mercadolivre.features.FormaPagamento;
import br.com.zupacademy.alissonprado.mercadolivre.features.StatusPagamento;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Compra {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Positive
    @Column(nullable = false)
    private Integer quantidade;

    @CreationTimestamp
    private LocalDateTime dataCompra;

    @NotNull @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento statusPagamento = StatusPagamento.INICIADA;

    @NotNull @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaPagamento formaPagamento;

    @NotNull @Positive
    @Column(nullable = false)
    private BigDecimal preco;

    @NotBlank
    @Column(nullable = false)
    private String chaveUUID;

    @ManyToOne
    Produto produto;

    @ManyToOne
    Usuario comprador;

    /**
     *
     * @param quantidade NotNull, Positive
     * @param produto NotNull
     * @param comprador NotNull, Usuario Logado
     */
    public Compra(@NotNull @Positive Integer quantidade,
                  @NotNull FormaPagamento formaPagamento,
                  @NotNull Produto produto,
                  @NotNull Usuario comprador) {
        Assert.notNull(quantidade, "O campo Quantidade não pode ser nulo");
        Assert.isTrue(quantidade > 0, "O campo Quantidade deve ser maior que zero");
        Assert.notNull(formaPagamento, "O campo formaPagamento não pode ser nulo");
        Assert.isTrue(formaPagamento.toString().matches("PAYPAL|PAGSEGURO"), "O campo formaPagamento está incorreto");
        Assert.notNull(produto, "O campo Produto não pode ser nulo");
        Assert.notNull(comprador, "O campo Usuario(comprador) não pode ser nulo");

        this.quantidade = quantidade;
        this.formaPagamento = formaPagamento;
        this.produto = produto;
        this.comprador = comprador;
        this.preco = produto.getPreco();
        this.chaveUUID = "0";
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public String getChaveUUID() {
        return chaveUUID;
    }

    public String geraUUID(Long id) {
        this.chaveUUID = this.id.toString() + "." + geraHashAleatorio();
        return this.chaveUUID;
    }

    private String geraHashAleatorio(){
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        return myRandom.substring(0,10);
    }
}
