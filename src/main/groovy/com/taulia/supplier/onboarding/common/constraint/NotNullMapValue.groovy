package com.taulia.supplier.onboarding.common.constraint

import javax.validation.Constraint
import javax.validation.Payload
import java.lang.annotation.*

@Documented
@Constraint(validatedBy = NotNullMapValueValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface NotNullMapValue {

    String message() default "Map value must not be null"

    Class<?>[] groups() default []

    Class<? extends Payload>[] payload() default []
}
