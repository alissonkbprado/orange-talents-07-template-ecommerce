package br.com.zupacademy.alissonprado.mercadolivre.service.pagamento.novaCompra;

import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;
import br.com.zupacademy.alissonprado.mercadolivre.service.mensagem.EnviaMensagem;
import org.springframework.stereotype.Service;

@Service
public class EmailComprador implements EventoCompraSucesso{

    private EnviaMensagem enviaMensagem;

    public EmailComprador(EnviaMensagem enviaMensagem) {
        this.enviaMensagem = enviaMensagem;
    }

    @Override
    public void processa(Compra compra) {
        enviaEmailSucesso(compra);
    }

    private void enviaEmailSucesso(Compra compra) {
        enviaMensagem.envia("naoresponder@mercadolivre.com",
                compra.getComprador().getEmail(),
                "Finalizado o processo de compra com sucesso:"  + " - Produto: " + compra.getProduto().getNome(),
                "Foi finalizado o processo de compra do produto " + compra.getProduto().getNome() + " na " +
                        "quantidade de " + compra.getQuantidade() + " unidade(s)");
    }
}
