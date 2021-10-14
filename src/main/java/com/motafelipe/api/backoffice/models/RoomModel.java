package com.motafelipe.api.backoffice.models;

import com.motafelipe.api.backoffice.domains.vo.entities.*;
import com.motafelipe.api.backoffice.dto.RoomDto;
import com.motafelipe.api.backoffice.models.pagination.PageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class RoomModel {
    public static PageModel<RoomDto> entityPageToPageModel(Page<RoomEntity> page){

        var listOfUsers = RoomModel.toModelList(page.toList());

        var result = new PageImpl<>(listOfUsers);

        return new PageModel<>((int)result.getTotalElements(), result.getSize(), result.getTotalPages(), result.getContent());
    }

    public static List<RoomDto> toModelList (List<RoomEntity> list){

        return list
                .stream()
                .map(entity -> new RoomDto().toDto(entity))
                .collect(Collectors.toList());
    }
}
