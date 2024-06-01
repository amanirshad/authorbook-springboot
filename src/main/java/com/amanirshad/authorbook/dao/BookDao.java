package com.amanirshad.authorbook.dao;

import com.amanirshad.authorbook.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> findMany();

    void update(String isbn, Book book);

    void delete(String isbn);
}