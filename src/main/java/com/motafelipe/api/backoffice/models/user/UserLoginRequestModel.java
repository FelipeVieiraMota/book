package com.motafelipe.api.backoffice.models.user;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginRequestModel {
    @Email (message = "Invalid e-mail address")
    private String email;
    @NotBlank (message = "Password required")
    private String password;
}
