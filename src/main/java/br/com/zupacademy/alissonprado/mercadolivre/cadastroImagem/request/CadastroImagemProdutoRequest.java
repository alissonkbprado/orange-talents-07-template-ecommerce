package br.com.zupacademy.alissonprado.mercadolivre.cadastroImagem.request;

import br.com.zupacademy.alissonprado.mercadolivre.model.ImagemProduto;
import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.ExistsId;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

public class CadastroImagemProdutoRequest {

    @NotNull @Positive @ExistsId(domainClass = Produto.class, idName = "id", optional = false, message = "O produto passado não está cadastrado no sistema")
    private Long idProduto;

    @NotNull @Size(min = 1)
    private List<MultipartFile> imagens;

    public CadastroImagemProdutoRequest(Long idProduto, List<MultipartFile> imagens) {
        this.idProduto = idProduto;
        this.imagens = imagens;
    }

    public ImagemProduto toModel(MultipartFile imagem, String url){
        return new ImagemProduto(imagem.getOriginalFilename(), url, new Produto(this.idProduto));
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
