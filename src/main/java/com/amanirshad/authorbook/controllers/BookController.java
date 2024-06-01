package com.amanirshad.authorbook.controllers;

import com.amanirshad.authorbook.domain.dto.BookDto;
import com.amanirshad.authorbook.domain.entities.BookEntity;
import com.amanirshad.authorbook.mappers.Mapper;
import com.amanirshad.authorbook.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private BookService bookService;

    private Mapper<BookEntity, BookDto> bookMapper;

    public BookController(BookService bookService, Mapper<BookEntity, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn, @RequestBody BookDto book) {
        BookEntity bookEntity = bookMapper.mapFrom(book);
        BookEntity savedBookEntity = bookService.createBook(isbn,bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(savedBookEntity), HttpStatus.CREATED);
    }


    @GetMapping(path = "/books")
    public List<BookDto> listBooks() {
        List<BookEntity> books = bookService.findAll();
        return books.stream().
                map(bookMapper::mapTo).collect(Collectors.toList());
    }
}
