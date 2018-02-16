package com.app.config;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import service.BookService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Qualifier("getBookService")
    @Autowired(required = true)
    private BookService bookService;

    @RequestMapping(value = "/book/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder){
        bookService.createBook(book);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/book/{id}").buildAndExpand(book.getId()).toUri());
        return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book){
        Book current = bookService.getBook(id);
        if (current == null) return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);

        current.setTitle(book.getTitle());
        current.setDescription(book.getDescription());
        current.setIsbn(book.getIsbn());
        current.setYear(book.getYear());
        current.setReadAlready(false);
        bookService.updateBook(current);

        return new ResponseEntity<Book>(current, HttpStatus.OK);
    }
    @RequestMapping(value = "/read/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateReadAlready(@PathVariable("id") int id, @RequestBody Book book){
        Book current = bookService.getBook(id);
        if (current == null) return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);

        current.setReadAlready(true);
        bookService.updateBook(current);
        return new ResponseEntity<Book>(HttpStatus.OK);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> deleteBook(@PathVariable("id") int id){
        Book book = bookService.getBook(id);
        if (book == null) return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);

        bookService.removeBook(id);
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/books/p={page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getBooks(@PathVariable("page") int page){
        List<Book> listBooks = this.bookService.listOfBooks(page);
        if (listBooks.isEmpty())
            return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);

        for (Book book: listBooks) System.out.print(book.getTitle() + " ");

        return new ResponseEntity<List<Book>>(listBooks, HttpStatus.OK);
    }

    @RequestMapping(value = "/allBooks/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> listBooks = this.bookService.listOfAllBooks();
        if (listBooks.isEmpty())
            return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Book>>(listBooks, HttpStatus.OK);
    }
}

