package com.motafelipe.api.backoffice.models;

import com.motafelipe.api.backoffice.domains.vo.entities.*;
import com.motafelipe.api.backoffice.dto.response.RoomResponseDto;
import com.motafelipe.api.backoffice.dto.response.PageResponseDto;
import com.motafelipe.api.backoffice.models.interfaces.BasicModelInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class RoomModel implements BasicModelInterface<RoomResponseDto, RoomEntity> {

    public PageResponseDto<RoomResponseDto> entityPageToPageModel(Page<RoomEntity> pageEntity){

        var listOfUsers = new RoomModel().toModelList(pageEntity.toList());

        var result = new PageImpl<>(listOfUsers);

        return new PageResponseDto<>((int)result.getTotalElements(), result.getSize(), result.getTotalPages(), result.getContent());
    }

    public List<RoomResponseDto> toModelList (List<RoomEntity> entityList){

        return entityList
                .stream()
                .map(entity -> new RoomResponseDto().toDto(entity))
                .collect(Collectors.toList());
    }
}
