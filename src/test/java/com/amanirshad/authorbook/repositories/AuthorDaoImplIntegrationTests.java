//package com.amanirshad.authorbook.repositories;
//
//import com.amanirshad.authorbook.TestDataUtils;
//import com.amanirshad.authorbook.domain.Author;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class AuthorDaoImplIntegrationTests {
//
//    private AuthorDaoImpl authorDaoImpl;
//
//    @Autowired
//    public AuthorDaoImplIntegrationTests(AuthorDaoImpl authorDaoImpl) {
//        this.authorDaoImpl = authorDaoImpl;
//    }
//
//    @Test
//    public void testThatAuthorCanBeCreatedAndRecalled(){
//        Author author = TestDataUtils.createTestAuthorA();
//        authorDaoImpl.create(author);
//        Optional<Author> result = authorDaoImpl.findOne(author.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(author);
//    }
//
//    @Test
//    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
//        Author authorA = TestDataUtils.createTestAuthorA();
//        authorDaoImpl.create(authorA);
//        Author authorB = TestDataUtils.createTestAuthorB();
//        authorDaoImpl.create(authorB);
//        Author authorC = TestDataUtils.createTestAuthorC();
//        authorDaoImpl.create(authorC);
//
//        List<Author> results = authorDaoImpl.findMany();
//        assertThat(results).hasSize(3);
//        assertThat(results).containsExactly(authorA,authorB, authorC);
//    }
//
//
//    @Test
//    public void testThatAuthorCanBeUpdated(){
//        Author authorA = TestDataUtils.createTestAuthorA();
//        authorDaoImpl.create(authorA);
//        authorA.setName("Updated Author Name");
//        authorDaoImpl.update(authorA.getId(),authorA);
//        Optional<Author> result = authorDaoImpl.findOne(authorA.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(authorA);
//    }
//
//    @Test
//    public void testThatAuthorCanBeDeleted(){
//        Author authorA = TestDataUtils.createTestAuthorA();
//        authorDaoImpl.create(authorA);
//        authorDaoImpl.delete(authorA.getId());
//        Optional<Author> result = authorDaoImpl.findOne(authorA.getId());
//        assertThat(result).isEmpty();
//    }
//}
