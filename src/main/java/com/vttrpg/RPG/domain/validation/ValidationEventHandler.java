package com.vttrpg.RPG.domain.validation;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

import java.util.Set;

@Component
@RepositoryEventHandler
@AllArgsConstructor
public class ValidationEventHandler {

    private final Validator validator;

    @HandleBeforeCreate
    public <T extends Validatable> void validateBeforeCreate(@Valid T entity) {
        validateEntity(entity);
    }

    @HandleBeforeSave
    public <T extends Validatable> void validateBeforeSave(@Valid T entity) {
        validateEntity(entity);
    }

    private <T extends Validatable> void validateEntity(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
