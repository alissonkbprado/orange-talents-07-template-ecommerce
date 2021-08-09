package br.com.zupacademy.alissonprado.mercadolivre.validacao;

import br.com.zupacademy.alissonprado.mercadolivre.features.compra.request.CompraPasso1Request;
import org.springframework.validation.BindException;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsQuantityProdutoValidator implements ConstraintValidator<ExistsQuantityProduto, CompraPasso1Request> {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean isValid(CompraPasso1Request request, ConstraintValidatorContext context) {

        Query query = manager.createQuery("SELECT 1 FROM Produto p WHERE p.quantidade >= :requestQuantidade and p.id = :requestIdProduto");
        query.setParameter("requestQuantidade", request.getQuantidade());
        query.setParameter("requestIdProduto", request.getIdProduto());

        List<?> list = query.getResultList();

        BindException problemaComEstoque = new BindException(request, "CompraPasso1Request");
        problemaComEstoque.reject(null, "Não foi possivel realizar a compra.");

        try {
            throw problemaComEstoque;

        } catch (BindException e) {
            e.printStackTrace();
            return !list.isEmpty();
        }



    }
}
