package br.com.zupacademy.alissonprado.mercadolivre.validacao;

public class ErroDeFormularioResponse {

    private String campo;
    private String erro;

    public ErroDeFormularioResponse(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
