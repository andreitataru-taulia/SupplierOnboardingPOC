package com.taulia.supplier.onboarding.common.constraint

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class NotNullMapValueValidator
        implements ConstraintValidator<NotNullMapValue, Map<String, Object>> {

    @Override
    void initialize(NotNullMapValue constraintAnnotation) {
        super.initialize(constraintAnnotation)
    }

    @Override
    boolean isValid(Map<String, Object> value, ConstraintValidatorContext context) {
        return value.values().stream().noneMatch(Objects::isNull)
    }
}
