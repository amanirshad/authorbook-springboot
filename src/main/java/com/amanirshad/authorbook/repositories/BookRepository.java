package com.amanirshad.authorbook.repositories;

import com.amanirshad.authorbook.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
}
