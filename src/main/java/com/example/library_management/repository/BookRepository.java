package com.example.library_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library_management.model.Book;

public interface BookRepository extends JpaRepository<Book , Long>{
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
}
