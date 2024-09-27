package com.vttrpg.RPG.application.exception;

import jakarta.validation.ConstraintViolationException;
import org.everit.json.schema.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptionException(ValidationException ex) {
        Map<String, String> errors = new HashMap<>();
        extractValidationErrors(ex, errors);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private void extractValidationErrors(ValidationException ex, Map<String, String> errors) {
        // If there are no further causing exceptions, it is a base-level violation
        if (ex.getCausingExceptions().isEmpty()) {
            String pointer = ex.getPointerToViolation();
            String message = ex.getMessage();

            // Find the first colon and extract everything after it
            int colonIndex = message.indexOf(":");
            String refinedMessage = colonIndex != -1 ? message.substring(colonIndex + 1).trim() : message;

            errors.put(pointer, refinedMessage);
        } else {
            // For nested causing exceptions, recurse into them
            ex.getCausingExceptions().forEach(causingEx -> extractValidationErrors(causingEx, errors));
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(cv -> {
            String fieldName = cv.getPropertyPath().toString();
            String errorMessage = cv.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

