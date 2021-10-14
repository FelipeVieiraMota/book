package com.motafelipe.api.backoffice.services;

import com.motafelipe.api.backoffice.dto.response.BookResponseDto;
import com.motafelipe.api.backoffice.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDto save(BookResponseDto bookResponseDto){
        return null;
    }
}
