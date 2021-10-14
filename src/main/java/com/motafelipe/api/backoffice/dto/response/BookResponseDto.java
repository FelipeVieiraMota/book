package com.motafelipe.api.backoffice.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.motafelipe.api.backoffice.domains.vo.entities.BookEntity;
import com.motafelipe.api.backoffice.dto.request.BookRequestDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponseDto extends BookRequestDto {

    @JsonProperty("id_room")
    private Long idBook;

    /**
     * Constructor
     * @param idBook - idBook
     * @param customer - customer
     * @param room - room
     * @param startDate - startDate
     * @param finishDate - finishDate
     * @param occupied - occupied
     * @param description - description
     */
    public BookResponseDto(
            Long idBook,
            CustomerResponseDto customer,
            RoomResponseDto room,
            Date startDate,
            Date finishDate,
            boolean occupied,
            String description) {
        super(
            customer,
            room,
            startDate,
            finishDate,
            occupied,
            description
        );

        this.idBook = idBook;
    }

    public BookEntity toEntity () {

        return new BookEntity(
                this.idBook,
                this.getCustomerResponseDto().toEntity(),
                this.getRoomResponseDto().toEntity(),
                this.getStartDate(),
                this.getFinishDate(),
                this.isOccupied(),
                this.getDescription()
        );
    }

    public BookResponseDto toDto(BookEntity bookEntity) {
        return new BookResponseDto(
                bookEntity.getIdBook(),
                new CustomerResponseDto().toDto(bookEntity.getCustomerEntity()),
                new RoomResponseDto().toDto(bookEntity.getRoomEntity()),
                bookEntity.getStartDate(),
                bookEntity.getFinishDate(),
                bookEntity.isOccupied(),
                bookEntity.getDescription()
        );
    }
}
