package br.com.zupacademy.alissonprado.mercadolivre.service.imagem;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Profile("prod")
@Service
public class EnviaImagemServiceAmazon implements EnviaImagem {

    String nomeImagem;
    String url;
    String hashAleatorio;

    @Override
    public String Envia(MultipartFile imagem) {

        nomeImagem = imagem.getOriginalFilename();

        //Envia imagem ao servidor na Amazone recebe a url
        System.out.println("Envia imagem.");

        hashAleatorio = geraHashAleatorio();

        return "http://zupacademy.s3-website-us-east-1.amazonaws.com/categories/images/" + hashAleatorio +  "." + nomeImagem;
    }

    private String geraHashAleatorio(){
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        return myRandom.substring(0,20);
    }
}
