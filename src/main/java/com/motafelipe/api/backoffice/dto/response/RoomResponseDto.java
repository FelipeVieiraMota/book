package com.motafelipe.api.backoffice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.motafelipe.api.backoffice.domains.vo.entities.RoomEntity;
import com.motafelipe.api.backoffice.dto.request.RoomRequestDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomResponseDto extends RoomRequestDto {

    @JsonProperty("id_room")
    private Long idRoom;

    public RoomResponseDto(Long idRoom, RoomRequestDto roomRequestDto) {
        super(roomRequestDto.getNumber(),roomRequestDto.isActivated());
        this.idRoom = idRoom;
    }

    public RoomResponseDto(Long idRoom, Long number, boolean activated) {
        super(number, activated);
        this.idRoom = idRoom;
    }

    public RoomEntity toEntity () {
        return new RoomEntity(
            this.idRoom, this.getNumber(), this.isActivated()
        );
    }

    public RoomResponseDto toDto(RoomEntity roomEntity){
        return new RoomResponseDto(roomEntity.getIdRoom(), roomEntity.getNumber(), roomEntity.isActivated());
    }
}
