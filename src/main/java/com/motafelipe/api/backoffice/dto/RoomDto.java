package com.motafelipe.api.backoffice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.motafelipe.api.backoffice.domains.vo.entities.RoomEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDto {

    @JsonProperty("id_room")
    private Long idRoom;

    @NotBlank(message = "room number is required")
    @NotEmpty(message = "room number is required")
    @NotNull(message = "room number is required")
    private Long number;

    @Builder.Default
    @JsonProperty("is_activated")
    private boolean isActivated = false;


    public RoomEntity toEntity () {
        return new RoomEntity(
            null, this.number, this.isActivated
        );
    }

    public RoomDto toDto(RoomEntity roomEntity){
        return new RoomDto(roomEntity.getIdRoom(), roomEntity.getIdRoom(), roomEntity.isActivated());
    }
}
