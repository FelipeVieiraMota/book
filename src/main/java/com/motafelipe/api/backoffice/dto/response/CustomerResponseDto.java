package com.motafelipe.api.backoffice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.motafelipe.api.backoffice.domains.vo.entities.CustomerEntity;
import com.motafelipe.api.backoffice.dto.request.CustomerRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponseDto extends CustomerRequestDto {

    @JsonProperty("id_customer")
    private Long idCustomer;

    public CustomerResponseDto(
            Long idCustomer,
            String firstName,
            String lastName,
            String email,
            String cellphone,
            Date dateOfBirth) {

        super(
            firstName,
            lastName,
            email,
            cellphone,
            dateOfBirth);

        this.idCustomer = idCustomer;
    }


    public CustomerEntity toEntity () {
        return new CustomerEntity(
            this.idCustomer,
            this.getFirstName(),
            this.getLastName(),
            this.getEmail(),
            this.getEmail(),
            this.getDateOfBirth()
        );
    }

    public CustomerResponseDto toDto(CustomerEntity userEntity){
        return new CustomerResponseDto(
            userEntity.getIdCustomer(),
            userEntity.getFirstName(),
            userEntity.getLastName(),
            userEntity.getEmail(),
            userEntity.getCellphone(),
            userEntity.getDateOfBirth()
        );
    }
}
