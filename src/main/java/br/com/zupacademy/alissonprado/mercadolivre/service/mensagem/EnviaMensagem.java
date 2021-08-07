package br.com.zupacademy.alissonprado.mercadolivre.service.mensagem;

import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;

public interface EnviaMensagem {
    void envia(String remetente, String destinatario, String titulo, String mensagem);
}
