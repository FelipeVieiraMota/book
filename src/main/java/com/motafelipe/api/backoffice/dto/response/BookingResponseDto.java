package com.motafelipe.api.backoffice.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.motafelipe.api.backoffice.domains.vo.entities.BookingEntity;
import com.motafelipe.api.backoffice.dto.request.BookingRequestDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponseDto extends BookingRequestDto {

    @JsonProperty("id_book")
    private Long idBook;

    /**
     * Constructor
     * @param idBook - idBook
     * @param customer - customer
     * @param startDate - startDate
     * @param finishDate - finishDate
     * @param occupied - occupied
     * @param description - description
     */
    public BookingResponseDto(
            Long idBook,
            CustomerResponseDto customer,
            LocalDate startDate,
            LocalDate finishDate,
            boolean occupied,
            String description) {
        super(
            customer,
            startDate,
            finishDate,
            occupied,
            description
        );

        this.idBook = idBook;
    }

    public BookingResponseDto(boolean occupied){
        super.setOccupied(occupied);
    }

    public BookingEntity toEntity () {

        return new BookingEntity(
                this.idBook,
                this.getCustomerResponseDto().toEntity(),
                this.getStartDate(),
                this.getFinishDate(),
                this.isOccupied(),
                this.getDescription()
        );
    }

    public BookingResponseDto toDto(BookingEntity bookingEntity) {
        return new BookingResponseDto(
                bookingEntity.getIdBook(),
                new CustomerResponseDto().toDto(bookingEntity.getCustomerEntity()),
                bookingEntity.getStartDate(),
                bookingEntity.getFinishDate(),
                bookingEntity.isOccupied(),
                bookingEntity.getDescription()
        );
    }
}
