package br.com.zupacademy.alissonprado.mercadolivre.service.formaPagamento;

import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class processaPagamentoProd implements ProcessaPagamento{
    @Override
    public String processa(Compra compra) {
        String chaveUUID = compra.geraUUID(compra.getId());
        return compra.getFormaPagamento().getUrl(chaveUUID) + "&redirectUrl=/api/produtos/compraPasso2/" + chaveUUID;
    }
}
