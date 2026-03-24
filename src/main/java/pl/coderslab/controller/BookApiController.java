package pl.coderslab.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Book;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookApiController {

    private final BookService bookService;

    public BookApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") Long id) {
        return bookService.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Book add(@RequestBody Book book) {
        bookService.save(book);
        return book;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }
    @PutMapping("/{id}")
    public Book update(@PathVariable("id") Long id, @RequestBody Book book) {
        Book bookFromDb = bookService.findById(id).orElse(null);
        if (bookFromDb != null) {
            bookFromDb.setTitle(book.getTitle());
            bookFromDb.setDescription(book.getDescription());
            bookFromDb.setRating(book.getRating());
            bookService.save(bookFromDb);
        }
        return bookFromDb;
    }
}
