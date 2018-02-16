package service;

import dao.BooksDao;
import model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    private BooksDao booksDao;

    public void setBooksDao(BooksDao booksDao) {
        this.booksDao = booksDao;
    }

    @Transactional
    public void createBook(Book book) {
        this.booksDao.createBook(book);
        System.out.println("create service");
    }

    @Transactional
    public void updateBook(Book book) {

        this.booksDao.updateBook(book);
        System.out.println("service update");
    }

    @Transactional
    public void removeBook(int id) {
        this.booksDao.removeBook(id);
    }

    @Transactional
    public Book getBook(int id) {
        return booksDao.getBook(id);
    }

    @Transactional(readOnly = true)
    public List<Book> listOfBooks(int page) {
        return this.booksDao.listOfBooks(page);
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Book> listOfAllBooks() {
        return booksDao.listOfAllBooks();
    }

}
