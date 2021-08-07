package br.com.zupacademy.alissonprado.mercadolivre.features.produto.detalharProduto.response;

import br.com.zupacademy.alissonprado.mercadolivre.model.PerguntaProduto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PerguntaProdutoResponse {

    private String titulo;
    private String pergunta;
    private String nome;
    private String dataCadastro;

    public PerguntaProdutoResponse(PerguntaProduto perguntaProduto) {
        this.titulo = perguntaProduto.getTitulo();
        this.pergunta = perguntaProduto.getPergunta();
        this.nome = perguntaProduto.getNomeUsuario();
        this.dataCadastro = perguntaProduto.getDataCadastro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:MM")).toString();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String getNome() {
        return nome;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }
}
