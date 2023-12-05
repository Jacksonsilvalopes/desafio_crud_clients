package br.com.jackson.desafio_crud_de_clientes.customers.controller;

import br.com.jackson.desafio_crud_de_clientes.customers.dto.ClientResponseDTO;
import br.com.jackson.desafio_crud_de_clientes.customers.dto.mapper.ClientMapper;
import br.com.jackson.desafio_crud_de_clientes.customers.entity.Client;
import br.com.jackson.desafio_crud_de_clientes.customers.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(@Valid @RequestBody ClientResponseDTO clientResponseDTO){
       Client client = clientService.create(ClientMapper.toClient(clientResponseDTO));
       return ResponseEntity.status(HttpStatus.CREATED).body(ClientMapper.toDTO(client));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getById(@Valid  @PathVariable Long id){
      Client  client = clientService.findById(id);
        return ResponseEntity.ok(ClientMapper.toDTO(client));
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponseDTO>> findAll(Pageable pageable){
        Page<Client>  client = clientService.findAll(pageable);
        return ResponseEntity.ok(ClientMapper.toListDTO(client));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ClientResponseDTO> getById(@Valid  @PathVariable Long id, @Valid  @RequestBody ClientResponseDTO clientResponseDTO){
        Client  client = clientService.update(id, clientResponseDTO);
        return ResponseEntity.ok(ClientMapper.toDTO(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid  @PathVariable Long id){
        clientService.delete(id);
      return   ResponseEntity.noContent().build();
    }

}
