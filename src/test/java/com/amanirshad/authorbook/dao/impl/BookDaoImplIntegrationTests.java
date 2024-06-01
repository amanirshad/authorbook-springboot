package com.amanirshad.authorbook.dao.impl;

import com.amanirshad.authorbook.TestDataUtils;
import com.amanirshad.authorbook.dao.AuthorDao;
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
public class BookDaoImplIntegrationTests {

    private AuthorDao authorDao;

    private BookDaoImpl bookDaoImpl;

    @Autowired
    public BookDaoImplIntegrationTests(BookDaoImpl bookDaoImpl, AuthorDao authorDao) {
        this.bookDaoImpl = bookDaoImpl;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtils.createTestAuthorA();
        authorDao.create(author);
        Book book = TestDataUtils.createTestBookA();
        book.setAuthorId(author.getId());
        bookDaoImpl.create(book);
        Optional<Book> result = bookDaoImpl.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author author = TestDataUtils.createTestAuthorA();
        authorDao.create(author);

        Book bookA = TestDataUtils.createTestBookA();
        bookA.setAuthorId(author.getId());
        bookDaoImpl.create(bookA);


        Book bookB = TestDataUtils.createTestBookB();
        bookB.setAuthorId(author.getId());
        bookDaoImpl.create(bookB);


        Book bookC = TestDataUtils.createTestBookC();
        bookC.setAuthorId(author.getId());
        bookDaoImpl.create(bookC);

        List<Book> results = bookDaoImpl.findMany();
        assertThat(results).hasSize(3);
        assertThat(results).containsExactly(bookA,bookB, bookC);


    }


    @Test
    public void testThatBookCanBeUpdated(){
        Author author = TestDataUtils.createTestAuthorA();
        authorDao.create(author);

        Book bookA = TestDataUtils.createTestBookA();
        bookA.setAuthorId(author.getId());
        bookDaoImpl.create(bookA);

        bookA.setTitle("Updated Title");
        bookDaoImpl.update(bookA.getIsbn(), bookA);
        Optional<Book> result = bookDaoImpl.findOne(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author author = TestDataUtils.createTestAuthorA();
        authorDao.create(author);

        Book bookA = TestDataUtils.createTestBookA();
        bookA.setAuthorId(author.getId());
        bookDaoImpl.create(bookA);
        bookDaoImpl.delete(bookA.getIsbn());
        Optional<Book> result = bookDaoImpl.findOne(bookA.getIsbn());
        assertThat(result).isEmpty();
    }
}
