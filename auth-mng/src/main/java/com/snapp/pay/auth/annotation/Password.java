package com.snapp.pay.auth.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE_PARAMETER})
@Pattern(regexp = "^(?=(.*[a-z]){1,})(?=(.*[A-Z]){1,})(?=(.*[0-9]){1,})(?!.*\\s)(?=(.*[!@#$%^&*()\\-__+.|{}',/:;<=>?`~]){1,}).{8,16}$")
public @interface Password {

    String message() default "The entered password doesn't meet the required complexity!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
