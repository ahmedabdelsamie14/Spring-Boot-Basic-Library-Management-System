package com.example.library_management.controller;

import com.example.library_management.dto.BookDTO;
import com.example.library_management.mapper.BookMapper;
import com.example.library_management.model.Book;
import com.example.library_management.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@Tag(name = "Book Controller", description = "APIs for book management")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    @Operation(summary = "Add a new book", description = "Admin only")
    public BookDTO addBook(@RequestBody BookDTO bookDTO) {
        Book book = BookMapper.INSTANCE.toBook(bookDTO);
        Book savedBook = bookService.addBook(book);
        return BookMapper.INSTANCE.toBookDTO(savedBook);
    }

    @GetMapping("/search")
    @Operation(summary = "Search books", description = "Search by title or author")
    public List<BookDTO> searchBooks(@RequestParam(required = false) String title,
                                     @RequestParam(required = false) String author) {
        return bookService.searchBooks(title, author).stream()
                .map(BookMapper.INSTANCE::toBookDTO)
                .collect(Collectors.toList());
    }
}
