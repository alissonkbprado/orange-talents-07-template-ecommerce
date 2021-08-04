package br.com.zupacademy.alissonprado.mercadolivre.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ExistsIdValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsId {
    String message() default ("O id informado não está cadatrado. Deve ser informado um id já cadastrado no sistema");

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean optional();

    String idName();

    Class<?> domainClass();
}
