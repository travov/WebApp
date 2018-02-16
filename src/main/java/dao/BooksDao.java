package dao;

import model.Book;

import java.util.List;

public interface BooksDao {

    void createBook(Book book);
    void updateBook(Book book);
    void removeBook(int id);
    Book getBook(int id);
    List<Book> listOfBooks(int page);
    List listOfAllBooks();

}
