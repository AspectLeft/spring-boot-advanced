package io.aspectleft.springbootadvanced.service;

import io.aspectleft.springbootadvanced.domain.Book;
import io.aspectleft.springbootadvanced.domain.BookRepository;
import io.aspectleft.springbootadvanced.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    /**
     * select * from book
     * @return book list
     */
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Page<Book> findAllByPage(final Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    /**
     * create a new book
     * @param book the book to save
     * @return saved book
     */
    public Book save(final Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(final long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("未找到书单信息"));
    }

    public void delete(final long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findByAuthor(final String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findByAuthorAndStatus(final String author, final int status) {
        return bookRepository.findByAuthorAndStatus(author, status);
    }

    public List<Book> findByDescriptionEndsWith(final String suffix) {
        return bookRepository.findByDescriptionEndsWith(suffix);
    }

    public List<Book> findByJPQL(final int length) {
        return bookRepository.findByJPQL(length);
    }


    public int updateByJPQL(final int id, final int status) {
        return bookRepository.updateByJPQL(id, status);
    }
}
