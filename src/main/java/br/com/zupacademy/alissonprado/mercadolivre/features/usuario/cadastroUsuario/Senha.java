package br.com.zupacademy.alissonprado.mercadolivre.features.usuario.cadastroUsuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Senha {

    private String senha;

    private Senha(String senhaCriptografada) {
        this.senha = senhaCriptografada;
    }

    /**
     *
     * @param senhaEmTextoPuro Senha Limpa (NÂO enviar o Hash), NotNull, Min = 6
     * @return
     */
    public static Senha encoda(@NotBlank @Size(min = 6) String senhaEmTextoPuro){
        Assert.hasText(senhaEmTextoPuro, "Obrigatório envio de senha ao cadastrar Usuario.");
        Assert.isTrue(senhaEmTextoPuro.length() >= 6, "A senha precisa ter no mínimo 6 caracteres");

        return new Senha(new BCryptPasswordEncoder().encode(senhaEmTextoPuro.trim()));
    }

    public String getSenha() {
        return senha;
    }

}
