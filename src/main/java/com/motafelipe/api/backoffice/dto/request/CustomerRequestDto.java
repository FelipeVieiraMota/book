package com.motafelipe.api.backoffice.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.motafelipe.api.backoffice.domains.vo.entities.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "First Name required")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "Last Name required")
    @JsonProperty("last_name")
    private String lastName;

    @Email(message = "Invalid e-mail address")
    private String email;

    @Size(min=7, max=99, message = "Password must be between 7 and 99")
    private String password;

    @JsonProperty(value = "cellphone")
    private String cellphone;


    @JsonProperty(value = "date_of_birth")
    @DateTimeFormat
    @NotNull(message = " Must need have a valid date field. ")
    @NotEmpty(message = " Must need have a valid date field. ")
    @NotBlank(message = " Must need have a valid date field. ")
    private Date dateOfBirth;

    public CustomerEntity toEntity () {
        return new CustomerEntity(
            null,
            this.firstName,
            this.lastName,
            this.email,
            this.password,
            this.cellphone,
            this.dateOfBirth
        );
    }

    public CustomerRequestDto toDto(CustomerEntity userEntity){
        return new CustomerRequestDto(
            userEntity.getFirstName(),
            userEntity.getLastName(),
            userEntity.getEmail(),
            userEntity.getPassword(),
            userEntity.getCellphone(),
            userEntity.getDateOfBirth()
        );
    }
}
