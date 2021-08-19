package com.ashishb.io.book.shope.Controller;


import com.ashishb.io.book.shope.Model.Book;
import com.ashishb.io.book.shope.Service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    //    ADD BOOK
    @PostMapping("add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveBook(@RequestBody Book book) {

        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    //DELETE BOOk
    @DeleteMapping("{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllBooks(){

        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
    }

    @GetMapping("page")
    public ResponseEntity<?>getAllBooksWithPage(Pageable page){
        return new ResponseEntity<>(bookService.findAllBooksWithPage(page), HttpStatus.OK);

    }
}