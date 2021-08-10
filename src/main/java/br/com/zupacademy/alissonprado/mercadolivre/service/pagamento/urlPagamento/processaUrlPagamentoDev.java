package br.com.zupacademy.alissonprado.mercadolivre.service.pagamento.urlPagamento;

import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
public class processaUrlPagamentoDev implements ProcessaUrlPagamento {
    @Override
    public String processa(Compra compra) {
        String chaveUUID = compra.geraUUID(compra.getId());
        return "localhost/api/produtos/compraPasso2/chaveUuid=" + chaveUUID;
    }

    @Override
    public String recupera(Compra compra) {
        return "http://localhost/api/produtos/compraPasso2/chaveUuid=" + compra.getChaveUUID();
    }
}
