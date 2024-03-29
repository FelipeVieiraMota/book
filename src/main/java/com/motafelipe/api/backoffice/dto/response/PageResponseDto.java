package com.motafelipe.api.backoffice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PageResponseDto<T> implements Serializable {
    private static final long serialVersionUID = 1l;
    private int totalElements;
    private int pageSize;
    private int totalPages;
    private List<T> elements;
}
