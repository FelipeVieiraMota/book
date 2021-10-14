package com.motafelipe.api.backoffice.controller.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ApiError implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("http_status_code")
    private int httpStatusCode;
    private String message;
    private Date date;
}