package com.motafelipe.api.backoffice.controller.resources.interfaces;

import com.motafelipe.api.backoffice.dto.EnvelopedData;
import com.motafelipe.api.backoffice.dto.response.PageResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface BasicInterface <TResponse, TRequest>{

    /**
     * Get all resources by lazy loading.
     * @param page - page
     * @param size - size of page.
     * @return ResponseEntity<PageModel<TResponse>>
     */
    @GetMapping
    ResponseEntity<PageResponseDto<TResponse>>
        getPaginated(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size);

    /**
     * save
     * @param tRequest - tRequest
     * @return - ResponseEntity<EnvelopedData<RoomResponseDto>>
     */
    @PostMapping()
    ResponseEntity<EnvelopedData<TResponse>> save (@RequestBody @Valid TRequest tRequest);


    /**
     * deleteById
     * @param id - id
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deleteById(@PathVariable(name="id") Long id);

    /**
     * update
     * @param id - id
     * @param tRequest - tRequest
     * @return - ResponseEntity<EnvelopedData<TResponse>>
     */
    @PutMapping("/{id}")
    ResponseEntity<EnvelopedData<TResponse>> update(@PathVariable(name="id") Long id, @RequestBody @Valid TRequest tRequest);
}
