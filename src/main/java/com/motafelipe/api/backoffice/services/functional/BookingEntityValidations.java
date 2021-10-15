package com.motafelipe.api.backoffice.services.functional;

import com.motafelipe.api.backoffice.domains.vo.domains.BookingEntityValidationResultEnum;
import com.motafelipe.api.backoffice.domains.vo.entities.BookingEntity;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static com.motafelipe.api.backoffice.domains.vo.domains.BookingEntityValidationResultEnum.*;

public interface BookingEntityValidations extends Function<Optional<BookingEntity>, BookingEntityValidationResultEnum> {

    static BookingEntityValidations isEntityPresent(){
        return entity -> {
            boolean validate  = entity.isPresent();
            return validate ?  UPDATABLE : NOT_PRESENT;
        };
    }

    static BookingEntityValidations isOccupied(){
        return entity -> {
            boolean validate  = entity.get().isOccupied();
            return validate ?  UNAVAILABLE : UPDATABLE ;
        };
    }

    static BookingEntityValidations isTheSameOwner(Long idCustomer){
        return entity -> {
            boolean validate  = Objects.equals(entity.get().getCustomerEntity().getIdCustomer(), idCustomer);
            return validate ?  UPDATABLE : ANOTHER_CUSTOMER;
        };
    }

    default BookingEntityValidations and (BookingEntityValidations other){
        return book -> {
            BookingEntityValidationResultEnum result = this.apply(book);
            return result.equals(UPDATABLE) ? other.apply(book) : result;
        };
    }
}
