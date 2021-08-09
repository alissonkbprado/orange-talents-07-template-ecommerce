package br.com.zupacademy.alissonprado.mercadolivre.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ExistsQuantityProdutoValidator.class)
@Documented
public @interface ExistsQuantityProduto {
    String message() default ("Não há produtos em estoque em quantidade suficiente para atender a solicitação, por favor informe uma quantidade menor.");

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

