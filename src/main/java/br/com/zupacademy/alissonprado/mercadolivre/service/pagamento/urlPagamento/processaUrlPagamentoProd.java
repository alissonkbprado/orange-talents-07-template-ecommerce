package br.com.zupacademy.alissonprado.mercadolivre.service.pagamento.urlPagamento;

import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class processaUrlPagamentoProd implements ProcessaUrlPagamento {
    @Override
    public String processa(Compra compra) {
        String chaveUUID = compra.geraUUID(compra.getId());
        return compra.getFormaPagamento().getUrl(chaveUUID) + "&redirectUrl=localhost/api/produtos/compraPasso2/" + chaveUUID;
    }

    @Override
    public String recupera(Compra compra) {
        return compra.getFormaPagamento().getUrl(compra.getChaveUUID()) + "&redirectUrl=localhost/api/produtos/compraPasso2/" + compra.getChaveUUID();
    }
}
