package br.com.zupacademy.alissonprado.mercadolivre.service.formaPagamento;

import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;

public interface ProcessaPagamento {
    String processa(Compra compra);
}
