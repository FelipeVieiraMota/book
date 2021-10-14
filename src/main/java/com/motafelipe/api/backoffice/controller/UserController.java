package com.motafelipe.api.backoffice.controller;

import com.motafelipe.api.backoffice.domains.vo.entities.UserEntity;
import com.motafelipe.api.backoffice.models.pagination.PageModel;
import com.motafelipe.api.backoffice.models.pagination.PageRequestModel;
import com.motafelipe.api.backoffice.models.user.UserModelRequest;
import com.motafelipe.api.backoffice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/v1/backoffice/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserEntity> save(@RequestBody @Valid UserModelRequest userModelRequest){
        var createdUser = userService.save(userModelRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<PageModel<UserEntity>> listAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequestModel pr = new PageRequestModel(page, size);
        PageModel<UserEntity> pm = userService.listAllOnLazyMode(pr);
        return ResponseEntity.ok(pm);
    }

}
