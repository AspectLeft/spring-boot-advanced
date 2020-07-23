package io.aspectleft.springbootadvanced.service;

import io.aspectleft.springbootadvanced.domain.Book;

public interface BookService {
    Book getBookById(final long id);
}
