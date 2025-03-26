package com.example.library_management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library_management.model.Book;
import com.example.library_management.repository.BookRepository;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/books")
public class BookController {
    
    private BookRepository _BookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return _BookRepository.findAll();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book)
    {
        return _BookRepository.save(book);
    }
    
}
