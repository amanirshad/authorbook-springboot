package com.amanirshad.authorbook.repositories;

import com.amanirshad.authorbook.TestDataUtils;
import com.amanirshad.authorbook.domain.Author;
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
public class AuthorRepositoryIntegrationTests {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtils.createTestAuthorA();
        authorRepository.save(author);
        Optional<Author> result = authorRepository.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtils.createTestAuthorA();
        authorRepository.save(authorA);
        Author authorB = TestDataUtils.createTestAuthorB();
        authorRepository.save(authorB);
        Author authorC = TestDataUtils.createTestAuthorC();
        authorRepository.save(authorC);

        Iterable<Author> results = authorRepository.findAll();
        assertThat(results).hasSize(3);
        assertThat(results).containsExactly(authorA,authorB, authorC);
    }


    @Test
    public void testThatAuthorCanBeUpdated(){
        Author authorA = TestDataUtils.createTestAuthorA();
        authorRepository.save(authorA);
        authorA.setName("Updated Author Name");
        authorRepository.save(authorA);
        Optional<Author> result = authorRepository.findById(authorA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);
    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        Author authorA = TestDataUtils.createTestAuthorA();
        authorRepository.save(authorA);
        authorRepository.deleteById(authorA.getId());
        Optional<Author> result = authorRepository.findById(authorA.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan(){
        Author authorA = TestDataUtils.createTestAuthorA();
        Author authorB = TestDataUtils.createTestAuthorB();
        Author authorC = TestDataUtils.createTestAuthorC();

        authorRepository.save(authorA);
        authorRepository.save(authorB);
        authorRepository.save(authorC);

        Iterable<Author> results = authorRepository.ageLessThan(50);
        assertThat(results).containsExactly(authorB,authorC);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan(){
        Author authorA = TestDataUtils.createTestAuthorA();
        Author authorB = TestDataUtils.createTestAuthorB();
        Author authorC = TestDataUtils.createTestAuthorC();

        authorRepository.save(authorA);
        authorRepository.save(authorB);
        authorRepository.save(authorC);

        Iterable<Author> results = authorRepository.findAuthorsWithAgeGreaterThan(50);
        assertThat(results).containsExactly(authorA);
    }
}
