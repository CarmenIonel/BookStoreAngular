package application.model;

import javax.persistence.*;

@Entity
@Table(name="shoppingCartItem")
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int booksOrdered;

    @OneToOne
    private Book book;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(int booksOrdered, Book book) {
        this.booksOrdered = booksOrdered;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBooksOrdered() {
        return booksOrdered;
    }

    public void setBooksOrdered(int booksOrdered) {
        this.booksOrdered = booksOrdered;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
