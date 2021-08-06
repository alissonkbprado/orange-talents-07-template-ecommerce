package br.com.zupacademy.alissonprado.mercadolivre.cadastroUsuario.request;

import br.com.zupacademy.alissonprado.mercadolivre.cadastroUsuario.Senha;
import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CadastroUsuarioRequest {

    @NotBlank @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "email")
    private String email;

    @NotBlank @Size(min = 6, message = "Deve possuir tamanho mínimo de 6.")
    private String senha;

    public CadastroUsuarioRequest(
            @NotBlank @Email String email,
            @NotBlank @Size(min = 6) String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario toModel(){

        return new Usuario(this.email.trim(), Senha.encoda(this.senha.trim()));
    }
}

