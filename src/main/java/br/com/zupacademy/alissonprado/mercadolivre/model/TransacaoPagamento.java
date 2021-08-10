package br.com.zupacademy.alissonprado.mercadolivre.model;

import br.com.zupacademy.alissonprado.mercadolivre.features.FormaPagamento;
import br.com.zupacademy.alissonprado.mercadolivre.features.StatusTransacaoPagamento;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class TransacaoPagamento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String idPagamento;

    @NotNull @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @NotNull @Enumerated(EnumType.STRING)
    private StatusTransacaoPagamento StatusTransacaoPagamento;

    @CreationTimestamp
    private LocalDateTime dataTransacao;

    @ManyToOne
    private Compra compra;

    @Deprecated
    public TransacaoPagamento() {
    }

    /**
     *
     * @param idPagamento NotBlank
     * @param formaPagamento NotNull
     * @param statusTransacaoPagamento NotNull
     * @param compra NotNull
     */
    public TransacaoPagamento(@NotBlank String idPagamento,
                              @NotNull FormaPagamento formaPagamento,
                              @NotNull StatusTransacaoPagamento statusTransacaoPagamento,
                              @NotNull Compra compra) {
        Assert.hasText(idPagamento, "O campo idPagamento deve ser preenchido");
        Assert.notNull(formaPagamento, "O campo formaPagamento não pode ser nulo");
        Assert.notNull(statusTransacaoPagamento, "O campo statusTransacaoPagamento não pode ser nulo");
        Assert.notNull(compra, "O campo compra não pode ser nulo");

        this.idPagamento = idPagamento;
        this.formaPagamento = formaPagamento;
        StatusTransacaoPagamento = statusTransacaoPagamento;
        this.compra = compra;
    }

    public br.com.zupacademy.alissonprado.mercadolivre.features.StatusTransacaoPagamento getStatusTransacaoPagamento() {
        return StatusTransacaoPagamento;
    }
}
