package br.com.zupacademy.alissonprado.mercadolivre.service;

import org.bouncycastle.pqc.math.linearalgebra.GF2Polynomial;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Profile("dev")
@Service
public class EnviaImagemDevService implements EnviaImagemService{

    String nomeImagem;
    String url;
    String hashAleatorio;

    @Override
    public String Envia(MultipartFile imagem) {

        nomeImagem = imagem.getOriginalFilename();

        //Envia imagem ao servidor na Amazone recebe a url
        System.out.println("Envia imagem.");

        hashAleatorio = geraHashAleatorio();

        return "/api/produtos/imagens/" + hashAleatorio + "." + nomeImagem;
    }

    private String geraHashAleatorio(){
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        return myRandom.substring(0,20);
    }
}
