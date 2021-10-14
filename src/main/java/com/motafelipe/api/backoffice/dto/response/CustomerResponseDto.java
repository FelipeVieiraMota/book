package com.motafelipe.api.backoffice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.motafelipe.api.backoffice.domains.vo.entities.CustomerEntity;
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
public class CustomerResponseDto {

    @JsonProperty("id_customer")
    private Long idCustomer;

    @NotBlank(message = "First Name required")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "Last Name required")
    @JsonProperty("last_name")
    private String lastName;

    @Email(message = "Invalid e-mail address")
    private String email;

    @JsonProperty(value = "cellphone")
    public String cellphone;


    @JsonProperty(value = "date_of_birth")
    @DateTimeFormat
    @NotNull(message = " Must need have a valid date field. ")
    @NotEmpty(message = " Must need have a valid date field. ")
    @NotBlank(message = " Must need have a valid date field. ")
    public Date dateOfBirth;

    public CustomerEntity toEntity () {
        return new CustomerEntity(
            null,
            this.firstName,
            this.lastName,
            this.email,
            this.cellphone,
            this.dateOfBirth
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
