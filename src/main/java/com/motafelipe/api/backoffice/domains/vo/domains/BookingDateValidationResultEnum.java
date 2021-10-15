package com.motafelipe.api.backoffice.domains.vo.domains;

public enum BookingDateValidationResultEnum {

    SUCCESS,
    START_DATE_MUST_BE_GREATER_THAN_TODAY,
    FINISH_DATE_MUST_BE_GREATER_THAN_TODAY,
    START_DATE_IS_GREATER_THAN_FINISH_DATE,
    THE_STAY_CANNOT_BE_MORE_THAN_3_DAYS,
    DATE_MORE_THAN_30_DAYS_IN_ADVANCE
}
