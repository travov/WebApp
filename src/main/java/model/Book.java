package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Books")
public class Book implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "printyear")
    private int year;

    @Column(name = "readAlready")
    private boolean isReadAlready;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isReadAlready() {
        return isReadAlready;
    }

    public void setReadAlready(boolean readAlready) {
        isReadAlready = readAlready;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (year != book.year) return false;
        if (isReadAlready != book.isReadAlready) return false;
        if (!title.equals(book.title)) return false;
        if (!description.equals(book.description)) return false;
        if (!author.equals(book.author)) return false;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + isbn.hashCode();
        result = 31 * result + year;
        result = 31 * result + (isReadAlready ? 1 : 0);
        return result;
    }
}
