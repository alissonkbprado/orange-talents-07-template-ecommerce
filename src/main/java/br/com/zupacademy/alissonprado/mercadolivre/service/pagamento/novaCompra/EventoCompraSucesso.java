package br.com.zupacademy.alissonprado.mercadolivre.service.pagamento.novaCompra;

import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;

/**
 * Todo evento com sucesso relacionado a uma nova compra deve implementar esta interface
 */
public interface EventoCompraSucesso {

    void processa(Compra compra);
}
