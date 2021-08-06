package br.com.zupacademy.alissonprado.mercadolivre.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Envia imagem para servidor e retorna url
 */
@Service
public interface EnviaImagemService {
    public String Envia(MultipartFile imagem);
}
