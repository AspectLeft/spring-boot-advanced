package io.aspectleft.springbootadvanced.web;

import io.aspectleft.springbootadvanced.domain.Book;
import io.aspectleft.springbootadvanced.exception.BookNotFoundException;
import io.aspectleft.springbootadvanced.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class BookController {
    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping("/books/{id}")
    public String detail(@PathVariable final long id, final Model model) {
        Book book = bookService.getBookById(id);

        model.addAttribute("book", book);
        return "book";
    }
}
