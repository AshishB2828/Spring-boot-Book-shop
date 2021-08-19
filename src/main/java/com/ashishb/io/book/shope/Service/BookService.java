package com.ashishb.io.book.shope.Service;


import com.ashishb.io.book.shope.Model.Book;
import com.ashishb.io.book.shope.Repository.IBookRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class BookService implements  IBookService{

    @Autowired
    private IBookRepo bookRepository;

    //    SAVE BOOK
    @Override
    public Book saveBook(Book book){

        book.setCreateTime(LocalDateTime.now());
        return  bookRepository.save(book);
    }
    //   DELETE BY ID
    @Override
    public  void deleteBookById(Long id){

        bookRepository.deleteById(id);
    }

    //    FIND ALL BOOKS
    @Override
    public List<Book> findAllBooks(){

        return bookRepository.findAll();
    }

    //    FIND ALL BOOKS
    @Override
    public Page<Book> findAllBooksWithPage(Pageable page){
        return bookRepository.findAll(page);
    }
}
