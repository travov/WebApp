package dao;

import model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BooksDaoImpl implements BooksDao {

    private static Logger logger = LoggerFactory.getLogger(BooksDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void createBook(Book book) {

        this.sessionFactory.getCurrentSession().persist(book);
        System.out.println("create dao");
    }

    public void updateBook(Book book) {
        try {
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
        logger.info("Book " + book.getTitle() + " was updated ");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void removeBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        if (book != null) session.delete(book);
    }

    public Book getBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        return book;
    }

    @SuppressWarnings("unchecked")
    public List<Book> listOfBooks(int page){
        Session session = sessionFactory.getCurrentSession();
        int pageSize = 10;
        org.hibernate.query.Query countQuery = session.createQuery("Select count (b.id) from Book b");
        Long countResults = (Long) countQuery.uniqueResult();
        int lastPageNumber = (int) Math.ceil(countResults / pageSize);
        System.out.println(page);
        org.hibernate.query.Query selectQuery = session.createQuery("from Book");
        selectQuery.setFirstResult(page * pageSize);
        selectQuery.setMaxResults(pageSize);
        List<Book> list = selectQuery.list();
        return list;
    }
    @SuppressWarnings("unchecked")
    public List<Book> listOfAllBooks() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Book").list();
    }


}
