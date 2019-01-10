package com.example.demo.validators;

import com.example.demo.services.UniqTestInterface;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;

public class UniqTextValidator implements Validator<Object> {

    /**
     * Validates the given value. Returns a {@code ValidationResult} instance
     * representing the outcome of the validation.
     *
     * @param value   the input value to validate
     * @param context the value context for validation
     * @return the validation result
     */

    protected UniqTestInterface uniqTestInterface;
    protected long id;

    public UniqTextValidator(UniqTestInterface uniqTestInterface, long id) {
        this.uniqTestInterface = uniqTestInterface;
        this.id = id;
    }

    @Override
    public ValidationResult apply(Object value, ValueContext context) {
        System.out.println(id);
        if (!uniqTestInterface.isUniq((String) value, id)) {
            return ValidationResult.error("Такие данные уже используется");
        } else return ValidationResult.ok();


    }
}