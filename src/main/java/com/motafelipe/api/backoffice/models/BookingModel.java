package com.motafelipe.api.backoffice.models;

import com.motafelipe.api.backoffice.domains.vo.entities.BookingEntity;
import com.motafelipe.api.backoffice.dto.response.BookingResponseDto;
import com.motafelipe.api.backoffice.dto.response.PageResponseDto;
import com.motafelipe.api.backoffice.models.interfaces.BasicModelInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class BookingModel implements BasicModelInterface<BookingResponseDto, BookingEntity> {

    public PageResponseDto<BookingResponseDto> entityPageToPageModel(Page<BookingEntity> pageEntity){

        var listOfUsers =
                new BookingModel().toModelList(pageEntity.toList());

        var result =
                new PageImpl<>(listOfUsers);

        return new PageResponseDto<>((int)result.getTotalElements(), result.getSize(), result.getTotalPages(), result.getContent());
    }

    public List<BookingResponseDto> toModelList (List<BookingEntity> entityList){

        return entityList
                .stream()
                .map(entity -> new BookingResponseDto().toDto(entity))
                .collect(Collectors.toList());
    }
}
