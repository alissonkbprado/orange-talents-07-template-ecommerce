package br.com.zupacademy.alissonprado.mercadolivre.service.mensagem;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class EnviaEmailServiceProd implements EnviaMensagem{

    EmailClient emailClient;

    public EnviaEmailServiceProd(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    @Override
    public void envia(String remetente, String destinatario, String titulo, String mensagem) {
        System.out.println("Inicio Envio de Email");

        EmailTemplate emailTemplate = new EmailTemplate(remetente, destinatario, titulo, mensagem);

        emailClient.envia(emailTemplate);

        System.out.println("Email enviado com sucesso!");
    }
}
