package br.com.zupacademy.alissonprado.mercadolivre.validacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    private boolean optional;
    private String idName;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsId params) {
        optional = params.optional();
        klass = params.domainClass();
        idName = params.idName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if(optional && value == null)
            return true;

        if(!optional && value == null)
            return false;

        if(!value.toString().matches("[0-9]*"))
            return false;

        Query query = manager.createQuery("SELECT 1 FROM " + klass.getSimpleName() + " WHERE " + idName + " = :value");
        query.setParameter("value", Long.parseLong(value.toString()));

        List<?> list = query.getResultList();

        return !list.isEmpty();
    }
}
