package application.controller;

import application.model.Book;
import application.service.BookService;
import application.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReportService reportService;

    //addBook
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> addBook(@RequestBody Book book)
    {
        book.setPathImg("");
        if(bookService.addBook(book) == true)
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        else
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    //deleteBook
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> deleteBook(@RequestParam(value = "ISBN")String ISBN)
    {
        if(bookService.deleteBook(ISBN) == true)
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        else
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    //updateBook
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updateBook(@RequestParam(value = "ISBN")String ISBN,@RequestBody Book book)
    {
        if(bookService.updateBook(ISBN,book) == true)
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        else
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    //generate report
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/generateReport", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> generateReport()
    {
        try {
            reportService.generateReport();
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    //search by title
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/searchTitle", method = RequestMethod.POST)
    public ResponseEntity<List<Book>> searchTitle(@RequestParam(value = "title")String title)
    {
        List<Book> books = bookService.searchTitle(title);
        if(books != null)
            return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
        else
            return new ResponseEntity<List<Book>>(books,HttpStatus.BAD_REQUEST);
    }

    //search by author
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/searchAuthor", method = RequestMethod.POST)
    public ResponseEntity<List<Book>> searchAuthor(@RequestParam(value = "author")String author)
    {
        List<Book> books = bookService.searchAuthor(author);
        if(books != null)
            return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
        else
            return new ResponseEntity<List<Book>>(books,HttpStatus.BAD_REQUEST);
    }

    //search by genre
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/searchGenre", method = RequestMethod.POST)
    public ResponseEntity<List<Book>> searchGenre(@RequestParam(value = "genre")String genre)
    {
        List<Book> books = bookService.searchGenre(genre);
        if(books != null)
            return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
        else
            return new ResponseEntity<List<Book>>(books,HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/allBooks", method = RequestMethod.POST)
    public ResponseEntity<List<Book>> allBooks()
    {
        List<Book> books = bookService.allBooks();
        if(books != null)
            return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
        else
            return new ResponseEntity<List<Book>>(books,HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/getBook", method = RequestMethod.POST)
    public ResponseEntity<Book> getBook(@RequestParam(value = "ISBN")String ISBN)
    {
        Book book = bookService.getBook(ISBN);
        if(book !=null)
            return new ResponseEntity<Book>(book,HttpStatus.OK);
        else
            return new ResponseEntity<Book>(book,HttpStatus.BAD_REQUEST);
    }
}
