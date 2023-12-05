package br.com.jackson.desafio_crud_de_clientes.customers.dto.mapper;

import br.com.jackson.desafio_crud_de_clientes.customers.dto.ClientResponseDTO;
import br.com.jackson.desafio_crud_de_clientes.customers.entity.Client;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class ClientMapper {
    public static ClientResponseDTO toDTO(Client client){
        return new ModelMapper().map(client, ClientResponseDTO.class);
    }

    public static Client toClient(ClientResponseDTO clientResponseDTO){
        return new ModelMapper().map(clientResponseDTO, Client.class);
    }

    public static Page<ClientResponseDTO> toListDTO(Page<Client> client) {
        return client.map(x -> toDTO(x));
    }

    public static void copyDtoToEntity(ClientResponseDTO clientResponseDTO, Client client){
        client.setName(clientResponseDTO.getName());
        client.setCpf(clientResponseDTO.getCpf());
        client.setIncome(clientResponseDTO.getIncome());
        client.setBirthDate(clientResponseDTO.getBirthDate());
        client.setChildren(clientResponseDTO.getChildren());

    }
}
