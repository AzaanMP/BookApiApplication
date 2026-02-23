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
        save(new Book(null, "Spring Boot in Action", "Craig Walls"));
        save(new Book(null, "Clean Code", "Robert Martin"));
        save(new Book(null, "Java: The Complete Reference", "Herbert Schildt"));
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