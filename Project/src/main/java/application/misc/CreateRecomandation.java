package application.misc;

import application.model.Book;
import application.model.Order;
import application.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CreateRecomandation {

    @Autowired
    private BookRepo bookRepo;

    public List<Book> recomandation (Order order)
    {
        List<Book> books = new ArrayList<Book>();

        String genre = order.getItems().get(0).getBook().getGenre();
        String author = order.getItems().get(0).getBook().getAuthor();

        List<Book> aux = bookRepo.findAllByGenreContaining(genre);
        books.add(aux.get(0));

        aux = bookRepo.findAllByAuthorContaining(author);
        books.add(aux.get(0));
        return books;
    }
}
