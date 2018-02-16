package service;

import dao.BooksDao;
import model.Book;

import java.util.List;

public interface BookService {

    void createBook(Book book);
    void updateBook(Book book);
    void removeBook(int id);
    Book getBook(int id);
    List<Book> listOfBooks(int page);
    List<Book> listOfAllBooks();
    void setBooksDao(BooksDao booksDao);
}
