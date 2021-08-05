package br.com.zupacademy.alissonprado.mercadolivre.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ExistsRepetitionCategotiaValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsRepetitionCategoria {

    String message() default ("A lista não pode pode ter itens repetidos.");

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
