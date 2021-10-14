package com.motafelipe.api.backoffice.models;

import com.motafelipe.api.backoffice.domains.vo.entities.*;
import com.motafelipe.api.backoffice.dto.response.RoomResponseDto;
import com.motafelipe.api.backoffice.dto.response.PageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class RoomModel {
    public static PageResponseDto<RoomResponseDto> entityPageToPageModel(Page<RoomEntity> page){

        var listOfUsers = RoomModel.toModelList(page.toList());

        var result = new PageImpl<>(listOfUsers);

        return new PageResponseDto<>((int)result.getTotalElements(), result.getSize(), result.getTotalPages(), result.getContent());
    }

    public static List<RoomResponseDto> toModelList (List<RoomEntity> list){

        return list
                .stream()
                .map(entity -> new RoomResponseDto().toDto(entity))
                .collect(Collectors.toList());
    }
}
