package com.amanirshad.authorbook.repositories;

import com.amanirshad.authorbook.TestDataUtils;
import com.amanirshad.authorbook.repositories.AuthorRepository;
import com.amanirshad.authorbook.domain.Author;
import com.amanirshad.authorbook.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {


    private BookRepository bookRepository;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtils.createTestAuthorA();
        Book book = TestDataUtils.createTestBookA(author);
        bookRepository.save(book);
        Optional<Book> result = bookRepository.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author author = TestDataUtils.createTestAuthorA();

        Book bookA = TestDataUtils.createTestBookA(author);
        bookRepository.save(bookA);


        Book bookB = TestDataUtils.createTestBookB(author);
        bookRepository.save(bookB);


        Book bookC = TestDataUtils.createTestBookC(author);
        bookRepository.save(bookC);

        Iterable<Book> results = bookRepository.findAll();
        assertThat(results).hasSize(3);
        assertThat(results).containsExactly(bookA,bookB, bookC);


    }


    @Test
    public void testThatBookCanBeUpdated(){
        Author author = TestDataUtils.createTestAuthorA();

        Book bookA = TestDataUtils.createTestBookA(author);
        bookRepository.save(bookA);

        bookA.setTitle("Updated Title");
        bookRepository.save( bookA);
        Optional<Book> result = bookRepository.findById(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author author = TestDataUtils.createTestAuthorA();
        Book bookA = TestDataUtils.createTestBookA(author);
        bookRepository.save(bookA);
        bookRepository.deleteById(bookA.getIsbn());
        Optional<Book> result = bookRepository.findById(bookA.getIsbn());
        assertThat(result).isEmpty();
    }
}
