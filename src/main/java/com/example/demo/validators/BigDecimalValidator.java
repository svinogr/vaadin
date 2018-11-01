package com.example.demo.validators;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;

import java.math.BigDecimal;

public class BigDecimalValidator implements Validator<String> {
    /**
     * Validates the given value. Returns a {@code ValidationResult} instance
     * representing the outcome of the validation.
     *
     * @param value   the input value to validate
     * @param context the value context for validation
     * @return the validation result
     */
    @Override
    public ValidationResult apply(String value, ValueContext context) {
        try {
            BigDecimal i = new BigDecimal(value);
        } catch (Exception e) {

            return ValidationResult.error("Необходимо ввести число через точку");
        }

        return ValidationResult.ok();
    }
}
