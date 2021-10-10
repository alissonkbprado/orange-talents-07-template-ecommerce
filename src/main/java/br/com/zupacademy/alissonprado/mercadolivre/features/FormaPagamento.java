package br.com.zupacademy.alissonprado.mercadolivre.features;

public enum FormaPagamento {

    PAYPAL {
        @Override
        public String getUrl(String uuid) {
            return "paypal.com?buyerId=" + uuid;
        }

        @Override
        public StatusTransacaoPagamento getStatusTransacaoPagamento(String statusTransacaoPagamentoRequest) {
            if (statusTransacaoPagamentoRequest.equals("1")) {
                return StatusTransacaoPagamento.SUCESSO;
            } else {
                return StatusTransacaoPagamento.FALHA;
            }
        }
    },
    PAGSEGURO {
        @Override
        public String getUrl(String uuid) {
            return "pagseguro.com?returnId=" + uuid;
        }

        @Override
        public StatusTransacaoPagamento getStatusTransacaoPagamento(String statusTransacaoPagamentoRequest) {
            if(statusTransacaoPagamentoRequest.equals("SUCESSO"))
                return StatusTransacaoPagamento.SUCESSO;

            return StatusTransacaoPagamento.FALHA;
        }
    },
    ;

    public abstract String getUrl(String uuid);

    public abstract StatusTransacaoPagamento getStatusTransacaoPagamento(String statusTransacaoPagamentoRequest);
}
