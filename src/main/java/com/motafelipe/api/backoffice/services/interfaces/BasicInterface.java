package com.motafelipe.api.backoffice.services.interfaces;

import com.motafelipe.api.backoffice.dto.request.PageRequestDto;
import com.motafelipe.api.backoffice.dto.response.PageResponseDto;

public interface BasicInterface <TResponse, TRequest>{

    TResponse save(TRequest tRequest);

    TResponse update(TResponse tResponse);

    void delete(Long id);

    PageResponseDto<TResponse> getPaginated(PageRequestDto pr);

    TResponse getById(Long id);
}
