package com.motafelipe.api.backoffice.services;

import com.motafelipe.api.backoffice.dto.request.CustomerRequestDto;
import com.motafelipe.api.backoffice.dto.request.PageRequestDto;
import com.motafelipe.api.backoffice.dto.response.CustomerResponseDto;
import com.motafelipe.api.backoffice.dto.response.PageResponseDto;
import com.motafelipe.api.backoffice.exception.NotFoundException;
import com.motafelipe.api.backoffice.models.CustomerModel;
import com.motafelipe.api.backoffice.repositories.BookingRepository;
import com.motafelipe.api.backoffice.repositories.CustomerRepository;
import com.motafelipe.api.backoffice.services.interfaces.BasicInterface;
import com.motafelipe.api.backoffice.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;


@Service
public class CustomerService implements BasicInterface<CustomerResponseDto, CustomerRequestDto> {

    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public CustomerResponseDto save(CustomerRequestDto customerRequestDto) {

        String hash = HashUtil.getSecureHash(customerRequestDto.getPassword());
        customerRequestDto.setPassword(hash);

        var data =
                Optional.of(customerRepository.save(customerRequestDto.toEntity()));

        data.orElseThrow(
                () -> new HttpServerErrorException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        " We cannot to save the customer : " + customerRequestDto.getFirstName() +
                                " in our database, please try it again in a few moment."
                )
        );
        return new CustomerResponseDto().toDto(data.get());
    }

    @Override
    public CustomerResponseDto update(Long id, CustomerRequestDto tResponse) {
        return null;
    }

    @Override
    public void delete(Long id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public PageResponseDto<CustomerResponseDto> getPaginated(PageRequestDto pr) {
        return new CustomerModel().entityPageToPageModel(this.customerRepository.findAll(PageRequest.of(pr.getPage(), pr.getSize())));
    }

    @Override
    public CustomerResponseDto getById(Long id) {

        var data =  this.customerRepository.getCustomerByIdCustomer(id);

        if (data.isEmpty())
            throw  new NotFoundException("There are not customer with id = " + id);

        return new CustomerResponseDto().toDto(data.get());
    }
}
