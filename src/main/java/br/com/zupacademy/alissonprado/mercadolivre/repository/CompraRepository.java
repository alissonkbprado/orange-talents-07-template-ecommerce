package br.com.zupacademy.alissonprado.mercadolivre.repository;

import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    Optional<Compra> findByChaveUUID(String chaveUuid);
}
