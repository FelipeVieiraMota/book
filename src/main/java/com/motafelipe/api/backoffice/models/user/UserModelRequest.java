package com.motafelipe.api.backoffice.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motafelipe.api.backoffice.domains.enums.Role;
import com.motafelipe.api.backoffice.domains.vo.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModelRequest implements Serializable  {

    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String password;
    private Role role;

    public static UserEntity toEntity(UserModelRequest userModelRequest){
        return new UserEntity(
            0L,
            userModelRequest.getName(),
            userModelRequest.getEmail(),
            userModelRequest.getPassword(),
            userModelRequest.getRole()
        );
    }

    public static UserModelRequest toModel(UserEntity userEntity){
        return new UserModelRequest(
            userEntity.getName(),
            userEntity.getEmail(),
            userEntity.getPassword(),
            userEntity.getRole()
        );
    }
}
