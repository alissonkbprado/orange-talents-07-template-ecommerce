package br.com.zupacademy.alissonprado.mercadolivre.repository;

import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String username);
}
