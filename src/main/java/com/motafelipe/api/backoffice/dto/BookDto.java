package com.motafelipe.api.backoffice.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.motafelipe.api.backoffice.domains.vo.entities.BookEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {

    @JsonProperty("id_room")
    private Long idBook;

    @JsonProperty("customer")
    private CustomerDto customerDto;

    @JsonProperty("room")
    private RoomDto roomDto;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("finish_date")
    private Date finishDate;

    @JsonProperty("is_occupied")
    private boolean isOccupied;

    private String description;

    public BookEntity toEntity () {

        return new BookEntity(
                this.idBook,
                this.customerDto.toEntity(),
                this.roomDto.toEntity(),
                this.startDate,
                this.finishDate,
                this.isOccupied,
                this.description
        );
    }

    public BookDto toDto(BookEntity bookEntity) {
        return new BookDto(
                bookEntity.getIdBook(),
                new CustomerDto().toDto(bookEntity.getCustomerEntity()),
                new RoomDto().toDto(bookEntity.getRoomEntity()),
                bookEntity.getStartDate(),
                bookEntity.getFinishDate(),
                bookEntity.isOccupied(),
                bookEntity.getDescription()
        );
    }
}
