package br.com.zupacademy.alissonprado.mercadolivre.repository;

import br.com.zupacademy.alissonprado.mercadolivre.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
