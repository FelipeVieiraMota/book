package com.motafelipe.api.backoffice.services;

import com.motafelipe.api.backoffice.domains.vo.entities.BookingEntity;
import com.motafelipe.api.backoffice.dto.request.BookingRequestDto;
import com.motafelipe.api.backoffice.dto.response.BookingResponseDto;
import com.motafelipe.api.backoffice.dto.response.CheckAvailabilityResponseDto;
import com.motafelipe.api.backoffice.exception.BadRequestException;
import com.motafelipe.api.backoffice.exception.NotFoundException;
import com.motafelipe.api.backoffice.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.motafelipe.api.backoffice.domains.vo.domains.BookingDateValidationResultEnum.SUCCESS;
import static com.motafelipe.api.backoffice.domains.vo.domains.BookingEntityValidationResultEnum.*;
import static com.motafelipe.api.backoffice.services.functional.BookingDatesValidation.*;
import static com.motafelipe.api.backoffice.services.functional.BookingEntityValidations.*;
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerService customerService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, CustomerService customerService) {
        this.bookingRepository = bookingRepository;
        this.customerService = customerService;
    }

    public void validateDate(BookingRequestDto bookingRequestDto){
        var dateValidation =
                isStartDateLessThanTheCurrentDate()
                        .and(isFinalDateGreaterThanToday())
                        .and(isFinalDateEqualsOrGraterThanStartDate())
                        .and(validPeriodThreeDaysStayDate())
                        .and(valid30DaysInAdvance())
                        .apply(bookingRequestDto);

        if (dateValidation != SUCCESS )
            throw new BadRequestException(dateValidation.name());
    }

    private  Optional<BookingEntity>  saveReservation(Long idBooking, BookingRequestDto bookingRequestDto){
        var customerDto = this.customerService.getById(bookingRequestDto.getCustomerResponseDto().getIdCustomer());
        var bookEntity = bookingRequestDto.toEntity(idBooking);
        bookEntity.setCustomerEntity(customerDto.toEntity());
        bookEntity.setOccupied(true);
        return Optional.of(this.bookingRepository.save(bookEntity));
    }


    public BookingResponseDto updateReservation(BookingRequestDto bookingRequestDto) {

        var entity = this.bookingRepository.getLastEntity();

        var entityIsPresent = isEntityPresent().apply(entity);

        if (entityIsPresent == UPDATABLE) {

            var anotherUser =
                    isTheSameOwner(bookingRequestDto.getCustomerResponseDto().getIdCustomer())
                    .apply(entity);

            var unavailable =
                    isOccupied()
                    .apply(entity);

            if (anotherUser == ANOTHER_CUSTOMER && unavailable == UNAVAILABLE)
                throw new NotFoundException(" The room is unavailable until " + entity.get().getFinishDate());

            entity = saveReservation(entity.get().getIdBook(), bookingRequestDto);
        }
        else
            throw new NotFoundException("There is not reservation for the customer = " + bookingRequestDto.getCustomerResponseDto().getIdCustomer());

        return new BookingResponseDto().toDto(entity.get());
    }

    public BookingResponseDto makeReservation(BookingRequestDto bookingRequestDto) {

        var entity = this.bookingRepository.getLastEntity();

        var entityIsPresent = isEntityPresent().apply(entity);

        if ( entityIsPresent == NOT_PRESENT ){
            entity = saveReservation(null, bookingRequestDto);
        }
        else {
            throw new NotFoundException(" The room is unavailable until " + entity.get().getFinishDate()
                    + ". Please if you are the owner of this check in disregard this message.");
        }

        return new BookingResponseDto().toDto(entity.get());
    }

    public void deleteReservationByIdCustomer(Long id) {

        var data = this.bookingRepository.getBookByIdCustomer(id);

        data.orElseThrow(
            () -> new NotFoundException("There is not reservation for the customer = " + id)
        );

        this.bookingRepository.deleteById(data.get().getIdBook());
    }

    public CheckAvailabilityResponseDto checkAvailability() {
        var entity = this.bookingRepository.getLastEntity();

        if (entity.isPresent() && entity.get().isOccupied()){
            return new CheckAvailabilityResponseDto(true, "The room is unavailable until " + entity.get().getFinishDate());
        }

        return new CheckAvailabilityResponseDto(false, "Room available.");
    }
}
