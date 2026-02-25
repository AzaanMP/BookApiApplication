package com.example.bookapiapplication.controller;

import com.example.bookapiapplication.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BookController {

    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    public BookController() {
        // Initial 15 books
        books.add(new Book(nextId++, "Spring Boot in Action", "Craig Walls", 39.99));
        books.add(new Book(nextId++, "Effective Java", "Joshua Bloch", 45.00));
        books.add(new Book(nextId++, "Clean Code", "Robert Martin", 42.50));
        books.add(new Book(nextId++, "Java Concurrency in Practice", "Brian Goetz", 49.99));
        books.add(new Book(nextId++, "Design Patterns", "Gang of Four", 54.99));
        books.add(new Book(nextId++, "Head First Java", "Kathy Sierra", 35.00));
        books.add(new Book(nextId++, "Spring in Action", "Craig Walls", 44.99));
        books.add(new Book(nextId++, "Clean Architecture", "Robert Martin", 39.99));
        books.add(new Book(nextId++, "Refactoring", "Martin Fowler", 47.50));
        books.add(new Book(nextId++, "The Pragmatic Programmer", "Andrew Hunt", 41.99));
        books.add(new Book(nextId++, "You Don't Know JS", "Kyle Simpson", 29.99));
        books.add(new Book(nextId++, "JavaScript: The Good Parts", "Douglas Crockford", 32.50));
        books.add(new Book(nextId++, "Eloquent JavaScript", "Marijn Haverbeke", 27.99));
        books.add(new Book(nextId++, "Python Crash Course", "Eric Matthes", 38.00));
        books.add(new Book(nextId++, "Automate the Boring Stuff", "Al Sweigart", 33.50));
    }

    // --- ASSIGNMENT ENDPOINTS ---

    // 1. PUT - Full Update
    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPrice(updatedBook.getPrice());
                return book;
            }
        }
        return null;
    }

    // 2. PATCH - Partial Update
    @PatchMapping("/books/{id}")
    public Book partialUpdate(@PathVariable Long id, @RequestBody Book updates) {
        Book book = books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
        if (book != null) {
            if (updates.getTitle() != null) book.setTitle(updates.getTitle());
            if (updates.getAuthor() != null) book.setAuthor(updates.getAuthor());
            if (updates.getPrice() > 0) book.setPrice(updates.getPrice());
        }
        return book;
    }

    // 3. DELETE
    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable Long id) {
        boolean removed = books.removeIf(book -> book.getId().equals(id));
        return removed ? "Book removed successfully" : "Book not found";
    }

    // 4. GET with Pagination
    @GetMapping("/books/page")
    public List<Book> getPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return books.stream()
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    // 5. Advanced GET (Filtering + Sorting + Pagination)
    @GetMapping("/books/advanced")
    public List<Book> getAdvanced(
            @RequestParam(required = false) String author,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return books.stream()
                .filter(b -> author == null || b.getAuthor().equalsIgnoreCase(author))
                .sorted((b1, b2) -> {
                    int res = 0;
                    if (sortBy.equalsIgnoreCase("author")) res = b1.getAuthor().compareTo(b2.getAuthor());
                    else if (sortBy.equalsIgnoreCase("price")) res = Double.compare(b1.getPrice(), b2.getPrice());
                    else res = b1.getTitle().compareTo(b2.getTitle());
                    return direction.equalsIgnoreCase("desc") ? -res : res;
                })
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    // --- BASE ENDPOINTS (From Class) ---
    @GetMapping("/books")
    public List<Book> getBooks() { return books; }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping("/books")
    public List<Book> createBook(@RequestBody Book book) {
        book.setId(nextId++);
        books.add(book);
        return books;
    }
}