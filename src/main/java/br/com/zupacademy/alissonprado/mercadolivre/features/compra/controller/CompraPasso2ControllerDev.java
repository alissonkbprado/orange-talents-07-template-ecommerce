package br.com.zupacademy.alissonprado.mercadolivre.features.compra.controller;

import br.com.zupacademy.alissonprado.mercadolivre.features.compra.request.CompraPasso2Request;
import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;
import br.com.zupacademy.alissonprado.mercadolivre.model.TransacaoPagamento;
import br.com.zupacademy.alissonprado.mercadolivre.repository.CompraRepository;
import br.com.zupacademy.alissonprado.mercadolivre.service.pagamento.novaCompra.EventosNovaCompra;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;

@RestController
//@Profile("dev")
public class CompraPasso2ControllerDev {

    private CompraRepository compraRepository;
    private EventosNovaCompra eventosNovaCompra;

    public CompraPasso2ControllerDev(CompraRepository compraRepository, EventosNovaCompra eventosNovaCompra) {
        this.compraRepository = compraRepository;
        this.eventosNovaCompra = eventosNovaCompra;
    }

    @GetMapping("/api/produtos/compraPasso2/{chaveUuid}")
    @Transactional
    public ResponseEntity<?> passo1(@PathVariable String chaveUuid, String idTrasacao, String status){

        return ResponseEntity.ok("Voltou!!! : " + chaveUuid);

    }

    @PostMapping("/api/produtos/compraPasso2/{chaveUuid}")
    @Transactional
    public ResponseEntity<?> compraPasso2(@PathVariable String chaveUuid, @RequestBody @Valid CompraPasso2Request request){

        Optional<Compra> optionalCompra = compraRepository.findByChaveUUID(chaveUuid);

        if (optionalCompra.isEmpty())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(geraMapErro("chaveUuid",
                    "Não há optionalCompra resgistrada com o identificador de optionalCompra enviado."));

        if (optionalCompra.get().getStatusPagamento().toString() != "INICIADA")
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(geraMapErro("Status Compra",
                    "Esta Compra está registrada com o Status " + optionalCompra.get().getStatusPagamento() + " e não pode prosseguir."));

        Compra compra = optionalCompra.get();

        TransacaoPagamento transacaoPagamento = request.toModel(optionalCompra.get());

        Boolean processado = compra.novoPagamento(transacaoPagamento, compraRepository);

        compraRepository.save(compra);

        if(eventosNovaCompra.processa(compra, processado))
            return ResponseEntity.status(HttpStatus.OK).body("Compra finalizada com sucesso");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(geraMapErro("Pagamento",
                "Houve falha no processamento do pagamento, encaminhado novo email ao comprador para refazer o processo."));
    }

    private HashMap<String, String> geraMapErro(String campo, String erro) {
        HashMap<String, String> map = new HashMap<>();
        map.put("campo", campo);
        map.put("erro", erro);
        return map;
    }
}
