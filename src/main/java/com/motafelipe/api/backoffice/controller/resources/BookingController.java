package com.motafelipe.api.backoffice.controller.resources;

import com.motafelipe.api.backoffice.dto.EnvelopedData;
import com.motafelipe.api.backoffice.dto.request.BookingRequestDto;
import com.motafelipe.api.backoffice.dto.response.CheckAvailabilityResponseDto;
import com.motafelipe.api.backoffice.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/aws/public/v1/booking")
public class BookingController  {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping()
    public ResponseEntity<EnvelopedData<BookingRequestDto>> createReservation(@RequestBody @Valid BookingRequestDto bookingRequestDto){
        this.bookingService.validateDate(bookingRequestDto);
        var result = this.bookingService.makeReservation(bookingRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EnvelopedData<>(result));
    }

    @PutMapping()
    public ResponseEntity<EnvelopedData<BookingRequestDto>> updateReservationByIdCustomer(@RequestBody @Valid BookingRequestDto bookingRequestDto){
        this.bookingService.validateDate(bookingRequestDto);
        var result = this.bookingService.updateReservation(bookingRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EnvelopedData<>(result));
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteReservationByIdCustomer(@RequestBody @Valid BookingRequestDto bookingRequestDto) {
        this.bookingService.deleteReservationByIdCustomer(bookingRequestDto.getCustomerResponseDto().getIdCustomer());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<EnvelopedData<CheckAvailabilityResponseDto>> checkAvailability(){
        return ResponseEntity.status(HttpStatus.OK).body(new EnvelopedData<>(this.bookingService.checkAvailability()));
    }
}
