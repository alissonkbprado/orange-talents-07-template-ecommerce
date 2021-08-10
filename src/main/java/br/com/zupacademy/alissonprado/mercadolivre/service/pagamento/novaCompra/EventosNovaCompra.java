package br.com.zupacademy.alissonprado.mercadolivre.service.pagamento.novaCompra;

import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;
import br.com.zupacademy.alissonprado.mercadolivre.service.mensagem.EnviaMensagem;
import br.com.zupacademy.alissonprado.mercadolivre.service.pagamento.urlPagamento.ProcessaUrlPagamento;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosNovaCompra {

    private Set<EventoCompraSucesso> eventoCompraSucessoSet;
    private EnviaMensagem enviaMensagem;
    private ProcessaUrlPagamento processaUrlPagamento;

    public EventosNovaCompra(Set<EventoCompraSucesso> eventoCompraSucessoSet, EnviaMensagem enviaMensagem, ProcessaUrlPagamento processaUrlPagamento) {
        this.eventoCompraSucessoSet = eventoCompraSucessoSet;
        this.enviaMensagem = enviaMensagem;
        this.processaUrlPagamento = processaUrlPagamento;
    }

    public boolean processa(Compra compra, boolean processado) {
        if(processado) {
            eventoCompraSucessoSet.forEach(evento -> {evento.processa(compra);});
            return true;
        } else {
            enviaEmailFalha(compra);
            return false;
        }
    }

    private void enviaEmailFalha(Compra compra) {
        enviaMensagem.envia("naoresponder@mercadolivre.com",
                compra.getComprador().getEmail(),
                "Falha no processamento de pagamento:"  + " - Produto: " + compra.getProduto().getNome(),
                "Não foi possível finalizar o processamento de pagamento do produto " + compra.getProduto().getNome() + " na " +
                        "quantidade de " + compra.getQuantidade() + " unidade(s) " +
                        "" +
                        "Por favor acesse o link para reiniciar o processo de pagamento: " + processaUrlPagamento.recupera(compra));
    }

}
