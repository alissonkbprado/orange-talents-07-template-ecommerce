package br.com.zupacademy.alissonprado.mercadolivre.model;

import br.com.zupacademy.alissonprado.mercadolivre.cadastroUsuario.Senha;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank @Size(min = 6)
    private String senha;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

    /**
     *
     * @param email NoNull, Email,
     * @param senha NotNull, Min = 6
     */
    public Usuario(@NotBlank @Email String email, @NotBlank @Size(min = 6) Senha senha) {
        Assert.hasText(email, "Obrigatório envio de email ao cadastrar Usuario.");
        Assert.notNull(senha, "Campo senha não pode ser nulo");

        this.email = email;
        this.senha = senha.getSenha();
    }

//    private String geraHash(String senhaLimpa){
//        return new BCryptPasswordEncoder().encode(senhaLimpa.trim());
//    }

    public String getEmail() {
        return email;
    }
}
