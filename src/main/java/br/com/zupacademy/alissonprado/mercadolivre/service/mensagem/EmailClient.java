package br.com.zupacademy.alissonprado.mercadolivre.service.mensagem;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "enviadorDeEmails", url = "http://localhost:8081/emails")
@Component
public interface EmailClient {

    @PostMapping
    void envia(EmailTemplate emailTemplate);
}
