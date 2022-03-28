package com.taulia.supplier.onboarding.common.constraint

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import javax.validation.ConstraintValidatorContext

import static org.junit.jupiter.api.Assertions.assertFalse
import static org.junit.jupiter.api.Assertions.assertTrue

@ExtendWith(MockitoExtension.class)
class NotNullMapValueValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext

    private final NotNullMapValueValidator notNullMapValueValidator = new NotNullMapValueValidator()

    @Test
    void isValidForMapWithNoNullValues() {

        // given
        Map<String, Object> map = Map.of("testKey", "testObj")

        // when
        var valid = notNullMapValueValidator.isValid(map, constraintValidatorContext)

        // then
        assertTrue(valid)
    }

    @Test
    void isValidForMapWithNullValues() {

        // given
        Map<String, Object> map = new HashMap<>()
        map.put("testKey", null)
        map.put("otherKey", "any")

        // when
        var valid = notNullMapValueValidator.isValid(map, constraintValidatorContext)

        // then
        assertFalse(valid)
    }
}
