package com.motafelipe.api.backoffice.models;

import com.motafelipe.api.backoffice.domains.vo.entities.CustomerEntity;
import com.motafelipe.api.backoffice.dto.response.CustomerResponseDto;
import com.motafelipe.api.backoffice.dto.response.PageResponseDto;
import com.motafelipe.api.backoffice.models.interfaces.BasicModelInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerModel implements BasicModelInterface<CustomerResponseDto, CustomerEntity> {

    public PageResponseDto<CustomerResponseDto> entityPageToPageModel(Page<CustomerEntity> pageEntity){

        var listOfUsers = new CustomerModel().toModelList(pageEntity.toList());

        var result = new PageImpl<>(listOfUsers);

        return new PageResponseDto<>((int)result.getTotalElements(), result.getSize(), result.getTotalPages(), result.getContent());
    }

    public List<CustomerResponseDto> toModelList (List<CustomerEntity> entityList){

        return entityList
                .stream()
                .map(entity -> new CustomerResponseDto().toDto(entity))
                .collect(Collectors.toList());
    }
}
