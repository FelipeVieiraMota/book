package com.motafelipe.api.backoffice.services;

import com.motafelipe.api.backoffice.dto.request.BookRequestDto;
import com.motafelipe.api.backoffice.dto.request.PageRequestDto;
import com.motafelipe.api.backoffice.dto.response.BookResponseDto;
import com.motafelipe.api.backoffice.dto.response.PageResponseDto;
import com.motafelipe.api.backoffice.repositories.BookRepository;
import com.motafelipe.api.backoffice.services.interfaces.BasicInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements BasicInterface <BookResponseDto, BookRequestDto>{

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookResponseDto save(BookRequestDto bookRequestDto) {
        return null;
    }

    @Override
    public BookResponseDto update(Long id, BookRequestDto tResponse) {
        return null;
    }

    @Override
    public void delete(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public PageResponseDto<BookResponseDto> getPaginated(PageRequestDto pr) {
        return null;
    }

    @Override
    public BookResponseDto getById(Long id) {
        return null;
    }
}
