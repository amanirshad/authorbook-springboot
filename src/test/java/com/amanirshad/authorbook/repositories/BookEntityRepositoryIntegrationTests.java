package com.amanirshad.authorbook.repositories;

import com.amanirshad.authorbook.TestDataUtils;
import com.amanirshad.authorbook.domain.entities.AuthorEntity;
import com.amanirshad.authorbook.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookEntityRepositoryIntegrationTests {


    private BookRepository bookRepository;

    @Autowired
    public BookEntityRepositoryIntegrationTests(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        AuthorEntity authorEntity = TestDataUtils.createTestAuthorA();
        BookEntity bookEntity = TestDataUtils.createTestBookA(authorEntity);
        bookRepository.save(bookEntity);
        Optional<BookEntity> result = bookRepository.findById(bookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        AuthorEntity authorEntity = TestDataUtils.createTestAuthorA();

        BookEntity bookEntityA = TestDataUtils.createTestBookA(authorEntity);
        bookRepository.save(bookEntityA);


        BookEntity bookEntityB = TestDataUtils.createTestBookB(authorEntity);
        bookRepository.save(bookEntityB);


        BookEntity bookEntityC = TestDataUtils.createTestBookC(authorEntity);
        bookRepository.save(bookEntityC);

        Iterable<BookEntity> results = bookRepository.findAll();
        assertThat(results).hasSize(3);
        assertThat(results).containsExactly(bookEntityA, bookEntityB, bookEntityC);


    }


    @Test
    public void testThatBookCanBeUpdated(){
        AuthorEntity authorEntity = TestDataUtils.createTestAuthorA();

        BookEntity bookEntityA = TestDataUtils.createTestBookA(authorEntity);
        bookRepository.save(bookEntityA);

        bookEntityA.setTitle("Updated Title");
        bookRepository.save(bookEntityA);
        Optional<BookEntity> result = bookRepository.findById(bookEntityA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntityA);
    }

    @Test
    public void testThatBookCanBeDeleted(){
        AuthorEntity authorEntity = TestDataUtils.createTestAuthorA();
        BookEntity bookEntityA = TestDataUtils.createTestBookA(authorEntity);
        bookRepository.save(bookEntityA);
        bookRepository.deleteById(bookEntityA.getIsbn());
        Optional<BookEntity> result = bookRepository.findById(bookEntityA.getIsbn());
        assertThat(result).isEmpty();
    }
}
