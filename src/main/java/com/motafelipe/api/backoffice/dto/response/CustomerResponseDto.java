package com.motafelipe.api.backoffice.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.motafelipe.api.backoffice.domains.vo.entities.CustomerEntity;
import com.motafelipe.api.backoffice.dto.request.CustomerRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"password"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponseDto extends CustomerRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id_customer")
    private Long idCustomer;

    public CustomerResponseDto(
            Long idCustomer,
            String firstName,
            String lastName,
            String email,
            String password,
            String cellphone,
            Date dateOfBirth) {

        super(
            firstName,
            lastName,
            email,
            password,
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
            this.getPassword(),
            this.getCellphone(),
            this.getDateOfBirth()
        );
    }

    public CustomerResponseDto toDto(CustomerEntity userEntity){
        return new CustomerResponseDto(
            userEntity.getIdCustomer(),
            userEntity.getFirstName(),
            userEntity.getLastName(),
            userEntity.getEmail(),
            userEntity.getPassword(),
            userEntity.getCellphone(),
            userEntity.getDateOfBirth()
        );
    }
}
