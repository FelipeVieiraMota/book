package com.motafelipe.api.backoffice.controller.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ApiErrorList extends ApiError {
    private static final Long serialVersionUID = 1L;
    private List<String> errors;
    public ApiErrorList(int code, String message, Date date, List<String> errors){
        super(code, message, date);
        this.errors = errors;
    }
}
