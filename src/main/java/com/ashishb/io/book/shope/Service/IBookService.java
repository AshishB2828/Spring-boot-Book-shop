package com.ashishb.io.book.shope.Service;

import com.ashishb.io.book.shope.Model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookService {
    //    save book
    Book saveBook(Book book);

    //    delete by id
    void deleteBookById(Long id);

    //    find all books
    List<Book> findAllBooks();



    //    FIND ALL BOOKS
    Page<Book> findAllBooksWithPage(Pageable page);
}
