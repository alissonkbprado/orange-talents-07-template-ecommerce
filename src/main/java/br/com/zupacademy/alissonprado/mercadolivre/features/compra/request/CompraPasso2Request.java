package br.com.zupacademy.alissonprado.mercadolivre.features.compra.request;

import br.com.zupacademy.alissonprado.mercadolivre.features.StatusTransacaoPagamento;
import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;
import br.com.zupacademy.alissonprado.mercadolivre.model.TransacaoPagamento;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.NamePattern;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.UniqueValue;

import javax.validation.constraints.NotNull;

public class CompraPasso2Request {

    @NotNull @UniqueValue(domainClass = TransacaoPagamento.class, fieldName = "id_pagamento")
    private String idPagamento;

    @NotNull @NamePattern(regexp = "SUCESSO|ERRO|0|1")
    private String Status;

    public CompraPasso2Request(String idPagamento, String status) {
        this.idPagamento = idPagamento;
        Status = status;
    }

    public TransacaoPagamento toModel(Compra compra){
        StatusTransacaoPagamento statusTransacaoPagamento = compra.getFormaPagamento().getStatusTransacaoPagamento(this.Status);

        return new TransacaoPagamento(this.idPagamento, compra.getFormaPagamento(), statusTransacaoPagamento, compra);
    }
}
