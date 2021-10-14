package com.motafelipe.api.backoffice.controller;

import com.motafelipe.api.backoffice.models.user.UserLoginRequestModel;
import com.motafelipe.api.backoffice.models.user.UserLoginResponseModel;
import com.motafelipe.api.backoffice.services.BackOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/v1/backoffice")
public class BackOfficeController {

    private final BackOfficeService backOfficeService;

    @Autowired
    public BackOfficeController(BackOfficeService backOfficeService) {
        this.backOfficeService = backOfficeService;
    }

    /**
     * Create an valid token
     * @param user - user
     * @return ResponseEntity<UserLoginResponseModel>
     */
    @PostMapping("/create_valid_token")
    public ResponseEntity<UserLoginResponseModel> login(@RequestBody @Valid UserLoginRequestModel user){
        return ResponseEntity.ok(backOfficeService.login(user));
    }
}
