package br.com.zupacademy.alissonprado.mercadolivre.service.pagamento.novaCompra;

import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class Ranking implements EventoCompraSucesso {

    @Override
    public void processa(Compra compra){
        HashMap<String, String> request = new HashMap<>();
        request.put("idCompra", compra.getId().toString());
        request.put("idVendedor", compra.getProduto().getUsuario().getId().toString());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://localhost/ranking", request, String.class);

    }
}
