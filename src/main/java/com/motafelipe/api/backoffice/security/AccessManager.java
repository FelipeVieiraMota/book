package com.motafelipe.api.backoffice.security;

import com.motafelipe.api.backoffice.domains.vo.entities.UserEntity;
import com.motafelipe.api.backoffice.exception.NotFoundException;
import com.motafelipe.api.backoffice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

@Component("accessManager")
public class AccessManager {

    @Autowired
    private UserRepository userRepository;

    public boolean isAdministrator(Long id){

        String email =
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<UserEntity> result =
                userRepository.findByEmail(email);

        result.orElseThrow(
            ()-> new NotFoundException("There are not user with email = " + email)
        );

        UserEntity user = result.get();

        return Objects.equals(user.getIdUser(), id);
    }
}
