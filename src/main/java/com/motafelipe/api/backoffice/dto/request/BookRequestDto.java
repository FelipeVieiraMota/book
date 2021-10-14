package com.motafelipe.api.backoffice.dto.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.motafelipe.api.backoffice.domains.vo.entities.BookEntity;
import com.motafelipe.api.backoffice.dto.response.CustomerResponseDto;
import com.motafelipe.api.backoffice.dto.response.RoomResponseDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookRequestDto {

    @JsonProperty("customer")
    private CustomerResponseDto customerResponseDto;

    @JsonProperty("room")
    private RoomResponseDto roomResponseDto;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("finish_date")
    private Date finishDate;

    @JsonProperty("is_occupied")
    private boolean isOccupied;

    private String description;

    public BookEntity toEntity () {

        return new BookEntity(
                null,
                this.customerResponseDto.toEntity(),
                this.roomResponseDto.toEntity(),
                this.startDate,
                this.finishDate,
                this.isOccupied,
                this.description
        );
    }

    public BookRequestDto toDto(BookEntity bookEntity) {
        return new BookRequestDto(
                new CustomerResponseDto().toDto(bookEntity.getCustomerEntity()),
                new RoomResponseDto().toDto(bookEntity.getRoomEntity()),
                bookEntity.getStartDate(),
                bookEntity.getFinishDate(),
                bookEntity.isOccupied(),
                bookEntity.getDescription()
        );
    }
}
