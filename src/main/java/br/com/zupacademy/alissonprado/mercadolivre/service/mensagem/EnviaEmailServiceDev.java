package br.com.zupacademy.alissonprado.mercadolivre.service.mensagem;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
public class EnviaEmailServiceDev implements EnviaMensagem{

    @Override
    public void envia(String remetente, String destinatario, String titulo, String mensagem) {

        System.out.println("### Simulando Envio de email ###");

        System.out.println("Inicio Envio de Email");
        System.out.println("De: " + remetente);
        System.out.println("Para: " + destinatario);
        System.out.println("Assunto: " + titulo);
        System.out.println("Mensagem: " + mensagem);
        System.out.println("Email enviado com sucesso!");
    }
}
