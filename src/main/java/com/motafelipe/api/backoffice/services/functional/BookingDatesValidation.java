package com.motafelipe.api.backoffice.services.functional;

import com.motafelipe.api.backoffice.domains.vo.domains.BookingDateValidationResultEnum;
import com.motafelipe.api.backoffice.dto.request.BookingRequestDto;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

import static com.motafelipe.api.backoffice.domains.vo.domains.BookingDateValidationResultEnum.*;

public interface BookingDatesValidation extends Function<BookingRequestDto, BookingDateValidationResultEnum> {

    static BookingDatesValidation isStartDateLessThanTheCurrentDate(){
        return book -> {
            boolean validate  = LocalDate.now().isBefore(book.getStartDate());
            System.out.println("validate " + validate);
            return validate ?  SUCCESS : START_DATE_MUST_BE_GREATER_THAN_TODAY;
        };
    }

    static BookingDatesValidation isFinalDateGreaterThanToday(){
        return book -> {
            boolean validate  = LocalDate.now().isBefore(book.getFinishDate());
            System.out.println("validate " + validate);
            return validate ? SUCCESS : FINISH_DATE_MUST_BE_GREATER_THAN_TODAY;
        };
    }

    static BookingDatesValidation isFinalDateEqualsOrGraterThanStartDate(){
        return book -> {
            boolean validate  = book.getStartDate().isBefore(book.getFinishDate());
            System.out.println("validate " + validate);
            return validate ? SUCCESS : START_DATE_IS_GREATER_THAN_FINISH_DATE;
        };
    }

    static BookingDatesValidation validPeriodThreeDaysStayDate(){
        return book -> {
            LocalDate startDate = book.getStartDate();
            LocalDate finalDate = book.getFinishDate();
            var period = Period.between(startDate, finalDate).getDays();
            var validate = period <= 3;
            var result =  validate ? SUCCESS : THE_STAY_CANNOT_BE_MORE_THAN_3_DAYS;
            System.out.println("validate " + validate);
            return  result;
        };
    }

    static BookingDatesValidation valid30DaysInAdvance(){
        return book -> {
            LocalDate startDate = book.getStartDate();
            long numberOfDays = ChronoUnit.DAYS.between(LocalDate.now(), startDate);
            System.out.println("numberOfDays " + numberOfDays);
            return numberOfDays <= 30 ? SUCCESS : DATE_MORE_THAN_30_DAYS_IN_ADVANCE;
        };
    }

    default BookingDatesValidation and (BookingDatesValidation other){
        return book -> {
            BookingDateValidationResultEnum result = this.apply(book);
            return result.equals(SUCCESS) ? other.apply(book) : result;
        };
    }
}
