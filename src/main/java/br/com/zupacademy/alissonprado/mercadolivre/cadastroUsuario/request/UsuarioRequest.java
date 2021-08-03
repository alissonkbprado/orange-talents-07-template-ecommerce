package br.com.zupacademy.alissonprado.mercadolivre.cadastroUsuario.request;

import br.com.zupacademy.alissonprado.mercadolivre.cadastroUsuario.Senha;
import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.validacao.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "email")
    private String login;

    @NotBlank @Size(min = 6, message = "Deve possuir tamanho mínimo de 6.")
    private String senha;

    public UsuarioRequest(
            @NotBlank @Email String login,
            @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel(){
        return new Usuario(this.login.trim(), Senha.encoda(this.senha.trim()));
    }
}

