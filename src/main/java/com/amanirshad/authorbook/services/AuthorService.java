package com.amanirshad.authorbook.services;

import com.amanirshad.authorbook.domain.entities.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorEntity save(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();

    Page<AuthorEntity> findAll(Pageable pageable);

    Optional<AuthorEntity> findOne(Long id);

    boolean ifExists(Long id);

    AuthorEntity partialUpdate(Long id, AuthorEntity author);

    void delete(Long id);
}
