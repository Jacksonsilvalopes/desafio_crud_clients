package br.com.jackson.desafio_crud_de_clientes.customers.repository;

import br.com.jackson.desafio_crud_de_clientes.customers.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client,Long>{
}
