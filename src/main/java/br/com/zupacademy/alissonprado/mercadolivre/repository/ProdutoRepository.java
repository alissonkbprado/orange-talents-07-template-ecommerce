package br.com.zupacademy.alissonprado.mercadolivre.repository;

import br.com.zupacademy.alissonprado.mercadolivre.model.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
}
