package com.amanirshad.authorbook.repositories;

import com.amanirshad.authorbook.TestDataUtils;
import com.amanirshad.authorbook.domain.entities.AuthorEntity;
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
public class AuthorEntityRepositoryIntegrationTests {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorEntityRepositoryIntegrationTests(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        AuthorEntity authorEntity = TestDataUtils.createTestAuthorA();
        authorRepository.save(authorEntity);
        Optional<AuthorEntity> result = authorRepository.findById(authorEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        AuthorEntity authorEntityA = TestDataUtils.createTestAuthorA();
        authorRepository.save(authorEntityA);
        AuthorEntity authorEntityB = TestDataUtils.createTestAuthorB();
        authorRepository.save(authorEntityB);
        AuthorEntity authorEntityC = TestDataUtils.createTestAuthorC();
        authorRepository.save(authorEntityC);

        Iterable<AuthorEntity> results = authorRepository.findAll();
        assertThat(results).hasSize(3);
        assertThat(results).containsExactly(authorEntityA, authorEntityB, authorEntityC);
    }


    @Test
    public void testThatAuthorCanBeUpdated(){
        AuthorEntity authorEntityA = TestDataUtils.createTestAuthorA();
        authorRepository.save(authorEntityA);
        authorEntityA.setName("Updated Author Name");
        authorRepository.save(authorEntityA);
        Optional<AuthorEntity> result = authorRepository.findById(authorEntityA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntityA);
    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        AuthorEntity authorEntityA = TestDataUtils.createTestAuthorA();
        authorRepository.save(authorEntityA);
        authorRepository.deleteById(authorEntityA.getId());
        Optional<AuthorEntity> result = authorRepository.findById(authorEntityA.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan(){
        AuthorEntity authorEntityA = TestDataUtils.createTestAuthorA();
        AuthorEntity authorEntityB = TestDataUtils.createTestAuthorB();
        AuthorEntity authorEntityC = TestDataUtils.createTestAuthorC();

        authorRepository.save(authorEntityA);
        authorRepository.save(authorEntityB);
        authorRepository.save(authorEntityC);

        Iterable<AuthorEntity> results = authorRepository.ageLessThan(50);
        assertThat(results).containsExactly(authorEntityB, authorEntityC);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan(){
        AuthorEntity authorEntityA = TestDataUtils.createTestAuthorA();
        AuthorEntity authorEntityB = TestDataUtils.createTestAuthorB();
        AuthorEntity authorEntityC = TestDataUtils.createTestAuthorC();

        authorRepository.save(authorEntityA);
        authorRepository.save(authorEntityB);
        authorRepository.save(authorEntityC);

        Iterable<AuthorEntity> results = authorRepository.findAuthorsWithAgeGreaterThan(50);
        assertThat(results).containsExactly(authorEntityA);
    }
}
