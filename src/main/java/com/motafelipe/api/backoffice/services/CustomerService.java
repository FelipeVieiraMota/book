package com.motafelipe.api.backoffice.services;

import com.motafelipe.api.backoffice.dto.request.CustomerRequestDto;
import com.motafelipe.api.backoffice.dto.request.PageRequestDto;
import com.motafelipe.api.backoffice.dto.response.CustomerResponseDto;
import com.motafelipe.api.backoffice.dto.response.PageResponseDto;
import com.motafelipe.api.backoffice.repositories.CustomerRepository;
import com.motafelipe.api.backoffice.services.interfaces.BasicInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements BasicInterface<CustomerResponseDto, CustomerRequestDto> {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponseDto save(CustomerRequestDto customerRequestDto) {
        return null;
    }

    @Override
    public CustomerResponseDto update(CustomerResponseDto customerResponseDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public PageResponseDto<CustomerResponseDto> getPaginated(PageRequestDto pr) {
        return null;
    }

    @Override
    public CustomerResponseDto getById(Long id) {
        return null;
    }
}
