package br.com.jackson.desafio_crud_de_clientes.customers.dto;


import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientResponseDTO {

        private Long id;

        @Size(min = 3, max = 80, message = "Name must be 3 to 80 Characters")
        @NotBlank(message = "Name cannot be null")
        private String name;

        @CPF(message = "cpf must be valid")
        private String cpf;

        @DecimalMin(value = "0.0", inclusive = false)
        private Double income;

        @NotNull(message = "LocalDate cannot be null")
        @PastOrPresent(message = "It cannot be a future date")
        private LocalDate birthDate;

        @NotNull(message = "Children cannot be null")
        @PositiveOrZero(message = "Must be greater than or equal to 0")
        private Integer children;        


}
