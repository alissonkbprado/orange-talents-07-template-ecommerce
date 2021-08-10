package br.com.zupacademy.alissonprado.mercadolivre.service.mensagem;

public class EmailTemplate {

    private String remetente;
    private String destinatario;
    private String assunto;
    private String mensagem;

    @Deprecated
    public EmailTemplate() {
    }

    public EmailTemplate(String remetente, String destinatario, String assunto, String mensagem) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.mensagem = mensagem;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getMensagem() {
        return mensagem;
    }
}
