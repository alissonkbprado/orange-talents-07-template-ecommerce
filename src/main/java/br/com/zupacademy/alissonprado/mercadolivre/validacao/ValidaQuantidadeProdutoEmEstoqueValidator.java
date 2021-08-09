package br.com.zupacademy.alissonprado.mercadolivre.validacao;

import br.com.zupacademy.alissonprado.mercadolivre.features.compra.request.CompraPasso1Request;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class ValidaQuantidadeProdutoEmEstoqueValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> aClass) {
        return CompraPasso1Request.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        CompraPasso1Request request = (CompraPasso1Request) target;

        Query query = manager.createQuery("SELECT 1 FROM Produto p WHERE p.quantidade >= :requestQuantidade and p.id = :requestIdProduto");
        query.setParameter("requestQuantidade", request.getQuantidade());
        query.setParameter("requestIdProduto", request.getIdProduto());

        List<?> list = query.getResultList();

        if (list.isEmpty())
            errors.rejectValue("Quantidade",
                    null,
                    "Não há produtos em estoque em quantidade suficiente para atender a solicitação, por favor informe uma quantidade menor.");
    }
}
