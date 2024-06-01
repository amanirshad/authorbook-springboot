package com.amanirshad.authorbook.services;

import com.amanirshad.authorbook.domain.entities.AuthorEntity;

import java.util.List;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();
}
