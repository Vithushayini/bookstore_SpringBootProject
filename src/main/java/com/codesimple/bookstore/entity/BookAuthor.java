package com.codesimple.bookstore.entity;

import jakarta.persistence.*;

@Entity
@Table
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "id",nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "author_id",referencedColumnName = "id",nullable = false)
    private Author author;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
