package br.com.jackson.desafio_crud_de_clientes.customers.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class ValidationError extends CustomError {


    private Map<String, String> errors;

    public ValidationError(Instant timestamp, Integer status, String error, String path, BindingResult result) {
        super(timestamp, status, error, path);
        addErrors(result);
    }

    public void addErrors(BindingResult result) {
        this.errors = new HashMap<>();
        for (FieldError fildError : result.getFieldErrors()) {
            this.errors.put(fildError.getField(), fildError.getDefaultMessage());
        }
    }
}
