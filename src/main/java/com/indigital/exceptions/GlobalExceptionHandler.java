package com.indigital.exceptions;

import com.indigital.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de excepciones para BusinessException personalizada
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorResponse> businessExceptionHandler(BusinessException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .date(LocalDateTime.now())
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    // Manejo de excepciones para MethodArgumentNotValidException (validación de argumentos)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DataErrorValidation::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    // Manejo de excepciones para HttpMediaTypeNotSupportedException
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse error = ErrorResponse.builder()
                .date(LocalDateTime.now())
                .code("P-400")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, status);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse error = ErrorResponse.builder()
                .date(LocalDateTime.now())
                .code("P-500")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, status);
    }

    // Este método maneja excepciones de tipo NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse error = ErrorResponse.builder()
                .date(LocalDateTime.now())
                .code("P-500")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, status);
    }


    // Este método maneja excepciones de tipo HttpRequestMethodNotSupportedException
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        ErrorResponse error = ErrorResponse.builder()
                .date(LocalDateTime.now())
                .code("P-405")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, status);
    }


    private record DataErrorValidation(String campo, String error){
        public DataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }


}
