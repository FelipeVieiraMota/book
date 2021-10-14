package com.motafelipe.api.backoffice.security;


import com.motafelipe.api.backoffice.constants.SecurityConstants;
import com.motafelipe.api.backoffice.models.user.UserLoginResponseModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class JwtManager {

    public UserLoginResponseModel createToken (String email, List<String> roles){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, SecurityConstants.JWT_EXP_DAYS);
        String jwt = Jwts.builder()
                .setSubject(email)
                .setExpiration(calendar.getTime())
                .claim(SecurityConstants.JWT_ROLE_KEY, roles)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.API_KEY.getBytes())
                .compact();
        Long expireIn = calendar.getTimeInMillis();
        return new UserLoginResponseModel(jwt, expireIn, SecurityConstants.JWT_PROVIDER);
    }

    public Claims parseToken (String jwt) throws JwtException {
        return Jwts
                .parser()
                .setSigningKey(SecurityConstants.API_KEY.getBytes())
                .parseClaimsJws(jwt)
                .getBody();
    }
}