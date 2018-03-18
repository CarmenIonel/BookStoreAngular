package application.service;

import application.model.Book;
import application.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    //addBook
    public boolean addBook(Book book)
    {
        try{
            bookRepo.save(book);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //deleteBook
    public boolean deleteBook(String ISBN)
    {
        Book book = null;
        try{
            book = bookRepo.findByIsbn(ISBN);
            bookRepo.delete(book);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //updateBook
    public boolean updateBook(String ISBN, Book updated)
    {
        Book book = null;
        try{
            book = bookRepo.findByIsbn(ISBN);
            book.setDescription(updated.getDescription());
            book.setPrice(updated.getPrice());
            book.setStock(updated.getStock());
            bookRepo.save(book);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Book> allBooks()
    {
        List<Book> books = null;
        try{
            books = bookRepo.findAll();
            Collections.sort(books, new Comparator<Book>() {
                public int compare(Book o1, Book o2) {
                    return o1.getTitle().compareToIgnoreCase(o2.getTitle());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> searchTitle(String title)
    {
        List<Book> books = null;
        try{
            books = bookRepo.findAllByTitleContaining(title);
            Collections.sort(books, new Comparator<Book>()
            {
                public int compare(Book o1, Book o2) {
                    return o1.getTitle().compareToIgnoreCase(o2.getTitle());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> searchAuthor(String author)
    {
        List<Book> books = null;
        try{
            books = bookRepo.findAllByAuthorContaining(author);
            Collections.sort(books, new Comparator<Book>()
            {
                public int compare(Book o1, Book o2) {
                    return o1.getTitle().compareToIgnoreCase(o2.getTitle());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> searchGenre(String genre)
    {
        List<Book> books = null;
        try{
            books = bookRepo.findAllByGenreContaining(genre);
            Collections.sort(books, new Comparator<Book>()
            {
                public int compare(Book o1, Book o2) {
                    return o1.getTitle().compareToIgnoreCase(o2.getTitle());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return books;
    }

    //get book
    public Book getBook(String ISBN)
    {
        Book book = null;
        try{
            book = bookRepo.findByIsbn(ISBN);
        }catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }
}
