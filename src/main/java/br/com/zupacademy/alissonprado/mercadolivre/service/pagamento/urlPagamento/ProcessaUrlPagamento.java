package br.com.zupacademy.alissonprado.mercadolivre.service.pagamento.urlPagamento;

import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;

public interface ProcessaUrlPagamento {
    String processa(Compra compra);

    String recupera(Compra compra);
}
