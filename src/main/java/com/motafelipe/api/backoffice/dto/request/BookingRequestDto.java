package com.motafelipe.api.backoffice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.motafelipe.api.backoffice.domains.vo.entities.BookingEntity;
import com.motafelipe.api.backoffice.dto.response.CustomerResponseDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingRequestDto {

    @JsonProperty("customer")
    private CustomerResponseDto customerResponseDto;

    @JsonProperty("start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @JsonProperty("finish_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate finishDate;

    @JsonProperty("is_occupied")
    private boolean isOccupied;

    private String description ;

    public BookingEntity toEntity (Long idBooking) {

        return new BookingEntity(
                idBooking,
                this.customerResponseDto == null ? new CustomerResponseDto().toEntity() : this.customerResponseDto.toEntity(),
                this.startDate,
                this.finishDate,
                this.isOccupied,
                this.description
        );
    }

    public BookingRequestDto toDto(BookingEntity bookingEntity) {
        return new BookingRequestDto(
                new CustomerResponseDto().toDto(bookingEntity.getCustomerEntity()),
                bookingEntity.getStartDate(),
                bookingEntity.getFinishDate(),
                bookingEntity.isOccupied(),
                bookingEntity.getDescription()
        );
    }
}
