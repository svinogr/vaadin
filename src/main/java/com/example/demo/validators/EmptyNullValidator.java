package com.example.demo.validators;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;

public class EmptyNullValidator implements Validator<Object> {
    /**
     * Validates the given value. Returns a {@code ValidationResult} instance
     * representing the outcome of the validation.
     *
     * @param value   the input value to validate
     * @param context the value context for validation
     * @return the validation result
     */


    @Override
    public ValidationResult apply(Object value, ValueContext context) {
        try {
            if (value == null) throw new Exception();
            if (((String) value).isEmpty()) throw new Exception();

        } catch (Exception e) {

            return ValidationResult.error("Поле не может быть пустым");
        }

        return ValidationResult.ok();
    }
}