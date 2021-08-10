package br.com.zupacademy.alissonprado.mercadolivre.features.externo;

public class NovoRankingNFRequest {

    private Long idCompra;
    private Long idVendedor;

    public NovoRankingNFRequest(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }
}
