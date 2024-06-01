package com.amanirshad.authorbook.dao.impl;

import com.amanirshad.authorbook.TestDataUtils;
import com.amanirshad.authorbook.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl bookDaoImpl;

    @Test
    public void testThatCreateBookGeneratesCorrectSQL(){
        Book book = TestDataUtils.createTestBookA();

        bookDaoImpl.create(book);

        verify(jdbcTemplate).update(eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("978-1-2345-6789-0"),
                eq("The Shadow in the Attic"),
                eq(1L)
        );
    }

    @Test
    public void testThatFindOneBookGeneratesCorrectSQL(){
        bookDaoImpl.findOne("978-1-2345-6789-0");
        verify(jdbcTemplate).query(eq("SELECT isbn, title, author_id FROM books where isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("978-1-2345-6789-0"));
    }


    @Test
    public void testThatFindAllBooksGeneratesCorrectSQL(){
        bookDaoImpl.findMany();
        verify(jdbcTemplate).query(eq("SELECT isbn, title, author_id FROM books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any());
    }


    @Test
    public void testThatUpdateBookGeneratesCorrectSQL(){
        Book book = TestDataUtils.createTestBookA();
        bookDaoImpl.update("978-1-2345-6789-0",book);
        verify(jdbcTemplate).update(eq("UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?"),
                eq("978-1-2345-6789-0"),
                eq("The Shadow in the Attic"),
                eq(1L),
                eq("978-1-2345-6789-0"));
    }

    @Test
    public void testThatDeleteBookGeneratesCorrectSQL(){
        bookDaoImpl.delete("978-1-2345-6789-0");
        verify(jdbcTemplate).update(eq("DELETE FROM books WHERE isbn = ?"),
                eq("978-1-2345-6789-0"));
    }
}
