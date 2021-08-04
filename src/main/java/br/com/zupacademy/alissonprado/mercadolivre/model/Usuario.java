package br.com.zupacademy.alissonprado.mercadolivre.model;

import br.com.zupacademy.alissonprado.mercadolivre.cadastroUsuario.Senha;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfilList = new ArrayList<>();

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;


    @Deprecated
    public Usuario() {
    }

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

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfilList;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
