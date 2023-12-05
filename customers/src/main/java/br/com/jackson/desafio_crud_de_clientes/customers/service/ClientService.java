package br.com.jackson.desafio_crud_de_clientes.customers.service;

import br.com.jackson.desafio_crud_de_clientes.customers.dto.ClientResponseDTO;
import br.com.jackson.desafio_crud_de_clientes.customers.dto.mapper.ClientMapper;
import br.com.jackson.desafio_crud_de_clientes.customers.entity.Client;
import br.com.jackson.desafio_crud_de_clientes.customers.repository.ClientRepository;
import br.com.jackson.desafio_crud_de_clientes.customers.service.exceptions.DatabaseException;
import br.com.jackson.desafio_crud_de_clientes.customers.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Client findById(Long id) {

        return clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource Not Found")
        );
    }

    @Transactional
    public Client create(Client client) {
        return clientRepository.save(client);

    }

    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);

    }

    @Transactional
    public Client update(Long id, ClientResponseDTO clientResponseDTO) {
        try {
            Client client = clientRepository.getReferenceById(id);
            ClientMapper.copyDtoToEntity(clientResponseDTO, client);
            return clientRepository.save(client);
        } catch (EntityNotFoundException e) {

            throw new ResourceNotFoundException("Resource Not Found");

        }

    }

    @Transactional
    public void delete(Long id) {

        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource Not Found");
        }
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure");
        }

    }
}
