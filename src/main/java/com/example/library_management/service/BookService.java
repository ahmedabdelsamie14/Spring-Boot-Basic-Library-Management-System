package com.example.library_management.service;

import com.example.library_management.model.Book;
import com.example.library_management.model.User;
import com.example.library_management.repository.BookRepository;
import com.example.library_management.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> searchBooks(String title, String author) {
        if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        } else if (author != null && !author.isEmpty()) {
            return bookRepository.findByAuthorContainingIgnoreCase(author);
        }
        return bookRepository.findAll();
    }

    public String addFavoriteBook(Long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return "User not found!";
        }

        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            return "Book not found!";
        }

        user.getFavoriteBooks().add(book.get());
        userRepository.save(user);

        return "Book added to favorites!";
    }

    public Set<Book> getUserFavoriteBooks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        }
        return user.getFavoriteBooks();
    }
}
