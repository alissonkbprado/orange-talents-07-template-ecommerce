package br.com.zupacademy.alissonprado.mercadolivre.service.mensagem;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "enviadorDeEmails", url = "http://localhost:8081/emails")
public interface EmailClient {

    @PostMapping
    void envia(EmailTemplate emailTemplate);
}
