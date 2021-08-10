package br.com.zupacademy.alissonprado.mercadolivre.service.pagamento.novaCompra;

import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class NotaFiscal implements EventoCompraSucesso {

    @Override
    public void processa(Compra compra){
        HashMap<String, String> request = new HashMap<>();
        request.put("idCompra", compra.getId().toString());
        request.put("idComprador", compra.getComprador().getId().toString());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://localhost/notas-fiscais", request, String.class);

    }
}
