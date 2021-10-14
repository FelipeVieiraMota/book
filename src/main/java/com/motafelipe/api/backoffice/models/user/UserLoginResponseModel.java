package com.motafelipe.api.backoffice.models.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class UserLoginResponseModel implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String token;
    @JsonProperty("expire_in")
    private Long expireIn;
    @JsonProperty("token_provider")
    private String tokenProvider;
}