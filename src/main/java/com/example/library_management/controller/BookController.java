package com.example.library_management.controller;

import com.example.library_management.model.Book;
import com.example.library_management.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/books")
@Tag(name = "Book Controller", description = "APIs for book management")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 🔹 Add a new book (Only Admin can access)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    @Operation(summary = "Add a new book", description = "Admin only")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    // 🔹 Search books by title or author (Public access)
    @GetMapping("/search")
    @Operation(summary = "Search books", description = "Search by title or author")
    public List<Book> searchBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author) {
        return bookService.searchBooks(title, author);
    }

    // 🔹 Add a book to the user's favorite list (Only User can access)
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/favorite/{bookId}")
    @Operation(summary = "Add book to favorites", description = "Users can mark books as favorite")
    public String addFavoriteBook(@PathVariable Long bookId) {
        return bookService.addFavoriteBook(bookId);
    }

    // 🔹 Get all favorite books of the logged-in user
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/favorites")
    @Operation(summary = "Get favorite books", description = "Returns all favorite books of the user")
    public Set<Book> getUserFavoriteBooks() {
        return bookService.getUserFavoriteBooks();
    }
}
