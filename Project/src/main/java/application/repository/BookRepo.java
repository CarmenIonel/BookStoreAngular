package application.repository;

import application.model.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

    Book findByIsbn(String ISBN);
    List <Book> findAll();
    List <Book> findAllByTitleContaining(String title);
    List<Book> findAllByAuthorContaining(String author);
    List<Book> findAllByGenreContaining(String genre);
}
