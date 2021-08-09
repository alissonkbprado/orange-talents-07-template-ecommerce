package br.com.zupacademy.alissonprado.mercadolivre.features.compra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class CompraPasso2Controller {

    @GetMapping("/api/produtos/compraPasso2/{chaveUuid}")
    @Transactional
    public ResponseEntity<?> passo1(@PathVariable String chaveUuid){

        return ResponseEntity.ok("Voltou!!! : " + chaveUuid);

    }
}
