package br.com.zupacademy.alissonprado.mercadolivre.features.externo;

public class NovaCompraNFRequest {

    private Long idCompra;
    private Long idComprador;

    public NovaCompraNFRequest(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }
}
