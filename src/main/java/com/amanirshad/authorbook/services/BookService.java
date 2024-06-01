package com.amanirshad.authorbook.services;

import com.amanirshad.authorbook.domain.entities.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity bookEntity);

    List<BookEntity> findAll();
}
