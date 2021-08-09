package br.com.zupacademy.alissonprado.mercadolivre.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = EnumNamePatternValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumNamePattern {
    String message() default ("Deve ser passado um dos seguintes valores \"{regexp}\"");

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String regexp();
}
