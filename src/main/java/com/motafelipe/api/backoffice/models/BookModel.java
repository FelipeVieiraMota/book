package com.motafelipe.api.backoffice.models;

import com.motafelipe.api.backoffice.domains.vo.entities.BookEntity;
import com.motafelipe.api.backoffice.dto.response.BookResponseDto;
import com.motafelipe.api.backoffice.dto.response.PageResponseDto;
import com.motafelipe.api.backoffice.models.interfaces.BasicModelInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class BookModel implements BasicModelInterface<BookResponseDto, BookEntity> {

    public PageResponseDto<BookResponseDto> entityPageToPageModel(Page<BookEntity> pageEntity){

        var listOfUsers =
                new BookModel().toModelList(pageEntity.toList());

        var result =
                new PageImpl<>(listOfUsers);

        return new PageResponseDto<>((int)result.getTotalElements(), result.getSize(), result.getTotalPages(), result.getContent());
    }

    public List<BookResponseDto> toModelList (List<BookEntity> entityList){

        return entityList
                .stream()
                .map(entity -> new BookResponseDto().toDto(entity))
                .collect(Collectors.toList());
    }
}
