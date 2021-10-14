package com.motafelipe.api.backoffice.controller.resources;

import com.motafelipe.api.backoffice.controller.resources.interfaces.BasicInterface;
import com.motafelipe.api.backoffice.dto.EnvelopedData;
import com.motafelipe.api.backoffice.dto.request.CustomerRequestDto;
import com.motafelipe.api.backoffice.dto.request.PageRequestDto;
import com.motafelipe.api.backoffice.dto.request.RoomRequestDto;
import com.motafelipe.api.backoffice.dto.response.CustomerResponseDto;
import com.motafelipe.api.backoffice.dto.response.PageResponseDto;
import com.motafelipe.api.backoffice.dto.response.RoomResponseDto;
import com.motafelipe.api.backoffice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/aws/public/v1/customers")
public class CustomerController implements BasicInterface<CustomerResponseDto, CustomerRequestDto> {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<PageResponseDto<CustomerResponseDto>> getPaginated(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequestDto pr = new PageRequestDto(page, size);
        PageResponseDto<CustomerResponseDto> pm = this.customerService.getPaginated(pr);
        return ResponseEntity.ok(pm);
    }

    @PostMapping()
    public ResponseEntity<EnvelopedData<CustomerResponseDto>> save (@RequestBody @Valid CustomerRequestDto customerRequestDto){
        var result = this.customerService.save(customerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EnvelopedData<>(result));
    }

    /**
     * deleteById
     * @param id - id
     * @return void
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable(name="id") Long id) {
        this.customerService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * update
     * @param id - id
     * @param customerRequestDto - customerRequestDto
     * @return - ResponseEntity<EnvelopedData<RoomResponseDto>>
     */
    @PutMapping("/{id}")
    public ResponseEntity<EnvelopedData<CustomerResponseDto>> update(
            @PathVariable(name="id") Long id,
            @RequestBody @Valid CustomerRequestDto customerRequestDto){
        var result = customerService.update(id, customerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EnvelopedData<>(result));
    }
}
