package br.com.zupacademy.alissonprado.mercadolivre.features;

import java.math.BigDecimal;

public enum FormaPagamento {

    PAYPAL {
        @Override
        public String getUrl(String uuid) {
            return "paypal.com?buyerId=" + uuid;
        }
    },
    PAGSEGURO {
        @Override
        public String getUrl(String uuid) {
            return "pagseguro.com?returnId=" + uuid;
        }
    },
    ;

    public abstract String getUrl(String uuid);
}
