package br.com.zupacademy.alissonprado.mercadolivre.cadastroImagem.controller;

import br.com.zupacademy.alissonprado.mercadolivre.cadastroImagem.request.CadastroImagemProdutoRequest;
import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.repository.ImagemProdutoRepository;
import br.com.zupacademy.alissonprado.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.alissonprado.mercadolivre.service.EnviaImagemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class CadastroImagemProdutoController {

    ImagemProdutoRepository imagemProdutoRepository;

    ProdutoRepository produtoRepository;

    EnviaImagemService enviaImagemService;

    public CadastroImagemProdutoController(ImagemProdutoRepository imagemProdutoRepository,
                                           ProdutoRepository produtoRepository,
                                           EnviaImagemService enviaImagemService) {
        this.imagemProdutoRepository = imagemProdutoRepository;
        this.produtoRepository = produtoRepository;
        this.enviaImagemService = enviaImagemService;
    }

    @PostMapping(value = "/api/produtos/imagens/")
    @Transactional
    public ResponseEntity<?> cadastrar(@Valid CadastroImagemProdutoRequest imagensRequest,
                                       @AuthenticationPrincipal Usuario usuario){
        String url;

        if (produtoNaoEhDoUsuarioLogado(imagensRequest.getIdProduto(), usuario.getId()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("O Produto indicado não pertence ao Usuario logado.");

        /**
         * Depois de persistir o objeto e gerar o id,
         * utilizamos o id para enviar ao repositório e gerar a url de acesso a imagem.
         */
        for (MultipartFile imagem : imagensRequest.getImagens()) {
            url = enviaImagemService.Envia(imagem);

            if (url.isBlank())
                return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE)
                        .body("Não foi possível salvar a imagem (" + imagem.getOriginalFilename() +") enviada, " +
                                "por favor tente tovamente.");

            imagemProdutoRepository.save(imagensRequest.toModel(imagem, url));
        }

        return ResponseEntity.ok("Imagens cadastradas com sucesso.");
    }

    private Boolean produtoNaoEhDoUsuarioLogado(Long idProduto, Long idUsuarioLogado) {
        Optional<Produto> produto = produtoRepository.findByIdAndUsuario_Id(idProduto, idUsuarioLogado);

        return  produto.isEmpty();
    }
}
