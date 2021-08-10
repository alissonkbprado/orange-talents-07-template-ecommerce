package br.com.zupacademy.alissonprado.mercadolivre.features.externo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutrosSistemasController {

    @PostMapping("/notas-fiscais")
    public void criaNoraFiscal(@RequestBody NovaCompraNFRequest request) throws InterruptedException{
        System.out.println("------------------> Criando Nota Fiscal para " + request.getIdCompra() + " do comprador " + request.getIdComprador());
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void criaRanking(@RequestBody NovoRankingNFRequest request) throws InterruptedException{
        System.out.println("------------------> Criando Ranking de Venda para " + request.getIdCompra() + " do vendedor " + request.getIdVendedor());
        Thread.sleep(150);
    }



}
