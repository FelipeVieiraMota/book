package com.motafelipe.api.backoffice.models;

import com.motafelipe.api.backoffice.domains.vo.entities.BookEntity;
import com.motafelipe.api.backoffice.dto.BookDto;
import com.motafelipe.api.backoffice.models.pagination.PageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class BookModel {

    public static PageModel<BookDto> entityPageToPageModel(Page<BookEntity> pageEntity){

        var listOfUsers = BookModel.toModelList(pageEntity.toList());

        var result = new PageImpl<>(listOfUsers);

        return new PageModel<>((int)result.getTotalElements(), result.getSize(), result.getTotalPages(), result.getContent());
    }

    public static List<BookDto> toModelList (List<BookEntity> entityList){

        return entityList
                .stream()
                .map(entity -> new BookDto().toDto(entity))
                .collect(Collectors.toList());
    }
}
