package br.com.jackson.desafio_crud_de_clientes.customers.service.exceptions;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String msg) {
        super(msg);
    }
}
