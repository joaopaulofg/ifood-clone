package com.joaopaulofg.catalog.infrastructure.input.rest.advice;

import com.joaopaulofg.catalog.domain.exception.InvalidProductException;
import com.joaopaulofg.catalog.domain.exception.CategoryNotFoundException;
import com.joaopaulofg.catalog.domain.exception.RestaurantNotFoundException;
import com.joaopaulofg.catalog.infrastructure.input.rest.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice(basePackages = "com.joaopaulofg.catalog.infrastructure.input.rest")
public class GlobalExceptionHandler {

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRestaurantNotFound(RestaurantNotFoundException ex, ServletWebRequest request) {
        ErrorResponse body = ErrorResponse.builder()
                .error("RestaurantNotFound")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequest().getRequestURI())
                .timestamp(OffsetDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFound(CategoryNotFoundException ex, ServletWebRequest request) {
        ErrorResponse body = ErrorResponse.builder()
                .error("CategoryNotFound")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequest().getRequestURI())
                .timestamp(OffsetDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ErrorResponse> handleInvalidProduct(InvalidProductException ex, ServletWebRequest request) {
        ErrorResponse body = ErrorResponse.builder()
                .error("InvalidProduct")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequest().getRequestURI())
                .timestamp(OffsetDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex, ServletWebRequest request) {
        ErrorResponse body = ErrorResponse.builder()
                .error("EntityNotFound")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequest().getRequestURI())
                .timestamp(OffsetDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, ServletWebRequest request) {
        List<String> details = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse body = ErrorResponse.builder()
                .error("ValidationError")
                .message("Validation failed")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequest().getRequestURI())
                .timestamp(OffsetDateTime.now())
                .details(details)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex, ServletWebRequest request) {
        List<String> details = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.toList());

        ErrorResponse body = ErrorResponse.builder()
                .error("ConstraintViolation")
                .message("Constraint violations occurred")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequest().getRequestURI())
                .timestamp(OffsetDateTime.now())
                .details(details)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, ServletWebRequest request) {
        ErrorResponse body = ErrorResponse.builder()
                .error("InternalServerError")
                .message("An unexpected error occurred")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getRequest().getRequestURI())
                .timestamp(OffsetDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}