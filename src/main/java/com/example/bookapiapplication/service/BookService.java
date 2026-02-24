package com.example.bookapiapplication.service;

import com.example.bookapiapplication.model.Book;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {
    private List<Book> bookList = new ArrayList<>();
    private Long nextId = 1L;

    public BookService() {
        // Professor's full dataset with prices
        save(new Book(null, "Spring Boot in Action", "Craig Walls", 39.99));
        save(new Book(null, "Effective Java", "Joshua Bloch", 45.00));
        save(new Book(null, "Clean Code", "Robert Martin", 42.50));
        save(new Book(null, "Java Concurrency in Practice", "Brian Goetz", 49.99));
        save(new Book(null, "Design Patterns", "Gang of Four", 54.99));
        save(new Book(null, "Head First Java", "Kathy Sierra", 35.00));
        save(new Book(null, "Spring in Action", "Craig Walls", 44.99));
        save(new Book(null, "Clean Architecture", "Robert Martin", 39.99));
        save(new Book(null, "Refactoring", "Martin Fowler", 47.50));
        save(new Book(null, "The Pragmatic Programmer", "Andrew Hunt", 41.99));
        save(new Book(null, "You Don't Know JS", "Kyle Simpson", 29.99));
        save(new Book(null, "JavaScript: The Good Parts", "Douglas Crockford", 32.50));
        save(new Book(null, "Eloquent JavaScript", "Marijn Haverbeke", 27.99));
        save(new Book(null, "Python Crash Course", "Eric Matthes", 38.00));
        save(new Book(null, "Automate the Boring Stuff", "Al Sweigart", 33.50));
    }

    public List<Book> findAll() { return bookList; }

    public Book findById(Long id) {
        return bookList.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    public Book save(Book book) {
        book.setId(nextId++);
        bookList.add(book);
        return book;
    }

    public Book update(Long id, Book bookDetails) {
        Book book = findById(id);
        if (book != null) {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPrice(bookDetails.getPrice());
            return book;
        }
        return null;
    }

    public Book patch(Long id, Map<String, Object> updates) {
        Book book = findById(id);
        if (book != null) {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "title": book.setTitle((String) value); break;
                    case "author": book.setAuthor((String) value); break;
                    case "price": book.setPrice(Double.valueOf(value.toString())); break;
                }
            });
            return book;
        }
        return null;
    }

    public boolean delete(Long id) {
        return bookList.removeIf(b -> b.getId().equals(id));
    }

    public List<Book> searchBooks(String title, String author, int page, int size, String sortBy) {
        return bookList.stream()
                .filter(b -> title == null || b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(b -> author == null || b.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .sorted((b1, b2) -> {
                    if ("title".equalsIgnoreCase(sortBy)) return b1.getTitle().compareToIgnoreCase(b2.getTitle());
                    if ("author".equalsIgnoreCase(sortBy)) return b1.getAuthor().compareToIgnoreCase(b2.getAuthor());
                    return b1.getId().compareTo(b2.getId());
                })
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }
}