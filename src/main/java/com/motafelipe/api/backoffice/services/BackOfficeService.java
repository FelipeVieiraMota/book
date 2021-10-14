package com.motafelipe.api.backoffice.services;

import com.motafelipe.api.backoffice.models.user.UserLoginRequestModel;
import com.motafelipe.api.backoffice.models.user.UserLoginResponseModel;
import com.motafelipe.api.backoffice.security.JwtManager;
import com.motafelipe.api.backoffice.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BackOfficeService {

    private final AuthenticationManager authManager;
    private final JwtManager jwtManager;

    @Autowired
    public BackOfficeService(AuthenticationManager authManager, JwtManager jwtManager) {
        this.authManager = authManager;
        this.jwtManager = jwtManager;
    }

    public UserLoginResponseModel login(UserLoginRequestModel user){
        var tPass = HashUtil.getSecureHash(user.getPassword());

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        Authentication auth =
                authManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(auth);

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User) auth.getPrincipal();

        String email =
                userSpring.getUsername();

        var roles = userSpring
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return jwtManager.createToken(email, roles);
    }
}
