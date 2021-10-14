package com.motafelipe.api.backoffice.models.interfaces;

import com.motafelipe.api.backoffice.dto.response.PageResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BasicModelInterface <TResponseDto, TEntity>{

    PageResponseDto<TResponseDto> entityPageToPageModel(Page<TEntity> pageEntity);
    List<TResponseDto> toModelList (List<TEntity> entityList);
}
