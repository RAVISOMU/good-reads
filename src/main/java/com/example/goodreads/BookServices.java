package com.example.goodreads;

import com.example.goodreads.Book;

import com.example.goodreads.BookRepository;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.*;

public class BookServices implements BookRepository {
    private HashMap<Integer, Book> hmap = new HashMap<>();
    int uniqueNumber = 3;

    public BookServices() {
        Book b1 = new Book(1, "The Raamayan", "ramayan.jpg");
        Book b2 = new Book(2, "Mahabharatham", "mahabharatham.jpg");
        hmap.put(b1.getId(), b1);
        hmap.put(b2.getId(), b2);
    }

    @Override
    public ArrayList<Book> getBooks() {
        Collection<Book> bookCollections = hmap.values();
        ArrayList<Book> books = new ArrayList<>(bookCollections);

        return books;
    }

    @Override
    public Book getBookById(int bookId) {
        Book book = hmap.get(bookId);
        if (book == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return book;
    }

    @Override
    public Book addBook(Book book) {
        book.setId(uniqueNumber);
        hmap.put(uniqueNumber, book);
        uniqueNumber += 1;

        return book;
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        Book existingBook = hmap.get(bookId);
        if (existingBook == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (book.getName() != null) {
            existingBook.setName(book.getName());
        }
        if (book.getImageUrl() != null) {
            existingBook.setImageUrl(book.getImageUrl());
        }

        return existingBook;
    }

    @Override
    public void deleteBook(int bookId) {
        Book book = hmap.get(bookId);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            hmap.remove(bookId);
        }
    }
}